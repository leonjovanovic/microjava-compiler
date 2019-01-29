// generated with ast extension for cup
// version 0.8
// 28/0/2019 23:34:4


package rs.ac.bg.etf.pp1.ast;

public class LoclVarDeclaration extends LoclVarDecl {

    private Type Type;
    private LoclVarList LoclVarList;

    public LoclVarDeclaration (Type Type, LoclVarList LoclVarList) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.LoclVarList=LoclVarList;
        if(LoclVarList!=null) LoclVarList.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public LoclVarList getLoclVarList() {
        return LoclVarList;
    }

    public void setLoclVarList(LoclVarList LoclVarList) {
        this.LoclVarList=LoclVarList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(LoclVarList!=null) LoclVarList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(LoclVarList!=null) LoclVarList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(LoclVarList!=null) LoclVarList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("LoclVarDeclaration(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(LoclVarList!=null)
            buffer.append(LoclVarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [LoclVarDeclaration]");
        return buffer.toString();
    }
}
