package compiler.Parser.Utility;

public class ParserHelper {
	private static ParserHelper ourInstance = new ParserHelper();
//	private boolean LValue;
	public boolean isLValueArray;

	public static ParserHelper getInstance() {
		return ourInstance;
	}

	private ParserHelper() {
//		LValue = false;
		isLValueArray = false;
	}

//	public boolean isLValue() {
//		return LValue;
//	}
//
//	public void setLValue( boolean LValue ) {
//		this.LValue = LValue;
//	}

}
