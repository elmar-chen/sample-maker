package elmar.test.sample.maker.parser.composite;

import elmar.test.sample.maker.parser.Parser;

public class RepeatParser<T> extends Parser<T> {


    
    
    private Repeat repeat;
    private  Parser<T> parser;
    
    public Repeat getRepeat() {
        return repeat;
    }
    public Parser<T> getParser() {
        return parser;
    }
    
   
}
