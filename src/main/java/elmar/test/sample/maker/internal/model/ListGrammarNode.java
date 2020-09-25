package elmar.test.sample.maker.internal.model;

import elmar.test.sample.maker.GrammarNode;
import elmar.test.sample.maker.ParseContext;
import elmar.test.sample.maker.parser.composite.Repeat;

public class ListGrammarNode implements GrammarNode {
	
	private Class<?> componentClass;
	
	private GrammarNode padding;
	
	private Repeat repeat;
	
	

	public ListGrammarNode(Class<?> componentClass) {
		this.componentClass = componentClass;
	}

	@Override
	public boolean read(ParseContext context) {
		return false;
	}

	public Class<?> getComponentClass() {
		return componentClass;
	}
}
