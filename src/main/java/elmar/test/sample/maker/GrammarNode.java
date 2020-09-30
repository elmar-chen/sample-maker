package elmar.test.sample.maker;

public interface GrammarNode {

	boolean read(ParseContext context, ParseStatus status, char c);
	boolean readEmpty(ParseContext context);
	
	 ParseStatus start(ParseContext context);
}
