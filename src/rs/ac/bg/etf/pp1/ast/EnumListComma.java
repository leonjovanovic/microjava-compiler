// generated with ast extension for cup
// version 0.8
// 29/11/2018 4:58:37


package rs.ac.bg.etf.pp1.ast;

public class EnumListComma extends EnumList {

    private EnumList EnumList;
    private Enums Enums;

    public EnumListComma (EnumList EnumList, Enums Enums) {
        this.EnumList=EnumList;
        if(EnumList!=null) EnumList.setParent(this);
        this.Enums=Enums;
        if(Enums!=null) Enums.setParent(this);
    }

    public EnumList getEnumList() {
        return EnumList;
    }

    public void setEnumList(EnumList EnumList) {
        this.EnumList=EnumList;
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
        if(EnumList!=null) EnumList.accept(visitor);
        if(Enums!=null) Enums.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EnumList!=null) EnumList.traverseTopDown(visitor);
        if(Enums!=null) Enums.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EnumList!=null) EnumList.traverseBottomUp(visitor);
        if(Enums!=null) Enums.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EnumListComma(\n");

        if(EnumList!=null)
            buffer.append(EnumList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Enums!=null)
            buffer.append(Enums.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EnumListComma]");
        return buffer.toString();
    }
}
