package elmar.test.sample.maker.parser.composite;

import elmar.test.sample.maker.ParseContext;

public abstract class Repeat {

    public static final int CAN_HAVE = 0;
    public static final int MUST_HAVE = 1;
    public static final int MUST_NOT_HAVE = 2;

    public abstract int shouldHaveMore(ParseContext context);
}
