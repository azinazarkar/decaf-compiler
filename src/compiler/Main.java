package compiler;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("src/compiler/program.txt");
        MyScanner yylex = new MyScanner( fileReader );
        while(true){
            Symbol symbol = yylex.next();
            if (symbol.tokenType == TokenType.EOF)
                break;
            if (symbol.tokenType != TokenType.WS)
                if (symbol.tokenType == TokenType.String) {
                    System.out.println(symbol.toPrint + " " + symbol.content);
                }
                else if (symbol.tokenType == TokenType.floatingPoint) {
                    System.out.println(symbol.toPrint + " " + symbol.content);
                }
                else
                    System.out.print(symbol.toPrint);

        }
    }
}
