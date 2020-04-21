import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class NumberSampler {
	int DEFAULT_NUM_SPACE_AS_TAB = 4;
	public static void main(String[] args) throws IOException {
		InputStream is = ClassLoader.getSystemResourceAsStream("def.yaml");
		new NumberSampler().parse(is);

	}

	int numOfSpaceAsTab = -1;
	private void parse(InputStream is) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = null;
		Exp parent = null;
		int lastIndent = 0;
		while ((line = br.readLine()) != null) {
			if (StringUtils.isEmpty(line))
				continue;
			int indent = determineIndent(lastIndent, line);
		}
		System.out.println(is);
	}
	private int determineIndent(int lastIndent, String line) {
		int numOfTab = 0;
		int numOfSpace = 0;
		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			if(c==' ') numOfSpace ++;
			if(c=='\t') numOfTab ++;
		}
		if(lastIndent == 0 && numOfSpace>0) {
			this.numOfSpaceAsTab = numOfSpace;
		}
		
		return  numOfTab + numOfSpace/this.numOfSpaceAsTab; //if this.numOfSpaceAsTab<0, numOfSpace is 0.
	}
}

class Exp {
	List<Exp> choices;

}