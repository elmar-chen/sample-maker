package elmar.test.sample.maker.internal.model;

import java.util.Stack;

import elmar.test.sample.maker.GrammarNode;
import elmar.test.sample.maker.ParseContext;
import elmar.test.sample.maker.ParseResult;

public abstract class SequenceGrammarNode extends BaseGrammarNode {

	Stack<GrammarNode> processedChild = new Stack<>();
	
	@Override
	public void process(ParseContext context) {
		ParseResult childResult = context.getChildResult();

		if (childResult == null) {
			initProcess(context);
		} else {
			if (childResult.isSuccess()) {
				processChildSuccess(context, childResult);
			} else {
				processChildFailed(context, childResult);
			}
		}
	}

	private void processChildFailed(ParseContext context, ParseResult childResult) {
		if(childResult.isStop()) {
			if(processedChild.isEmpty()) {
				context.pop(ParseResult.FAIL_STOP);
			}
			else {
				context.pushNode(processedChild.pop());
			}
		} else {
			context.pushNode(context.getChildStatus().getNode());
		}
	}

	private void processChildSuccess(ParseContext context, ParseResult childResult) {
		GrammarNode nextChild = getNextChild();
		if (nextChild == null) {
			context.pop(ParseResult.SUCCESS_STOP);
		} else {
			context.pushNode(nextChild);
		}
	}

	private void initProcess(ParseContext context) {
		GrammarNode nextChild = getNextChild();
		if (nextChild != null) {
			context.pushNode(nextChild);
		} else {
			context.pop(ParseResult.SUCCESS_STOP);
		}
	}

	public abstract GrammarNode getNextChild();

}
