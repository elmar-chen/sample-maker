package elmar.test.sample.maker;

import elmar.test.sample.maker.parser.Padding;

import java.util.List;

public class SampleDefinition {
    @Padding("\r?\n")
    List<Statement> expressions;
}
