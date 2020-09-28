package elmar.test.sample.maker.internal.model;

import elmar.test.sample.maker.GrammarNode;
import elmar.test.sample.maker.ParseContext;

public class WrapperLeftPartGrammarNode implements GrammarNode{
	public static final String LEFT_CHARS = "([{<\"`";;
	public static final String RIGHT_CHARS = ")]}>\"`";

	private char left = (char) -1;
	
	
	public boolean read(ParseContext context, char readChar) {
		if(LEFT_CHARS.indexOf(readChar)>=0) {
			left = readChar;
			return true;
		}
		return false;
	}
	
	@Override
	public boolean readEmpty(ParseContext context) {
		return false;
	}
	public char getExpectedRightPart() {
		int idx = LEFT_CHARS.indexOf(left);
		if(idx>0 && idx<RIGHT_CHARS.length()) {
			return RIGHT_CHARS.charAt(idx);
		}
		return (char) -1;
	}

}
