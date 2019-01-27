// generated with ast extension for cup
// version 0.8
// 27/0/2019 5:2:17


package rs.ac.bg.etf.pp1.ast;

public class LoclVarNoBrackets extends LoclVar {

    private String varName;

    public LoclVarNoBrackets (String varName) {
        this.varName=varName;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("LoclVarNoBrackets(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [LoclVarNoBrackets]");
        return buffer.toString();
    }
}
