package elmar.test.sample.maker;

public class ParseContext {
    private String source;
    private int pos = -1;

    public char nextChar() {
        return source.charAt(++pos);
    }

    public void rewind() {
        pos--;
    }

    public void putLexerResult(StringBuffer buff) {

    }

    public void fail() {
    }
}
