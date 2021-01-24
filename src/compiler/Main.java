package compiler;

public class Main {

	public static void main(String[] args) throws Exception {
		Compiler.getInstance().compile( "src/compiler/program.txt" );
		System.out.println("OK");
	}
}