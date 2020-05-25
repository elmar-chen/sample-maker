package elmar.test.sample.maker.parser.composite;

import java.util.ArrayList;
import java.util.List;

import elmar.test.sample.maker.ParseContext;
import elmar.test.sample.maker.parser.Parser;

public class ChoicesParser extends Parser {

    private List<Parser> children = new ArrayList<>();

    public static ChoicesParser startChoices() {
        return new ChoicesParser();
    }

    public static ChoicesParser start() {
        return new ChoicesParser();
    }


    public ChoicesParser add(Parser parser) {
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
