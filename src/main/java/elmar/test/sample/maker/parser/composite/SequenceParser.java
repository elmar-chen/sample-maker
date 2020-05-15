package elmar.test.sample.maker.parser.composite;

import elmar.test.sample.maker.ParseContext;
import elmar.test.sample.maker.parser.Lexer;
import elmar.test.sample.maker.parser.Parser;

import java.util.ArrayList;
import java.util.List;

public class SequenceParser implements Parser {

    private List<Parser> children = new ArrayList<>();

    public static SequenceParser startChoices() {
        return new SequenceParser();
    }

    public static SequenceParser start() {
        return new SequenceParser();
    }


    public SequenceParser add(Parser parser) {
        children.add(parser);
        return this;
    }
    public SequenceParser addOptional(Parser parser) {
        children.add(ParserBuilder.repeat(parser, Repeat.ZERO_OR_ONE));
        return this;
    }
    public SequenceParser repeat(Parser parser, Lexer pad, Repeat repeat) {
        SequenceParser seq = new SequenceParser();
        
        return this;
    }
    
 

    
    
    
    @Override
    public boolean parse(ParseContext context) {
        return false;
    }

    public static enum ChildrenGroupType {
        CHOOSE, SEQUENCE
    }

}
