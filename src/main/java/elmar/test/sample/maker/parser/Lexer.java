package elmar.test.sample.maker.parser;

import elmar.test.sample.maker.ParseContext;

public abstract class Lexer implements Parser {

    StringBuffer buff = new StringBuffer();

    @Override
    public void parse(ParseContext context) {
    }

    protected abstract boolean isValid(CharSequence previous, char c);
}

