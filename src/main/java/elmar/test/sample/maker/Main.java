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
import elmar.test.sample.maker.parser.Lexer;
import elmar.test.sample.maker.parser.Padding;

public class Main {
    public static final String REGEXP_ID = "";
    public static final String WHITE_SPACES = "\\s*";

    @ParseRoot
    @Padding("\\r?\\n")
    public static List<Statement> statements;

    public static void main(String[] args) throws IOException, ParseException {
        System.out.println(~0);
        String content = IOUtils.resourceToString("/lang.def", Charset.forName("utf-8"));
        ParseContext context = new ParseContext(content);

        ParseElement root = findParseRoot(Main.class);
        context.pushParseElemet(root);
        doPrase(context);

    }

    static void doPrase(ParseContext context) throws ParseException {
        while (true) {
            ParseElement currentElement = context.popParseElement();

            ParseResult<?> result = context.getLastResult();
            if (result != null) {
                // pop
                if (result.isSuccess()) {
					boolean shouldHaveMore = currentElement.shouldHaveMore(context);

					if (shouldHaveMore) {
						context.pushParseElemet(currentElement);
					} else {
						ParseElement slibing = currentElement.getSlibing();
						if (slibing != null) {
							context.pushParseElemet(slibing);
						} else {
							context.popToParent();
						}
                    }

                } else {
					boolean minimalMeet = currentElement.minimalMet();
					if (minimalMeet) {
						ParseElement slibing = currentElement.getSlibing();
						if (slibing != null) {
							context.pushParseElemet(slibing);
						} else {
							context.popToParent();
						}
					} else {
						popError();
					}
                }
            }
			else {
				if (currentElement.getChildElements().size() > 1) {
					context.pushParseElemet(currentElement.getChildElements().get(0));
				} else {
					pushResult(parseElement(currentElement, context));
				}
			}

        }

    }

	private static void pushResult(Object obj) {

	}

	private static void popError() {
		// TODO Auto-generated method stub

	}

	private static Object parseElement(ParseElement parseElement, ParseContext context) throws ParseException {
		if (parseElement.isLexer()) {
            parseLexer((LexerParseElement<?>) parseElement, context);
		}
        else {
            List<ParseElement> childElements = parseElement.getChildElements();
            context.pushParseElemet(childElements.get(0));
        }
		return null;
	}

    private static Object parseLexer(LexerParseElement<?> parseElement, ParseContext context) {
		Lexer<?> lexer = parseElement.getLexer();
        return lexer.extract(context);

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
