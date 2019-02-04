// generated with ast extension for cup
// version 0.8
// 4/1/2019 1:14:23


package rs.ac.bg.etf.pp1.ast;

public class EnumNoComma extends EnumList {

    private Enums Enums;

    public EnumNoComma (Enums Enums) {
        this.Enums=Enums;
        if(Enums!=null) Enums.setParent(this);
    }

    public Enums getEnums() {
        return Enums;
    }

    public void setEnums(Enums Enums) {
        this.Enums=Enums;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Enums!=null) Enums.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Enums!=null) Enums.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Enums!=null) Enums.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EnumNoComma(\n");

        if(Enums!=null)
            buffer.append(Enums.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EnumNoComma]");
        return buffer.toString();
    }
}
