package elmar.test.sample.maker.parser.composite;

import java.util.ArrayList;
import java.util.List;

import elmar.test.sample.maker.ParseContext;
import elmar.test.sample.maker.parser.Parser;

public class CompositeParser extends Parser {

    private List<Parser> children = new ArrayList<>();

    public CompositeParser addChild(Parser parser) {
        children.add(parser);
        return this;
    }

    @Override
    public boolean parse(ParseContext context) {
        return false;
    }


}
