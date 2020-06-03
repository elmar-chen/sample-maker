package elmar.test.sample.maker;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseContext {

	private int pos = 0;
	private String content;

	Map<String, Object> variables = new HashMap<String, Object>();

	public ParseContext(String content) {
		this.content = content;

	}

	public void putLexerResult(StringBuffer buff) {

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
}
