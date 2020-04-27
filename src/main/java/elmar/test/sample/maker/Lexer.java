package elmar.test.sample.maker;

public abstract class Lexer implements Parser {
    private static final int FAIL = 0;
    private static final int DONE = 1;
    private static final int CONTINUE = 2;


    StringBuffer buff = new StringBuffer();

    @Override
    public void parse(ParseContext context) {
        while (true) {
            char c = context.nextChar();
            boolean valid = isValid(buff, c);

            if (valid) {
                buff.append(c);
            } else {
                context.rewind();
                break;
            }
        }
        if (buff.length() > 0) {
            context.putLexerResult(buff);
        } else {
            context.fail();
        }
    }

    protected abstract boolean isValid(CharSequence previous, char c);
}

