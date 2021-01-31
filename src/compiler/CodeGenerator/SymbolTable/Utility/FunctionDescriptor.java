package compiler.CodeGenerator.SymbolTable.Utility;

import java.util.ArrayList;
import java.util.List;

public class FunctionDescriptor extends Descriptor {

	private String functionName;
	private List<String> argumentsName;
	private List<Descriptor> argumentsDescriptor;

	public FunctionDescriptor( String functionName, String name, Type returnType ) {
		super( name, returnType );
		this.argumentsName = new ArrayList<>( 0 );
		this.argumentsDescriptor = new ArrayList<>( 0 );
		this.functionName = functionName;
	}

	public void addArgument( String argumentName, Descriptor descriptor, boolean inFirst ) {
		if ( inFirst ) {
			argumentsName.add(0, argumentName);
			argumentsDescriptor.add(0, descriptor);
		}
		else {
			argumentsName.add(argumentName);
			argumentsDescriptor.add(descriptor);
		}
	}

	public void addArgument( String argumentName, Descriptor descriptor ) {
		addArgument( argumentName, descriptor, false );
	}

	public String getArgumentName( int index ) {
		return argumentsName.get( index );
	}

	public Descriptor getArgumentDescriptor( int index ) {
		return argumentsDescriptor.get( index );
	}

	public int getArgumentCount() {
		return argumentsName.size();
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	@Override
	public String toString() {
		String returnValue = getName() + ": ";
		for ( int i = 0; i < argumentsName.size(); i++ )
			returnValue += "   (" + argumentsName.get( i ) + ", " + argumentsDescriptor.get( i ) + ")";
		return returnValue;
	}
}
