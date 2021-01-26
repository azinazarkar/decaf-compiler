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
		dataSeg += "\t_true_print_string: .asciiz \"true\"\n";
		dataSeg += "\t_false_print_string: .asciiz \"false\"\n";
		dataSeg += "\n";
		dataSeg += "\t_dtoi_border_value: .word 0xbf000000\n";
		dataSeg += "\n";
		dataSeg += "\t_array_size_error_message: .asciiz \"array size less than zero\\n\"\n";
		dataSeg += "\n";
		addEmptyLine();
		addBooleanPrintMethods();
		addEmptyLine();
		addStringSizeMethod();
		addEmptyLine();
		addEmptyLine();
	}

	public void addToData( String in ) {
		dataSeg = dataSeg + "\t" + in + "\n";
	}

	public void addToData( String name, String type, String value ) {
		addToData( name + ": " + type + " " + value );
	}

	public void addToData( String name, String type, int value ) {
		addToData( name, type, Integer.toString( value ) );
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

	private void addBooleanPrintMethods() {
		addToText( "_print_true:", true );
		addToText( "li $v0, 4" );
		addToText( "la $a0, _true_print_string" );
		addToText( "syscall" );
		addToText( "jr $ra" );
		addEmptyLine();
		addToText( "_print_false:", true );
		addToText( "li $v0, 4" );
		addToText( "la $a0, _false_print_string" );
		addToText( "syscall" );
		addToText( "jr $ra" );
	}

	private void addStringSizeMethod() {
		addToText( "_get_string_size:", true );
		addToText( "move $s0, $a0" );
		addToText( "move $s1, $zero" );
		addToText( "_get_string_size_loop:", true );
		addToText( "lb $t2, 0($s0)" );
		addToText( "beqz $t2, _get_string_size_end" );
		addToText( "addi $s1, $s1, 1" );
		addToText( "addi $s0, $s0, 1" );
		addToText( "j _get_string_size_loop" );
		addToText( "_get_string_size_end:", true );
		addToText( "move $v0, $s1" );
		addToText( "jr $ra" );
	}

}
