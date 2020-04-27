package elmar.test.sample.maker;

import java.util.List;
import java.util.Stack;

public class RecursiveParser {

    public static RecursiveParser start() {
        return new RecursiveParser();
    }

    List<RecursiveParser> getChoice(){
        return null;
    }
    
    Stack<Object[]> results = new Stack<Object[]>();
    public Object parse() {
        for (RecursiveParser chioce : getChoice()) {
            
        }
        return null;
    }
    class ParseContext {
        
    }
}
