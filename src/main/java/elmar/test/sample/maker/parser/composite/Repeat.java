package elmar.test.sample.maker.parser.composite;

import elmar.test.sample.maker.ParseContext;

public abstract class Repeat {

    public static final int CAN_HAVE = 0;
    public static final int MUST_HAVE = 1;
    public static final int MUST_NOT_HAVE = 2;

	public abstract boolean canHaveMore(ParseContext context);

	public boolean canFinish(ParseContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean minimalMet() {
		// TODO Auto-generated method stub
		return false;
	}
}
