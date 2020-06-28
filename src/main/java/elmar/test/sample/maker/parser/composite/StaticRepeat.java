package elmar.test.sample.maker.parser.composite;

import elmar.test.sample.maker.ParseContext;

public class StaticRepeat extends Repeat {

    public static final StaticRepeat ZERO_OR_ONE = new StaticRepeat(0, 1);
    public static final StaticRepeat EXACTLY_ONE = new StaticRepeat(1, 1);
    public static final StaticRepeat ONE_OR_MORE = new StaticRepeat(1, Integer.MAX_VALUE);
    public static final StaticRepeat ANY_NUMBER = new StaticRepeat(0, Integer.MAX_VALUE);

    private final int min;
    private final int max;

    
    public int getMax() {
        return max;
    }
    public int getMin() {
        return min;
    }
    
    public StaticRepeat(int min, int max) {
        this.max = max;
        this.min = min;
    }
    
    

    public StaticRepeat() {
        this(1, Integer.MAX_VALUE);
    }
    
    public static StaticRepeat exact(int amount) {
        return new StaticRepeat(amount, amount);
    }

    public static StaticRepeat atLeast(int amount) {
        return new StaticRepeat(amount, Integer.MAX_VALUE);
    }

    public static StaticRepeat atMost(int amount) {
        return new StaticRepeat(0, amount);
    }

	@Override
	public boolean canHaveMore(ParseContext context) {
		int c = context.getCurrentResultCount();
		return c < max;
	}
}
