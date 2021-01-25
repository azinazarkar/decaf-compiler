package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.IDGenerator;
import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;

public class WhileCodeGen {
    private static WhileCodeGen instance = new WhileCodeGen();
    public static WhileCodeGen getInstance() {return instance;}
    private WhileCodeGen(){}
    public void cgen(){
        Descriptor condition = (Descriptor) SemanticStack.getInstance().popDescriptor();
        String endOfWhileLabel = IDGenerator.getInstance().getNextID();
        String whileLabel = IDGenerator.getInstance().getNextID();
        SemanticStack.getInstance().pushDescriptor(endOfWhileLabel);
        SemanticStack.getInstance().pushDescriptor(whileLabel);
        // TODO optimization (reloading can be omitted)
        CodeGen.getInstance().addToText(whileLabel, true);
        CodeGen.getInstance().addToText("# while statement");
        CodeGen.getInstance().addToText("lw " + "$a0, " + condition.getName());
        CodeGen.getInstance().addToText("beq " +  "$a0, " + "0, " + endOfWhileLabel);
        CodeGen.getInstance().addEmptyLine();
    }
}
