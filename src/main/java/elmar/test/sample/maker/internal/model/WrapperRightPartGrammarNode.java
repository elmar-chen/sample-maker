package elmar.test.sample.maker.internal.model;

import elmar.test.sample.maker.GrammarNode;
import elmar.test.sample.maker.ParseContext;

public class WrapperRightPartGrammarNode implements GrammarNode{
	private WrapperLeftPartGrammarNode leftNode;

	@Override
	public boolean read(ParseContext context) {
		char readChar = context.readChar();
		return leftNode.getExpectedRightPart() == readChar;
	}
	
}
