package elmar.test.sample.maker.parser;

import elmar.test.sample.maker.parser.composite.ChoicesParser;
import elmar.test.sample.maker.parser.composite.Repeat;
import elmar.test.sample.maker.parser.composite.RepeatParser;
import elmar.test.sample.maker.parser.composite.SequenceParser;

public class ExpressionParserBuilder extends ParserBuilder {
    public Parser build() {

        return RepeatParser.build(buildParts(), Repeat.ANY_NUMBER);
    }

    private Parser buildParts() {
        return RepeatParser.build(new PartParserBuilder(), Repeat.ONE_OR_MORE);
    }

    public static class PartParserBuilder extends ParserBuilder {

        @Override
        public Parser build() {
            SequenceParser unQuotedPart = SequenceParser.start()
                    .add(enumPart(), Repeat.EXACTLY_ONE)
                    .add(distributionPart(), Repeat.ZERO_OR_ONE);
            return ChoicesParser.start()
                    .add(quotedParts())
                    .add(unQuotedPart);
        }

        private Parser enumPart() {
            Parser enumItem = ChoicesParser.start()
                    .add(new RegExpLexer("<[A-Za-z_ -]+>"))
                    .add(new RegExpLexer(""));
            return SequenceParser.start()
                    .add(new RegExpLexer("["), Repeat.EXACTLY_ONE)
                    .add(enumItem, Repeat.ONE_OR_MORE)
                    .add(new RegExpLexer("["), Repeat.EXACTLY_ONE);

        }

        private Parser distributionPart() {
            return SequenceParser.start()
                    .add(new RegExpLexer("[0-9]+"), Repeat.EXACTLY_ONE)
                    .add(new RegExpLexer("%"), Repeat.ZERO_OR_ONE);
        }

        private Parser quotedParts() {
            return SequenceParser.start()
                    .add(new RegExpLexer("("), Repeat.EXACTLY_ONE)
                    .add(new PartParserBuilder(), Repeat.ONE_OR_MORE)
                    .add(new RegExpLexer(")"), Repeat.EXACTLY_ONE);
        }
    }
}
