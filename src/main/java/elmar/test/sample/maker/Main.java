package elmar.test.sample.maker;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

public class Main {
    public static final String REGEXP_ID = "";
    public static final String WHITE_SPACES = "\\s*";
    
    public static void main(String[] args) throws IOException {

        
        String content = IOUtils.resourceToString("lang.def", Charset.forName("utf-8"));
        ParseContext context = new ParseContext(content);
        doParse(context, Statement[].class);
        
        
    }

    private static void doParse(ParseContext context, Class<?> clazz) {
        clazz.isArray();
        clazz.getComponentType();
        
    }
}
