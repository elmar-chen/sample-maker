package elmar.test.sample.maker;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseUtil {

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

}
