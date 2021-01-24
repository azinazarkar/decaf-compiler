package compiler.CodeGenerator;

import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;

import java.util.Stack;

public class SemanticStack {
	private static SemanticStack ourInstance = new SemanticStack();
	private Stack<Object> stack;

	public static SemanticStack getInstance() {
		return ourInstance;
	}

	private SemanticStack() {
		stack = new Stack<>();
	}

	public void pushDescriptor( Object value ) {
		stack.push( value );
	}

	public Object popDescriptor() {
		return stack.pop();
	}

	public Object topDescriptor() {
		return stack.peek();
	}

}
