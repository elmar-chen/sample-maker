package elmar.test.sample.maker.parser;

import elmar.test.sample.maker.ParseContext;

public abstract class ParserBuilder implements Parser{
    public abstract Parser build();

    @Override
    public boolean parse(ParseContext context) {
        build().parse(context);
    }
}
