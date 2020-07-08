package elmar.test.sample.maker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class ParseUtilTest {

    @Test
    void test() {
        assertEquals(Arrays.asList("<", "abc", " , ", "b"), ParseUtil.splitKeepDelim("<abc , b", "\\w+"));
        assertEquals(Arrays.asList("<", "abc", " , ", "b", ">"), ParseUtil.splitKeepDelim("<abc , b>", "\\w+"));
        assertEquals(Arrays.asList("<<<<"), ParseUtil.splitKeepDelim("<<<<", "\\w+"));
        assertEquals(Arrays.asList("aaa"), ParseUtil.splitKeepDelim("aaa", "\\w+"));
        assertTrue(ParseUtil.splitKeepDelim("", "\\w+").isEmpty());

        assertEquals(Arrays.asList("a", "a", "a"), ParseUtil.splitKeepDelim("aaa", "b*"));
    }

    @Test
    void testParseTemplate() throws ParseException {
        assertEquals(Arrays.asList("<abc>"), ParseUtil.extractParts("<abc>"));
        assertEquals(Arrays.asList("{abc}"), ParseUtil.extractParts("{abc}"));
        assertEquals(Arrays.asList("[abc]"), ParseUtil.extractParts("[abc]"));
        assertEquals(Arrays.asList("<abc>", ",", "(aa)"), ParseUtil.extractParts("<abc>,(aa)"));
        assertEquals(Arrays.asList("<abc(aaa(ccc))>", ",", "(aa)"), ParseUtil.extractParts("<abc(aaa(ccc))>,(aa)"));
        assertEquals(Arrays.asList("<abc<aaa<ccc>>>", ",", "(aa)"), ParseUtil.extractParts("<abc<aaa<ccc>>>,(aa)"));
        assertEquals(Arrays.asList("\"aa\"", "<abc<aaa<ccc>>>", ",", "(aa)"),
                ParseUtil.extractParts("\"aa\"<abc<aaa<ccc>>>,(aa)"));

        assertEquals(Arrays.asList("\"<abc<aaa<ccc>>>\"", ",", "(aa)"),
                ParseUtil.extractParts("\"<abc<aaa<ccc>>>\",(aa)"));

        String[] badTemplates = { "(aa)b)", "(aa)(b", "\"aa\"\"b", "(aa<)(b>)", };
        for (String badTemplate : badTemplates) {

            try {
                ParseUtil.extractParts(badTemplate);
                fail(badTemplate + " should have unbalanced error.");
            } catch (ParseException e) {
                // do nothing
            }
        }

    }

}
