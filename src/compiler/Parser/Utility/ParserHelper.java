package compiler.Parser.Utility;

import compiler.CodeGenerator.SymbolTable.Utility.FunctionDescriptor;

public class ParserHelper {
	private static ParserHelper ourInstance = new ParserHelper();
	public boolean isLValueArray;
	public boolean insideFunctionFormals;
	public FunctionDescriptor currentFunctionDscp;

	public static ParserHelper getInstance() {
		return ourInstance;
	}

	private ParserHelper() {
		isLValueArray = false;
		insideFunctionFormals = false;
		currentFunctionDscp = null;
	}

}
