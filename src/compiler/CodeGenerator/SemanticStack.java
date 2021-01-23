package compiler.CodeGenerator;

import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;

import java.util.Stack;

public class SemanticStack {
	private static SemanticStack ourInstance = new SemanticStack();
	private Stack<Descriptor> stack;

	public static SemanticStack getInstance() {
		return ourInstance;
	}

	private SemanticStack() {
		stack = new Stack<>();
	}

	public void pushDescriptor(Descriptor descriptor ) {
		stack.push( descriptor );
	}

	public Descriptor popDescriptor() {
		return stack.pop();
	}

	public Descriptor topDescriptor() {
		return stack.peek();
	}

}
