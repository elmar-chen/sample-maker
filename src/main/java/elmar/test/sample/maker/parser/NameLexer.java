package elmar.test.sample.maker.parser;

public class NameLexer extends Lexer {

    @Override
    protected boolean isValid(CharSequence previous, char c) {
        boolean first = previous.length()== 0;
        boolean valid = (first && Character.isJavaIdentifierStart(c));
        valid = valid || (!first && Character.isJavaIdentifierPart(c));
        return valid;
    }
}
