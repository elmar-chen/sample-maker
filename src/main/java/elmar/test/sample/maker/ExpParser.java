package elmar.test.sample.maker;

import java.util.Stack;

public class ExpParser {
    private static final int START = -1;
    private Stack<Integer> statuses = new Stack<>();
    private Stack<Exp> results = new Stack<>();
    private String input;
    private int inputIndex;
    
    public Exp parse(String line) {
        String[] parts = line.split(":", 2);
        parseLabelAndPercile(parts[0]);
        if(parts.length>1) parseExp(parts[1].trim());
        
       
        return null;
    }

    private void parseExp(String line) {
        statuses.push(START);
        if(line.charAt(0)=='[') {
            parseSelect();
        }
    }

    private void parseSelect() {
        
    }

    private void parseLabelAndPercile(String label) {
        String[] parts = label.split("~");
        getCurrentExp().label = parts[0].trim(); 
        getCurrentExp().proportionExp = parts[1].trim();
    }

    private Exp getCurrentExp() {
        return results.peek();
    }


    public static void main(String[] args) {
        System.out.println(":aaa".split(":")[0]+">");
    }
    
}
