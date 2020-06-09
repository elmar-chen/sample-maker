package elmar.test.sample.maker;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class ParseUtilTest {

    @Test
    void test() {
        assertEquals(Arrays.asList("<" ,"abc"," , ","b"), ParseUtil.splitKeepDelim("<abc , b", "\\w+"));
        assertEquals(Arrays.asList("<" ,"abc"," , ","b",">"), ParseUtil.splitKeepDelim("<abc , b>", "\\w+"));
        assertEquals(Arrays.asList("<<<<"), ParseUtil.splitKeepDelim("<<<<", "\\w+"));
        assertEquals(Arrays.asList("aaa"), ParseUtil.splitKeepDelim("aaa", "\\w+"));
        assertTrue(ParseUtil.splitKeepDelim("", "\\w+").isEmpty());
        
        assertEquals(Arrays.asList("a","a","a"), ParseUtil.splitKeepDelim("aaa", "b*"));
    }

}
