package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.IDGenerator;
import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.LabelStack;
import compiler.CodeGenerator.SymbolTable.SymbolTable;
import compiler.CodeGenerator.SymbolTable.SymbolTableNode;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;

public class WhileCodeGen {
    private static WhileCodeGen instance = new WhileCodeGen();
    public static WhileCodeGen getInstance() {return instance;}
    private WhileCodeGen(){}
    public void cgen(){
        Descriptor condition = (Descriptor) SemanticStack.getInstance().popDescriptor();
        SymbolTableNode temp = SymbolTable.getInstance().getSymbolTable();
        String endOfWhileLabel = "_end_of_loop_while" + "_" + IDGenerator.getInstance().getNextID();
        LabelStack.getInstance().pushLabel("while", endOfWhileLabel);
        CodeGen.getInstance().addToText("# while statement");
        CodeGen.getInstance().addToText("lw " + "$a0, " + condition.getName());
        if (condition.isFromArray())
            CodeGen.getInstance().addToText( "lw $a0, 0($a0)" );
        CodeGen.getInstance().addToText("beq " +  "$a0, " + "0, " + endOfWhileLabel);
        CodeGen.getInstance().addEmptyLine();
    }
}
