package elmar.test.sample.maker;

import java.io.IOException;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
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
        
        AnnotatedElement root = findParseRoot(Main.class);
        context.pushParseElemet(root);
        doPrase(context);

    }

    private static void doPrase(ParseContext context) {
        while(context.getCurrentParseElement()!=null) {
            AnnotatedElement parseElement = context.popParseElement();
            parseElement.ge
            parseElement.getDeclaredAnnotation(Pattern.class);
            
            
        }
        
    }

    private static AnnotatedElement findParseRoot(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        Method[] methods = clazz.getDeclaredMethods();
        List<AccessibleObject> objects = new ArrayList<>();
        for (Field field : fields) {
            if (field.getDeclaredAnnotation(ParseRoot.class) != null) {
                objects.add(field);
            }
        }
        for (Method field : methods) {
            if (field.getDeclaredAnnotation(ParseRoot.class) != null) {
                objects.add(field);
            }
        }

        if(objects.size()>1) {
            throw new RuntimeException("Multiple @ParseRoot found. Must be only one.");
        }
        return objects.isEmpty() ? clazz : objects.get(0);
    }
}
