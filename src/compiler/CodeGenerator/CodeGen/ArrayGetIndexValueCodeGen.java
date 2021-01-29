package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.Exceptions.SemanticErrors.InvalidOperator;
import compiler.CodeGenerator.IDGenerator;
import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.SymbolTable;
import compiler.CodeGenerator.SymbolTable.Utility.ArrayDescriptor;
import compiler.CodeGenerator.SymbolTable.Utility.ArrayType;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;
import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class ArrayGetIndexValueCodeGen {
	private static ArrayGetIndexValueCodeGen ourInstance = new ArrayGetIndexValueCodeGen();

	public static ArrayGetIndexValueCodeGen getInstance() {
		return ourInstance;
	}

	private ArrayGetIndexValueCodeGen() {
	}

	public void cgen() {
		Descriptor temp = (Descriptor) SemanticStack.getInstance().popDescriptor();
		Descriptor index = (Descriptor) SemanticStack.getInstance().popDescriptor();
		if ( ! (temp instanceof ArrayDescriptor) )
			throw new InvalidOperator( "[]", temp.getType() );
		ArrayDescriptor lv = (ArrayDescriptor) temp;
		if ( index.getType() != Type.INT )
			throw new InvalidOperator( "[]", index.getType() );

		Descriptor returnValue = null;
		if ( lv.getDimensionCount() == 1 ) {
			returnValue = new Descriptor(
					"_" + IDGenerator.getInstance().getNextID(),
					lv.getSubType(),
					true
			);
		}
		else {
			returnValue = new ArrayDescriptor(
					"_" + IDGenerator.getInstance().getNextID(),
					new ArrayType( lv.getSubType(), lv.getDimensionCount() - 1 ),
					true
			);
		}

		SymbolTable.getInstance().getSymbolTable().addEntry(returnValue.getName(), temp);
		CodeGen.getInstance().addToData(returnValue.getName(), Type.getMipsType(returnValue.getType()), 0);

		String invalidIndex = "_invalid_array_index_" + IDGenerator.getInstance().getNextID();
		String fetchEnd = "_retrieve_array_value_" + IDGenerator.getInstance().getNextID();
		CodeGen.getInstance().addToText( "# Getting " + index.getName() + "-th cell of array " + lv.getName() );
		CodeGen.getInstance().addToText( "lw $s0, " + lv.getName() ); // load array address into $s0
		if ( lv.isFromArray() )
			CodeGen.getInstance().addToText( "lw $s0, 0($s0)" );
		CodeGen.getInstance().addToText( "lw $s1, " + index.getName() ); // load index into $s1
		CodeGen.getInstance().addToText( "lw $s2, 0($s0)" ); // size of array is in $s2
		CodeGen.getInstance().addToText( "li $s4, 1" ); // load 1 into $s4 to sub from size (checking index)
		CodeGen.getInstance().addToText( "sub $s2, $s2, $s4" ); // decrease size of array by one (for index checking)
		CodeGen.getInstance().addToText( "blt $s2, $s1, " + invalidIndex );
		CodeGen.getInstance().addToText( "li $s4, 4" ); // we have to multiply index by 4 (but not yet)
		CodeGen.getInstance().addToText( "mult $s4, $s1" ); // multiply index by 4
		CodeGen.getInstance().addToText( "mflo $s1" ); // $s1 now has index * 4
		CodeGen.getInstance().addToText( "addi $s0, $s0, 4" ); // add 4 to $s0 to begin from the first cell (0-th cell is the size)
		CodeGen.getInstance().addToText( "add $s0, $s0, $s1" ); // $s0 has address of the wanted byte
		CodeGen.getInstance().addToText( "sw $s0, " + returnValue.getName() ); // save answer in returnValue address
		CodeGen.getInstance().addToText( "j " + fetchEnd );
		CodeGen.getInstance().addToText( invalidIndex + ": ", true );
		CodeGen.getInstance().addToText( "li $v0, 4" );
		CodeGen.getInstance().addToText( "la $a0, _array_index_illegal" );
		CodeGen.getInstance().addToText( "syscall" );
		CodeGen.getInstance().addToText( "li $v0, 10" );
		CodeGen.getInstance().addToText( "syscall" );
		CodeGen.getInstance().addToText( fetchEnd + ": ", true );
		CodeGen.getInstance().addEmptyLine();
		SemanticStack.getInstance().pushDescriptor( returnValue );
	}

}
