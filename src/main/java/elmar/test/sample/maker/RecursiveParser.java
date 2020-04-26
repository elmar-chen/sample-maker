package elmar.test.sample.maker;

import java.util.List;
import java.util.Stack;

public abstract class RecursiveParser {
    
    abstract List<RecursiveParser> getChoice();
    
    Stack<Object[]> results = new Stack<Object[]>();
    public Object parse() {
        for (RecursiveParser chioce : getChoice()) {
            
        }
        return null;
    }
    class ParseContext {
        
    }
}
