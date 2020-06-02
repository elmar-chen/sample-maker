package elmar.test.sample.maker;

import java.util.List;

import elmar.test.sample.maker.parser.Lexer;
import lombok.Data;

public abstract class Enumeration {

    @Data
    @Pattern("(expression)")
    public static class QuotatedExpression extends Enumeration {
        Expression expression;
    }

    @Data
    @Pattern("[tokens]")
    public static class MultiTokenEnumeration extends Enumeration {
        List<EnumerationToken> tokens;
    }

    @Data
    @Pattern("start-end")
    public static class EnumerationToken extends Enumeration {
        
        @RegExp()
        private String start;

        private String end;
        
    }

    public static class EnumerationTokenLexer extends Lexer{
        
    }
}
