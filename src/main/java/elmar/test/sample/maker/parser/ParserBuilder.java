package elmar.test.sample.maker.parser;

public abstract class ParserBuilder {

    private Class<?> target;

    protected ParserBuilder createFor(Class<?> target) {
        this.target = target;
        return this;
    };

    
}
