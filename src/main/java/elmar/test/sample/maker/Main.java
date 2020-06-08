package elmar.test.sample.maker;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import elmar.test.sample.maker.parser.Padding;

public class Main {
	public static final String REGEXP_ID = "";
	public static final String WHITE_SPACES = "\\s*";

	@ParseRoot
	@Padding("\\r?\\n")
	public static List<Statement> statements;

	public static void main(String[] args) throws IOException {
		String[] split = "level name ~ quotation : expressions".split("\b");
		System.out.println(split);
//		String content = IOUtils.resourceToString("lang.def", Charset.forName("utf-8"));
//		ParseContext context = new ParseContext(content);
//
//		ParseElement root = findParseRoot(Main.class);
//		context.pushParseElemet(root);
//		doPrase(context);

	}

	private static void doPrase(ParseContext context) {
		ParseElement parseElement = null;
		while ((parseElement = context.popParseElement()) != null) {
			Pattern pattern = parseElement.getAnnotation(Pattern.class);
			List<ParseElement> elements = getChildElements(parseElement, pattern);
		}

	}

	private static List<ParseElement> getChildElements(ParseElement parseElement, Pattern pattern) {
		String strPattern = pattern.value();
		int j = strPattern.length();
		for (int i = 0; i < strPattern.length(); i++) {

		}
		return null;
	}

	private static ParseElement findParseRoot(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		Method[] methods = clazz.getDeclaredMethods();
		Predicate<? super AnnotatedElement> isParseRoot = f -> f.isAnnotationPresent(ParseRoot.class);
		List<Field> annoedFields = Stream.of(fields).filter(isParseRoot).collect(Collectors.toList());
		List<Method> annoedMethods = Stream.of(methods).filter(isParseRoot).collect(Collectors.toList());

		if (annoedFields.size() + annoedMethods.size() > 1) {
			throw new RuntimeException("Multiple @ParseRoot found. Must be only one.");
		}
		if (annoedFields.size() == 1) {
			return ParseElement.from(annoedFields.get(0));
		}
		if (annoedMethods.size() == 1) {
			return ParseElement.from(annoedMethods.get(0));
		}

		return ParseElement.from(clazz);
	}
}
