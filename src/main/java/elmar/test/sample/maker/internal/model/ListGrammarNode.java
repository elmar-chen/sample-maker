package elmar.test.sample.maker.internal.model;

import elmar.test.sample.maker.GrammarNode;
import elmar.test.sample.maker.ParseContext;
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
	public boolean read(ParseContext context, char c) {
		GrammarNode node = context.createGrammarNodeForClass(componentClass);

		boolean read = node.read(context, c);
		if(read) {
			
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

	public static void main(String[] args) {
		char c = (char) -1;
		char b = (char) 0xff;
		System.out.println(c == b);
	}
}
