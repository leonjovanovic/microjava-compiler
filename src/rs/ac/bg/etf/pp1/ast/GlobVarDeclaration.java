// generated with ast extension for cup
// version 0.8
// 28/0/2019 23:34:4


package rs.ac.bg.etf.pp1.ast;

public class GlobVarDeclaration extends GlobVarDecl {

    private Type Type;
    private GlobVarList GlobVarList;

    public GlobVarDeclaration (Type Type, GlobVarList GlobVarList) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.GlobVarList=GlobVarList;
        if(GlobVarList!=null) GlobVarList.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public GlobVarList getGlobVarList() {
        return GlobVarList;
    }

    public void setGlobVarList(GlobVarList GlobVarList) {
        this.GlobVarList=GlobVarList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(GlobVarList!=null) GlobVarList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(GlobVarList!=null) GlobVarList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(GlobVarList!=null) GlobVarList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobVarDeclaration(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(GlobVarList!=null)
            buffer.append(GlobVarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobVarDeclaration]");
        return buffer.toString();
    }
}
