package elmar.test.sample.maker;

import elmar.test.sample.maker.annotations.Lex;

public class Quotation {

	private int number;

	@Lex("[%]")
	private String type;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
