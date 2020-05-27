package elmar.test.sample.maker.parser.composite;

import java.util.ArrayList;
import java.util.List;

import elmar.test.sample.maker.parser.Parser;

public class BranchParser<T> extends Parser<T> {

    private List<Parser<? extends T>> children = new ArrayList<>();

    public List<Parser<? extends T>> getChildren() {
        return children;
    }
}
