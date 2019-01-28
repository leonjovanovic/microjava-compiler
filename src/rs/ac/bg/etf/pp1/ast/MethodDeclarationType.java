// generated with ast extension for cup
// version 0.8
// 28/0/2019 6:42:7


package rs.ac.bg.etf.pp1.ast;

public class MethodDeclarationType extends MethodDecl {

    private MethodNameType MethodNameType;
    private FormPars FormPars;
    private MethVarDecl MethVarDecl;
    private StatementList StatementList;

    public MethodDeclarationType (MethodNameType MethodNameType, FormPars FormPars, MethVarDecl MethVarDecl, StatementList StatementList) {
        this.MethodNameType=MethodNameType;
        if(MethodNameType!=null) MethodNameType.setParent(this);
        this.FormPars=FormPars;
        if(FormPars!=null) FormPars.setParent(this);
        this.MethVarDecl=MethVarDecl;
        if(MethVarDecl!=null) MethVarDecl.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public MethodNameType getMethodNameType() {
        return MethodNameType;
    }

    public void setMethodNameType(MethodNameType MethodNameType) {
        this.MethodNameType=MethodNameType;
    }

    public FormPars getFormPars() {
        return FormPars;
    }

    public void setFormPars(FormPars FormPars) {
        this.FormPars=FormPars;
    }

    public MethVarDecl getMethVarDecl() {
        return MethVarDecl;
    }

    public void setMethVarDecl(MethVarDecl MethVarDecl) {
        this.MethVarDecl=MethVarDecl;
    }

    public StatementList getStatementList() {
        return StatementList;
    }

    public void setStatementList(StatementList StatementList) {
        this.StatementList=StatementList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodNameType!=null) MethodNameType.accept(visitor);
        if(FormPars!=null) FormPars.accept(visitor);
        if(MethVarDecl!=null) MethVarDecl.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodNameType!=null) MethodNameType.traverseTopDown(visitor);
        if(FormPars!=null) FormPars.traverseTopDown(visitor);
        if(MethVarDecl!=null) MethVarDecl.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodNameType!=null) MethodNameType.traverseBottomUp(visitor);
        if(FormPars!=null) FormPars.traverseBottomUp(visitor);
        if(MethVarDecl!=null) MethVarDecl.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDeclarationType(\n");

        if(MethodNameType!=null)
            buffer.append(MethodNameType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormPars!=null)
            buffer.append(FormPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethVarDecl!=null)
            buffer.append(MethVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDeclarationType]");
        return buffer.toString();
    }
}
