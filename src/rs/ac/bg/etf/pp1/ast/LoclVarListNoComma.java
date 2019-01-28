// generated with ast extension for cup
// version 0.8
// 28/0/2019 6:42:7


package rs.ac.bg.etf.pp1.ast;

public class LoclVarListNoComma extends LoclVarList {

    private LoclVar LoclVar;

    public LoclVarListNoComma (LoclVar LoclVar) {
        this.LoclVar=LoclVar;
        if(LoclVar!=null) LoclVar.setParent(this);
    }

    public LoclVar getLoclVar() {
        return LoclVar;
    }

    public void setLoclVar(LoclVar LoclVar) {
        this.LoclVar=LoclVar;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(LoclVar!=null) LoclVar.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(LoclVar!=null) LoclVar.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(LoclVar!=null) LoclVar.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("LoclVarListNoComma(\n");

        if(LoclVar!=null)
            buffer.append(LoclVar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [LoclVarListNoComma]");
        return buffer.toString();
    }
}
