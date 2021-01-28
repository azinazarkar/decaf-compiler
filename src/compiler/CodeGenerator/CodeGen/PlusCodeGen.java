package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.Exceptions.SemanticErrors.CalculationTypeMismatch;
import compiler.CodeGenerator.Exceptions.SemanticErrors.InvalidOperator;
import compiler.CodeGenerator.IDGenerator;
import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.SymbolTable;
import compiler.CodeGenerator.SymbolTable.Utility.CompileTimeDescriptor;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;
import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class PlusCodeGen {
	private static PlusCodeGen ourInstance = new PlusCodeGen();

	public static PlusCodeGen getInstance() {
		return ourInstance;
	}

	private PlusCodeGen() {
	}

	public void cgen() {
		Descriptor e2 = (Descriptor) SemanticStack.getInstance().popDescriptor();
		Descriptor e1 = (Descriptor) SemanticStack.getInstance().popDescriptor();
		CodeGen.getInstance().addToText( "# Adding " + e1.getName() + " and " + e2.getName() );
		if ( e1.getType() != e2.getType() &&
				( ( e1.getType() != Type.STRINGLITERAL && e2.getType() != Type.STRING )
				&& ( e1.getType() != Type.STRING && e2.getType() != Type.STRINGLITERAL ) )
		)
			throw new CalculationTypeMismatch( "+", e1.getType(), e2.getType() );
		if ( e1.getType() != e2.getType() ) {
			boolean b1 = e1.getType() == Type.STRINGLITERAL && e2.getType() == Type.STRING;
			boolean b2 = e1.getType() == Type.STRING && e2.getType() == Type.STRINGLITERAL;
			if ( !b1 && !b2 )
				throw new InvalidOperator("+", Type.BOOL);
		}
//		if ( e1.getType() == Type.BOOL)
//			throw new InvalidOperator("+", Type.BOOL);
		if ( e1.getType() == Type.INT ) {
			Descriptor temp = new Descriptor(
					"_" + IDGenerator.getInstance().getNextID(),
					Type.INT
			);
			SymbolTable.getInstance().getSymbolTable().addEntry(temp.getName(), temp);
			CodeGen.getInstance().addToData(temp.getName(), Type.getMipsType(temp.getType()), 0);
			CodeGen.getInstance().addToText("lw " + "$a0, " + e1.getName());
			if ( e1.isFromArray() )
				CodeGen.getInstance().addToText( "lw $a0, 0($a0)" );
			CodeGen.getInstance().addToText("lw " + "$a1, " + e2.getName());
			if ( e2.isFromArray() )
				CodeGen.getInstance().addToText( "lw $a1, 0($a1)" );
			CodeGen.getInstance().addToText("add $t0, $a0, $a1");
			CodeGen.getInstance().addToText("la " + "$a2, " + temp.getName());
			CodeGen.getInstance().addToText("sw $t0, 0($a2)");
			CodeGen.getInstance().addEmptyLine();
			SemanticStack.getInstance().pushDescriptor( temp );
		}
		else if ( e1.getType() == Type.DOUBLE ) {
			Descriptor temp = new Descriptor(
					"_" + IDGenerator.getInstance().getNextID(),
					Type.DOUBLE
			);
			SymbolTable.getInstance().getSymbolTable().addEntry(temp.getName(), temp);
			CodeGen.getInstance().addToData(temp.getName(), Type.getMipsType(temp.getType()), 0);
			CodeGen.getInstance().addToData(temp.getName(), Type.getMipsType(temp.getType()), 0);
			CodeGen.getInstance().addToText( "lw $a0, " + e1.getName() );
			if ( e1.isFromArray() )
				CodeGen.getInstance().addToText( "lw $a0, 0($a0)" );
			CodeGen.getInstance().addToText( "mtc1 $a0, $f0" );
			CodeGen.getInstance().addToText( "lw $a1, " + e2.getName() );
			if ( e2.isFromArray() )
				CodeGen.getInstance().addToText( "lw $a1, 0($a1)" );
			CodeGen.getInstance().addToText( "mtc1 $a1, $f1" );
			CodeGen.getInstance().addToText( "add.s $f2, $f0, $f1" );
			CodeGen.getInstance().addToText( "la $a0, " + temp.getName() );
			CodeGen.getInstance().addToText( "swc1 $f2, 0($a0)" );
			CodeGen.getInstance().addEmptyLine();
			SemanticStack.getInstance().pushDescriptor( temp );
		}
		else if ( e1.getType() == Type.STRINGLITERAL && e2.getType() == Type.STRINGLITERAL ) {
			CompileTimeDescriptor d1 = (CompileTimeDescriptor) e1;
			CompileTimeDescriptor d2 = (CompileTimeDescriptor) e2;
			CompileTimeDescriptor temp = new CompileTimeDescriptor(
					"_" + IDGenerator.getInstance().getNextID(),
					Type.STRINGLITERAL,
					d1.getValue().toString().substring( 0, d1.getValue().toString().length() - 1 )
							+ d2.getValue().toString()
			);
			SymbolTable.getInstance().getSymbolTable().addEntry(temp.getName(), temp);
			CodeGen.getInstance().addToData(temp.getName(), Type.getMipsType(temp.getType()), 0);
			SemanticStack.getInstance().pushDescriptor( temp );
			StringLiteralCodeGen.getInstance().cgen();
			SemanticStack.getInstance().pushDescriptor( temp );
		}
		else if ( e1.getType() == Type.STRING && e2.getType() == Type.STRING ) {
			Descriptor temp = new Descriptor(
					"_" + IDGenerator.getInstance().getNextID(),
					Type.STRING
			);
			SymbolTable.getInstance().getSymbolTable().addEntry(temp.getName(), temp);
			CodeGen.getInstance().addToData(temp.getName(), Type.getMipsType(temp.getType()), 0);

			// get size of s1
			String getStringSizeLoop = "_getting_string_size_loop_" + IDGenerator.getInstance().getNextID();
			String getStringSizeEnd = "_getting_string_size_done_" + IDGenerator.getInstance().getNextID();
			CodeGen.getInstance().addToText( "li $a0, 0" ); // $a0 will have the size of the string
			CodeGen.getInstance().addToText( "lw $s0, " + e1.getName() );   // $s0 has address of the string
			CodeGen.getInstance().addToText( getStringSizeLoop + ": ", true );
			CodeGen.getInstance().addToText( "lb $s1, 0($s0)" );    // $s1 has the current char of string
			CodeGen.getInstance().addToText( "beqz $s1, " + getStringSizeEnd ); // if current char is \0, we're done
			CodeGen.getInstance().addToText( "addi $s0, $s0, 1" );  // increase index by one
			CodeGen.getInstance().addToText( "addi $a0, $a0, 1" );  // increase size by one
			CodeGen.getInstance().addToText( "j " + getStringSizeLoop );    // continue loop
			CodeGen.getInstance().addToText( getStringSizeEnd + ": ", true ); // now we have size of s1 in $a0

			// get size of s2
			getStringSizeLoop = "_getting_string_size_loop_" + IDGenerator.getInstance().getNextID();
			getStringSizeEnd = "_getting_string_size_done_" + IDGenerator.getInstance().getNextID();
			CodeGen.getInstance().addToText( "li $a1, 0" ); // $a1 will have the size of the string
			CodeGen.getInstance().addToText( "lw $s0, " + e2.getName() );   // $s0 has address of the string
			CodeGen.getInstance().addToText( getStringSizeLoop + ": ", true );
			CodeGen.getInstance().addToText( "lb $s1, 0($s0)" );    // $s1 has the current char of string
			CodeGen.getInstance().addToText( "beqz $s1, " + getStringSizeEnd ); // if current char is \0, we're done
			CodeGen.getInstance().addToText( "addi $s0, $s0, 1" );  // increase index by one
			CodeGen.getInstance().addToText( "addi $a1, $a1, 1" );  // increase size by one
			CodeGen.getInstance().addToText( "j " + getStringSizeLoop );    // continue loop
			CodeGen.getInstance().addToText( getStringSizeEnd + ": ", true ); // now we have size of s2 in $a1

			// allocate space for the new string
			CodeGen.getInstance().addToText( "add $a0, $a0, $a1" ); // size of new string is sum of two strings' sizes
			CodeGen.getInstance().addToText( "addi $a0, $a0, 1" );  // + a byte for \0
			CodeGen.getInstance().addToText( "li $v0, 9" );
			CodeGen.getInstance().addToText( "syscall" );
			CodeGen.getInstance().addToText( "sw $v0, " + temp.getName() );

			// copy s1 into the new space
			String stringCopyLoop = "_copy_string_loop_" + IDGenerator.getInstance().getNextID();
			String stringCopyLoopEnd = "_copy_string_loop_end_" + IDGenerator.getInstance().getNextID();
			CodeGen.getInstance().addToText( "lw $a0, " + temp.getName() ); // $a0 has the address of result string
			CodeGen.getInstance().addToText( "lw $s0, " + e1.getName() );   // $s0 has the address of s1
			CodeGen.getInstance().addToText( stringCopyLoop + ": ", true );
			CodeGen.getInstance().addToText( "lb $s1, 0($s0)" );    // $s1 has the current char of the string
			CodeGen.getInstance().addToText( "beqz $s1, " + stringCopyLoopEnd );
			CodeGen.getInstance().addToText( "sb $s1, 0($a0)" );    // saving the current char to corresponding index of result
			CodeGen.getInstance().addToText( "addi $a0, $a0, 1" );
			CodeGen.getInstance().addToText( "addi $s0, $s0, 1" );
			CodeGen.getInstance().addToText( "j " + stringCopyLoop );
			CodeGen.getInstance().addToText( stringCopyLoopEnd + ": ", true );

			// copy s2 into the new space
			stringCopyLoop = "_copy_string_loop_" + IDGenerator.getInstance().getNextID();
			stringCopyLoopEnd = "_copy_string_loop_end_" + IDGenerator.getInstance().getNextID();
			CodeGen.getInstance().addToText( "lw $s0, " + e2.getName() );   // $s0 has the address of s2
			CodeGen.getInstance().addToText( stringCopyLoop + ": ", true );
			CodeGen.getInstance().addToText( "lb $s1, 0($s0)" );    // $s1 has the current char of the string
			CodeGen.getInstance().addToText( "beqz $s1, " + stringCopyLoopEnd );
			CodeGen.getInstance().addToText( "sb $s1, 0($a0)" );    // saving the current char to corresponding index of result
			CodeGen.getInstance().addToText( "addi $a0, $a0, 1" );
			CodeGen.getInstance().addToText( "addi $s0, $s0, 1" );
			CodeGen.getInstance().addToText( "j " + stringCopyLoop );
			CodeGen.getInstance().addToText( stringCopyLoopEnd + ": ", true );

			CodeGen.getInstance().addToText( "sb $zero, 0($a0)" ); // add \0 at the end of the string

			CodeGen.getInstance().addEmptyLine();

			SemanticStack.getInstance().pushDescriptor( temp );
		}
		else if ( e1.getType() == Type.STRINGLITERAL && e2.getType() == Type.STRING ) {
			CompileTimeDescriptor s1 = (CompileTimeDescriptor) e1;
			Descriptor temp = new Descriptor(
					"_" + IDGenerator.getInstance().getNextID(),
					Type.STRING
			);
			SymbolTable.getInstance().getSymbolTable().addEntry(temp.getName(), temp);
			CodeGen.getInstance().addToData(temp.getName(), Type.getMipsType(temp.getType()), 0);

			// get size of s1
			CodeGen.getInstance().addToText( "li $a0, " + (s1.getValue().toString().length() - 1) );// now we have size of s1 in $a0

			// get size of s2
			String getStringSizeLoop = "_getting_string_size_loop_" + IDGenerator.getInstance().getNextID();
			String getStringSizeEnd = "_getting_string_size_done_" + IDGenerator.getInstance().getNextID();
			CodeGen.getInstance().addToText( "li $a1, 0" ); // $a1 will have the size of the string
			CodeGen.getInstance().addToText( "lw $s0, " + e2.getName() );   // $s0 has address of the string
			CodeGen.getInstance().addToText( getStringSizeLoop + ": ", true );
			CodeGen.getInstance().addToText( "lb $s1, 0($s0)" );    // $s1 has the current char of string
			CodeGen.getInstance().addToText( "beqz $s1, " + getStringSizeEnd ); // if current char is \0, we're done
			CodeGen.getInstance().addToText( "addi $s0, $s0, 1" );  // increase index by one
			CodeGen.getInstance().addToText( "addi $a1, $a1, 1" );  // increase size by one
			CodeGen.getInstance().addToText( "j " + getStringSizeLoop );    // continue loop
			CodeGen.getInstance().addToText( getStringSizeEnd + ": ", true ); // now we have size of s2 in $a1

			// allocate space for the new string
			CodeGen.getInstance().addToText( "add $a0, $a0, $a1" ); // size of new string is sum of two strings' sizes
			CodeGen.getInstance().addToText( "addi $a0, $a0, 1" );  // + a byte for \0
			CodeGen.getInstance().addToText( "li $v0, 9" );
			CodeGen.getInstance().addToText( "syscall" );
			CodeGen.getInstance().addToText( "sw $v0, " + temp.getName() );

			// copy s1 into the new space
			CodeGen.getInstance().addToText( "lw $a0, " + temp.getName() ); // $a0 has the address of the result string
			String s1Value = s1.getValue().toString();
			int size = s1Value.length();
			for ( int i = 0; i < size - 1; i++ ) {
				char currentChar = s1Value.charAt( i );
				CodeGen.getInstance().addToText( "li $s1, \'" + currentChar + "\'" );
				CodeGen.getInstance().addToText( "sb $s1, 0($a0)" );
				CodeGen.getInstance().addToText( "addi $a0, $a0, 1" );
			}

			// copy s2 into the new space
			String stringCopyLoop = "_copy_string_loop_" + IDGenerator.getInstance().getNextID();
			String stringCopyLoopEnd = "_copy_string_loop_end_" + IDGenerator.getInstance().getNextID();
			CodeGen.getInstance().addToText( "lw $s0, " + e2.getName() );   // $s0 has the address of s2
			CodeGen.getInstance().addToText( stringCopyLoop + ": ", true );
			CodeGen.getInstance().addToText( "lb $s1, 0($s0)" );    // $s1 has the current char of the string
			CodeGen.getInstance().addToText( "beqz $s1, " + stringCopyLoopEnd );
			CodeGen.getInstance().addToText( "sb $s1, 0($a0)" );    // saving the current char to corresponding index of result
			CodeGen.getInstance().addToText( "addi $a0, $a0, 1" );
			CodeGen.getInstance().addToText( "addi $s0, $s0, 1" );
			CodeGen.getInstance().addToText( "j " + stringCopyLoop );
			CodeGen.getInstance().addToText( stringCopyLoopEnd + ": ", true );

			CodeGen.getInstance().addToText( "sb $zero, 0($a0)" ); // add \0 at the end of the string

			CodeGen.getInstance().addEmptyLine();

			SemanticStack.getInstance().pushDescriptor( temp );

		}
		else if ( e1.getType() == Type.STRING && e2.getType() == Type.STRINGLITERAL ) {

			CompileTimeDescriptor s2 = (CompileTimeDescriptor) e2;
			Descriptor temp = new Descriptor(
					"_" + IDGenerator.getInstance().getNextID(),
					Type.STRING
			);
			SymbolTable.getInstance().getSymbolTable().addEntry(temp.getName(), temp);
			CodeGen.getInstance().addToData(temp.getName(), Type.getMipsType(temp.getType()), 0);

			// get size of s1
			String getStringSizeLoop = "_getting_string_size_loop_" + IDGenerator.getInstance().getNextID();
			String getStringSizeEnd = "_getting_string_size_done_" + IDGenerator.getInstance().getNextID();
			CodeGen.getInstance().addToText( "li $a0, 0" ); // $a0 will have the size of the string
			CodeGen.getInstance().addToText( "lw $s0, " + e1.getName() );   // $s0 has address of the string
			CodeGen.getInstance().addToText( getStringSizeLoop + ": ", true );
			CodeGen.getInstance().addToText( "lb $s1, 0($s0)" );    // $s1 has the current char of string
			CodeGen.getInstance().addToText( "beqz $s1, " + getStringSizeEnd ); // if current char is \0, we're done
			CodeGen.getInstance().addToText( "addi $s0, $s0, 1" );  // increase index by one
			CodeGen.getInstance().addToText( "addi $a0, $a0, 1" );  // increase size by one
			CodeGen.getInstance().addToText( "j " + getStringSizeLoop );    // continue loop
			CodeGen.getInstance().addToText( getStringSizeEnd + ": ", true ); // now we have size of s1 in $a0

			// get size of s2
			CodeGen.getInstance().addToText( "li $a1, " + (s2.getValue().toString().length() - 1) );// now we have size of s2 in $a1

			// allocate space for the new string
			CodeGen.getInstance().addToText( "add $a0, $a0, $a1" ); // size of new string is sum of two strings' sizes
			CodeGen.getInstance().addToText( "addi $a0, $a0, 1" );  // + a byte for \0
			CodeGen.getInstance().addToText( "li $v0, 9" );
			CodeGen.getInstance().addToText( "syscall" );
			CodeGen.getInstance().addToText( "sw $v0, " + temp.getName() );

			// copy s1 into the new space
			String stringCopyLoop = "_copy_string_loop_" + IDGenerator.getInstance().getNextID();
			String stringCopyLoopEnd = "_copy_string_loop_end_" + IDGenerator.getInstance().getNextID();
			CodeGen.getInstance().addToText( "lw $a0, " + temp.getName() ); // $a0 has the address of result string
			CodeGen.getInstance().addToText( "lw $s0, " + e1.getName() );   // $s0 has the address of s1
			CodeGen.getInstance().addToText( stringCopyLoop + ": ", true );
			CodeGen.getInstance().addToText( "lb $s1, 0($s0)" );    // $s1 has the current char of the string
			CodeGen.getInstance().addToText( "beqz $s1, " + stringCopyLoopEnd );
			CodeGen.getInstance().addToText( "sb $s1, 0($a0)" );    // saving the current char to corresponding index of result
			CodeGen.getInstance().addToText( "addi $a0, $a0, 1" );
			CodeGen.getInstance().addToText( "addi $s0, $s0, 1" );
			CodeGen.getInstance().addToText( "j " + stringCopyLoop );
			CodeGen.getInstance().addToText( stringCopyLoopEnd + ": ", true );

			// copy s2 into the new space
			String s1Value = s2.getValue().toString();
			int size = s1Value.length();
			for ( int i = 0; i < size - 1; i++ ) {
				char currentChar = s1Value.charAt( i );
				CodeGen.getInstance().addToText( "li $s1, \'" + currentChar + "\'" );
				CodeGen.getInstance().addToText( "sb $s1, 0($a0)" );
				CodeGen.getInstance().addToText( "addi $a0, $a0, 1" );
			}

			CodeGen.getInstance().addToText( "sb $zero, 0($a0)" ); // add \0 at the end of the string

			CodeGen.getInstance().addEmptyLine();

			SemanticStack.getInstance().pushDescriptor( temp );

		}
	}

}
