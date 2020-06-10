package elmar.test.sample.maker;

import java.util.List;

import elmar.test.sample.maker.annotations.Template;

@Template("function( parameters )")
public class Distribution {
    String function;
    
    List<String> parameters;
}
