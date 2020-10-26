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
	VOID, INT, DOUBLE, BOOL, STRING, CLASS, INTERFACE, NULL, THIS,
	EXTENDS, IMPLEMENTS, FOR, WHILE, IF, ELSE, RETURN, BREAK, CONTINUE, NEW,
	NEWARRAY, PRINT, READINTEGER, READLINE, DTOI, ITOD, BTOI, ITOB,
	PRIVATE, PROTECTED, PUBLIC,
	PLUS, MINUS, MULTIPLY, DIVIDE, MOD, LESS, LESSEQUAL, GREATER, GREATEREQUAL, ASSIGN, EQUAL, NOTEQUAL,
	AND, OR, NOT, SEMICOLON, COMMA, DOT,
	OPENCURLYBRACES, CLOSECURLYBRACES, OPENPARENTHESIS, CLOSEPARENTHESIS, OPENBRACKET, CLOSEBRACKET,
	DECIMAL, FLOATINGPOINT, ID, EOF, WS, STRINGLITERAL, BOOLEANLITERAL, COMMENT
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
whiteSpace = {endLine} | [ \t\f]
decimal = ({digit})+
hexadigit = {digit} | [a-fA-F]
hexadecimal = 0[xX]({hexadigit})+
floatingPoint = ({digit}+\.{digit}*)
scientificFloat = {floatingPoint}[Ee][+-]?{decimal}
floatingPointAll = {floatingPoint} | {scientificFloat}
inputCharacter = [^\r\n]
singleLineComment = \/\/{inputCharacter}*
multiLineComment = \/\*~\*\/
%%

<YYINITIAL> {

	"void"          {return new Symbol(TokenType.VOID, "void", yytext()); }
	"int"           {return new Symbol(TokenType.INT, "int", yytext()); }
	"double"        {return new Symbol(TokenType.DOUBLE, "double", yytext()); }
	"bool"          {return new Symbol(TokenType.BOOL, "bool", yytext()); }
	"string"        {return new Symbol(TokenType.STRING, "string", yytext()); }
	"class"         {return new Symbol(TokenType.CLASS, "class", yytext()); }
	"interface"     {return new Symbol(TokenType.INTERFACE, "interface", yytext()); }
	"null"          {return new Symbol(TokenType.NULL, "null", yytext()); }
	"this"          {return new Symbol(TokenType.THIS, "this", yytext()); }
	"extends"       {return new Symbol(TokenType.EXTENDS, "extends", yytext()); }
	"implements"    {return new Symbol(TokenType.IMPLEMENTS, "implements", yytext()); }
	"for"           {return new Symbol(TokenType.FOR, "for", yytext()); }
	"while"         {return new Symbol(TokenType.WHILE, "while", yytext()); }
	"if"            {return new Symbol(TokenType.IF, "if", yytext()); }
	"else"          {return new Symbol(TokenType.ELSE, "else", yytext()); }
	"return"        {return new Symbol(TokenType.RETURN, "return", yytext()); }
	"break"         {return new Symbol(TokenType.BREAK, "break", yytext()); }
	"continue"      {return new Symbol(TokenType.CONTINUE, "continue", yytext()); }
	"new"           {return new Symbol(TokenType.NEW, "new", yytext()); }
	"NewArray"      {return new Symbol(TokenType.NEWARRAY, "NewArray", yytext()); }
	"Print"         {return new Symbol(TokenType.PRINT, "Print", yytext()); }
	"ReadInteger"   {return new Symbol(TokenType.READINTEGER, "ReadInteger", yytext()); }
	"ReadLine"      {return new Symbol(TokenType.READLINE, "ReadLine", yytext()); }
	"dtoi"          {return new Symbol(TokenType.DTOI, "dtoi", yytext()); }
	"itod"          {return new Symbol(TokenType.ITOD, "itod", yytext()); }
	"btoi"          {return new Symbol(TokenType.BTOI, "btoi", yytext()); }
	"itob"          {return new Symbol(TokenType.ITOB, "itob", yytext()); }
	"private"       {return new Symbol(TokenType.PRIVATE, "private", yytext()); }
	"protected"     {return new Symbol(TokenType.PROTECTED, "protected", yytext()); }
	"public"        {return new Symbol(TokenType.PUBLIC, "public", yytext()); }
	"true"          {return new Symbol(TokenType.BOOLEANLITERAL, "T_BOOLEANLITERAL", yytext()); }
	"false"          {return new Symbol(TokenType.BOOLEANLITERAL, "T_BOOLEANLITERAL", yytext()); }


	"+"     {return new Symbol(TokenType.PLUS, "+", yytext()); }
	"-"     {return new Symbol(TokenType.MINUS, "-", yytext()); }
	"*"     {return new Symbol(TokenType.MULTIPLY, "*", yytext()); }
	"/"     {return new Symbol(TokenType.DIVIDE, "/", yytext()); }
	"%"     {return new Symbol(TokenType.MOD, "%", yytext()); }
	"<"     {return new Symbol(TokenType.LESS, "<", yytext()); }
	"<="    {return new Symbol(TokenType.LESSEQUAL, "<=", yytext()); }
	">"     {return new Symbol(TokenType.GREATER, ">", yytext()); }
	">="    {return new Symbol(TokenType.GREATEREQUAL, ">=", yytext()); }
	"="     {return new Symbol(TokenType.ASSIGN, "=", yytext()); }
	"=="    {return new Symbol(TokenType.EQUAL, "==", yytext()); }
	"!="    {return new Symbol(TokenType.NOTEQUAL, "!=", yytext()); }
	"&&"    {return new Symbol(TokenType.AND, "&&", yytext()); }
	"||"    {return new Symbol(TokenType.OR, "||", yytext()); }
	"!"     {return new Symbol(TokenType.NOT, "!", yytext()); }
	";"     {return new Symbol(TokenType.SEMICOLON, ";", yytext()); }
	","     {return new Symbol(TokenType.COMMA, ",", yytext()); }
	"."     {return new Symbol(TokenType.DOT, ".", yytext()); }

	"{"    {return new Symbol(TokenType.OPENCURLYBRACES, "{", yytext()); }
	"}"    {return new Symbol(TokenType.CLOSECURLYBRACES, "}", yytext()); }
	"("    {return new Symbol(TokenType.OPENPARENTHESIS, "(", yytext()); }
	")"    {return new Symbol(TokenType.CLOSEPARENTHESIS, ")", yytext()); }
	"["    {return new Symbol(TokenType.OPENBRACKET, "[", yytext()); }
	"]"    {return new Symbol(TokenType.CLOSEBRACKET, "]", yytext()); }


	{decimal}               {return new Symbol(TokenType.DECIMAL, "T_INTLITERAL", yytext());}
	{floatingPointAll}      {return new Symbol(TokenType.FLOATINGPOINT, "T_DOUBLELITERAL", yytext());}
	{hexadecimal}           {return new Symbol(TokenType.DECIMAL, "T_INTLITERAL", yytext()); }
	{id}                    {return new Symbol(TokenType.ID, "T_ID", yytext());}
	{whiteSpace}            {return new Symbol(TokenType.WS , "" , yytext());}
	{singleLineComment}     {return new Symbol(TokenType.COMMENT, "", yytext());}
	{multiLineComment}      {return new Symbol(TokenType.COMMENT, "", yytext());}
	"\""                    {yybegin(STRING); string = "" + yytext();}
}
<STRING> {
	"\""    {
		yybegin(YYINITIAL);
		return new Symbol(TokenType.STRINGLITERAL, "T_STRINGLITERAL", string + yytext());
		}
	 .      {string = string + yytext();}
}


<<EOF>>             { return new Symbol( TokenType.EOF, "", "EOF"); }

