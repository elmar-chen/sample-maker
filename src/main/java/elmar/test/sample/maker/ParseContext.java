package elmar.test.sample.maker;

import elmar.test.sample.maker.parser.composite.RepeatParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ParseContext {

    private BufferedReader source;
    private StringBuilder buff = new StringBuilder();
    private int pos = -1;

    public ParseContext(InputStream src) {
        source = new BufferedReader(new InputStreamReader(src));

    }

    public char nextChar() throws IOException {
        char[] buff = new char[1];
        int n = source.read(buff);
        return n>0 ? buff[0] : (char) -1;
    }

    public void putLexerResult(StringBuffer buff) {

    }

    public void fail() {
    }

    public void pushParser(RepeatParser repeatParser) {
    }
}
