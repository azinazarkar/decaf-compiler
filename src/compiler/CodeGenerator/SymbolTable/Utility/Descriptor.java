package compiler.CodeGenerator.SymbolTable.Utility;

public class Descriptor {

	private Type type;
	private Object value;

	public Descriptor(Type type, Object value) {
		this.type = type;
		this.value = value;
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

	@Override
	public String toString() {
		return "" + type;
	}
}
