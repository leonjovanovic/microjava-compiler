// generated with ast extension for cup
// version 0.8
// 27/0/2019 5:2:17


package rs.ac.bg.etf.pp1.ast;

public class UnmatchedFor extends Unmatched {

    private Unmatched_For Unmatched_For;

    public UnmatchedFor (Unmatched_For Unmatched_For) {
        this.Unmatched_For=Unmatched_For;
        if(Unmatched_For!=null) Unmatched_For.setParent(this);
    }

    public Unmatched_For getUnmatched_For() {
        return Unmatched_For;
    }

    public void setUnmatched_For(Unmatched_For Unmatched_For) {
        this.Unmatched_For=Unmatched_For;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Unmatched_For!=null) Unmatched_For.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Unmatched_For!=null) Unmatched_For.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Unmatched_For!=null) Unmatched_For.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("UnmatchedFor(\n");

        if(Unmatched_For!=null)
            buffer.append(Unmatched_For.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [UnmatchedFor]");
        return buffer.toString();
    }
}
