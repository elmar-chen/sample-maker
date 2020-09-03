package elmar.test.sample.maker;

import lombok.Data;

@Data
public class TemplatePart {
	enum Type{TEXT, ID, EXPR};
	public TemplatePart(String text) {
		type = Type.TEXT;
		content = text;
	}
	Type type;
	String wrapLeft;
	String wrapRight;
	String content;
	String prefix;
	String suffix;
	int offset;
}
