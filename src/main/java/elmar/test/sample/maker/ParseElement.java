package elmar.test.sample.maker;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import elmar.test.sample.maker.annotations.Template;
import elmar.test.sample.maker.parser.composite.Repeat;
import elmar.test.sample.maker.parser.composite.StaticRepeat;
import lombok.Data;

@Data
public class ParseElement {
    private Class<?> targetClass;
    private AnnotatedElement source;
    private String[] wrapper;
    private String template;

    private Repeat repeat;

    private ParseResult result;

    private boolean lastAttempFailed;

    public static ParseElement from(Field field) throws ParseException {
        ParseElement parseElement = new ParseElement();
        parseElement.source = field;
        determineByType(field.getGenericType(), parseElement);
        Template tplAnno = parseElement.targetClass.getDeclaredAnnotation(Template.class);
        parseElement.template = tplAnno == null ? "" : tplAnno.value();
        return parseElement;
    }

    private static void determineByType(Type type, ParseElement parseElement) throws ParseException {
        Class<?> rawType = null;
        ParameterizedType paramedType = null;
        Type[] typeArgs = null;

        if (type instanceof ParameterizedType) {
            paramedType = (ParameterizedType) type;
            rawType = (Class<?>) paramedType.getRawType();
            typeArgs = paramedType.getActualTypeArguments();
        }
        if (List.class.isAssignableFrom(rawType)) {
            if (isTypeArgumentsDetermined(1, typeArgs)) {
                parseElement.targetClass = (Class<?>) typeArgs[0];
                parseElement.repeat = StaticRepeat.ANY_NUMBER;
            }
        } else if (rawType != null) {
            parseElement.targetClass = rawType;
            parseElement.repeat = StaticRepeat.ZERO_OR_ONE;
        } else {
            throw new ParseException("cannot determine the type", 1);
        }
//        } else if (Map.class.isAssignableFrom(rawType)) {
//            if (isTypeArgumentsDetermined(2, typeArgs)) {
//                parseElement.targetClass = rawType;
//                parseElement.repeat = StaticRepeat.ANY_NUMBER;
//            }
//        }

    }

    private static boolean isTypeArgumentsDetermined(int n, Type[] typeArgs) {
        boolean valid = typeArgs.length == n;
        for (int i = 0; i < n && valid; i++) {
            valid = valid && isDeterminedType(typeArgs[0]);
        }
        return valid;
    }

    private static boolean isDeterminedType(Type type) {
        return type instanceof Class<?> || type instanceof ParameterizedType;
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
        if (template == null) {
            throw new ParseException("template missing", 1);
        }

        List<ParseElement> childElements = new ArrayList<ParseElement>();

        List<String> parts = ParseUtil.extractParts(template);

        int offset = 0;
        for (String part : parts) {
            childElements.add(ParseElement.fromTemplate(this, part, offset += parts.size()));
        }
        if (childElements.size() == 1) {
            String subTemplate = parts.get(0);
            if (ParseUtil.LEFT_CHARS.indexOf(template.charAt(0)) >= 0) {
                ParseElement element = ParseElement.fromTemplate(this,
                        subTemplate.substring(1, subTemplate.length() - 1), 1);
                element.setWrapper(
                        new String[] { subTemplate.substring(0, 1), subTemplate.substring(subTemplate.length() - 1) });
                return Arrays.asList(element);
            }
            else {
                String[] tokens = subTemplate.split("\\s+");
                for (String token : tokens) {
                    if (token.matches("[a-zA-Z_][a-zA-Z_0-9]*")) {

                    }
                }

            }
        }
        return childElements;
    }

    public boolean isLexer() {
        return false;
    }

    public boolean shouldRead(ParseContext context) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean shouldHaveMore(ParseContext context) {
        return !this.lastAttempFailed && this.getRepeat().canHaveMore(context);
    }

    public ParseElement getSlibing() {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean minimalMet() {
        // TODO Auto-generated method stub
        return false;
    }

    public void addResult(String text) {
        // TODO Auto-generated method stub

    }

    public void finish() {
        // TODO Auto-generated method stub

    }

    public void fail() {
        // TODO Auto-generated method stub

    }

}
