package elmar.test.sample.maker;

import java.util.Stack;

public class ExpParser {
    private static final int START = -1;
    private Stack<Integer> statuss = new Stack<>();
    private Stack<Exp> results = new Stack<>();

    public Exp parse(String line) {
        statuss.push(START);
        for (int i = 0; i < line.length(); i++) {
            consume(line.charAt(i));
        }
        return null;
    }

    private void consume(char charAt) {
        int status = statuss.peek();
        if(status==START) {
            
        }
    }
}
