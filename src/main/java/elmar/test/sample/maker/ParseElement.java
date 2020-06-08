package elmar.test.sample.maker;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

public class ParseElement {
    Class<?> targetClass;
    AnnotatedElement source;
    
    public static ParseElement from(Field field) {
        ParseElement parseElement = new ParseElement();
        parseElement.source = field;
        parseElement.targetClass = field.getType();
        
        return parseElement;
    }
}
