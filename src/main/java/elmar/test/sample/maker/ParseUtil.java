package elmar.test.sample.maker;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseUtil {

    public static final String LEFT_CHARS = "([{<\"`";;
    public static final String RIGHT_CHARS = ")]}>\"`";

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

    public static List<String> parseTemplate(String template) throws ParseException {

        List<String> parts = new ArrayList<String>();
        int i = 0;
        boolean readPaired = false;
        while (i < template.length()) {
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

            if (idxLeft >= 0) {
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


}
