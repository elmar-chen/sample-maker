package elmar.test.sample.maker;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import elmar.test.sample.maker.internal.model.Template;
import lombok.Getter;

@Getter
public class ParseContext {

	private int pos = 0;
	private String content;

	Map<String, Object> variables = new HashMap<String, Object>();
    private Stack<ParseStatus> parseStatck = new Stack<ParseStatus>();
    private int slibingIdx;
    private ParseStatus childStatus;

	public ParseContext(String content) {
		this.content = content;

	}

	

	public int getInt(String identNumOfSpace, int def) {
		Object oVal = variables.get(identNumOfSpace);
		if (oVal instanceof Integer) {
			return (Integer) oVal;

		}
		return def;
	}

	public String readRegExp(String regexp) {
		Pattern patter = Pattern.compile(regexp);
		Matcher matcher = patter.matcher(content);
		boolean matches = matcher.find(pos);
		if (matches && matcher.start() == pos) {
			String rslt = matcher.group();
			pos += rslt.length();
			return rslt;
		}
		return null;
	}

	public String readRegExpAndReplace(String regexp, String replace) {
		String read = readRegExp(regexp);
		if (read != null) {
			return read.replaceFirst(regexp, replace);
		}
		return null;
	}

	public char readChar() {
		return content.charAt(pos++);
	}

	public void unread(int amount) {
		pos -= amount;
	}

    public ParseResult getChildResult() {
        // TODO Auto-generated method stub
        return null;
    }

	public void popToParent() {
		// TODO Auto-generated method stub

	}

	public int getCurrentResultCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setListRootElement(Class<Template> clazz) {
		
	}

	public GrammarNode createGrammarNodeForClass(Class<?> componentClass) {
		return null;
	}

	public void addBranch(ParseStatus start) {
		// TODO Auto-generated method stub
		
	}



	public void pushNode(GrammarNode node) {
		// TODO Auto-generated method stub
		
	}



	public void pop(boolean result) {
		
	}

}
