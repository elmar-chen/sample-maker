package elmar.test.sample.maker;

import lombok.Data;

@Data
public class ParseStatus {
	private GrammarNode node;
	private	int readStart;
	private	int readEnd;
	
}
