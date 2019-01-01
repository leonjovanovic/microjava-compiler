package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticPass extends VisitorAdaptor {
	
	int printCallCount = 0;
	int varDeclCount = 0;
	
	Logger log = Logger.getLogger(getClass());
	
    public void visit(MatchedPrint print) {
		printCallCount++;
		log.info("Prepoznata naredba print!");
	}
    
    public void visit(ProgName progName) {
    	progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
    	Tab.openScope();
    }
    
    public void visit(Program program) {
    	Tab.chainLocalSymbols(program.getProgName().obj);
    	Tab.closeScope();
    }

	public void visit(VarDeclaration varDecl){
		varDeclCount++;
		Tab.insert(Obj.Type, varDecl.getVarList(), varDecl.getType().);
	}
}
