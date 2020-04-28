package elmar.test.sample.maker;

public class RegExpLexer extends Lexer{
    private String regex;

    public RegExpLexer(String regex) {
        this.regex = regex;
    }

    @Override
    protected boolean isValid(CharSequence previous, char c) {
        String str = previous.toString()+c;
        return str.matches(regex);
    }
}
