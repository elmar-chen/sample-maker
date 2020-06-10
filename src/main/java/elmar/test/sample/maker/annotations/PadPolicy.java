package elmar.test.sample.maker.annotations;

public @interface PadPolicy {

    public static final int OPTIONAL = 1;
    public static final int ALWAYS = 2;
    public static final int ONLY_NECESSARY = 4;

    public static @interface PadBefore {
        int value() default ONLY_NECESSARY;
    }

    public static @interface PadAfter {
        int value() default ONLY_NECESSARY;
    }
}
