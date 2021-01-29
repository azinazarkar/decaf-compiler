package compiler.CodeGenerator.CodeGen;

public class ErrorCodeGen {
	private static ErrorCodeGen ourInstance = new ErrorCodeGen();

	public static ErrorCodeGen getInstance() {
		return ourInstance;
	}

	private ErrorCodeGen() {
	}

	public void cgen( String message ) {
		CodeGen.getInstance().reset();
		CodeGen.getInstance().addToData( "_error_message: .asciiz \"" + message + "\"" );
		CodeGen.getInstance().addToText( "main: ", true );
		CodeGen.getInstance().addToText( "li $v0, 4" );
		CodeGen.getInstance().addToText( "la $a0, _error_message" );
		CodeGen.getInstance().addToText( "syscall" );
		CodeGen.getInstance().addEmptyLine();
		CodeGen.getInstance().addToText( "li $v0, 10" );
		CodeGen.getInstance().addToText( "syscall" );

	}

}
