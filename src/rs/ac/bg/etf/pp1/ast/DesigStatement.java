// generated with ast extension for cup
// version 0.8
// 29/11/2018 4:58:38


package rs.ac.bg.etf.pp1.ast;

public class DesigStatement extends DesignatorStatement {

    private Designator Designator;
    private DesignatorPart DesignatorPart;

    public DesigStatement (Designator Designator, DesignatorPart DesignatorPart) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.DesignatorPart=DesignatorPart;
        if(DesignatorPart!=null) DesignatorPart.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public DesignatorPart getDesignatorPart() {
        return DesignatorPart;
    }

    public void setDesignatorPart(DesignatorPart DesignatorPart) {
        this.DesignatorPart=DesignatorPart;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(DesignatorPart!=null) DesignatorPart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(DesignatorPart!=null) DesignatorPart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(DesignatorPart!=null) DesignatorPart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesigStatement(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorPart!=null)
            buffer.append(DesignatorPart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesigStatement]");
        return buffer.toString();
    }
}
