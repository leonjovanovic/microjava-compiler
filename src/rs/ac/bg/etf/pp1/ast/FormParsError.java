// generated with ast extension for cup
// version 0.8
// 27/0/2019 5:2:17


package rs.ac.bg.etf.pp1.ast;

public class FormParsError extends Form {

    public FormParsError () {
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
        buffer.append("FormParsError(\n");

        buffer.append(tab);
        buffer.append(") [FormParsError]");
        return buffer.toString();
    }
}