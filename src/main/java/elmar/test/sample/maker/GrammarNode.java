package elmar.test.sample.maker;

public interface GrammarNode {

	boolean read(ParseContext context, char c);
	public boolean readEmpty(ParseContext context);
	
}
