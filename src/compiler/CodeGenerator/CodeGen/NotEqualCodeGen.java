package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.Exceptions.SemanticErrors.CalculationTypeMismatch;
import compiler.CodeGenerator.IDGenerator;
import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.SymbolTable;
import compiler.CodeGenerator.SymbolTable.Utility.CompileTimeDescriptor;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;
import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class NotEqualCodeGen {
    private static NotEqualCodeGen instance = new NotEqualCodeGen();
    public static NotEqualCodeGen getInstance(){return instance;}
    private NotEqualCodeGen(){}
    public void cgen(){
        Descriptor d2 = (Descriptor) SemanticStack.getInstance().popDescriptor();
        Descriptor d1 = (Descriptor) SemanticStack.getInstance().popDescriptor();
        CodeGen.getInstance().addToText("# Is " + d1.getName() + " not equal to " + d2.getName() + "? ");
        if ( d1.getType() != d2.getType() &&
                ( ( d1.getType() != Type.STRINGLITERAL && d2.getType() != Type.STRING )
                        && ( d1.getType() != Type.STRING && d2.getType() != Type.STRINGLITERAL ) )
        )
            throw new CalculationTypeMismatch("==", d1.getType(), d2.getType());
        if (d1.getType() == Type.INT
                || d1.getType() == Type.DOUBLE
                || d1.getType() == Type.BOOL
                || d1.getType() == Type.ARRAY
        ){
            Descriptor operationResult = new Descriptor(
                    "_" + IDGenerator.getInstance().getNextID(),
                    Type.BOOL
            );
            SymbolTable.getInstance().getSymbolTable().addEntry(operationResult.getName(), operationResult);
            CodeGen.getInstance().addToData(
                    operationResult.getName(),
                    Type.getMipsType(operationResult.getType()),
                    0
            );
            CodeGen.getInstance().addToText("lw " + "$a0, " + d1.getName());
            if ( d1.isFromArray() )
                CodeGen.getInstance().addToText( "lw $a0, 0($a0)" );
            CodeGen.getInstance().addToText("lw " + "$a1, " + d2.getName());
            if ( d2.isFromArray() )
                CodeGen.getInstance().addToText( "lw $a1, 0($a1)" );
            CodeGen.getInstance().addToText("sne $t0, $a0, $a1");
            CodeGen.getInstance().addToText("la " + "$a2, " + operationResult.getName());
            CodeGen.getInstance().addToText("sw $t0, 0($a2)");
            CodeGen.getInstance().addEmptyLine();
            SemanticStack.getInstance().pushDescriptor( operationResult );
        }
        else if ( d1.getType() == Type.STRING && d2.getType() == Type.STRING ) {
            Descriptor operationResult = new Descriptor(
                    "_" + IDGenerator.getInstance().getNextID(),
                    Type.BOOL
            );
            SymbolTable.getInstance().getSymbolTable().addEntry(operationResult.getName(), operationResult);
            CodeGen.getInstance().addToData(
                    operationResult.getName(),
                    Type.getMipsType(operationResult.getType()),
                    0
            );
            CodeGen.getInstance().addToText( "lw $s0, " + d1.getName() );
            if ( d1.isFromArray() )
                CodeGen.getInstance().addToText( "lw $s0, 0($s0)" );    // $s0 has the address of s1
            CodeGen.getInstance().addToText( "lw $s1, " + d2.getName() );
            if ( d2.isFromArray() )
                CodeGen.getInstance().addToText( "lw $s1, 0($s1)" );    // $s1 has the address of s2

            String stringNotEqualLoop = "_string_nonequality_check_loop_" + IDGenerator.getInstance().getNextID();
            String stringNotEqualLoopEnd = "_string_nonequality_check_loop_end_" + IDGenerator.getInstance().getNextID();
            String stringNotEqual = "_string_nonequality_yes_" + IDGenerator.getInstance().getNextID();
            String stringEqual = "_string_nonequality_no_" + IDGenerator.getInstance().getNextID();
            CodeGen.getInstance().addToText( stringNotEqualLoop + ": ", true );
            CodeGen.getInstance().addToText( "lb $s2, 0($s0)" );    // $s2 has current char of s1
            CodeGen.getInstance().addToText( "lb $s3, 0($s1)" );    // $s3 has current char of s2
            CodeGen.getInstance().addToText( "bne $s2, $s3, " + stringNotEqual );    // if chars are not equal, strings are not equal
            CodeGen.getInstance().addToText( "beq $s2, $zero, " + stringEqual ); // if we reached \0, strings are equal
            CodeGen.getInstance().addToText( "addi $s0, $s0, 1" );  // move $s0 forward
            CodeGen.getInstance().addToText( "addi $s1, $s1, 1" );  // move $s1 forward
            CodeGen.getInstance().addToText( "j " + stringNotEqualLoop );
            CodeGen.getInstance().addToText( stringNotEqual + ": ", true );
            CodeGen.getInstance().addToText( "li $t0, 1" );
            CodeGen.getInstance().addToText( "sw $t0, " + operationResult.getName() );
            CodeGen.getInstance().addToText( "j " + stringNotEqualLoopEnd );
            CodeGen.getInstance().addToText( stringEqual + ": ", true );
            CodeGen.getInstance().addToText( "sw $zero, " + operationResult.getName() );
            CodeGen.getInstance().addToText( "j " + stringNotEqualLoopEnd );
            CodeGen.getInstance().addToText( stringNotEqualLoopEnd +": ", true );
            CodeGen.getInstance().addEmptyLine();

            SemanticStack.getInstance().pushDescriptor( operationResult );
        }
        else if ( d1.getType() == Type.STRING && d2.getType() == Type.STRINGLITERAL ) {
            Descriptor operationResult = new Descriptor(
                    "_" + IDGenerator.getInstance().getNextID(),
                    Type.BOOL
            );
            SymbolTable.getInstance().getSymbolTable().addEntry(operationResult.getName(), operationResult);
            CodeGen.getInstance().addToData(
                    operationResult.getName(),
                    Type.getMipsType(operationResult.getType()),
                    0
            );
            CodeGen.getInstance().addToText( "lw $s0, " + d1.getName() );
            if ( d1.isFromArray() )
                CodeGen.getInstance().addToText( "lw $s0, 0($s0)" );    // $s0 has the address of s1
            CodeGen.getInstance().addToText( "la $s1, " + d2.getName() ); // $s1 has the address of s2

            String stringNotEqualLoop = "_string_nonequality_check_loop_" + IDGenerator.getInstance().getNextID();
            String stringNotEqualLoopEnd = "_string_nonequality_check_loop_end_" + IDGenerator.getInstance().getNextID();
            String stringNotEqual = "_string_nonequality_yes_" + IDGenerator.getInstance().getNextID();
            String stringEqual = "_string_nonequality_no_" + IDGenerator.getInstance().getNextID();
            CodeGen.getInstance().addToText( stringNotEqualLoop + ": ", true );
            CodeGen.getInstance().addToText( "lb $s2, 0($s0)" );    // $s2 has current char of s1
            CodeGen.getInstance().addToText( "lb $s3, 0($s1)" );    // $s3 has current char of s2
            CodeGen.getInstance().addToText( "bne $s2, $s3, " + stringNotEqual );    // if chars are not equal, strings are not equal
            CodeGen.getInstance().addToText( "beq $s2, $zero, " + stringEqual ); // if we reached \0, strings are equal
            CodeGen.getInstance().addToText( "addi $s0, $s0, 1" );  // move $s0 forward
            CodeGen.getInstance().addToText( "addi $s1, $s1, 1" );  // move $s1 forward
            CodeGen.getInstance().addToText( "j " + stringNotEqualLoop );
            CodeGen.getInstance().addToText( stringNotEqual + ": ", true );
            CodeGen.getInstance().addToText( "li $t0, 1" );
            CodeGen.getInstance().addToText( "sw $t0, " + operationResult.getName() );
            CodeGen.getInstance().addToText( "j " + stringNotEqualLoopEnd );
            CodeGen.getInstance().addToText( stringEqual + ": ", true );
            CodeGen.getInstance().addToText( "sw $zero, " + operationResult.getName() );
            CodeGen.getInstance().addToText( "j " + stringNotEqualLoopEnd );
            CodeGen.getInstance().addToText( stringNotEqualLoopEnd +": ", true );
            CodeGen.getInstance().addEmptyLine();

            SemanticStack.getInstance().pushDescriptor( operationResult );
        }
        else if ( d1.getType() == Type.STRINGLITERAL && d2.getType() == Type.STRING ) {
            Descriptor operationResult = new Descriptor(
                    "_" + IDGenerator.getInstance().getNextID(),
                    Type.BOOL
            );
            SymbolTable.getInstance().getSymbolTable().addEntry(operationResult.getName(), operationResult);
            CodeGen.getInstance().addToData(
                    operationResult.getName(),
                    Type.getMipsType(operationResult.getType()),
                    0
            );
            CodeGen.getInstance().addToText( "la $s0, " + d1.getName() ); // $s0 has the address of s1
            CodeGen.getInstance().addToText( "lw $s1, " + d2.getName() );
            if ( d2.isFromArray() )
                CodeGen.getInstance().addToText( "lw $s1, 0($s1)" );    // $s1 has the address of s2

            String stringNotEqualLoop = "_string_nonequality_check_loop_" + IDGenerator.getInstance().getNextID();
            String stringNotEqualLoopEnd = "_string_nonequality_check_loop_end_" + IDGenerator.getInstance().getNextID();
            String stringNotEqual = "_string_nonequality_yes_" + IDGenerator.getInstance().getNextID();
            String stringEqual = "_string_nonequality_no_" + IDGenerator.getInstance().getNextID();
            CodeGen.getInstance().addToText( stringNotEqualLoop + ": ", true );
            CodeGen.getInstance().addToText( "lb $s2, 0($s0)" );    // $s2 has current char of s1
            CodeGen.getInstance().addToText( "lb $s3, 0($s1)" );    // $s3 has current char of s2
            CodeGen.getInstance().addToText( "bne $s2, $s3, " + stringNotEqual );    // if chars are not equal, strings are not equal
            CodeGen.getInstance().addToText( "beq $s2, $zero, " + stringEqual ); // if we reached \0, strings are equal
            CodeGen.getInstance().addToText( "addi $s0, $s0, 1" );  // move $s0 forward
            CodeGen.getInstance().addToText( "addi $s1, $s1, 1" );  // move $s1 forward
            CodeGen.getInstance().addToText( "j " + stringNotEqualLoop );
            CodeGen.getInstance().addToText( stringNotEqual + ": ", true );
            CodeGen.getInstance().addToText( "li $t0, 1" );
            CodeGen.getInstance().addToText( "sw $t0, " + operationResult.getName() );
            CodeGen.getInstance().addToText( "j " + stringNotEqualLoopEnd );
            CodeGen.getInstance().addToText( stringEqual + ": ", true );
            CodeGen.getInstance().addToText( "sw $zero, " + operationResult.getName() );
            CodeGen.getInstance().addToText( "j " + stringNotEqualLoopEnd );
            CodeGen.getInstance().addToText( stringNotEqualLoopEnd +": ", true );
            CodeGen.getInstance().addEmptyLine();

            SemanticStack.getInstance().pushDescriptor( operationResult );
        }
        else if ( d1.getType() == Type.STRINGLITERAL && d2.getType() == Type.STRINGLITERAL ) {
            CompileTimeDescriptor temp1 = (CompileTimeDescriptor) d1;
            CompileTimeDescriptor temp2 = (CompileTimeDescriptor) d2;
            Descriptor operationResult = new Descriptor(
                    "_" + IDGenerator.getInstance().getNextID(),
                    Type.BOOL
            );
            SymbolTable.getInstance().getSymbolTable().addEntry(operationResult.getName(), operationResult);
            CodeGen.getInstance().addToData(
                    operationResult.getName(),
                    Type.getMipsType(operationResult.getType()),
                    0
            );
            if ( temp1.getValue().toString().equals( temp2.getValue().toString() ) )
                CodeGen.getInstance().addToText( "sw $zero, " + operationResult.getName() );
            else {
                CodeGen.getInstance().addToText( "li $t0, 1" );
                CodeGen.getInstance().addToText("sw $t0, " + operationResult.getName() );
            }
            CodeGen.getInstance().addEmptyLine();

            SemanticStack.getInstance().pushDescriptor( operationResult );
        }
    }
}
