package compiler.CodeGenerator.SymbolTable;

import java.lang.reflect.Array;
import java.util.ArrayList;
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
    public ArrayList popLabel(){
        return labels.pop();
    }
    public String popLabel(String type){
        ArrayList res = new ArrayList();
        res = labels.pop();
        while(res.get(0) != type){
            res = labels.pop();
        }
        return (String) res.get(1);
    }
}
