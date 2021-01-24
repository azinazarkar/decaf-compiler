package compiler;

import compiler.CodeGenerator.CodeGen.CodeGen;
import compiler.CodeGenerator.SymbolTable.SymbolTable;
import compiler.Parser.ParserPhase;
import compiler.Parser.parser;
import compiler.Scanner.MyScanner;

import java.io.FileReader;
import java.io.IOException;

public class Compiler {
	private static Compiler ourInstance = new Compiler();

	public static Compiler getInstance() {
		return ourInstance;
	}

	private Compiler() {
	}

	public void compile( String fileAddress ) throws IOException {
		FileReader fileReader = new FileReader( fileAddress );
		MyScanner yylex = new MyScanner( fileReader );
		parser p = new parser(yylex);
		// first phase of parsing
		System.out.println( "First phase." );
		try {
			p.parse();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit( 1 );
		}
		// now, next phase
		ParserPhase.getInstance().nextPhase();
		fileReader.close();
		fileReader = new FileReader( fileAddress );
		p = new parser( new MyScanner( fileReader ) );
		System.out.println( "Second phase." );
		try {
			p.parse();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit( 1 );
		}
		CodeGen.getInstance().writeToFile();
	}

}
