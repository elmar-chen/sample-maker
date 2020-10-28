package elmar.test.sample.maker;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.IOUtils;

import elmar.test.sample.maker.annotations.ParseRoot;
import elmar.test.sample.maker.parser.Padding;

public class Main {
    public static final String REGEXP_ID = "";
    public static final String WHITE_SPACES = "\\s*";

    @ParseRoot
    @Padding("\\r?\\n")
    public static List<Statement> statements;

    public static void main(String[] args) throws IOException, ParseException {
        String content = IOUtils.resourceToString("/lang.def", Charset.forName("utf-8"));
        ParseContext context = new ParseContext(content);

        ParseElement root = findParseRoot(Main.class);

        loadParseTree(root);

        List<ParseElement> childElements = root.getChildElements();

        System.out.println(childElements);
//        context.pushParseElemet(root);
        doPrase(context);

    }

    private static void loadParseTree(ParseElement root) throws ParseException {
        String template = root.getTemplate();
        List<String> parts = ParseUtil.extractParts(template);
        for (String part : parts) {
			ParseElement ele = loadParseTreeByTemplatePart(part, root);
		}
        System.out.println(parts);
    }

    private static ParseElement loadParseTreeByTemplatePart(String part, ParseElement root) {
    	ParseUtil.isWrapped(part);
		return null;
	}

	static void doPrase(ParseContext context) throws ParseException {
//        while (true) {
////            ParseElement currentElement = context.popParseElement();
//            boolean shouldHaveMore = currentElement.shouldHaveMore(context);
//            if (shouldHaveMore) {
//                if (currentElement instanceof LexerParseElement<?>) {
//                    LexerParseElement<?> lexerParseElement = (LexerParseElement<?>) currentElement;
//                    String text = lexerParseElement.getLexer().readText(context);
//                    if (text == null) {
//                        currentElement.fail();
//                    } else {
//                        currentElement.addResult(text);
//                    }
//                } else {
//                    List<ParseElement> childs = currentElement.getChildElements();
//                    context.pushParseElemet(childs.get(0));
//                }
//            } else {
//                if (currentElement.minimalMet()) {
//                    ParseElement slibing = currentElement.getSlibing();
//                    if (slibing != null) {
//                        context.pushParseElemet(slibing);
//                    } else {
//                        // do nothing, parent is on top of stack;
//                    }
//                } else {
//                    currentElement.fail();
//                }
//            }
//        }

    }

    private static ParseElement findParseRoot(Class<?> clazz) throws ParseException {
        Field[] fields = clazz.getDeclaredFields();
        Method[] methods = clazz.getDeclaredMethods();
        Predicate<? super AnnotatedElement> isParseRoot = f -> f.isAnnotationPresent(ParseRoot.class);
        List<Field> annoedFields = Stream.of(fields).filter(isParseRoot).collect(Collectors.toList());
        List<Method> annoedMethods = Stream.of(methods).filter(isParseRoot).collect(Collectors.toList());

        if (annoedFields.size() + annoedMethods.size() > 1) {
            throw new ParseException(
                    "Multiple @ParseRoot found in " + clazz.getName() + ". Only one occurence is allowed.", -1);
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
