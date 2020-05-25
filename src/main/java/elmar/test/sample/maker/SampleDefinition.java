package elmar.test.sample.maker;

import java.util.List;

import elmar.test.sample.maker.parser.Padding;

public class SampleDefinition {
    @Padding("\r?\n")
    List<Statement> expressions;
}
