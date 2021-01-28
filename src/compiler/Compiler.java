package compiler;

import compiler.CodeGenerator.CodeGen.CodeGen;
import compiler.CodeGenerator.CodeGen.ErrorCodeGen;
import compiler.CodeGenerator.Exceptions.SemanticError;
import compiler.CodeGenerator.Exceptions.SemanticErrors.AssignmentTypeMismatch;
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

	public void compile( String inputFileName, String outputFileName ) throws IOException {
		FileReader fileReader = new FileReader( inputFileName );
		MyScanner yylex = new MyScanner( fileReader );
		parser p = new parser(yylex);
		// first phase of parsing
//		System.out.println( "First phase." );
		try {
			p.parse();
		} catch (SemanticError e) {
			System.out.println( 1 );
			ErrorCodeGen.getInstance().cgen( "Semantic Error" );
			CodeGen.getInstance().writeToFile( outputFileName );
			System.exit( 0 );
		}
		catch (Exception e) {
			System.out.println( 2 );
			ErrorCodeGen.getInstance().cgen( "Syntax Error" );
			CodeGen.getInstance().writeToFile( outputFileName );
			System.exit( 0 );
		}
		// now, next phase
		ParserPhase.getInstance().nextPhase();
		fileReader.close();
		fileReader = new FileReader( inputFileName );
		p = new parser( new MyScanner( fileReader ) );
//		System.out.println( "Second phase." );
		try {
			p.parse();
		} catch (SemanticError e) {
			e.printStackTrace();
			System.out.println( 3 );
			ErrorCodeGen.getInstance().cgen( "Semantic Error" );
			CodeGen.getInstance().writeToFile( outputFileName );
			System.exit( 0 );
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println( 4 );
			ErrorCodeGen.getInstance().cgen( "Syntax Error" );
			CodeGen.getInstance().writeToFile( outputFileName );
			System.exit( 0 );
		}
		CodeGen.getInstance().writeToFile( outputFileName );
	}

}
