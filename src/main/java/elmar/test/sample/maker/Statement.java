package elmar.test.sample.maker;

import java.util.List;

import lombok.Data;

@Data
@Pattern("level name ~ quotation : expressions")
public class Statement {

    private static final String IDENT_NUM_OF_SPACE = "IDENT_NUM_OF_SPACE";

    @RegExp(ref = Main.WHITE_SPACES)
    private int level;

    @RegExp(ref = Main.REGEXP_ID)
    private String name;

    private Quotation quotation;

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
