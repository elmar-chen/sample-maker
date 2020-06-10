package elmar.test.sample.maker;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import lombok.Data;

@Data
public class ParseElement {
    private Class<?> targetClass;
    private AnnotatedElement source;
    private String[] wrapper;

    public static ParseElement from(Field field) {
        ParseElement parseElement = new ParseElement();
        parseElement.source = field;
        parseElement.targetClass = field.getType();
        return parseElement;
    }

    public static ParseElement from(Method field) {
        ParseElement parseElement = new ParseElement();
        parseElement.source = field;
        parseElement.targetClass = field.getReturnType();
        return parseElement;
    }

    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        return source.getAnnotation(annotationClass);
    }

    public static ParseElement from(Class<?> clazz) {
        ParseElement parseElement = new ParseElement();
        parseElement.source = clazz;
        parseElement.targetClass = clazz;
        return parseElement;
    }

}
