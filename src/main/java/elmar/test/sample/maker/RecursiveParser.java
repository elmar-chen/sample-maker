package elmar.test.sample.maker;

public class RecursiveParser implements Parser{

    private final ChildrenGroupType type;

    public RecursiveParser(ChildrenGroupType type) {
        this.type = type;
    }

    public static RecursiveParser startChoices() {
        return new RecursiveParser(ChildrenGroupType.CHOOSE);
    }

    public static RecursiveParser startSequence() {
        return new RecursiveParser(ChildrenGroupType.SEQUENCE);
    }

    public static RecursiveParser pair(RecursiveParser left, RecursiveParser right, RecursiveParser pad){
        return null;
    }
    public RecursiveParser add(Parser parser, Repeat repeat) {
        return this;
    }

    @Override
    public void parse(ParseContext context) {

    }

    public static enum ChildrenGroupType {
        CHOOSE, SEQUENCE
    }

}
