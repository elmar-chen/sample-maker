package elmar.test.sample.maker;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import elmar.test.sample.maker.parser.composite.Repeat;
import elmar.test.sample.maker.parser.composite.StaticRepeat;
import lombok.Data;

@Data
public class GetterParseElement {
	private Class<?> targetClass;
	private AnnotatedElement source;
	private String[] wrapper;
	private String template;

	private Repeat repeat;

	private ParseResult result;

	public static GetterParseElement from(Field field) throws ParseException {
		GetterParseElement parseElement = new GetterParseElement();
		parseElement.source = field;
		if (List.class.isAssignableFrom(field.getType())) {
			Class<?> type = field.getType();
			Type genericType = field.getGenericType();
			if (genericType instanceof ParameterizedType) {
				ParameterizedType paramedType = (ParameterizedType) genericType;
				Type[] actualTypeArguments = paramedType.getActualTypeArguments();
				if (actualTypeArguments.length == 1 && actualTypeArguments[0] instanceof Class)
					parseElement.targetClass = (Class<?>) actualTypeArguments[0];

				if (parseElement.targetClass == null)
					throw new ParseException("Cann't determine the actual type", -1);
				parseElement.repeat = StaticRepeat.ANY_NUMBER;
			} else {
				parseElement.targetClass = type;
				parseElement.repeat = StaticRepeat.ZERO_OR_ONE;
			}
		}
		return parseElement;
	}

	public static GetterParseElement from(Method field) {
		GetterParseElement parseElement = new GetterParseElement();
		parseElement.source = field;
		parseElement.targetClass = field.getReturnType();
		return parseElement;
	}

	public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
		return source.getAnnotation(annotationClass);
	}

	public static GetterParseElement from(Class<?> clazz) {
		GetterParseElement parseElement = new GetterParseElement();
		parseElement.source = clazz;
		parseElement.targetClass = clazz;
		return parseElement;
	}

	public static GetterParseElement fromTemplate(GetterParseElement parent, String subTemplate, int offset) {
		GetterParseElement parseElement = new GetterParseElement();
		parseElement.source = parent.getSource();
		parseElement.template = subTemplate;
		return parseElement;
	}

	public List<GetterParseElement> getChildElements() throws ParseException {
		String template = this.getTemplate();
		List<GetterParseElement> childElements = new ArrayList<GetterParseElement>();
		if (template != null) {
			List<String> parts = ParseUtil.extractParts(template);
			int offset = 0;
			for (String part : parts) {
				childElements.add(GetterParseElement.fromTemplate(this, part, offset += parts.size()));
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

	public boolean shouldHaveMore(ParseContext context) {
		return this.getRepeat().canHaveMore(context);
	}

	public GetterParseElement getSlibing() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean minimalMet() {
		// TODO Auto-generated method stub
		return false;
	}

}
