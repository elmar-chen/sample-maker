package elmar.test.sample.maker.parser.composite;

import java.util.ArrayList;
import java.util.List;

import elmar.test.sample.maker.ParseContext;
import elmar.test.sample.maker.parser.Parser;

public class SequenceParser extends Parser {

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
  
 

    
    
    
    @Override
    public boolean parse(ParseContext context) {
        return false;
    }

    public static enum ChildrenGroupType {
        CHOOSE, SEQUENCE
    }

}
