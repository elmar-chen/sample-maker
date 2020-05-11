package elmar.test.sample.maker.parser.composite;

import elmar.test.sample.maker.ParseContext;
import elmar.test.sample.maker.parser.Parser;

public class RepeatParser implements Parser {

    private final Repeat repeat;
    private Parser parser;
    private int successCount = 0;

    public RepeatParser(Parser parser, Repeat repeat) {
        this.parser = parser;
        this.repeat = repeat;
    }

    @Override
    public boolean parse(ParseContext context) {
        boolean success = parser.parse(context);
        if (success) {
            successCount++;
            return true;
        } else {
            return successCount >= repeat.getMin() && successCount <= repeat.getMax();
        }
    }

}
