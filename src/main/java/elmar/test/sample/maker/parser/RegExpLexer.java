package elmar.test.sample.maker.parser;

import elmar.test.sample.maker.ParseContext;

public class RegExpLexer implements Lexer {
	private String pattern;

	public RegExpLexer(String regex) {
		this.pattern = regex;
	}

	@Override
	public String readText(ParseContext context) {
		return context.readRegExp(pattern);
	}
}
