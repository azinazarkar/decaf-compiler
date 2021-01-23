package compiler;

import compiler.CodeGenerator.CodeGen.CodeGen;
import compiler.Parser.parser;
import compiler.Scanner.MyScanner;

import java.io.FileReader;

public class Main {

	public static void main(String[] args) throws Exception {
		FileReader fileReader = new FileReader( "src/compiler/program.txt" );
		MyScanner yylex = new MyScanner( fileReader );
		parser p = new parser(yylex);
		try {
			p.parse();
		} catch (Exception e) {
			System.out.println( e );
			System.exit( 1 );
		}
		CodeGen.getInstance().compile();
		System.out.println("OK");
	}
}