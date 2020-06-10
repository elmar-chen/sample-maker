package elmar.test.sample.maker.annotations;

public @interface Lex {
	public String value() default "";

	public String text() default "";

	public Class<?> imp() default Void.class;
}
