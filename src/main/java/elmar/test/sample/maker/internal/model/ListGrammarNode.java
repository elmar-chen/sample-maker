package elmar.test.sample.maker.internal.model;

import elmar.test.sample.maker.GrammarNode;
import elmar.test.sample.maker.ParseContext;
import elmar.test.sample.maker.ParseStatus;
import elmar.test.sample.maker.parser.composite.Repeat;

public class ListGrammarNode implements GrammarNode {

	private Class<?> componentClass;

	private GrammarNode padding;

	private Repeat repeat;
	
	boolean readPading = false;

	public ListGrammarNode(Class<?> componentClass) {
		this.componentClass = componentClass;
	}

	@Override
	public boolean read(ParseContext context, ParseStatus _status, char c) {
		
		
		ListGrammarNodeParseStatus status = (ListGrammarNodeParseStatus) _status;
		
		
		GrammarNode node = null;
		if(status.currentComponent==null) {
			node = context.createGrammarNodeForClass(componentClass);
		}
		
		
		
		boolean success = node.read(context, status, c);
		
		status.setNode(node);
		status.setReadEnd(context.getPos());
		
		
		if(success) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean readEmpty(ParseContext context) {
		return false;
	}

	public Class<?> getComponentClass() {
		return componentClass;
	}

	public void setRepeat(Repeat repeat) {
		this.repeat = repeat;
	}
	
	static class ListGrammarNodeParseStatus extends ParseStatus {
		GrammarNode currentComponent;
		
	}

	@Override
	public  ParseStatus start(ParseContext context) {
		return new ListGrammarNodeParseStatus();
	}
}
