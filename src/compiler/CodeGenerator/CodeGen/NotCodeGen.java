package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.Exceptions.InvalidOperator;
import compiler.CodeGenerator.IDGenerator;
import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.SymbolTable;
import compiler.CodeGenerator.SymbolTable.Utility.Descriptor;
import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class NotCodeGen {
    private static NotCodeGen instance = new NotCodeGen();
    public static NotCodeGen getInstance(){return instance;}
    private NotCodeGen(){}
    public void cgen(){
        Descriptor d1 = (Descriptor) SemanticStack.getInstance().popDescriptor();
        CodeGen.getInstance().addToText( "#not " + d1.getName() );
        if (d1.getType() != Type.BOOL )
            throw new InvalidOperator("!" , d1.getType());
        Descriptor operationResult = new Descriptor(
                "_" + IDGenerator.getInstance().getNextID(),
                Type.BOOL,
                !(boolean)d1.getValue()
        );
        SymbolTable.getInstance().getSymbolTable().addEntry(operationResult.getName(), operationResult);
        CodeGen.getInstance().addToText("lw " + "$a0, " + d1.getName());
        CodeGen.getInstance().addToText("not $t0, $a0");
        CodeGen.getInstance().addToText("la " + "$a1, " + operationResult.getName());
        CodeGen.getInstance().addToText("sw $t0, 0($a1)");
        CodeGen.getInstance().addEmptyLine();
        SemanticStack.getInstance().pushDescriptor( operationResult );
    }
}
