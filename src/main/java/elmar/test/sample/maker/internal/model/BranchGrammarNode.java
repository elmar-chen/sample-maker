package elmar.test.sample.maker.internal.model;

import java.util.Stack;

import elmar.test.sample.maker.GrammarNode;
import elmar.test.sample.maker.ParseContext;
import elmar.test.sample.maker.ParseResult;

public abstract class BranchGrammarNode extends BaseGrammarNode {

	Stack<GrammarNode> processedChild = new Stack<>();
	boolean emptyTried = false;

	@Override
	public void advance(ParseContext context) {


		ParseResult childResult = context.getChildResult();
		if (childResult != null && !childResult.isSuccess()) {
			if(processedChild.isEmpty()) {
				context.pop(false);
			}
			else {
				context.pushNode(processedChild.pop());
			}
		} else {
			boolean hasEnoughChild = hasEnoughChild(context);
			if(hasEnoughChild) {
				context.pop(true);
			}
			else {
				GrammarNode nextChild = getNextChild(context);
				if (nextChild == null) {
					context.pop(true);
				} else {
					context.pushNode(nextChild);
				}
			}
		}
	}

	public abstract GrammarNode getNextChild(ParseContext context);

	public abstract boolean hasEnoughChild(ParseContext context);
}
