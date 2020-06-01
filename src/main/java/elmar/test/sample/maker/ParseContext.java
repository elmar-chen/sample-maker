package elmar.test.sample.maker;

import java.util.HashMap;
import java.util.Map;

import elmar.test.sample.maker.parser.composite.RepeatParser;

public class ParseContext {

    private int pos = -1;
    private String content;
    
    Map<String, Object> variables = new HashMap<String, Object>();

    public ParseContext(String content) {
        this.content = content;

    }

    public void putLexerResult(StringBuffer buff) {

    }

    public void fail() {
    }

    public void pushParser(RepeatParser repeatParser) {
    }

    public int getInt(String identNumOfSpace, int def) {
        Object oVal = variables.get(identNumOfSpace);
        if (oVal instanceof Integer) {
            return (Integer) oVal;
            
        }
        return def;
    }
}
