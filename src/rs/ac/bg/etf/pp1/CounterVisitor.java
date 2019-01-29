package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.FormBrackets;
import rs.ac.bg.etf.pp1.ast.FormNoBrackets;
import rs.ac.bg.etf.pp1.ast.LoclVarBrackets;
import rs.ac.bg.etf.pp1.ast.LoclVarNoBrackets;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;

public class CounterVisitor extends VisitorAdaptor {

		protected int count;
		
		public int getCount() {
			return count;
		}
		
		public static class FormParamCounter extends CounterVisitor{
			
			public void visit(FormNoBrackets form) {
				count++;
			}
			public void visit(FormBrackets form) {
				count++;
			}
		}
		
		public static class LoclVarCounter extends CounterVisitor{
			
			public void visit(LoclVarNoBrackets var) {
				count++;
			}
			public void visit(LoclVarBrackets var) {
				count++;
			}
		}
}
