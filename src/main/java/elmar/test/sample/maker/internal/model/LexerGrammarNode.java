package elmar.test.sample.maker.internal.model;

import elmar.test.sample.maker.GrammarNode;
import elmar.test.sample.maker.ParseContext;

public abstract class LexerGrammarNode implements GrammarNode{

	public abstract boolean read(ParseContext context, char c);

	public abstract boolean readEmpty(ParseContext context);
	
	boolean emptyTried;
	
	@Override
	public void process(ParseContext context) {
	}
	
}
