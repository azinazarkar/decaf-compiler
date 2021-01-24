package compiler.CodeGenerator.CodeGen;

import java.io.FileWriter;
import java.io.IOException;

public class CodeGen {
	private static CodeGen ourInstance = new CodeGen();
	private String dataSeg = "\t.data\n\n";
	private String textSeg = "\t.text\n\t.globl main\n\n";

	public static CodeGen getInstance() {
		return ourInstance;
	}

	private CodeGen() {
		dataSeg += "\ttrue_print_string: .asciiz \"true\"\n";
		dataSeg += "\tfalse_print_string: .asciiz \"false\"\n";
		dataSeg += "\n";
	}

	public void addToData( String in ) {
		dataSeg = dataSeg + "\t" + in + "\n";
	}

	public void addToData( String name, String type, String value ) {
		addToData( name + ": " + type + " " + value );
	}

	public void addToText( String in ) {
		addToText( in, false );
	}

	public void addToText( String in, boolean isLabel ) {
		if ( !isLabel )
			textSeg = textSeg + "\t";
		textSeg = textSeg + in + "\n";
	}

	public void addEmptyLine() {
		addToText( "" );
	}

	public void cgen() {

	}

	public void writeToFile() throws IOException {
		FileWriter out = new FileWriter( "out.asm" );
		out.write( dataSeg + "\n" + textSeg);
		out.close();
	}

}
