package elmar.test.sample.maker;

public class Repeat {

    public static final Repeat ZERO_OR_ONE = new Repeat(0, 1);
    public static final Repeat EXACTLY_ONE = new Repeat(1, 1);
    public static final Repeat ONE_OR_MORE = new Repeat(1, Integer.MAX_VALUE);
    public static final Repeat ANY_NUMBER = new Repeat(0, Integer.MAX_VALUE);

    private final int min;
    private final int max;

    public Repeat(int min, int max) {
        this.max = max;
        this.min = min;
    }

    public static Repeat exact(int amount) {
        return new Repeat(amount, amount);
    }

    public static Repeat atLeast(int amount) {
        return new Repeat(amount, Integer.MAX_VALUE);
    }

    public static Repeat atMost(int amount) {
        return new Repeat(0, amount);
    }
}
