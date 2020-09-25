package elmar.test.sample.maker.internal.model;

import elmar.test.sample.maker.GrammarNode;
import elmar.test.sample.maker.ParseContext;

public class TemplateParser {

	public static void main(String[] args) {
		ParseContext context = new ParseContext("");
		ListGrammarNode root = new ListGrammarNode(TemplatePart.class);
		
//		root.set
		
		context.setListRootElement(Template.class);
	}
}
