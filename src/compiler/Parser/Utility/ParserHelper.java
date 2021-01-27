package compiler.Parser.Utility;

public class ParserHelper {
	private static ParserHelper ourInstance = new ParserHelper();
	public boolean isLValueArray;
	public boolean insideFunctionFormals;

	public static ParserHelper getInstance() {
		return ourInstance;
	}

	private ParserHelper() {
		isLValueArray = false;
		insideFunctionFormals = false;
	}

}
