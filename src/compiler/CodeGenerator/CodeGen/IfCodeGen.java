package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.IDGenerator;
import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.LabelStack;
import compiler.CodeGenerator.SymbolTable.SymbolTable;
import compiler.CodeGenerator.SymbolTable.SymbolTableNode;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;

public class IfCodeGen {
    private static IfCodeGen instance = new IfCodeGen();
    public static IfCodeGen getInstance() {return instance;}
    private IfCodeGen(){}
    public void cgen(){
        Descriptor condition = (Descriptor) SemanticStack.getInstance().popDescriptor();
        SymbolTableNode temp = SymbolTable.getInstance().getSymbolTable();
        String endLabel = "ifEnd" + "_" + IDGenerator.getInstance().getNextID();
        String elseLabel = "ifElse" + "_" + IDGenerator.getInstance().getNextID();
        CodeGen.getInstance().addToText("# if statement");
        CodeGen.getInstance().addToText("lw " + "$a0, " + condition.getName());
        if ( condition.isFromArray() )
            CodeGen.getInstance().addToText( "lw $a0, 0($a0)" );
        CodeGen.getInstance().addToText("beq " +  "$a0, " + "0, " + elseLabel);
        CodeGen.getInstance().addEmptyLine();
        LabelStack.getInstance().pushLabel("if", endLabel);
        LabelStack.getInstance().pushLabel("if", elseLabel);
    }
}
