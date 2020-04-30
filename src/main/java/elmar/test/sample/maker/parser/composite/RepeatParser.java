package elmar.test.sample.maker.parser.composite;

import elmar.test.sample.maker.ParseContext;
import elmar.test.sample.maker.parser.Parser;

public class RepeatParser implements Parser {

    private final Repeat repeat;
    private Parser parser;

    public RepeatParser(Parser parser, Repeat repeat) {
        this.parser = parser;
        this.repeat = repeat;
    }

    public static RepeatParser build(Parser parser, Repeat repeat) {
        return new RepeatParser(parser, repeat);
    }

    @Override
    public void parse(ParseContext context) {

    }

}
