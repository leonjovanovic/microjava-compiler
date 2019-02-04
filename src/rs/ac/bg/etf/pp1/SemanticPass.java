package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;
import rs.etf.pp1.symboltable.structure.SymbolDataStructure;

public class SemanticPass extends VisitorAdaptor {
	
	int printCallCount = 0;
	int varDeclCount = 0;
	Struct currentType = null;
	Obj currentMethod = null;
	Obj currentEnum = null;
	int numEnumConst = 0;
	List<Integer> enumConst =  new ArrayList<Integer>();
	boolean returnFound=false;
	boolean errorDetected = false;
	Stack<Obj> actParamStack = new Stack<Obj>();
	Stack<Integer> numParamStack = new Stack<Integer>();
	boolean doForFlag = false;
	int nVars;
	
	Logger log = Logger.getLogger(getClass());
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	
	
	public void visit(ProgName progName) {
    	progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
    	Tab.openScope();
    }
    
	public void visit(Program program) {
		nVars = Tab.currentScope.getnVars();
    	Tab.chainLocalSymbols(program.getProgName().obj);
    	Tab.closeScope();
    }
	
    //==================================================================================
    //=================================== Constants ====================================
	//==================================================================================
	
	public void visit(ConstNumber num) {
		num.struct = Tab.intType;
	}	
	
	public void visit(ConstChar c) {
		c.struct = Tab.charType;
	}	
	
	public void visit(ConstBool b) {
		b.struct = TabExtended.boolType;
	}	
	
	public void visit(ConstError e) {
		e.struct = TabExtended.noType;
	}	
	
	public void visit(ConstantsPart con) {		
		if(Tab.currentScope().findSymbol(con.getConstName()) != null) {
			report_error("Greska na liniji "+ con.getLine() + " : konstanta "+ con.getConstName()+ " vec deklarisana u ovom scope-u! ", null);
		}
		Struct c = con.getConsts().struct;
		if(!c.compatibleWith(this.currentType)) {
			report_error("Greska na liniji "+ con.getLine() + " : tip vrednosti koja se dodeljuje konstanti "+ con.getConstName()+ " nije dobar! ", null);
		}
		report_info("Deklarisana konstanta " + con.getConstName(), con);
		int address = 0;
		if(con.getConsts() instanceof ConstNumber) address = ((ConstNumber) con.getConsts()).getValue();
		else if(con.getConsts() instanceof ConstChar) address = ((ConstChar) con.getConsts()).getValue();
		else if(con.getConsts() instanceof ConstBool) {
			Boolean value =((ConstBool) con.getConsts()).getValue();
			if(value.equals(true)) address = 1;
			else address = 0;
		}
		Tab.insert(Obj.Con, con.getConstName(), this.currentType).setAdr(address);
	}
	
	public void visit(ConstantsDecl con) {
		currentType = null;
	}
   
    //==================================================================================
    //=================================== Enum =========================================
	//==================================================================================
	//enum Num { ZERO, ONE, TEN = 2 }
	public void visit(EnumNoNum en){
		int address = numEnumConst++;
		for(int i = 0; i < enumConst.size(); i++) {
			if (address == enumConst.get(i)) {
				address = numEnumConst++; //povecaj je +1
				i = 0;//i resetuj iterator da bi proverili sve vrednosti za novu vrednost
			}
		}
		en.obj = Tab.insert(Obj.Con, en.getName(), Tab.intType);
		en.obj.setAdr(address);
		enumConst.add(address);
	}	
	
	public void visit(EnumNum en){
		int address = en.getValue();
		for(int i = 0; i < enumConst.size(); i++) {
			if (address == enumConst.get(i)) {
				report_error("Greska na liniji " + en.getLine() + " : celobrojna vrednost "+en.getName()+" mora biti jednistvena u okviru nabrajanja "+ currentEnum.getName(), null);
			}
		}
		en.obj = Tab.insert(Obj.Con, en.getName(), Tab.intType);
		en.obj.setAdr(address);
		enumConst.add(address);
	}
	
	public void visit(StartEnum en) {
		if(Tab.currentScope().findSymbol(en.getEnumName()) != null) {
			report_error("Greska na liniji " + en.getLine() + " : enum "+en.getEnumName()+ " je vec deklarisan! ", null);
		}
		currentEnum = Tab.insert(Obj.Type, en.getEnumName(), TabExtended.enumType);
		en.obj = currentEnum;
		Tab.openScope();
		report_info("Obradjuje se enum " + en.getEnumName(), en);
	}

	public void visit(EnumDeclaration en){
		if(currentEnum==null)return;
		
		Tab.chainLocalSymbols(currentEnum);
		Tab.closeScope();
		
		currentEnum = null;
		numEnumConst = 0;
		enumConst = null;
		
	}
   
    //==================================================================================
    //=================================== Variables ====================================
	//==================================================================================
    //=============================== Global variables =================================
	//==================================================================================
	
    public void visit(GlobVarDeclaration varDecl){
		currentType = null;
	}
    
	public void visit(GlobVarNoBrackets varDecl){
		if(Tab.currentScope().findSymbol(varDecl.getVarName()) != null) {
			report_error("Greska na liniji "+ varDecl.getLine() + " : promenjljiva "+ varDecl.getVarName()+ " vec deklarisana u ovom scope-u! ", null);
		}
		report_info("Deklarisana globalna promenjljiva bez zagrada " + varDecl.getVarName(), varDecl);
		Tab.insert(Obj.Var, varDecl.getVarName(), this.currentType);
	}
	
	public void visit(GlobVarBrackets varDecl){
		if(Tab.currentScope().findSymbol(varDecl.getVarName()) != null) {
			report_error("Greska na liniji "+ varDecl.getLine() + " : promenjljiva "+ varDecl.getVarName()+ " vec deklarisana u ovom scope-u! ", null);
		}
		report_info("Deklarisana globalna promenjljiva sa zagradama " + varDecl.getVarName(), varDecl);
		Tab.insert(Obj.Var, varDecl.getVarName(), new Struct(Struct.Array, currentType));
	}

	//==================================================================================
	//========================= Local variables ========================================
	//==================================================================================
	 public void visit(LoclVarDeclaration varDecl){
			currentType = null;
	}
	    
	public void visit(LoclVarNoBrackets varDecl){
		if(Tab.currentScope().findSymbol(varDecl.getVarName()) != null) {
			report_error("Greska na liniji "+ varDecl.getLine() + " : promenjljiva "+ varDecl.getVarName()+ " vec deklarisana u ovom scope-u! ", null);
		}
		report_info("Deklarisana lokalna promenjljiva bez zagrada " + varDecl.getVarName(), varDecl);
		Tab.insert(Obj.Var, varDecl.getVarName(), this.currentType);
	}
	
	public void visit(LoclVarBrackets varDecl){
		if(Tab.currentScope().findSymbol(varDecl.getVarName()) != null) {
			report_error("Greska na liniji "+ varDecl.getLine() + " : promenjljiva "+ varDecl.getVarName()+ " vec deklarisana u ovom scope-u! ", null);
		}
		report_info("Deklarisana lokalna promenjljiva sa zagradama " + varDecl.getVarName(), varDecl);
		Tab.insert(Obj.Var, varDecl.getVarName(), new Struct(Struct.Array, currentType));
	}

	//==============================================================
	//====================== Designator - dopuniti==================
	//==============================================================
	
	public void visit(DesignatorID designator) {//provereno za enum
		Obj obj = Tab.find(designator.getName());
		if(obj == Tab.noObj) {
			report_error("Greska na liniji " + designator.getLine() + " : ime "+designator.getName()+" nije deklarisano!", null);
		}else {
			report_info("Pristup elementu " + designator.getName(), designator);
		}
		designator.obj = obj;
	}
	
	public void visit(DesignatorExpr elem) {
		Obj designator = Tab.find(elem.getDesignator().obj.getName());
		if(elem.getExpr().struct != Tab.intType && elem.getExpr().struct != TabExtended.enumType)
			report_error("Greska na liniji " + elem.getLine() + " : vrednost unutar zagrada mora biti tipa int!", null);
		if(designator==Tab.noObj) {
			report_error("Greska na liniji " + elem.getLine() + " : niz "+elem.getDesignator().obj.getName()+" nije deklarisan!", null);
		}else {
			report_info("Pristup elementu niza " + elem.getDesignator().obj.getName(), elem);
		}
		elem.obj = new Obj(Obj.Elem, elem.getDesignator().obj.getName(), designator.getType().getElemType());
	}
	
	public void visit(DesignatorDot desig) {
		Obj des = desig.getDesignator().obj;
		Obj ident = Tab.find(desig.getIdent());
		if(des.getType() != TabExtended.enumType) {
			report_error("Greska na liniji " + desig.getLine() + " : promenjiva "+des.getName()+" mora biti nabrajanje (enum)!", null);
			desig.obj = ident;
		}
		else{
			Iterator<Obj> itPar = des.getLocalSymbols().iterator();//Iterator svih simbola naseg objekta
			boolean found = false;
			while(itPar.hasNext()) {
				Obj obj = itPar.next();
				if (desig.getIdent().equalsIgnoreCase(obj.getName())) { //da li je parametar koji smo skinuli sa steka moze da se dodeli sledecem simbolu metode
					found = true;
					desig.obj = obj;
				}
			}
			if(!found) {
				report_error("Greska na liniji " + desig.getLine() + " : identifikator "+ desig.getIdent() +" nije jedan od konstanti unutar nabrajanja "+ des.getName(), null);
				desig.obj = ident;
			}
		}
	}

	//==================================================================================
	//================================ Type ============================================
	//==================================================================================
	
	public void visit(Types type) {
		Obj typeNode = TabExtended.find(type.getTypeName());
		if(typeNode == Tab.noObj) {
			report_error("Nije pronadjen tip "+ type.getTypeName()+ "u tabeli simbola! ", null);
			type.struct = Tab.noType;
		}else {
			if(Obj.Type == typeNode.getKind()) {
				type.struct = typeNode.getType();
			}else {
				report_error("GreskaL Ime " + type.getTypeName() + " ne predstavlja tip!", type);
				type.struct = Tab.noType;
			}
		}
		currentType = type.struct;
	}
	
	//==================================================================================
	//================================ Methods =========================================
	//==================================================================================
	
	public void visit(MethNameType method) {
		if(Tab.currentScope().findSymbol(method.getMethodName()) != null) {
			report_error("Greska na liniji " + method.getLine() + " : metoda "+method.getMethodName()+ " je vec deklarisana! ", null);
		}
		currentMethod = Tab.insert(Obj.Meth, method.getMethodName(), method.getType().struct);
		method.obj = currentMethod;
		
		currentMethod.setLevel(0);
		Tab.openScope();
		report_info("Obradjuje se return funkcija " + method.getMethodName(), method);
	}
	
	public void visit(MethNameVoid method) {
		if(Tab.currentScope().findSymbol(method.getMethodName()) != null) {
			report_error("Greska na liniji " + method.getLine() + " : metoda "+method.getMethodName()+ " je vec deklarisana! ", null);
		}
		currentMethod = Tab.insert(Obj.Meth, method.getMethodName(), Tab.noType);
		method.obj = currentMethod;
		currentMethod.setLevel(0);
		Tab.openScope();
		report_info("Obradjuje se void funkcija " + method.getMethodName(), method);
	}
	
	public void visit(MethodDeclarationType method) {
		
		if(currentMethod==null)return;
		
		if (!returnFound && currentMethod.getType() != Tab.noType) { //Ukoliko je imao povratni tip a nema return javi gresku
			report_error("Greska na liniji " + method.getLine() + ": funcija " + currentMethod.getName() + " nema return!", null);
		}
		
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
		
		currentMethod = null;
		returnFound = false; //Ubaciti return_true = true kad budes radio return expr
	}
	
	//==============================================================
	//======================Formal Parameters=======================
	//==============================================================
	
	public void visit(FormNoBrackets form) {//void f(char ch, int a, int arg)
		if(Tab.currentScope().findSymbol(form.getFormName())!=null) {
			report_error("Greska na liniji " + form.getLine() + " : parametar "+form.getFormName()+ " je vec deklarisana! ", null);
		}
		else {
			Obj obj = Tab.insert(Obj.Var, form.getFormName(), form.getType().struct);
			int level = currentMethod.getLevel();
			obj.setLevel(level++);
			currentMethod.setLevel(level);
			report_info("Deklaracija parametra "+form.getFormName(), form);
		}
	}
	
	public void visit(FormBrackets form) {
		if(Tab.currentScope().findSymbol(form.getFormName())!=null) {
			report_error("Greska na liniji " + form.getLine() + " : parametar "+form.getFormName()+ " je vec deklarisana! ", null);
		}
		else {
			Obj obj = Tab.insert(Obj.Var, form.getFormName(), new Struct(Struct.Array, form.getType().struct));
			int level = currentMethod.getLevel();
			obj.setLevel(level++);
			currentMethod.setLevel(level);
			report_info("Deklaracija parametra "+form.getFormName(), form);
		}
		
	}
	
	//==============================================================
	//======================Actual Parameters=======================
	//==============================================================
	
	public void visit(ActParsListNoComma act) {
		Obj param = new Obj(Obj.Var, "actParam", act.getExpr().struct);
		numParamStack.push(1);
		actParamStack.push(param);		
	}
	
	public void visit(ActParsListComma acts) {
		Obj params = new Obj(Obj.Var, "actParams", acts.getExpr().struct);
		int level = numParamStack.pop();
		numParamStack.push(++level);
		actParamStack.push(params);		
	}
	
	public void visit(ActualParameters act) {
		
	}
	
	public void visit(NoActPars act) {
		
	}	
	
	//==============================================================
	//==================== DesignatorStatement =====================
	//==============================================================
	
	public void visit(DesignatorAssign assignment) {
		Obj desig = assignment.getDesignator().obj;
		Struct expr = assignment.getExpr().struct;
		if(!expr.assignableTo(desig.getType())) {
			if(!(expr == TabExtended.intType && desig.getType() == TabExtended.enumType))
				report_error("Greska na liniji " + assignment.getLine() + " : nekompatibilni tipovi u dodeli vrednosti! "+ expr+"///"+desig.getType(), null);
		}
		if(desig.getKind() != Obj.Var && desig.getKind() != Obj.Elem) {
			report_error("Greska na liniji " + assignment.getLine() + " : vrednost se ne moze dodeliti necemu sto nije promenjljiva ili element niza!", null);
		}
	}
	
	public void visit(DesignatorParams func) {
		Obj obj = func.getDesignator().obj;
		if(obj.getKind() != Obj.Meth) {
			report_error("Greska na liniji " + func.getLine() + " : pozvana varijabla "+obj.getName()+": nije funkcija!", null);
		}else {
			report_info("Pozvana je funkcija "+obj.getName(), func);
		}
		int level = 0;
		if(numParamStack.size()>0) {
			level = numParamStack.pop();
		}
		if(obj.getKind() == Obj.Meth) {
			if(obj.getLevel() != level) {
				report_error("Greska na liniji " + func.getLine() + " : neodgovarajuci broj prosledjenih parametara funkcije "+obj.getName(), null);
			}
			if(obj.getLevel() > 0) {
				Iterator<Obj> itPar = obj.getLocalSymbols().iterator();//Iterator svih simbola naseg objekta
				for (int i = 0; i < level; i++) {
					Obj obj1=itPar.next();
					Obj obj2 = actParamStack.pop();
					boolean flag1 = obj1.getType()==TabExtended.enumType;
					boolean flag2 = obj2.getType()==Tab.intType;
					if (!(obj1.getType().assignableTo(obj1.getType()) || (flag1 && flag2 ))) {//da li je parametar koji smo skinuli sa steka moze da se dodeli sledecem simbolu metode
							report_error("Greska na liniji " + func.getLine() + " : pogresan tip parametara u funkciji "+ obj.getName(), null);
					}
				}
			}
		}		
	}

	public void visit(DesignatorInc assignment) {
		if(assignment.getDesignator().obj.getType() != Tab.intType) {
			report_error("Greska na liniji " + assignment.getLine() + " : nekompatibilni tipovi u dodeli vrednosti! ", null);
		}
		if(assignment.getDesignator().obj.getKind() != Obj.Var && assignment.getDesignator().obj.getKind() != Obj.Elem) {
			report_error("Greska na liniji " + assignment.getLine() + " : vrednost se ne moze dodeliti necemu sto nije promenjljiva ili element niza!", null);
		}	
	}

	public void visit(DesignatorDec assignment) {
		if(assignment.getDesignator().obj.getType() != Tab.intType) {
			report_error("Greska na liniji " + assignment.getLine() + " : nekompatibilni tipovi u dodeli vrednosti! ", null);
		}
		if(assignment.getDesignator().obj.getKind() != Obj.Var && assignment.getDesignator().obj.getKind() != Obj.Elem) {
			report_error("Greska na liniji " + assignment.getLine() + " : vrednost se ne moze dodeliti necemu sto nije promenjljiva ili element niza!", null);
		}		
	}	
	
	public void visit(DesigStatementY desig) {
		
	}
	
	public void visit(NoDesigStatement desig) {
		
	}	
		
	//==============================================================
	//========================== FACTOR ============================
	//==============================================================
	
	public void visit(FactorNum num) {
		num.struct = Tab.intType;
	}
	
	public void visit(FactorChar ch) {
		ch.struct = Tab.charType;
	}
	
	public void visit(FactorBool b) {
		b.struct = TabExtended.boolType;
	}
	
	public void visit(FactorDesignator var) {
		var.struct = var.getDesignator().obj.getType();
	}
	
	public void visit(FactorDesignatorActPars funcCall) {
		Obj func = funcCall.getDesignator().obj;
		int level = 0;
		if (numParamStack.size() > 0) level=numParamStack.pop();
		
		if(Obj.Meth == func.getKind()) {
			if(func.getType() == Tab.noType)
				report_error("Greska na liniji " + funcCall.getLine() + " : metoda "+func.getName()+" se ne moze koristiti u izrazima jer nema povratnu vrednost!", null);
			funcCall.struct = func.getType();
			report_info("Pronadjen poziv funkcije " + func.getName() + " na liniji " + funcCall.getLine(), funcCall);
			if(func.getLevel() != level) {
				report_error("Greska na liniji " + funcCall.getLine() + " : neodgovarajuci broj prosledjenih parametara funkcije "+func.getName(), null);
			}else if(func.getLevel()>0) {
				Iterator<Obj> itPar = func.getLocalSymbols().iterator();
				for(int i = 0; i < level; i++) {
					Obj obj1=itPar.next();
					Obj obj2 = actParamStack.pop();
					boolean flag1 = obj1.getType()==TabExtended.enumType;
					boolean flag2 = obj2.getType()==Tab.intType;
					if (!(obj1.getType().assignableTo(obj1.getType()) || (flag1 && flag2 ))) {//da li je parametar koji smo skinuli sa steka moze da se dodeli sledecem simbolu metode
							report_error("Greska na liniji " + funcCall.getLine() + " : pogresan tip parametara u funkciji "+ func.getName(), null);
					}
				}
			}
		}else {
			report_error("Greska na liniji " +funcCall.getLine() + " : ime "+func.getName()+" nije funkcija!", null);
			funcCall.struct = Tab.noType;
		}
	}
	
	public void visit(FactorNewExpr newArray) {
		if(newArray.getExpr().struct != Tab.intType){
			report_error("Greska na liniji " + newArray.getLine() + " : niz mora da bude tipa int!", null);
		}
		newArray.struct = new Struct(Struct.Array, newArray.getType().struct);
	}
	
	public void visit(FactorExpr fac) {
		fac.struct = fac.getExpr().struct;
	}
	
	//==============================================================
	//==========================TERM ===============================
	//==============================================================
		
	public void visit(Terms term) {//++++++++++++++++++++++++++ DODATI +++++++++++++++++++++++++++++ Ako je Mulop kombinovani aritmetički operator (*=, /=, %=), Term mora označavati promenljivu, element niza ili polje unutar objekta.
		if(term.getFactor().struct != Tab.intType && term.getFactor().struct != TabExtended.enumType)
			report_error("Greska na liniji " + term.getLine() + " : tipovi kod mnozenja moraju biti int!", null);
		if(term.getTerm().struct != Tab.intType && term.getTerm().struct != TabExtended.enumType)
			report_error("Greska na liniji " + term.getLine() + " : tipovi kod mnozenja moraju biti int!", null);
		term.struct = term.getFactor().struct;
	}
	
	public void visit(TermNoMulop term) {
		term.struct = term.getFactor().struct;
	}

	//==============================================================
	//========================== EXPR ==============================
	//==============================================================
	
	public void visit(ExprTermNoAddop term) {
		term.struct = term.getTerm().struct;
	}
	
	public void visit(ExprListAddop addExpr) {//++++++++++++++++++++++++++ DODATI ++++++++++++++++++++++++++++++ Ako je Addop kombinovani aritmetički operator (+=, -=), Expr mora označavati promenljivu, element niza ili polje unutar objekta.
		Struct expr = addExpr.getExprList().struct;//ExprList ::= (ExprListAddop) ExprList:te Addop:ad Term:t
		Struct term = addExpr.getTerm().struct; //da li su oba elementa iste strukture
		if(expr.compatibleWith(term) || ((expr == Tab.intType || expr == TabExtended.enumType) && (term == Tab.intType || term == TabExtended.enumType))) {
			addExpr.struct = expr;
		}else {
			report_error("Greska na liniji " + addExpr.getLine() + " : nekompatibilni tipovi u izrazu za sabiranje!", null);
			addExpr.struct = Tab.noType;
		}
	}
	
	public void visit(ExprNoMinus expr) {
		expr.struct = expr.getExprList().struct;
	}

	public void visit(ExprMinus expr) {
		if(expr.getExprList().struct != Tab.intType)
			report_error("Greska na liniji " + expr.getLine() + " : negativne vrednosti moraju biti tipa int!", null);
		expr.struct = expr.getExprList().struct;
	}

	public void visit(Exprs expr) {
		expr.struct = expr.getExpr().struct;
	}
	
	public void visit(NoExpr expr) {
		expr.struct = Tab.noType;
	}
	
	//==============================================================
	//========================== CondFact ==========================
	//==============================================================
	
	public void visit(CondFactNoRelop cond) {
		if(cond.getExpr().struct != TabExtended.boolType)
			report_error("Greska na liniji " + cond.getLine() + " : uslov mora biti tipa bool!", null);
		cond.struct = cond.getExpr().struct;
	}
	
	public void visit(CondFactRelop cond) {
		Struct e1 = cond.getExpr().struct;
		Struct e2 = cond.getExpr1().struct;
		if(!e1.compatibleWith(e2))
			if(!(e1==TabExtended.enumType && e2==Tab.intType || e2==TabExtended.enumType && e1==Tab.intType))
				report_error("Greska na liniji " + cond.getLine() + " : tpovi oba izraza nisu kompatibilni!", null);
		if((cond.getExpr().struct.getKind() == Struct.Array || cond.getExpr1().struct.getKind() == Struct.Array) 
				&& !(cond.getRelop() instanceof RelopEq || cond.getRelop() instanceof RelopDif)) {
			report_error("Greska na liniji " + cond.getLine() + " : za poredjenje nizova moraju da se koriste operatori '==' ili '!=' !", null);
		}
		cond.struct = TabExtended.boolType;
	}
	
	//==============================================================
	//========================== CondTerm ==========================
	//==============================================================
	
	public void visit(CondTermListAnd cond) {
		cond.struct = cond.getCondFact().struct;
	}
	
	public void visit(CondTermListNoAnd cond) {
		cond.struct = cond.getCondFact().struct;
	}
	
	//==============================================================
	//========================== Condition =========================
	//==============================================================	
	
	public void visit(ConditionListOr cond) {
		cond.struct = cond.getCondTerm().struct;
	}
	
	public void visit(ConditionListNoOr cond) {
		cond.struct = cond.getCondTerm().struct;
	}
	
	public void visit(NoConditions noCond) {
		noCond.struct = TabExtended.boolType;
	}		

	//==============================================================
	//======================== Statements ==========================
	//==============================================================
	
	public void visit(MatchedReturnExpr returnExpr) {
		returnFound = true;
		Struct currMethType = currentMethod.getType();
		if(!currMethType.compatibleWith(returnExpr.getExprOpt().struct)) {
			report_error("Greska na liniji " + returnExpr.getLine() + " : tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije "+currentMethod.getName(), null);
		}
		if(currentMethod == null)
			report_error("Greska na liniji " + returnExpr.getLine() + " : return se ne sme nalaziti izvan funkcije!", null);
	}
	
	public void visit(MatchedBreak br) {
		if(doForFlag == false)
			report_error("Greska na liniji " + br.getLine() + " : break se ne sme nalaziti izvan for petlje!", null);
		else report_info("Detektovan prekid for petlje!", br);
	}
	
	public void visit(MatchedContinue con) {
		if(doForFlag == false)
			report_error("Greska na liniji " + con.getLine() + " : continue se ne sme nalaziti izvan for petlje!", null);
		else report_info("Detektovan continue for petlje!", con);
	}
	
	public void visit(MatchedRead read) {
		Obj oread = read.getDesignator().obj;
		Struct sread = read.getDesignator().obj.getType();
		if(oread.getKind() != Obj.Var && oread.getKind() != Obj.Elem) 
			report_error("Greska na liniji " + read.getLine() + " : vrednost koju citamo mora biti tipa promenljiva ili element niza!", null);
		else if(sread != Tab.intType && sread != Tab.charType && sread != TabExtended.boolType)
				report_error("Greska na liniji " + read.getLine() + " : vrednost koju citamo mora biti tipa int, char ili bool!", null);
		else report_info("Detektovana operacija citanja!", read);
		
	}
	
	public void visit(MatchedPrint print) {
		Struct sprint = print.getExpr().struct;
		if(sprint != Tab.intType && sprint != Tab.charType && sprint != TabExtended.boolType)
			report_error("Greska na liniji " + print.getLine() + " : vrednost koju ispisujemo mora biti tipa int, char ili bool!", null);
		else report_info("Detektovana operacija ispisivanja!", print);
	}
	
	public void visit(MatchedPrintNumber print) {
		Struct sprint = print.getExpr().struct;
		if(sprint != Tab.intType && sprint != Tab.charType && sprint != TabExtended.boolType)
			report_error("Greska na liniji " + print.getLine() + " : vrednost koju ispisujemo mora biti tipa int, char ili bool!", null);
		else report_info("Detektovana operacija ispisivanja!", print);
	}
	
	public void visit(MatchedIfElse ifelse) {
		if(ifelse.getCondition().struct != TabExtended.boolType) {
			report_error("Greska na liniji " + ifelse.getLine() + " : uslov mora biti tipa bool!", null);
		}		
	}
	
	public void visit(UnmatchedIfNoElse ifnoelse) {		
		if(ifnoelse.getCondition().struct != TabExtended.boolType) {
			report_error("Greska na liniji " + ifnoelse.getLine() + " : uslov mora biti tipa bool!", null);
		}		
	}
	
	public void visit(UnmatchedIfElse ifelse) {		
		if(ifelse.getCondition().struct != TabExtended.boolType) {
			report_error("Greska na liniji " + ifelse.getLine() + " : uslov mora biti tipa bool!", null);
		}		
	}
	
	public void visit(For123 f) {
		doForFlag = false;
		if(f.getCondition().struct != TabExtended.boolType) {
			report_error("Greska na liniji " + f.getLine() + " : uslov mora biti tipa bool!", null);
		}
	}
	
	public void visit(StartFor f) {
		doForFlag = true;
	}
		
	public boolean passed() {
		return !errorDetected;
	}
	
	
}
