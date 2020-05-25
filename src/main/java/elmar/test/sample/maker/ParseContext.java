package elmar.test.sample.maker;

import elmar.test.sample.maker.parser.composite.RepeatParser;

public class ParseContext {

    private int pos = -1;
    private String content;

    public ParseContext(String content) {
        this.content = content;

    }

    public void putLexerResult(StringBuffer buff) {

    }

    public void fail() {
    }

    public void pushParser(RepeatParser repeatParser) {
    }
}
