package elmar.test.sample.maker;

public @interface Lex {
	public String value() default "";

	public String text() default "";

	public Class<?> imp() default Void.class;
}
