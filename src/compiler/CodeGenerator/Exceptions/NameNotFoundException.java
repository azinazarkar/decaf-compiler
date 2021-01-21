package compiler.CodeGenerator.Exceptions;

public class NameNotFoundException extends RuntimeException {

	private String name;

	public NameNotFoundException(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getMessage() {
		return "variable " + name + " not found in this scope.";
	}
}
