package elmar.test.sample.maker;

import lombok.Data;

@Data
public class TemplatePart {
	String wrapLeft;
	String wrapRight;
	String prefix;
	String suffix;
	int offset;
}
