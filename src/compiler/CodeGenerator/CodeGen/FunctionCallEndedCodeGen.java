package compiler.CodeGenerator.CodeGen;

public class FunctionCallEndedCodeGen {
	private static FunctionCallEndedCodeGen ourInstance = new FunctionCallEndedCodeGen();

	public static FunctionCallEndedCodeGen getInstance() {
		return ourInstance;
	}

	private FunctionCallEndedCodeGen() {
	}

	public void cgen() {
		CodeGen.getInstance().addToText( "# functino call is ended" );
		CodeGen.getInstance().addToText( "lw $ra, 0($sp)" );
		CodeGen.getInstance().addToText( "addi $sp, $sp, 4" );
		CodeGen.getInstance().addEmptyLine();
	}

}
