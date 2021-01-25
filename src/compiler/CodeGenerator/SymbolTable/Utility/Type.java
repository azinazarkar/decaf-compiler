package compiler.CodeGenerator.SymbolTable.Utility;

public enum Type {
	INT, DOUBLE, BOOL, STRING, STRINGLITERAL, VOID, NULL, OBJECT, DUMMY;

	public static String getMipsType( Type type ) {
		if ( type == INT || type == DOUBLE || type == BOOL || type == STRING )
			return ".word";
		if ( type == STRINGLITERAL )
			return ".byte";
		else return "IDON'TKNOW";
	}

}

