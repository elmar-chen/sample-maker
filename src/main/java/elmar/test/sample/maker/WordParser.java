package elmar.test.sample.maker;

public class WordParser {
    private static final int FAIL = 0;
    private static final int DONE = 1;
    private static final int CONTINUE = 2;

    StringBuffer result = new StringBuffer();

    public int consume(char c, ParseContext context) {

        boolean error = false;
        boolean valid = Character.isJavaIdentifierStart(c)
                || (result.length() > 0 && Character.isJavaIdentifierPart(c));
        if (valid) {
            result.append(c);
        } else {
            error = !Character.isWhitespace(c);
        }
        if (error)
            return FAIL;
        return result.length() > 0 ? CONTINUE | DONE : CONTINUE;
    }
}
