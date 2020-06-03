package elmar.test.sample.maker.parser;

import elmar.test.sample.maker.ParseContext;

public interface Lexer<T> {


    public abstract T extract(ParseContext context);

}
