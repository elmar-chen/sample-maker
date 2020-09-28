package elmar.test.sample.maker.internal.model;

import elmar.test.sample.maker.ParseContext;
import elmar.test.sample.maker.parser.composite.StaticRepeat;

public class TemplateParser {

	public static void main(String[] args) {
		ParseContext context = new ParseContext("");
		ListGrammarNode root = new ListGrammarNode(TemplatePart.class);
		root.setRepeat(StaticRepeat.ANY_NUMBER);
		
		context.setListRootElement(Template.class);
	}
}
