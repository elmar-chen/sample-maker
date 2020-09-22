package elmar.test.sample.maker;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateParser {

	public static final String LEFT_CHARS = "([{<\"`";;
	public static final String RIGHT_CHARS = ")]}>\"`";

	private String template;
	private StringBuilder buff;

	private int parseIndex;

	private char lastRead;

	public TemplateParser(String template) {
		this.template = template;
		buff = new StringBuilder(template.length());
	}

	TemplatePart readNext() {
		read(Character::isWhitespace);

		Predicate<Character> readPrefix = new Predicate<Character>() {
			boolean isFirst = true;

			@Override
			public boolean test(Character ch) {
				boolean result = isFirst ? Character.isJavaIdentifierStart(ch) : Character.isJavaIdentifierStart(ch);
				isFirst = false;
				return result;
			}
		};
		return null;
	}

	private void read(Predicate<Character> shouldRead) {
		buff.setLength(0);
		while (parseIndex < template.length()) {
			char ch = template.charAt(parseIndex);
			if (!shouldRead.test(ch)) {
				break;
			}
			parseIndex++;
		}
	}

	private void skipSpaces() {
		while (parseIndex < template.length()) {
			char ch = template.charAt(parseIndex);
			if (!Character.isWhitespace(ch)) {
				break;
			}
			parseIndex++;
		}

	}

	private String readPrefix() {
		int startIdx = parseIndex;
		while (parseIndex < template.length()) {
			char ch = template.charAt(parseIndex);
			if (!Character.isWhitespace(ch)) {
				break;
			}
			parseIndex++;
		}
		return template.substring(startIdx, parseIndex);
	}

	public static List<String> splitKeepDelim(String text, String regExp) {
		Matcher matcher = Pattern.compile(regExp).matcher(text);
		List<String> rslt = new ArrayList<String>();
		int lastEnd = 0;
		while (matcher.find()) {
			if (lastEnd < matcher.start()) {
				rslt.add(text.substring(lastEnd, matcher.start()));
			}
			if (matcher.start() < matcher.end()) {
				rslt.add(text.substring(matcher.start(), matcher.end()));
			}
			lastEnd = matcher.end();
		}
		if (lastEnd < text.length()) {
			rslt.add(text.substring(lastEnd, text.length()));
		}
		return rslt;
	}

	public static List<String> extractParts(String template) throws ParseException {

		List<String> parts = new ArrayList<String>();
		int i = 0;
		while (i < template.length()) {
			char ch = template.charAt(i);
			if (Character.isWhitespace(ch)) {
				i++;
				continue;
			}
			boolean readPaired = LEFT_CHARS.indexOf(ch) >= 0;
			String token = readPaired ? readPaired(i, template) : readUnpaired(i, template);
			i += token.length();
			if (token.length() > 0)
				parts.add(token);
			readPaired = !readPaired;
		}

		return parts;
	}

	private static String readPaired(int start, String template) throws ParseException {

		char left = template.charAt(start);

		int expectedBalIndx = LEFT_CHARS.indexOf(left);

		int[] balances = new int[LEFT_CHARS.length()];
		int end = start;
		for (int chIdx = start; chIdx < template.length(); chIdx++) {
			char ch = template.charAt(chIdx);

			int balIdx = -1;
			int idxLeft = LEFT_CHARS.indexOf(ch);
			int idxRight = RIGHT_CHARS.indexOf(ch);

			if (idxLeft > 0 && idxRight > 0) {
				balIdx = idxLeft;
				balances[balIdx] += balances[balIdx] == 0 ? 1 : -1;
			} else if (idxLeft >= 0) {
				balIdx = idxLeft;
				balances[balIdx]++;
			} else if (idxRight >= 0) {
				balIdx = idxRight;
				balances[balIdx]--;
			}
			if (balIdx < 0)
				continue;
			if (balances[balIdx] < 0) {
				String msg = String.format("right character '%c' has no corresponding left char '%c'",
						RIGHT_CHARS.charAt(balIdx), LEFT_CHARS.charAt(balIdx));
				throw new ParseException(msg, chIdx);
			}
			if (balances[balIdx] == 0 && balIdx == expectedBalIndx) {
				end = chIdx;
				break;
			}
		}
		for (int i = 0; i < balances.length; i++) {
			if (balances[i] != 0) {
				String msg = String.format("left character '%c' has no corresponding right char '%c'",
						LEFT_CHARS.charAt(balances[i]), RIGHT_CHARS.charAt(balances[i]));
				throw new ParseException(msg, end);
			}
		}
		return template.substring(start, end + 1);
	}

	private static String readUnpaired(int start, String template) throws ParseException {
		StringBuilder sb = new StringBuilder();
		for (; start < template.length(); start++) {
			char ch = template.charAt(start);
			int idxLeft = LEFT_CHARS.indexOf(ch);
			int idxRight = RIGHT_CHARS.indexOf(ch);

			if (idxLeft >= 0 || Character.isWhitespace(ch)) {
				break;
			}
			if (idxRight >= 0) {
				String msg = String.format("right character '%c' has no corresponding left char '%c'",
						RIGHT_CHARS.charAt(idxRight), LEFT_CHARS.charAt(idxRight));
				throw new ParseException(msg, idxRight);
			}
			sb.append(ch);
		}

		return sb.toString();
	}

	static boolean isLeftChar(char c) {
		return LEFT_CHARS.indexOf(c) >= 0;
	}

	public static boolean isWrapped(String part) {

		int leftIdx = LEFT_CHARS.indexOf(part.charAt(0));
		int rightIdx = RIGHT_CHARS.indexOf(part.charAt(part.length() - 1));
		return part.length() >= 2 && leftIdx == rightIdx && leftIdx >= 0;
	}
	public static void main(String[] args) {
		byte[] decode = new org.apache.commons.codec.binary.Base64().decode("eyJzdWIiOiJRRlAtMkRFMTE4RkEiLCJhdWQiOiJDQVRQZWlqaWFuXzIwMjBfYWNfY2xpZW50IiwianRpIjoiN0hsRldIazFtaHh0WEZ2d0tTdlY2bCIsImlzcyI6Imh0dHBzOi8vZmVkbG9naW5xYS5jYXQuY29tIiwiaWF0IjoxNTk4NDIzNzE2LCJleHAiOjE1OTg0MjQwMTYsImFjciI6InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDphYzpjbGFzc2VzOnVuc3BlY2lmaWVkIiwidGVsZXBob25lbnVtYmVyIjoiKzg2MTgwMTI2NjQ0MDUiLCJnaXZlbm5hbWUiOiLmtYvor5XotKblj7ciLCJzbiI6Iua1i-ivlei0puWPtyIsImNhdHJlY2lkIjoiUUZQLTJERTExOEZBIiwicGkuc3JpIjoiU3l1V2RKMmVycmZKVGdOYXd0SWR6aURoWWUwLi5SZ0trIn0");
		try {
			System.out.println(new String(decode, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
