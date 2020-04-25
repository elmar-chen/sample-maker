package elmar.test.sample.maker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class NumberSampler {
	int DEFAULT_NUM_SPACE_AS_TAB = 4;
	public static void main(String[] args) throws IOException {

	    Pattern.matches("a[a-z]+@\\.com", "abc@.com");
	    
	    InputStream is = ClassLoader.getSystemResourceAsStream("lang.def");
		new NumberSampler().parse(is);

	}

	int numOfSpaceAsTab = -1;
	private void parse(InputStream is) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = null;
		Exp root = new Exp("#root");
        Exp last = root;
		int lastIndent = -1;
		while ((line = br.readLine()) != null) {
			if (StringUtils.isBlank(line))
				continue;
			int indent = determineIndent(lastIndent, line);
			Exp exp = parseLine(line.trim());
			
			exp.parent = determineParent(last, lastIndent, indent);
			exp.parent.choices.add(exp);
			last = exp;
			lastIndent = indent;
		}
		System.out.println(root);
	}
    private Exp determineParent(Exp last, int lastIndent, int indent) {
        if(indent < lastIndent) {
            return last.parent.parent;
        }
        else  if(indent == lastIndent) {
            return last.parent;
        }
        return last;
    }
	private Exp parseLine(String exp) {
        return new Exp(exp);
    }
    private int determineIndent(int lastIndent, String line) {
		int numOfTab = 0;
		int numOfSpace = 0;
		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			if(c==' ') numOfSpace ++;
			if(c=='\t') numOfTab ++;
		}
		if(this.numOfSpaceAsTab < 0 && numOfSpace>0) {
			this.numOfSpaceAsTab = numOfSpace;
		}
		
		return  numOfTab + numOfSpace/this.numOfSpaceAsTab; //if this.numOfSpaceAsTab<0, numOfSpace is 0.
	}
}

class Exp {
	private String exp;
    public Exp(String exp) {
        this.exp = exp;
    }
    public Exp() {
    }
    List<Exp> choices = new ArrayList<Exp>();
	Exp parent;
    public String proportionExp;
    public String label; 
    @Override
    public String toString() {
        return "Exp [exp=" + exp + ", choices=" + choices + "]";
    }
	
}