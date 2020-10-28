package elmar.test.sample.maker.internal.model;

import elmar.test.sample.maker.ParseContext;
import elmar.test.sample.maker.ParseStatus;

public class WrapperLeftPartGrammarNode extends LexerGrammarNode {
	public static final String LEFT_CHARS = "([{<\"`";;
	public static final String RIGHT_CHARS = ")]}>\"`";

	private char left = (char) -1;
	
	
	@Override
	public boolean read(ParseContext context, char c) {
		if(LEFT_CHARS.indexOf(c)>=0)  {
			left = c;
			return true;
		}
		return false;
	}


	@Override
	public boolean readEmpty(ParseContext context) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
