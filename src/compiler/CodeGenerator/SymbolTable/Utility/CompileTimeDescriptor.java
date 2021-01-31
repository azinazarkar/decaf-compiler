package compiler.CodeGenerator.SymbolTable.Utility;

public class CompileTimeDescriptor extends Descriptor {

	private Object value;

	public CompileTimeDescriptor( String name, Type type, Object value ) {
		super( name, type );
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
