package elmar.test.sample.maker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpLexer implements Parser{
    private Pattern pattern;

    @Override
    public boolean parse(String input) {
        return pattern.matcher(input).matches();
    }

    public static void main(String[] args) {
        Pattern pattern2 = Pattern.compile("ab+c*");
        Matcher matcher = pattern2.matcher("ddabbccc");
        boolean found = matcher.find();
        String group = matcher.group();
        matcher.matches();
        System.out.println(group);
        boolean found2 = matcher.find();
        boolean hitEnd = matcher.hitEnd();
        System.out.println(found+"-"+found2+"-"+hitEnd);

    }
}
