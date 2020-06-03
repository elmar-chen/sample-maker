package elmar.test.sample.maker.parser;

import elmar.test.sample.maker.ParseContext;

public class RegExpLexer implements Lexer<String> {
	private String pattern;

	public RegExpLexer(String regex) {
		this.pattern = regex;
	}

	@Override
	public String extract(ParseContext context) {
		return context.readRegExp(pattern);
	}
}
