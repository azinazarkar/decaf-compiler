package compiler.CodeGenerator.Exceptions.SemanticErrors;

import compiler.CodeGenerator.Exceptions.SemanticError;
import compiler.CodeGenerator.SymbolTable.Utility.Type;

public class InvalidOperator extends SemanticError {

    private String operator;
    private Type t;

    public InvalidOperator( String operator, Type t ) {
        this.operator = operator;
        this.t = t;
    }

    @Override
    public String getMessage() {
        return "Operator " + this.operator + " is invalid for type " + t.name() + "!";
    }
}
