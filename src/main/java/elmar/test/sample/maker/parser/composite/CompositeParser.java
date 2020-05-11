package elmar.test.sample.maker.parser.composite;

import elmar.test.sample.maker.ParseContext;
import elmar.test.sample.maker.parser.Parser;

import java.util.ArrayList;
import java.util.List;

public class CompositeParser implements Parser {

    private List<Parser> children = new ArrayList<>();

    public CompositeParser addChild(Parser parser) {
        children.add(parser);
        return this;
    }

    @Override
    public boolean parse(ParseContext context) {

    }


}
