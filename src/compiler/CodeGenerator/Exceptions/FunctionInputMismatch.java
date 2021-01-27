package compiler.CodeGenerator.Exceptions;

public class FunctionInputMismatch extends RuntimeException{

	private String functionName;

	public FunctionInputMismatch(String functionName) {
		this.functionName = functionName;
	}

	@Override
	public String getMessage() {
		return "Input mismatch on calling function " + this.functionName;
	}
}
