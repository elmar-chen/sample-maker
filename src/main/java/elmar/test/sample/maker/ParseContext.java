package elmar.test.sample.maker;

import elmar.test.sample.maker.parser.composite.RepeatParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
