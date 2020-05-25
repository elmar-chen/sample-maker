package elmar.test.sample.maker.parser;

import java.util.List;

public class GrammarNode<T> {
    Class<T> result;

    boolean multiple;
    
    Lexer pad;
        
    List<GrammarNode<?>> children;
    
}
