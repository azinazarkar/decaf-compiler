package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.IDGenerator;
import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.LabelStack;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;

public class ForCodeGen {
    private static ForCodeGen instance = new ForCodeGen();
    private ForCodeGen(){}

    public static ForCodeGen getInstance() {
        return instance;
    }
    public void cgen(){
        Descriptor condition = (Descriptor) SemanticStack.getInstance().popDescriptor();
        String endOfFor = "_end_of_loop_for_" + IDGenerator.getInstance().getNextID();
        CodeGen.getInstance().addToText("# for statement");
        CodeGen.getInstance().addToText("lw " + "$a0, " + condition.getName());
        if (condition.isFromArray())
            CodeGen.getInstance().addToText( "lw $a0, 0($a0)" );
        CodeGen.getInstance().addToText("beq " +  "$a0, " + "0, " + endOfFor);
        LabelStack.getInstance().pushLabel("for", endOfFor);
    }
}
