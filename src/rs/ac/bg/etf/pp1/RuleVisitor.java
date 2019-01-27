package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;
import rs.ac.bg.etf.pp1.ast.*;

public class RuleVisitor extends VisitorAdaptor{

	int printCallCount = 0;
	int varDeclCount = 0;
	int formCallCount = 0;
	int condCallCount = 0;
	
	Logger log = Logger.getLogger(getClass());

	public void visit(GlobVarNoBrackets vardecl){
		varDeclCount++;
	}
	
    public void visit(MatchedPrint print) {
		printCallCount++;
		log.info("Prepoznata naredba print!");
	} 
    
    public void visit(FormNoBrackets form) {
    	formCallCount++;
		log.info("Prepoznata naredba formalni parametar!");
	}
    
    public void visit(ConditionListOr cond) {
    	condCallCount++;
		log.info("Prepoznat uslov!");
	}
    
    public void visit(ConditionListNoOr cond) {
    	condCallCount++;
		log.info("Prepoznat uslov!");
	}

}
