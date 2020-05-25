package elmar.test.sample.maker.parser;

import elmar.test.sample.maker.ParseContext;

public abstract class Lexer extends Parser {

    StringBuffer buff = new StringBuffer();

  
    protected abstract boolean isValid(CharSequence previous, char c);
}

