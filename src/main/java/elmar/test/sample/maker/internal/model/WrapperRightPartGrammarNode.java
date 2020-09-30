package elmar.test.sample.maker.internal.model;

import elmar.test.sample.maker.GrammarNode;
import elmar.test.sample.maker.ParseContext;
import elmar.test.sample.maker.ParseStatus;

public class WrapperRightPartGrammarNode implements GrammarNode{
	private WrapperLeftPartGrammarNode leftNode;

	@Override
	public boolean read(ParseContext context, ParseStatus status, char c) {
		return leftNode.getExpectedRightPart() == c;
	}
	@Override
	public boolean readEmpty(ParseContext context) {
		return false;
	}
	@Override
	public ParseStatus start(ParseContext context) {
		// TODO Auto-generated method stub
		return null;
	}
}
