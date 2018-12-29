// generated with ast extension for cup
// version 0.8
// 29/11/2018 4:58:37


package rs.ac.bg.etf.pp1.ast;

public class MatchedFor extends Matched {

    private Matched_For Matched_For;

    public MatchedFor (Matched_For Matched_For) {
        this.Matched_For=Matched_For;
        if(Matched_For!=null) Matched_For.setParent(this);
    }

    public Matched_For getMatched_For() {
        return Matched_For;
    }

    public void setMatched_For(Matched_For Matched_For) {
        this.Matched_For=Matched_For;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Matched_For!=null) Matched_For.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Matched_For!=null) Matched_For.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Matched_For!=null) Matched_For.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MatchedFor(\n");

        if(Matched_For!=null)
            buffer.append(Matched_For.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MatchedFor]");
        return buffer.toString();
    }
}
