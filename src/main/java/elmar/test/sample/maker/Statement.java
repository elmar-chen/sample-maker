package elmar.test.sample.maker;

import java.util.List;

import javax.annotation.Nullable;

import elmar.test.sample.maker.PadPolicy.PadBefore;
import lombok.Data;

@Data
@Pattern("level name ~ quotation : expressions")
public class Statement {

    private static final String IDENT_NUM_OF_SPACE = "IDENT_NUM_OF_SPACE";

    @Lex(ref = Main.WHITE_SPACES)
    private int level;

    @Nullable
    @Lex(ref = Main.REGEXP_ID)
    private String name;
    
    @Nullable
    @PadBefore()
    private Quotation quotation;
    
    
    @PadBefore(PadPolicy.ALWAYS)
    @Repeats(min = 0)
    private List<Expression> expressions;

    
    public int getLevel(String text, ParseContext context) {
        int numOfSpaceAsOneIdent = context.getInt(IDENT_NUM_OF_SPACE, 4);
        int numOfTab = 0;
        int numOfSpace = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if(c==' ') numOfSpace ++;
            if(c=='\t') numOfTab ++;
        }
        return numOfTab + numOfSpace/numOfSpaceAsOneIdent; 
    }
    
}
