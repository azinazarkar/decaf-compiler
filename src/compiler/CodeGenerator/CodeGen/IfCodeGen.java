package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.IDGenerator;
import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;
import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class IfCodeGen {
    private static IfCodeGen instance = new IfCodeGen();
    public static IfCodeGen getInstance() {return instance;}
    private IfCodeGen(){}
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
