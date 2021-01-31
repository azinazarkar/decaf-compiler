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

		for ( int i = 0; i < 2; i++ ) { // parsing is done in 2 phases
			FileReader fileReader = new FileReader( inputFileName );
			MyScanner yylex = new MyScanner( fileReader );
			parser p = new parser(yylex);
			try {
				p.parse();
			} catch (SemanticError e) {
				ErrorCodeGen.getInstance().cgen( "Semantic Error" );
				CodeGen.getInstance().writeToFile( outputFileName );
				System.exit( 0 );
			}
			catch (Exception e) {
				ErrorCodeGen.getInstance().cgen( "Syntax Error" );
				CodeGen.getInstance().writeToFile( outputFileName );
				System.exit( 0 );
			}
			ParserPhase.getInstance().nextPhase();
			fileReader.close();
		}

		CodeGen.getInstance().writeToFile( outputFileName );

	}

}
