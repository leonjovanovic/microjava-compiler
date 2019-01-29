// generated with ast extension for cup
// version 0.8
// 28/0/2019 23:34:4


package rs.ac.bg.etf.pp1.ast;

public class MethodVarDecl extends MethVarDecl {

    private MethVarDecl MethVarDecl;
    private LoclVarDecl LoclVarDecl;

    public MethodVarDecl (MethVarDecl MethVarDecl, LoclVarDecl LoclVarDecl) {
        this.MethVarDecl=MethVarDecl;
        if(MethVarDecl!=null) MethVarDecl.setParent(this);
        this.LoclVarDecl=LoclVarDecl;
        if(LoclVarDecl!=null) LoclVarDecl.setParent(this);
    }

    public MethVarDecl getMethVarDecl() {
        return MethVarDecl;
    }

    public void setMethVarDecl(MethVarDecl MethVarDecl) {
        this.MethVarDecl=MethVarDecl;
    }

    public LoclVarDecl getLoclVarDecl() {
        return LoclVarDecl;
    }

    public void setLoclVarDecl(LoclVarDecl LoclVarDecl) {
        this.LoclVarDecl=LoclVarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethVarDecl!=null) MethVarDecl.accept(visitor);
        if(LoclVarDecl!=null) LoclVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethVarDecl!=null) MethVarDecl.traverseTopDown(visitor);
        if(LoclVarDecl!=null) LoclVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethVarDecl!=null) MethVarDecl.traverseBottomUp(visitor);
        if(LoclVarDecl!=null) LoclVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodVarDecl(\n");

        if(MethVarDecl!=null)
            buffer.append(MethVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(LoclVarDecl!=null)
            buffer.append(LoclVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodVarDecl]");
        return buffer.toString();
    }
}
