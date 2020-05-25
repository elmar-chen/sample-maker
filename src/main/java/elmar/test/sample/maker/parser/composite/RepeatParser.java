package elmar.test.sample.maker.parser.composite;

import elmar.test.sample.maker.ParseContext;
import elmar.test.sample.maker.parser.Lexer;
import elmar.test.sample.maker.parser.Parser;

public class RepeatParser extends Parser {

    public static final int TAIL_PAD_POLICY_MUST_HAVE = 1;
    public static final int TAIL_PAD_POLICY_MUST_NOT_HAVE = 2;
    public static final int TAIL_PAD_POLICY_OPTIONAL = 4;
    
    
    private final Repeat repeat;
    private Class<?> target;
    
    private int successCount = 0;
    private int padPolicy;
    private Lexer pad;

    public RepeatParser(Class<?> target, Repeat repeat) {
        this.target = target;
        this.repeat = repeat;
    }
    
    public RepeatParser padsWith(Lexer pad, int policy){
        this.pad = pad;
        this.padPolicy = policy;
        return this;
    }
    public RepeatParser padsWith(Lexer pad){
        return padsWith(pad, TAIL_PAD_POLICY_OPTIONAL); 
    }
   
    
    public static RepeatParser create(Class<?> target, Repeat repeat) {
        return new RepeatParser( target, repeat);
    }
}
