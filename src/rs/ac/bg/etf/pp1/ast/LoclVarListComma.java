// generated with ast extension for cup
// version 0.8
// 4/1/2019 1:14:23


package rs.ac.bg.etf.pp1.ast;

public class LoclVarListComma extends LoclVarList {

    private LoclVarList LoclVarList;
    private LoclVar LoclVar;

    public LoclVarListComma (LoclVarList LoclVarList, LoclVar LoclVar) {
        this.LoclVarList=LoclVarList;
        if(LoclVarList!=null) LoclVarList.setParent(this);
        this.LoclVar=LoclVar;
        if(LoclVar!=null) LoclVar.setParent(this);
    }

    public LoclVarList getLoclVarList() {
        return LoclVarList;
    }

    public void setLoclVarList(LoclVarList LoclVarList) {
        this.LoclVarList=LoclVarList;
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
        if(LoclVarList!=null) LoclVarList.accept(visitor);
        if(LoclVar!=null) LoclVar.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(LoclVarList!=null) LoclVarList.traverseTopDown(visitor);
        if(LoclVar!=null) LoclVar.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(LoclVarList!=null) LoclVarList.traverseBottomUp(visitor);
        if(LoclVar!=null) LoclVar.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("LoclVarListComma(\n");

        if(LoclVarList!=null)
            buffer.append(LoclVarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(LoclVar!=null)
            buffer.append(LoclVar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [LoclVarListComma]");
        return buffer.toString();
    }
}
