package elmar.test.sample.maker.parser;

import elmar.test.sample.maker.ParseContext;

public abstract class Lexer<T> {

    StringBuffer buff = new StringBuffer();

    protected abstract T extract(ParseContext context);

}
