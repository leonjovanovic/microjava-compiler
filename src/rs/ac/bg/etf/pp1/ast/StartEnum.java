// generated with ast extension for cup
// version 0.8
// 28/0/2019 23:34:4


package rs.ac.bg.etf.pp1.ast;

public class StartEnum extends EnumStart {

    private String enumName;

    public StartEnum (String enumName) {
        this.enumName=enumName;
    }

    public String getEnumName() {
        return enumName;
    }

    public void setEnumName(String enumName) {
        this.enumName=enumName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StartEnum(\n");

        buffer.append(" "+tab+enumName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StartEnum]");
        return buffer.toString();
    }
}
