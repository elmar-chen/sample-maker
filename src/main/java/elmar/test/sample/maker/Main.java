package elmar.test.sample.maker;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.IOUtils;

import elmar.test.sample.maker.parser.Padding;

public class Main {
    public static final String REGEXP_ID = "";
    public static final String WHITE_SPACES = "\\s*";
    
    
    @ParseRoot
    @Padding("\\r?\\n")
    public static List<Statement> statements;
    
    public static void main(String[] args) throws IOException {
        
        String content = IOUtils.resourceToString("lang.def", Charset.forName("utf-8"));
        ParseContext context = new ParseContext(content);
        doParse(context, Main.class);
        
    }
    
    

    private static void doParse(ParseContext context, Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        Method[] methods = clazz.getDeclaredMethods();
        
    }
}
