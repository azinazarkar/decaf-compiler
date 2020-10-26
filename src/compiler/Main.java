package compiler;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		final List<TokenType> literals = new ArrayList<>(Arrays.asList(
				TokenType.DECIMAL, TokenType.FLOATINGPOINT, TokenType.ID,
				TokenType.STRINGLITERAL, TokenType.BOOLEANLITERAL
		));

		String inputFile = new Scanner( System.in ).next();
//		FileReader fileReader = new FileReader( "src/compiler/program.txt" );
		FileReader fileReader = new FileReader( inputFile );
		MyScanner yylex = new MyScanner( fileReader );
		while(true){
			Symbol symbol = yylex.next();
			if (symbol.tokenType == TokenType.EOF)
				break;
			if (symbol.tokenType != TokenType.WS)
				if ( literals.contains( symbol.tokenType ) )
					System.out.println(symbol.toPrint + " " + symbol.content);
				else
					System.out.println(symbol.toPrint);
		}
	}
}
