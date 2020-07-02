package elmar.test.sample.maker;

import java.util.List;

import elmar.test.sample.maker.annotations.AtLeastOne;
import elmar.test.sample.maker.annotations.Lex;
import elmar.test.sample.maker.annotations.PadPolicy;
import elmar.test.sample.maker.annotations.PadPolicy.PadBefore;
import elmar.test.sample.maker.annotations.Template;
import elmar.test.sample.maker.parser.Lexer;
import lombok.Data;

public abstract class Enumeration {

	@Data
	@Template("(expression)")
	public static class QuotatedExpression extends Enumeration {
		Expression expression;
	}

	@Data
	@Template("[tokens]")
	public static class MultiTokenEnumeration extends Enumeration {
		List<EnumerationToken> tokens;
	}

	@Data
	@Template("start - end")
	public static class EnumerationToken extends Enumeration {

		@Lex(imp = EnumerationTokenLexer.class)
		@AtLeastOne
		private String start;
		
	    @AtLeastOne 
	    @PadBefore(PadPolicy.ALWAYS)
		@Lex(imp = EnumerationTokenLexer.class)
		private String end;

	}

	public static class EnumerationTokenLexer implements Lexer {

		@Override
		public String readText(ParseContext context) {
//			String text = null;
//			if ((text = context.readRegExpAndReplace("\\u([0-9]{4})", "$1")) != null) {
//				int code = Integer.parseInt(text.substring(2), 16);
//				return new Character((char) code);
//			}
//			if ((text = context.readRegExpAndReplace("\\(.)", "$1")) != null) {
//				return text.charAt(0);
//			}
//			char c = context.readChar();
//			if ("-[]".indexOf(c) < 0) {
//				return c;
//			} else {
//				context.unread(1);
//				return null;
//			}
			return null;
		}

	}
}
