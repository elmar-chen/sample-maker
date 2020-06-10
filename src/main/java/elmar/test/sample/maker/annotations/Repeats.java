package elmar.test.sample.maker.annotations;

public @interface Repeats {
    public int min() default 0;
    public int max() default Integer.MAX_VALUE;
}
