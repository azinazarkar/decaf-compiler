package compiler.CodeGenerator.Exceptions.SemanticErrors;

import compiler.CodeGenerator.Exceptions.SemanticError;
import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class InvalidContinueStatement extends SemanticError {

    @Override
    public String getMessage() {
        return "Invalid continue statement";
    }
}
