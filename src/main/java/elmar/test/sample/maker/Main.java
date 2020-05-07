package elmar.test.sample.maker;

import elmar.test.sample.maker.parser.RegExpLexer;
import elmar.test.sample.maker.parser.StatementParserBuilder;
import elmar.test.sample.maker.parser.composite.Repeat;
import elmar.test.sample.maker.parser.composite.SequenceParser;

import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStream src = ClassLoader.getSystemResourceAsStream("lang.def");
        ParseContext context = new ParseContext(src);
        SequenceParser.start().add(new StatementParserBuilder(), Repeat.EXACTLY_ONE).add(new RegExpLexer("\\r\\n|\\n"), Repeat.EXACTLY_ONE);
        System.out.println((char)src.read());
    }
}
