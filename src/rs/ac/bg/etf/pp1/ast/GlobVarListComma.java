// generated with ast extension for cup
// version 0.8
// 4/1/2019 1:14:23


package rs.ac.bg.etf.pp1.ast;

public class GlobVarListComma extends GlobVarList {

    private GlobVarList GlobVarList;
    private GlobVar GlobVar;

    public GlobVarListComma (GlobVarList GlobVarList, GlobVar GlobVar) {
        this.GlobVarList=GlobVarList;
        if(GlobVarList!=null) GlobVarList.setParent(this);
        this.GlobVar=GlobVar;
        if(GlobVar!=null) GlobVar.setParent(this);
    }

    public GlobVarList getGlobVarList() {
        return GlobVarList;
    }

    public void setGlobVarList(GlobVarList GlobVarList) {
        this.GlobVarList=GlobVarList;
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
        if(GlobVarList!=null) GlobVarList.accept(visitor);
        if(GlobVar!=null) GlobVar.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(GlobVarList!=null) GlobVarList.traverseTopDown(visitor);
        if(GlobVar!=null) GlobVar.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(GlobVarList!=null) GlobVarList.traverseBottomUp(visitor);
        if(GlobVar!=null) GlobVar.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobVarListComma(\n");

        if(GlobVarList!=null)
            buffer.append(GlobVarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(GlobVar!=null)
            buffer.append(GlobVar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobVarListComma]");
        return buffer.toString();
    }
}
