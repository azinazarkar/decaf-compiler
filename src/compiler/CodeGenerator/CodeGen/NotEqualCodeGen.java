package compiler.CodeGenerator.CodeGen;

import compiler.CodeGenerator.Exceptions.CalculationTypeMismatch;
import compiler.CodeGenerator.IDGenerator;
import compiler.CodeGenerator.SemanticStack;
import compiler.CodeGenerator.SymbolTable.SymbolTable;
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
        if (d1.getType() != d2.getType())
            throw new CalculationTypeMismatch("!=", d1.getType(), d2.getType());
        if (d1.getType() == Type.INT){
            Descriptor operationResult = new Descriptor(
                    "_" + IDGenerator.getInstance().getNextID(),
                    Type.BOOL,
                    (int)d1.getValue() != (int)d2.getValue()
            );
            SymbolTable.getInstance().getSymbolTable().addEntry(operationResult.getName(), operationResult);
            CodeGen.getInstance().addToText("lw " + "$a0, " + d1.getName());
            CodeGen.getInstance().addToText("lw " + "$a1, " + d2.getName());
            CodeGen.getInstance().addToText("sne $t0, $a0, $a1");
            CodeGen.getInstance().addToText("la " + "$a2, " + operationResult.getName());
            CodeGen.getInstance().addToText("sw $t0, 0($a2)");
            CodeGen.getInstance().addEmptyLine();
            SemanticStack.getInstance().pushDescriptor( operationResult );
        }
        else if (d1.getType() == Type.BOOL){
            Descriptor operationResult = new Descriptor(
                    "_" + IDGenerator.getInstance().getNextID(),
                    Type.BOOL,
                    (boolean)d1.getValue() != (boolean)d2.getValue()
            );
            SymbolTable.getInstance().getSymbolTable().addEntry(operationResult.getName(), operationResult);
            CodeGen.getInstance().addToText("lw " + "$a0, " + d1.getName());
            CodeGen.getInstance().addToText("lw " + "$a1, " + d2.getName());
            CodeGen.getInstance().addToText("sne $t0, $a0, $a1");
            CodeGen.getInstance().addToText("la " + "$a2, " + operationResult.getName());
            CodeGen.getInstance().addToText("sw $t0, 0($a2)");
            CodeGen.getInstance().addEmptyLine();
            SemanticStack.getInstance().pushDescriptor( operationResult );
        }
    }
}
