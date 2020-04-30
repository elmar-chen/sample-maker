package elmar.test.sample.maker.parser;

import elmar.test.sample.maker.parser.composite.Repeat;
import elmar.test.sample.maker.parser.composite.SequenceParser;

public class StatementParserBuilder implements ParserBuilder {

    public Parser build(){


        SequenceParser quotationPart = SequenceParser.start()
                .add(new RegExpLexer("~"), Repeat.EXACTLY_ONE)
                .add(new QuotationParser(), Repeat.EXACTLY_ONE);

        SequenceParser leftPart = SequenceParser.start()
                .add(new NameLexer(), Repeat.ZERO_OR_ONE)
                .add(quotationPart, Repeat.ZERO_OR_ONE);

        Parser rightPart = new ExpressionParserBuilder().build();

        return SequenceParser.start()
                .add(leftPart, Repeat.ZERO_OR_ONE)
                .add(new RegExpLexer(":"), Repeat.EXACTLY_ONE)
                .add(rightPart, Repeat.ZERO_OR_ONE);
    }
}

