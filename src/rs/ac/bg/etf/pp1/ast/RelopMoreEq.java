// generated with ast extension for cup
// version 0.8
// 28/0/2019 23:34:5


package rs.ac.bg.etf.pp1.ast;

public class RelopMoreEq extends Relop {

    public RelopMoreEq () {
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
        buffer.append("RelopMoreEq(\n");

        buffer.append(tab);
        buffer.append(") [RelopMoreEq]");
        return buffer.toString();
    }
}
