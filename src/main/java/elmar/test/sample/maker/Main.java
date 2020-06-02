package elmar.test.sample.maker;

import java.io.IOException;
import java.util.regex.Pattern;

import elmar.test.sample.maker.parser.RegExpLexer;
import elmar.test.sample.maker.parser.composite.Repeat;
import elmar.test.sample.maker.parser.composite.RepeatParser;
import elmar.test.sample.maker.parser.composite.SequenceParser;

public class Main {
    public static final String REGEXP_ID = "";
    public static final String WHITE_SPACES = "\\s*";
    
    public static void main(String[] args) throws IOException {
        Pattern compile = Pattern.compile("[1-9]");
        System.out.println(compile);
        
        RepeatParser<Statement> root = new RepeatParser<>(Statement.class);
        root.setRepeat(Repeat.atLeast(1));
//        root.padsWith(new RegExpLexer("\\r\\n|\\n"), PadPolicy.TAIL_OPTIONAL);
        
        
        SequenceParser<Statement> stat = new SequenceParser<>(Statement.class);
        
        
        
        
        SequenceParser<Void> leftPart = new SequenceParser<>(Void.class);
        leftPart.addLexer(Statement::setName, new RegExpLexer("[a-zA-Z0-9]+"));
        leftPart.addPad(new RegExpLexer("~"));
        leftPart.add(Quotation.class);
        
        
        stat.add(leftPart);
        stat.addPad(new RegExpLexer(":"));
        stat.add(Expression.class);
        
        
        RepeatParser<Expression> expression = new RepeatParser<Expression>(Expression.class);
        expression.setRepeat(Repeat.atLeast(1));
        
        
        
        SequenceParser<Distribution> distr = new SequenceParser<>(Distribution.class);
        distr.setWrapper(new RegExpLexer("{"), new RegExpLexer("}"));
        
        
//        stat.addParser(Label.class);
//        stat.addParse(Lab);
       
        
//        Parser root = RepeatParser.create(Statement.class, Repeat.ONE_OR_MORE).padsWith(new RegExpLexer("\\r\\n|\\n"));
        
        
//        String content = IOUtils.resourceToString("lang.def", Charset.forName("utf-8"));
//        ParseContext context = new ParseContext(content);
//        parser.parse(context);
        
        
    }
}
