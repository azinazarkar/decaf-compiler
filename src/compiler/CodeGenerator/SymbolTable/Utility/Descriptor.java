package compiler.CodeGenerator.SymbolTable.Utility;

public class Descriptor {

	private String name;
	private Type type;

	public Descriptor(String name, Type type) {
		this.name = name;
		this.type = type;
	}

	public static Type getType( String in ) {
		if ( in.equals( "INT" ) )
			return Type.INT;
		else if ( in.equals( "DOUBLE" ) )
			return Type.DOUBLE;
		else if ( in.equals( "BOOL" ) )
			return Type.BOOL;
		else if ( in.equals( "STRING" ) )
			return Type.STRING;
		else if ( in.equals( "VOID" ) )
			return Type.VOID;
		else return Type.OBJECT;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return name + " " + type;
	}
}
