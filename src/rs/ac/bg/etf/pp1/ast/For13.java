// generated with ast extension for cup
// version 0.8
// 29/11/2018 4:58:38


package rs.ac.bg.etf.pp1.ast;

public class For13 extends For {

    private DesignatorStatement DesignatorStatement;
    private DesignatorStatement DesignatorStatement1;

    public For13 (DesignatorStatement DesignatorStatement, DesignatorStatement DesignatorStatement1) {
        this.DesignatorStatement=DesignatorStatement;
        if(DesignatorStatement!=null) DesignatorStatement.setParent(this);
        this.DesignatorStatement1=DesignatorStatement1;
        if(DesignatorStatement1!=null) DesignatorStatement1.setParent(this);
    }

    public DesignatorStatement getDesignatorStatement() {
        return DesignatorStatement;
    }

    public void setDesignatorStatement(DesignatorStatement DesignatorStatement) {
        this.DesignatorStatement=DesignatorStatement;
    }

    public DesignatorStatement getDesignatorStatement1() {
        return DesignatorStatement1;
    }

    public void setDesignatorStatement1(DesignatorStatement DesignatorStatement1) {
        this.DesignatorStatement1=DesignatorStatement1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorStatement!=null) DesignatorStatement.accept(visitor);
        if(DesignatorStatement1!=null) DesignatorStatement1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorStatement!=null) DesignatorStatement.traverseTopDown(visitor);
        if(DesignatorStatement1!=null) DesignatorStatement1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorStatement!=null) DesignatorStatement.traverseBottomUp(visitor);
        if(DesignatorStatement1!=null) DesignatorStatement1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("For13(\n");

        if(DesignatorStatement!=null)
            buffer.append(DesignatorStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorStatement1!=null)
            buffer.append(DesignatorStatement1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [For13]");
        return buffer.toString();
    }
}
