package compiler.CodeGenerator.SymbolTable;

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
        System.out.println("push " + name);
        labels.push(typeLabel);
    }
    public String popLabel(){
        ArrayList arr = labels.pop();
        System.out.println("pop " + arr.get(1));
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
}
