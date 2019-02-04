// generated with ast extension for cup
// version 0.8
// 4/1/2019 1:14:23


package rs.ac.bg.etf.pp1.ast;

public class EnumDeclaration extends EnumDecl {

    private EnumStart EnumStart;
    private EnumList EnumList;

    public EnumDeclaration (EnumStart EnumStart, EnumList EnumList) {
        this.EnumStart=EnumStart;
        if(EnumStart!=null) EnumStart.setParent(this);
        this.EnumList=EnumList;
        if(EnumList!=null) EnumList.setParent(this);
    }

    public EnumStart getEnumStart() {
        return EnumStart;
    }

    public void setEnumStart(EnumStart EnumStart) {
        this.EnumStart=EnumStart;
    }

    public EnumList getEnumList() {
        return EnumList;
    }

    public void setEnumList(EnumList EnumList) {
        this.EnumList=EnumList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(EnumStart!=null) EnumStart.accept(visitor);
        if(EnumList!=null) EnumList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EnumStart!=null) EnumStart.traverseTopDown(visitor);
        if(EnumList!=null) EnumList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EnumStart!=null) EnumStart.traverseBottomUp(visitor);
        if(EnumList!=null) EnumList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EnumDeclaration(\n");

        if(EnumStart!=null)
            buffer.append(EnumStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(EnumList!=null)
            buffer.append(EnumList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EnumDeclaration]");
        return buffer.toString();
    }
}
