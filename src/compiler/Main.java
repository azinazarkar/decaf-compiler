package compiler;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		FileReader fileReader = new FileReader( "src/compiler/program.txt" );
		MyScanner yylex = new MyScanner( fileReader );
		parser p = new parser(yylex);
		p.parse();
		System.out.println("bashe");
	}
}