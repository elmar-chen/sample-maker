package elmar.test.sample.maker.parser;

public abstract class ParserBuilder {

    private Class<?> target;
    private boolean repeat;

    public ParserBuilder createFor(Class<?> target) {
        this.target = target;
        return this;
    }
    
    public ParserBuilder repeat() {
        this.repeat = true;
        return this;
    }

    
    
    
}
