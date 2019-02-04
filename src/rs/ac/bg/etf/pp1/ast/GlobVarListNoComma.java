// generated with ast extension for cup
// version 0.8
// 4/1/2019 1:14:23


package rs.ac.bg.etf.pp1.ast;

public class GlobVarListNoComma extends GlobVarList {

    private GlobVar GlobVar;

    public GlobVarListNoComma (GlobVar GlobVar) {
        this.GlobVar=GlobVar;
        if(GlobVar!=null) GlobVar.setParent(this);
    }

    public GlobVar getGlobVar() {
        return GlobVar;
    }

    public void setGlobVar(GlobVar GlobVar) {
        this.GlobVar=GlobVar;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(GlobVar!=null) GlobVar.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(GlobVar!=null) GlobVar.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(GlobVar!=null) GlobVar.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobVarListNoComma(\n");

        if(GlobVar!=null)
            buffer.append(GlobVar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobVarListNoComma]");
        return buffer.toString();
    }
}
