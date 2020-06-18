package elmar.test.sample.maker;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import elmar.test.sample.maker.parser.composite.Repeat;
import lombok.Data;

@Data
public class ParseElement {
    private Class<?> targetClass;
    private AnnotatedElement source;
    private String[] wrapper;
    private String template;

    private Repeat repeat;

    private ParseResult<?> result;
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

    public static ParseElement fromTemplate(ParseElement parent, String subTemplate, int offset) {
        ParseElement parseElement = new ParseElement();
        parseElement.source = parent.getSource();
        parseElement.template = subTemplate;
        return parseElement;
    }

	public List<ParseElement> getChildElements() throws ParseException {
        String template = this.getTemplate();
        List<ParseElement> childElements = new ArrayList<ParseElement>();
        if (template != null) {
            List<String> parts = ParseUtil.parseTemplate(template);
            int offset = 0;
            for (String part : parts) {
                childElements.add(ParseElement.fromTemplate(this, part, offset += parts.size()));
            }
        }
		return childElements;
    }

    public static void main(String[] args) {
        int i = 0;
        System.out.println(i += 10);
    }

	public boolean isLexer() {
		return false;
	}

    public boolean shouldRead(ParseContext context) {
        // TODO Auto-generated method stub
        return false;
    }


}
