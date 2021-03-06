// generated with ast extension for cup
// version 0.8
// 4/1/2019 1:14:23


package rs.ac.bg.etf.pp1.ast;

public class MatchedIfElse extends Matched {

    private Condition Condition;
    private ConditionEnd ConditionEnd;
    private Matched Matched;
    private StartElse StartElse;
    private Matched Matched1;

    public MatchedIfElse (Condition Condition, ConditionEnd ConditionEnd, Matched Matched, StartElse StartElse, Matched Matched1) {
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.ConditionEnd=ConditionEnd;
        if(ConditionEnd!=null) ConditionEnd.setParent(this);
        this.Matched=Matched;
        if(Matched!=null) Matched.setParent(this);
        this.StartElse=StartElse;
        if(StartElse!=null) StartElse.setParent(this);
        this.Matched1=Matched1;
        if(Matched1!=null) Matched1.setParent(this);
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public ConditionEnd getConditionEnd() {
        return ConditionEnd;
    }

    public void setConditionEnd(ConditionEnd ConditionEnd) {
        this.ConditionEnd=ConditionEnd;
    }

    public Matched getMatched() {
        return Matched;
    }

    public void setMatched(Matched Matched) {
        this.Matched=Matched;
    }

    public StartElse getStartElse() {
        return StartElse;
    }

    public void setStartElse(StartElse StartElse) {
        this.StartElse=StartElse;
    }

    public Matched getMatched1() {
        return Matched1;
    }

    public void setMatched1(Matched Matched1) {
        this.Matched1=Matched1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Condition!=null) Condition.accept(visitor);
        if(ConditionEnd!=null) ConditionEnd.accept(visitor);
        if(Matched!=null) Matched.accept(visitor);
        if(StartElse!=null) StartElse.accept(visitor);
        if(Matched1!=null) Matched1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(ConditionEnd!=null) ConditionEnd.traverseTopDown(visitor);
        if(Matched!=null) Matched.traverseTopDown(visitor);
        if(StartElse!=null) StartElse.traverseTopDown(visitor);
        if(Matched1!=null) Matched1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(ConditionEnd!=null) ConditionEnd.traverseBottomUp(visitor);
        if(Matched!=null) Matched.traverseBottomUp(visitor);
        if(StartElse!=null) StartElse.traverseBottomUp(visitor);
        if(Matched1!=null) Matched1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MatchedIfElse(\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConditionEnd!=null)
            buffer.append(ConditionEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Matched!=null)
            buffer.append(Matched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StartElse!=null)
            buffer.append(StartElse.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Matched1!=null)
            buffer.append(Matched1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MatchedIfElse]");
        return buffer.toString();
    }
}
