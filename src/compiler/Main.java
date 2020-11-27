package compiler;

import java.io.FileReader;

public class Main {

	public static void main(String[] args) throws Exception {
		FileReader fileReader = new FileReader( "src/compiler/program.txt" );
		MyScanner yylex = new MyScanner( fileReader );
		parser p = new parser(yylex);
		try {
			p.parse();
		} catch (Exception e) {
			System.out.println("Syntax Error");
			System.exit( 0 );
		}
		System.out.println("OK");
	}
}