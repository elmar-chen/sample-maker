package elmar.test.sample.maker.parser;

import elmar.test.sample.maker.parser.composite.Repeat;
import elmar.test.sample.maker.parser.composite.ParserBuilder;

public class StatementParserBuilder extends elmar.test.sample.maker.parser.ParserBuilder {

    public Parser build(){


        ParserBuilder quotationPart = ParserBuilder.start()
                .add(new RegExpLexer("~"), Repeat.EXACTLY_ONE)
                .add(new QuotationParser(), Repeat.EXACTLY_ONE);

        ParserBuilder leftPart = ParserBuilder.start()
                .add(new NameLexer(), Repeat.ZERO_OR_ONE)
                .add(quotationPart, Repeat.ZERO_OR_ONE);

        Parser rightPart = new ExpressionParserBuilder().build();

        return ParserBuilder.start()
                .add(leftPart, Repeat.ZERO_OR_ONE)
                .add(new RegExpLexer(":"), Repeat.EXACTLY_ONE)
                .add(rightPart, Repeat.ZERO_OR_ONE);
    }
}

