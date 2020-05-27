package elmar.test.sample.maker;

import java.io.IOException;
import java.util.regex.Pattern;

import elmar.test.sample.maker.parser.Parser;
import elmar.test.sample.maker.parser.ParserBuilder;
import elmar.test.sample.maker.parser.RegExpLexer;
import elmar.test.sample.maker.parser.composite.Repeat;
import elmar.test.sample.maker.parser.composite.RepeatParser;

public class Main {
    public static void main(String[] args) throws IOException {
        Pattern compile = Pattern.compile("[1-9]");
        System.out.println(compile);
        
        ParserBuilder.createFor(Statement.class).repeat().atLeast(1).padsWith(new RegExpLexer("\\r\\n|\\n"), PadPolicy.TAIL_OPTIONAL);
        
//        Parser root = RepeatParser.create(Statement.class, Repeat.ONE_OR_MORE).padsWith(new RegExpLexer("\\r\\n|\\n"));
        
        
//        String content = IOUtils.resourceToString("lang.def", Charset.forName("utf-8"));
//        ParseContext context = new ParseContext(content);
//        parser.parse(context);
    }
}
