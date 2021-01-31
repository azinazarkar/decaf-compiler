package compiler;

import java.io.IOException;

public class TesterMain {

	public static void main(String[] args) {
		String inputFileName = null;
		String outputFileName = null;
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				if (args[i].equals("-i")) {
					inputFileName = args[i + 1];
				}
				if (args[i].equals("-o")) {
					outputFileName = args[i + 1];
				}
			}
		}
		try {
			Compiler.getInstance().compile( inputFileName, outputFileName );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
