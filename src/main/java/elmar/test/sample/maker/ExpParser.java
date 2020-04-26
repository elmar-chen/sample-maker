package elmar.test.sample.maker;

public class ExpParser {
    String line;

    public void parse() {

    }

}

class LabelParser {
    public void consume(char c, ParseContext context) {

    }
}

class ParseContext {
    private String source;
    private int pos = -1;

    public char nextChar() {
        return source.charAt(++pos);
    }

    public void rewind() {
        pos--;
    }

}