package compiler.CodeGenerator;

import java.io.FileWriter;
import java.io.IOException;

public class CodeGen {
	private static CodeGen ourInstance = new CodeGen();
	private String dataSeg = "\t.data\n";
	private String textSeg = "\t.text\n\t.global main\nmain:\n";

	public static CodeGen getInstance() {
		return ourInstance;
	}

	private CodeGen() {
	}

	public void addToData( String in ) {
		dataSeg = dataSeg + "\t" + in + "\n";
	}

	public void addToData( String name, String type, String value ) {
		addToData( name + ": " + type + " " + value );
	}

	public void addToText( String in, boolean isLabel ) {
		if ( !isLabel )
			textSeg = textSeg + "\t";
		textSeg = textSeg + in + "\n";
	}

	public void cgen() {

	}

	public void compile() throws IOException {
		FileWriter out = new FileWriter( "out.asm" );
		out.write( dataSeg + "\n" + textSeg);
		out.close();
	}

}
