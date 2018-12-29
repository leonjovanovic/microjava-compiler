// generated with ast extension for cup
// version 0.8
// 29/11/2018 4:58:37


package rs.ac.bg.etf.pp1.ast;

public class ClassPartDerived1 extends ClassPart {

    public ClassPartDerived1 () {
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
        buffer.append("ClassPartDerived1(\n");

        buffer.append(tab);
        buffer.append(") [ClassPartDerived1]");
        return buffer.toString();
    }
}
