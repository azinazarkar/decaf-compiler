package compiler;

import java.io.*;

class Symbol {

	public String content;
	public TokenType tokenType;
	public String toPrint;

	public Symbol( TokenType tokenType, String toPrint, String content ) {
		this.content = content;
		this.tokenType = tokenType;
		this.toPrint = toPrint;
	}

}

enum TokenType {
    decimal, floatingPoint, id, EOF, WS, String , Char, comment
}

%%

%public
%line
%column

%class MyScanner
%unicode

%{
    String string = "";
%}

%type Symbol

%function next
%state STRING

alphabet = [A-Za-z]
digit = [0-9]
underLine = "_"
id = {alphabet}({alphabet} | {digit} | {underLine})*
endLine = \n|\r|\r\n
whiteSpace = " " |\t| {endLine}
decimal = ({digit})+
hexadigit = {digit} | [a-fA-F]
hexadecimal = 0[xX]{hexadigit}+
floatingPoint = ({digit}+\.{digit}*)
scientificFloat = {floatingPoint}[Ee][+-]?{decimal}
floatingPointAll = {floatingPoint} | {scientificFloat}
inputCharacter = [^\r\n]
singleLineComment = \/\/{inputCharacter}*
multiLineComment = \/\*~\*\/
%%

<YYINITIAL> {
    {decimal}            {return new Symbol(TokenType.decimal, "T_INTLITERAL", yytext());}
    {floatingPointAll}   {return new Symbol(TokenType.floatingPoint, "T_DOUBLELITERAL", yytext());}
    {id} {return new Symbol(TokenType.id, "T_ID", yytext());}
    {whiteSpace} {return new Symbol(TokenType.WS , "" , yytext());}
    {singleLineComment} {return new Symbol(TokenType.comment, "", yytext());}
    {multiLineComment}  {return new Symbol(TokenType.comment, "", yytext());}
    "\"" {yybegin(STRING); string = "" + yytext();}
}
<STRING> {
    "\"" {yybegin(YYINITIAL);
          return new Symbol(TokenType.String, "T_STRINGLITERAL", string + yytext());}
     . {string = string + yytext();}
}


<<EOF>>             { return new Symbol( TokenType.EOF, "", "EOF"); }

