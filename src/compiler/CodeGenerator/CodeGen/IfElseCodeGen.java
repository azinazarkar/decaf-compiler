package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.IDGenerator;
import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;

public class IfElseCodeGen {
    private static IfElseCodeGen instance = new IfElseCodeGen();
    public static IfElseCodeGen getInstance() {return instance;}
    private IfElseCodeGen(){}
    public void cgen(){
        Descriptor condition = (Descriptor) SemanticStack.getInstance().popDescriptor();
        String elseLabel = IDGenerator.getInstance().getNextID();
        String endLabel = IDGenerator.getInstance().getNextID();
        // TODO optimization (reloading can be omitted)
        CodeGen.getInstance().addToText("# if statement");
        CodeGen.getInstance().addToText("lw " + "$a0, " + condition.getName());
        CodeGen.getInstance().addToText("beq " +  "$a0, " + "0, " + elseLabel);
        CodeGen.getInstance().addEmptyLine();
        SemanticStack.getInstance().pushDescriptor(endLabel);
        SemanticStack.getInstance().pushDescriptor(elseLabel);
    }
}
