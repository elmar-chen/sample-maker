package elmar.test.sample.maker;

import elmar.test.sample.maker.parser.Lexer;

public @interface Lex {
	public String value() default "";

	public String ref() default "";

	public Class<? extends Lexer<?>> imp();
}
