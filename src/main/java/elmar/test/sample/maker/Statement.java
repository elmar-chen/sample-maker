package elmar.test.sample.maker;

import com.sun.istack.internal.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Statement {

	public int getLevel(@RegExp("\\s*") String src){
	    int numOfChar = 0;
	    int numOfTab = 0;
	    for(int i=0;i<src.length();i++){
	        if(src.charAt(i)==' '){
	            numOfChar++;
            }
            if(src.charAt(i)=='\t'){
                numOfTab++;
            }
        }
	    return numOfChar/4+numOfTab;
    }

    private Quotation quotation;
	@Nullable @PaddingBefore(":")
	private Expression expression;
}
