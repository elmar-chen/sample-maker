package elmar.test.sample.maker.parser.composite;

import elmar.test.sample.maker.ParseContext;
import elmar.test.sample.maker.parser.Parser;

public abstract class ParserBuilder implements Parser {

    public static SequenceParser sequence(Parser... parsers) {
        SequenceParser sequenceParser = new SequenceParser();
        for (Parser parser: parsers) {
            sequenceParser.add(parser);
        }
        return sequenceParser;
    }
    public static ChoicesParser branch(Parser... parsers) {
        ChoicesParser choicesParser = new ChoicesParser();
        for (Parser parser: parsers) {
            choicesParser.add(parser);
        }
        return choicesParser;
    }

    public static RepeatParser repeat(Parser parser, Repeat repeat) {
        return new RepeatParser(parser, repeat);
    }
    
    @Override
    public void parse(ParseContext context) {
            build().parse(context);
    }

    public abstract Parser build();
}
