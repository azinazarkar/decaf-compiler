package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;

public class StringLiteralCodeGen {
	private static StringLiteralCodeGen ourInstance = new StringLiteralCodeGen();

	public static StringLiteralCodeGen getInstance() {
		return ourInstance;
	}

	private StringLiteralCodeGen() {
	}

	public void cgen() {
		Descriptor descriptor = (Descriptor) SemanticStack.getInstance().popDescriptor();
		String s = descriptor.getValue().toString();
		int size = s.length();
		CodeGen.getInstance().addToText( "# Storing string \"" + s.substring(0, Math.min( 10, s.length() - 1 ) ) + "...\" into data segment" );
		CodeGen.getInstance().addToText( "la $s0, " + descriptor.getName() );
		for ( int i = 0; i < size; i++ ) {
			char currentChar = s.charAt( i );
			CodeGen.getInstance().addToText( "li $s1, \'" + currentChar + "\'" );
			CodeGen.getInstance().addToText( "sb $s1, " + i + "($s0)" );
			System.out.println( (int) currentChar );
		}
	}

}
