package elmar.test.sample.maker.parser.composite;

import elmar.test.sample.maker.ParseContext;
import elmar.test.sample.maker.parser.Parser;

import java.util.ArrayList;
import java.util.List;

public class SequenceParser implements Parser {

    private List<Parser> children = new ArrayList<>();

    public static SequenceParser start() {
        return new SequenceParser();
    }

    public SequenceParser add(Parser parser, Repeat repeat) {
        children.add(RepeatParser.build(parser, repeat));
        return this;
    }

    @Override
    public void parse(ParseContext context) {

    }
}
