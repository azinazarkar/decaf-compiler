package compiler.CodeGenerator.Exceptions;

import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class CalculationTypeMismatch extends RuntimeException {

	private String symbol;
	private Type t1, t2;

	public CalculationTypeMismatch( String symbol, Type t1, Type t2 ) {
		this.symbol = symbol;
		this.t1 = t1;
		this.t2 = t2;
	}

	@Override
	public String getMessage() {
		return "Cannot do " + symbol + " on types " + t1.name() + " and " + t2.name() + "!";
	}
}
