package elmar.test.sample.maker;

import lombok.Data;

@Data
public class ParseResult<T> {
    private boolean success;
    private T result;
}
