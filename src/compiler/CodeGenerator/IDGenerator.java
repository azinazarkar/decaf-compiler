package compiler.CodeGenerator;

public class IDGenerator {
	private static IDGenerator ourInstance = new IDGenerator();
	private static int currentIndex = 28; // first id is aa.

	public static IDGenerator getInstance() {
		return ourInstance;
	}

	private IDGenerator() {
	}

	public String getNextID() {
		int temp = currentIndex;
		String returnValue = "";
		while ( temp > 0 ) {
			int mod = temp % 27;
			returnValue = (char) ('a' - 1 + mod) + returnValue;
			temp = temp / 27;
		}
		currentIndex++;
		if ( currentIndex % 27 == 0 )
			currentIndex++;
		return returnValue;
	}

}
