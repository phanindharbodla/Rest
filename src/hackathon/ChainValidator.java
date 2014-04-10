package hackathon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class ChainValidator {
	public static void main(String[] args) throws IOException {
		File f = new File(args[0]); // File access Object to Dictionary
		BufferedReader br = new BufferedReader(new FileReader(f));// (please add) new InputStreamReader(System.in));//
		String strLine = "", filedata = "", current, prev = "";
		StringTokenizer stk;
		LinkedHashSet<String> output, input;
		int counter = 0, N;
		boolean validOutput = true;
		while ((strLine = br.readLine()) != null) {
			filedata += strLine + " ";
		}
		stk = new StringTokenizer(filedata);
		N = Integer.parseInt(stk.nextToken());
		input = new LinkedHashSet<String>();
		while (stk.hasMoreTokens()) {
			input.add(stk.nextToken());
			N--;
			if (N == 0)
				break;
		}
		while ((strLine = br.readLine()) != null) {
			filedata += strLine + " ";
		}
		br.close();
		output = new LinkedHashSet<String>();
		if (stk.hasMoreTokens()) {
			prev = stk.nextToken();
			counter++;
			output.add(prev);
		}
		while (stk.hasMoreTokens()) {
			current = stk.nextToken();
			output.add(current);
			if (prev.charAt(prev.length() - 1) != current.charAt(0)) {
				validOutput = false;
				break;
			}
			prev = current;
			counter++;
		}
		if (!input.containsAll(output) || output.size() != counter) {
			validOutput = false;
		}
		if (validOutput) {
			System.out.println(counter);
		} else {
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
