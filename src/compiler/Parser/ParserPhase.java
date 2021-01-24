package compiler.Parser;

import compiler.CodeGenerator.SymbolTable.SymbolTable;

public class ParserPhase {
	private static ParserPhase ourInstance = new ParserPhase();

	public static ParserPhase getInstance() {
		return ourInstance;
	}

	private ParserPhase() {
		phase = 0;
	}

	private int phase;

	public void nextPhase() {
		phase++;
		SymbolTable.getInstance().resetIndexes();
	}

	public int getPhase() {
		return phase;
	}

}
