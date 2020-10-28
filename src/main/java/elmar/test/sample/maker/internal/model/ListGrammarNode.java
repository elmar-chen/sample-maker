package elmar.test.sample.maker.internal.model;

import elmar.test.sample.maker.GrammarNode;
import elmar.test.sample.maker.parser.composite.Repeat;

public class ListGrammarNode extends SequenceGrammarNode {

	private GrammarNode component;

	private GrammarNode padding;

	private Repeat repeat;
	
	boolean wasReadPadding = false;
	
	@Override
	public GrammarNode getNextChild() {
		if(!wasReadPadding) {
			return padding;
		}
		if(repeat.minimalMet()) {
			
		}
		return null;
	}



	


}
