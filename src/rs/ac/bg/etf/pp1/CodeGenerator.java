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
	
	public void visit(MatchedPrint print) {
		if(print.getExpr().struct == Tab.intType) {
			Code.loadConst(5);//sirina 5
			Code.put(Code.print);
		}else {
			Code.loadConst(1);//sirina 1
			Code.put(Code.print);
		}
	}
	
	public void visit(FactorNum num) {
		Obj con = Tab.insert(Obj.Con, "$", num.struct);
		con.setLevel(0);
		con.setAdr(num.getN1());
		
		Code.load(con);
	}
	
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
	
	public void visit(MethodDeclarationType method) {
		
		Code.put(Code.exit);
		Code.put(Code.return_);
	}	
	
	public void visit(DesignatorAssign assign) {
		Code.store(assign.getDesignator().obj);//storuj novu vrednost na zadati objekat
	}
	
	public void visit(DesignatorID desig) {
		SyntaxNode parent = desig.getParent();
		if(DesignatorAssign.class != parent.getClass() && FactorDesignatorActPars.class != parent.getClass()) {
			Code.load(desig.obj);//Kada nije dodeljivanje vec samo koriscenje te promenljive kao npr
		}//u pritn(x) onda loaduj da bi mogao da procita
	}
	
	public void visit(FactorDesignatorActPars funcCall) {// dodeljujemo vrednost funkcije nekoj promenljivoj
		Obj funcObj = funcCall.getDesignator().obj;
		int offset = funcObj.getAdr() - Code.pc;
		Code.put(Code.call);
		
		Code.put2(offset);		
	}
	
	public void visit(DesignatorParams procCall) {//zovemo fiju bez ikakvog dodeljivanja - nezavisna
		Obj funcObj = procCall.getDesignator().obj;
		int offset = funcObj.getAdr() - Code.pc;
		Code.put(Code.call);		
		Code.put2(offset);		
		if(procCall.getDesignator().obj.getType() != Tab.noType) {//metoda koja ne vraca nista
			Code.put(Code.pop);//pop ostavlja prazan stek
		}
	}
	
	public void visit(MatchedReturnExpr returnExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(ExprListAddop addExpr) {
		Code.put(Code.add);
	}
	
}
