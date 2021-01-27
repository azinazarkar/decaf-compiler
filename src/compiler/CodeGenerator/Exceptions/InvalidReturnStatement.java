package compiler.CodeGenerator.Exceptions;

public class InvalidReturnStatement extends RuntimeException {

	@Override
	public String getMessage() {
		return "You can't have a return statement here!";
	}
}
