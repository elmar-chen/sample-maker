package elmar.test.sample.maker.parser;

import elmar.test.sample.maker.parser.composite.ChoicesParser;
import elmar.test.sample.maker.parser.composite.Repeat;
import elmar.test.sample.maker.parser.composite.RepeatParser;
import elmar.test.sample.maker.parser.composite.SequenceParser;

public class ExpressionParserBuilder extends ParserBuilder{
    public Parser build() {

        return RepeatParser.build(buildParts(), Repeat.ANY_NUMBER);
    }

    private Parser buildParts() {
        return RepeatParser.build(new PartParserBuilder(), Repeat.ONE_OR_MORE);
    }

    private Parser quotedParts() {
        return SequenceParser.start()
                .add(new RegExpLexer("("), Repeat.EXACTLY_ONE)
                .add(new PartParserBuilder(), Repeat.ONE_OR_MORE)
                .add(new RegExpLexer(")"), Repeat.EXACTLY_ONE);
    }

    public static class PartParserBuilder extends ParserBuilder {

        @Override
        public Parser build() {
            return null;
        }
    }
}
