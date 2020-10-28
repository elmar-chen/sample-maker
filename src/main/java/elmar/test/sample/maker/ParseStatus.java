package elmar.test.sample.maker;

import lombok.Data;

@Data
public class ParseStatus {
	private GrammarNode node;
	private ParseResult parseResult;
	private int parseStart;
	private int parseEnd;
}
