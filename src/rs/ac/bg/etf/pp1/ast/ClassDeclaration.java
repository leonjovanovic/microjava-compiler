// generated with ast extension for cup
// version 0.8
// 4/1/2019 1:14:23


package rs.ac.bg.etf.pp1.ast;

public class ClassDeclaration extends ClassDecl {

    private String className;
    private LoclVarDecl LoclVarDecl;
    private ClassPart ClassPart;

    public ClassDeclaration (String className, LoclVarDecl LoclVarDecl, ClassPart ClassPart) {
        this.className=className;
        this.LoclVarDecl=LoclVarDecl;
        if(LoclVarDecl!=null) LoclVarDecl.setParent(this);
        this.ClassPart=ClassPart;
        if(ClassPart!=null) ClassPart.setParent(this);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className=className;
    }

    public LoclVarDecl getLoclVarDecl() {
        return LoclVarDecl;
    }

    public void setLoclVarDecl(LoclVarDecl LoclVarDecl) {
        this.LoclVarDecl=LoclVarDecl;
    }

    public ClassPart getClassPart() {
        return ClassPart;
    }

    public void setClassPart(ClassPart ClassPart) {
        this.ClassPart=ClassPart;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(LoclVarDecl!=null) LoclVarDecl.accept(visitor);
        if(ClassPart!=null) ClassPart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(LoclVarDecl!=null) LoclVarDecl.traverseTopDown(visitor);
        if(ClassPart!=null) ClassPart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(LoclVarDecl!=null) LoclVarDecl.traverseBottomUp(visitor);
        if(ClassPart!=null) ClassPart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDeclaration(\n");

        buffer.append(" "+tab+className);
        buffer.append("\n");

        if(LoclVarDecl!=null)
            buffer.append(LoclVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassPart!=null)
            buffer.append(ClassPart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDeclaration]");
        return buffer.toString();
    }
}
