package elmar.test.sample.maker;

public class ExpParser {
    String line;

    public void parse() {

    }
    public RecursiveParser build(){
        RecursiveParser quotationPart = RecursiveParser.startSequence()
                .add(new RegExpLexer("~"), Repeat.EXACTLY_ONE)
                .add(new QuotationParser(), Repeat.EXACTLY_ONE);


        return RecursiveParser.startSequence().add(new NameParser(), Repeat.ZERO_OR_ONE).add(quotationPart, Repeat.ZERO_OR_ONE);
    }
}

