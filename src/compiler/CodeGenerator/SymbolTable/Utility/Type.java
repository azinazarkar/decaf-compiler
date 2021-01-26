package compiler.CodeGenerator.SymbolTable.Utility;

public enum Type {
	INT, DOUBLE, BOOL, STRING, STRINGLITERAL, ARRAY, VOID, NULL, OBJECT, DUMMY;

	public static String getMipsType( Object type ) {
		if ( type instanceof Type ) {
			if (type == INT || type == DOUBLE || type == BOOL || type == STRING || type == ARRAY)
				return ".word";
			if (type == STRINGLITERAL)
				return ".space";
		}
		else if ( type instanceof ArrayType )
			return ".word";
		return "IDON'TKNOW";
	}

}

