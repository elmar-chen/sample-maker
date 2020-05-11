package elmar.test.sample.maker.parser;

import elmar.test.sample.maker.ParseContext;
import elmar.test.sample.maker.parser.composite.ChoicesParser;
import elmar.test.sample.maker.parser.composite.ParserBuilder;
import elmar.test.sample.maker.parser.composite.Repeat;
import elmar.test.sample.maker.parser.composite.SequenceParser;

public class ExpressionParserBuilder extends ParserBuilder {

    @Override
    public Parser build() {
        return ParserBuilder.repeat(new PartParserBuilder(), Repeat.ONE_OR_MORE);
    }

    public static class PartParserBuilder extends elmar.test.sample.maker.parser.ParserBuilder {

        @Override
        public Parser build() {
            SequenceParser unQuotedPart = ParserBuilder.sequence().add(enumPart()).addOptional(distributionPart());
            return ChoicesParser.start().add(quotedParts()).add(unQuotedPart);
        }

        private Parser enumPart() {
            Parser enumItem = ParserBuilder.branch(new RegExpLexer("<[A-Za-z_ -]+>"), new RegExpLexer(""));

            return ParserBuilder.sequence(new RegExpLexer("[")).repeat(enumItem, Repeat.ONE_OR_MORE)
                    .add(new RegExpLexer("]"));

        }

        private Parser distributionPart() {
            return ParserBuilder.sequence().add(new RegExpLexer("[0-9]+")).addOptional(new RegExpLexer("%"));
        }

        private Parser quotedParts() {
            return ParserBuilder.sequence().add(new RegExpLexer("("))
                    .repeat(new PartParserBuilder(), Repeat.ONE_OR_MORE).add(new RegExpLexer(")"));
        }
    }

    @Override
    public boolean parse(ParseContext context) {
    }
}
