package compiler.CodeGenerator.SymbolTable;

import compiler.CodeGenerator.Exceptions.SemanticErrors.InvalidBreakStatement;
import compiler.CodeGenerator.Exceptions.SemanticErrors.InvalidContinueStatement;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class LabelStack {
    private static LabelStack instance = new LabelStack();

    public static LabelStack getInstance() {return instance;}
    private Stack<ArrayList> labels;
    private LabelStack(){labels = new Stack<>();}
    public void pushLabel (String type, String name){
        ArrayList typeLabel = new ArrayList();
        typeLabel.add(type);
        typeLabel.add(name);
        labels.push(typeLabel);
    }
    public String popLabel(){
        ArrayList arr = labels.pop();
        return (String) arr.get(1);
    }
    public String popLabel(String type, int depth){
        int counter = 0;
        ArrayList res = new ArrayList();
        Stack<ArrayList> temp = new Stack<>();
        res = labels.pop();
        while(res.get(0) != type || depth != counter){
            if (res.get(0) == type) counter ++;
            temp.push(res);
            res = labels.pop();
        }
        labels.push(res);
        Iterator<ArrayList> iterator = temp.iterator();
        while(iterator.hasNext()){
            labels.push(temp.pop());
        }
        return (String) res.get(1);
    }
    public String popLabel (boolean isBreak){
        ArrayList res ;
        String pattern = isBreak ? "_end_of_loop" : "_loop_cond_";
        Stack<ArrayList> temp = new Stack();
        res = labels.pop();
        while(!res.get(1).toString().startsWith(pattern)){
            temp.push(res);
            try{
                res = labels.pop();
            }
            catch (Exception e){
                if(isBreak)
                    throw new InvalidBreakStatement();
                else
                    throw new InvalidContinueStatement();
            }

        }
        labels.push(res);
        Iterator<ArrayList> iterator = temp.iterator();
        while(iterator.hasNext()){
            labels.push(temp.pop());
        }
        return (String) res.get(1);
    }
}
