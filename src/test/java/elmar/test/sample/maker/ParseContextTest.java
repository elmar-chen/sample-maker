package elmar.test.sample.maker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParseContextTest {

	@Test
	void testReadRegExp() {
			ParseContext parseContext = new ParseContext("aaaabbbbcccc");
			assertEquals(null, parseContext.readRegExp("b+"));
			assertEquals("aaaa", parseContext.readRegExp("a+"));
			assertEquals("", parseContext.readRegExp("h*"));
			assertEquals(null, parseContext.readRegExp("d"));
			assertEquals("bbbb", parseContext.readRegExp("b+"));
			assertEquals("cccc", parseContext.readRegExp("c+"));
			
		}
	
	@Test
	void testReadRegExpAndReplace() {
			ParseContext parseContext = new ParseContext("aaaabbbbcccc");
			assertEquals(null, parseContext.readRegExpAndReplace("b+", "aaa"));
			assertEquals("(aaaa)", parseContext.readRegExpAndReplace("a+", "($0)"));
			assertEquals("-", parseContext.readRegExpAndReplace("h*", "-"));
			assertEquals(null, parseContext.readRegExpAndReplace("d", ""));
			assertEquals("bbb", parseContext.readRegExpAndReplace("b(b+)", "$1"));
			
		}

}
