package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;

public class LessCodeGen {
    private static LessCodeGen ourInstance = new LessCodeGen();
    public static LessCodeGen getInstance () {return ourInstance;}
    private LessCodeGen(){}
    public void cgen(){
        // azin is working
        Descriptor d2 = SemanticStack.getInstance().popDescriptor();
    }
}
