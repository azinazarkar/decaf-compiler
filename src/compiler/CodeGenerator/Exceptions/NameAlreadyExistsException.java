package compiler.CodeGenerator.Exceptions;

public class NameAlreadyExistsException extends RuntimeException {

	private String name;

	public NameAlreadyExistsException(String name ) {
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
		return "variable " + name + " already exists in this scope!";
	}
}
