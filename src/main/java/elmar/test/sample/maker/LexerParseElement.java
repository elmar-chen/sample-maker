package elmar.test.sample.maker;

import elmar.test.sample.maker.parser.Lexer;

public class LexerParseElement<T> extends ParseElement {
	Lexer<T> lexer;

	public Lexer<T> getLexer() {
		return lexer;
	}

}
