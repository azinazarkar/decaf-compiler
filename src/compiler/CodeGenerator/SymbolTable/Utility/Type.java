package compiler.CodeGenerator.SymbolTable.Utility;

public enum Type {
	INT, DOUBLE, BOOL, STRING, VOID, NULL, OBJECT, DUMMY;

	public static String getMipsType( Type type ) {
		if ( type == INT || type == DOUBLE || type == BOOL )
			return ".word";
		if ( type == STRING )
			return ".asciiz";
		else return "IDON'TKNOW";
	}

}

