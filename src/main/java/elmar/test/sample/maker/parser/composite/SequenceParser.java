package elmar.test.sample.maker.parser.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import elmar.test.sample.maker.Quotation;
import elmar.test.sample.maker.parser.Lexer;
import elmar.test.sample.maker.parser.Parser;
import elmar.test.sample.maker.parser.RegExpLexer;

public class SequenceParser<T> extends Parser<T> {

    private List<Parser<?>> children = new ArrayList<>();

    public SequenceParser(Class<T> class1) {
        // TODO Auto-generated constructor stub
    }

    public List<Parser<?>> getChildren() {
        
        return children;
    }

    public <OT, FT> void  addLexer(BiConsumer<OT, FT> setter, RegExpLexer regExpLexer) {
        // TODO Auto-generated method stub
        
    }
    
    public <OT, FT> void  addLexer(BiConsumer<OT, FT> setter, RegExpLexer regExpLexer, Function<String, FT> converter) {
        // TODO Auto-generated method stub
        
    }

    public void addPad(RegExpLexer regExpLexer) {
        // TODO Auto-generated method stub
        
    }

    public void add(Class<?> clazz) {
        // TODO Auto-generated method stub
        
    }

    public void add(Parser<?> parer) {
        
    }

    public void setWrapper(Lexer before, Lexer after) {
        // TODO Auto-generated method stub
        
    }
    
    
}
