package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.MatchedPrint;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.LoclVarCounter;
import rs.ac.bg.etf.pp1.ast.*;

public class CodeGenerator extends VisitorAdaptor {
	
	private int mainPc;
	
	public int getMainPc() {
		return mainPc;
	}
    //==================================================================================
    //================================== Constants =====================================
	//==================================================================================
	
	public void visit (ConstantsPart con) {
		Obj obj = Tab.find(con.getConstName());
		Code.store(obj);
	}
	
	public void visit(ConstNumber num) {
		Obj obj = new Obj(Obj.Con, "con", num.struct, num.getValue(), 0);
		Code.load(obj);
	}
	
	public void visit(ConstChar ch) {
		Obj obj = new Obj(Obj.Con, "con", ch.struct, ch.getValue(), 0);
		Code.load(obj);
	}
	
	public void visit(ConstBool bool) {
		int value = 0;
		if(bool.getValue() == true)value = 1;
		Obj con = new Obj(Obj.Con, "con", bool.struct, value, 0);
		Code.load(con);
	}
	
    //==================================================================================
    //==================================== Enum ========================================
	//==================================================================================
	
	public void visit(EnumNum enumNum) {
		Obj obj = new Obj(Obj.Con, "con", enumNum.obj.getType(), enumNum.obj.getAdr(), 0);
		System.out.println("Vrednost "+ enumNum.getName()+ " je "+enumNum.obj.getAdr());
		Code.load(obj);
	}
	
	public void visit(EnumNoNum enumNoNum) {
		Obj obj = new Obj(Obj.Con, "con", enumNoNum.obj.getType(), enumNoNum.obj.getAdr(), 0);
		System.out.println("Vrednost "+ enumNoNum.getName()+ " je "+enumNoNum.obj.getAdr());
		Code.load(obj);
	}
	
    //==================================================================================
    //================================== Statements ====================================
	//==================================================================================
		
	public void visit(MatchedReturnExpr returnExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(MatchedPrint print) {
		if(print.getExpr().struct == Tab.intType) {
			Code.loadConst(5);//sirina 5
			Code.put(Code.print);
		}else if(print.getExpr().struct == Tab.charType){
			Code.loadConst(1);//sirina 1
			Code.put(Code.bprint);
		}else if(print.getExpr().struct == TabExtended.boolType) {			
			Code.loadConst(5);//sirina 1
			Code.put(Code.print);
		}
	}
	
	public void visit(MatchedPrintNumber printArg) {
		int value = printArg.getN2();
		if(printArg.getExpr().struct == Tab.intType) {
			Code.loadConst(value);//sirina 5
			Code.put(Code.print);
		}else if(printArg.getExpr().struct == Tab.charType){
			Code.loadConst(value);//sirina 1
			Code.put(Code.bprint);
		}else if(printArg.getExpr().struct == TabExtended.boolType) {			
			Code.loadConst(value);//sirina 1
			Code.put(Code.print);
		}
	}
	
	public void visit(MatchedRead read) {
		Obj obj = read.getDesignator().obj;
		if(obj.getType() == Tab.intType)
			Code.put(Code.read);
		else if(obj.getType() == Tab.charType)
			Code.put(Code.bread);
		else if (obj.getType() == TabExtended.boolType)
			Code.put(Code.read);
		Code.store(obj);
	}
	
	//==================================================================================
	//================================ Methods =========================================
	//==================================================================================
	
	public void visit(MethNameType typeName) {
		
		if("main".equalsIgnoreCase(typeName.getMethodName())) {
			mainPc = Code.pc;
		}
		typeName.obj.setAdr(Code.pc);
		// Collect arguments and local variables
		SyntaxNode node = typeName.getParent();
		
		LoclVarCounter varCnt = new LoclVarCounter();
		node.traverseTopDown(varCnt);
		
		FormParamCounter fpCnt = new FormParamCounter();
		node.traverseTopDown(fpCnt);
		
		// Generate the entry
		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(fpCnt.getCount() + varCnt.getCount());
	}
	
	public void visit(MethNameVoid typeName) {
		
		if("main".equalsIgnoreCase(typeName.getMethodName())) {
			mainPc = Code.pc;
		}
		typeName.obj.setAdr(Code.pc);
		// Collect arguments and local variables
		SyntaxNode node = typeName.getParent();
		
		LoclVarCounter varCnt = new LoclVarCounter();
		node.traverseTopDown(varCnt);
		
		FormParamCounter fpCnt = new FormParamCounter();
		node.traverseTopDown(fpCnt);
		
		// Generate the entry
		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(fpCnt.getCount() + varCnt.getCount());
	}
	
	public void visit(MethodDeclarationType method) {		
		Code.put(Code.exit);
		Code.put(Code.return_);
	}	
	
	public boolean isNative(String method) {
		if(method.equals("len")) {
			Code.put(Code.arraylength);
			return true;
		}
		if(method.equals("ord") || method.equals("chr")) {
			return true;
		}
		return false;
	}
	
	//==============================================================
	//======================Actual Parameters=======================
	//==============================================================
	
	public void visit(ActParsListNoComma par) {
	}
	
	//==============================================================
	//======================Formal Parameters=======================
	//==============================================================
	
	/*public void visit(FormNoBrackets par) {
		Obj obj = new Obj(Obj.Var, par.getFormName(), par.getType().struct);
		obj.setLevel(1);
		Code.put(Code.pop);
		Code.store(obj);
	}*/
	
	//==============================================================
	//=================== Designator Statement =====================
	//==============================================================
	
	public void visit(DesignatorAssign assign) {
		Code.store(assign.getDesignator().obj);//storuj novu vrednost na zadati objekat
	}
	
	public void visit(DesignatorParams procCall) {//zovemo fiju bez ikakvog dodeljivanja - nezavisna
		Obj funcObj = procCall.getDesignator().obj;
		if(isNative(funcObj.getName())) return;
		int offset = funcObj.getAdr() - Code.pc;
		Code.put(Code.call);		
		Code.put2(offset);		
		if(procCall.getDesignator().obj.getType() != Tab.noType) {//metoda koja ne vraca nista
			Code.put(Code.pop);//pop ostavlja prazan stek
		}
	}
	
	public void visit(DesignatorInc inc) {
		Obj obj = inc.getDesignator().obj;
		if(obj.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
			Code.load(obj);
		}
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(obj);
	}
	
	public void visit(DesignatorDec dec) {
		Obj obj = dec.getDesignator().obj;
		if(obj.getKind() == Obj.Elem) {
			Code.put(Code.dup2);//to je adresa niza
			Code.load(obj);
		}
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(obj);
	}
	
	//==============================================================
	//========================= Designator =========================
	//==============================================================
	
	public void visit(DesignatorID desig) {
		SyntaxNode parent = desig.getParent();
		if(DesignatorAssign.class != parent.getClass() && FactorDesignatorActPars.class != parent.getClass()
				&& DesignatorParams.class != parent.getClass() && MatchedRead.class != parent.getClass()) {
			Code.load(desig.obj);//Kada nije dodeljivanje vec samo koriscenje te promenljive kao npr
		}//u print(x) onda loaduj da bi mogao da procita
	}
	
	public void visit(DesignatorExpr desig) {
		SyntaxNode parent = desig.getParent();
		if(DesignatorAssign.class != parent.getClass() && DesignatorInc.class != parent.getClass()
				&& DesignatorDec.class != parent.getClass() && MatchedRead.class != parent.getClass()) {
			Code.load(desig.obj);//Kada nije dodeljivanje vec samo koriscenje te promenljive kao npr
		}//u print(x) onda loaduj da bi mogao da procita
	}
	
	public void visit(DesignatorDot desig) {
		Tab.insert(Obj.Con, "$", desig.obj.getType());
		int value = desig.obj.getAdr();
		Obj con = new Obj(Obj.Con, "con",  desig.obj.getType(), value, 0);
		Code.load(con);
		System.out.println("Vrednost "+ desig.getIdent()+ " je "+desig.obj.getAdr());
	}
	
	
	
	//==============================================================
	//============================ Expr ============================
	//==============================================================
	
	public void visit(ExprListAddop e) {
		if(e.getAddop() instanceof AddopPlus)
			Code.put(Code.add);
		else Code.put(Code.sub);
	}
	
	public void visit(ExprMinus e) {
		Code.put(Code.neg);
	}
	
	//==============================================================
	//============================ Term ============================
	//==============================================================
	
	public void visit(Terms term) {
		if(term.getMulop() instanceof MulopMul)
			Code.put(Code.mul);
		else if(term.getMulop() instanceof MulopDiv)
			Code.put(Code.div);
		else if(term.getMulop() instanceof MulopMod)
			Code.put(Code.rem);
	}
	
	//==============================================================
	//============================Factor============================
	//==============================================================
	
	public void visit(FactorDesignatorActPars funcCall) {// dodeljujemo vrednost funkcije nekoj promenljivoj
		Obj funcObj = funcCall.getDesignator().obj;
		int offset = funcObj.getAdr() - Code.pc;
		if(isNative(funcObj.getName())) return;
		Code.put(Code.call);		
		Code.put2(offset);		
	}
	
	public void visit(FactorNum num) {
		Obj con = Tab.insert(Obj.Con, "$", num.struct);
		con.setLevel(0);
		con.setAdr(num.getN1());		
		Code.load(con);
	}
	
	public void visit(FactorChar ch) {
		Tab.insert(Obj.Con, "$", ch.struct);
		Obj con = new Obj(Obj.Con, "con", ch.struct, ch.getC1(), 0);
		Code.load(con);
	}
	
	public void visit(FactorBool bool) {
		Tab.insert(Obj.Con, "$", bool.struct);
		int value = 0;
		if(bool.getB1() == true)value = 1;
		Obj con = new Obj(Obj.Con, "con", bool.struct, value, 0);
		Code.load(con);
	}
	
	public void visit(FactorNewNoExpr f) {
		Code.put(Code.new_);
	}
	
	public void visit(FactorNewExpr f) {
		Code.put(Code.newarray);
		
		if(f.getType().struct == Tab.charType)
			Code.put(1);
		else 
			Code.put(0);
	}
	
	//==============================================================
	//========================= Condition===========================
	//==============================================================

	public void visit(CondFactRelop rel) {
		int value;
		if(rel.getRelop() instanceof RelopEq)
			value = Code.eq;
		else if(rel.getRelop() instanceof RelopDif)
			value = Code.ne;
		else if(rel.getRelop() instanceof RelopMore)
			value = Code.gt;
		else if(rel.getRelop() instanceof RelopMoreEq)
			value = Code.ge;
		else if(rel.getRelop() instanceof RelopLess)
			value = Code.lt;
		else value = Code.le;
	}
	
}
