package elmar.test.sample.maker.internal.model;

public class PaddingTemplatePart {
	enum Type{TEXT, ID, EXPR};
	public PaddingTemplatePart(String text) {
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
