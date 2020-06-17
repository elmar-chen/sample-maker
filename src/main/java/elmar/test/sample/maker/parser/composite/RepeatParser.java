package elmar.test.sample.maker.parser.composite;

import elmar.test.sample.maker.parser.Parser;
import elmar.test.sample.maker.parser.RegExpLexer;

public class RepeatParser<T> extends Parser<T> {


    
    
    private StaticRepeat repeat;
    private int padPolicy;
    private RegExpLexer pad;
    
    public RegExpLexer getPad() {
        return pad;
    }
    public int getPadPolicy() {
        return padPolicy;
    }
    
    public RepeatParser(Class<T> target) { 
        this.target = target;
    }

    public StaticRepeat getRepeat() {
        return repeat;
    }
    
    public void setRepeat(StaticRepeat repeat) {
        this.repeat = repeat;
    }

    public void padsWith(RegExpLexer pad, int padPolicy) {
        this.pad=pad;
        this.padPolicy = padPolicy;
    }
   
}
