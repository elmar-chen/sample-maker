package elmar.test.sample.maker;

import java.util.List;

import elmar.test.sample.maker.PadPolicy.PadBefore;
import elmar.test.sample.maker.parser.Lexer;
import lombok.Data;

public abstract class Enumeration {

	@Data
	@Pattern("(expression)")
	public static class QuotatedExpression extends Enumeration {
		Expression expression;
	}

	@Data
	@Pattern("[tokens]")
	public static class MultiTokenEnumeration extends Enumeration {
		List<EnumerationToken> tokens;
	}

	@Data
	@Pattern("start - end")
	public static class EnumerationToken extends Enumeration {

		@RegExp(imp = EnumerationTokenLexer.class)
		@AtLeastOne
		private String start;
		
	    @AtLeastOne 
	    @PadBefore(PadPolicy.ALWAYS)
		@RegExp(imp = EnumerationTokenLexer.class)
		private String end;

	}

	public static class EnumerationTokenLexer implements Lexer<Character> {

		@Override
		public Character extract(ParseContext context) {
			String text = null;
			if ((text = context.readRegExpAndReplace("\\u([0-9]{4})", "$1")) != null) {
				int code = Integer.parseInt(text.substring(2), 16);
				return new Character((char) code);
			}
			if ((text = context.readRegExpAndReplace("\\(.)", "$1")) != null) {
				return text.charAt(0);
			}
			char c = context.readChar();
			if ("-[]".indexOf(c) < 0) {
				return c;
			} else {
				context.unread(1);
				return null;
			}
		}

	}
}
