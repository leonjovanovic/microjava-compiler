package rs.ac.bg.etf.pp1;

import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class TabExtended extends Tab {

	public static final int Bool = 5;
	public static final int Enum = 6;
	
	public static final Struct boolType = new Struct(Bool);
	public static final Struct enumType = new Struct(Enum);

	public static void init() {	
		rs.etf.pp1.symboltable.Tab.init();
		currentScope.addToLocals(new Obj(Obj.Type, "bool", boolType));
		currentScope.addToLocals(new Obj(Obj.Type, "enum", enumType));
	}

}