/**
 * 
 */
package hackathon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * @author Phanindhar Bodla
 * 
 */
class Chain {
	char start, end;
	short length, startCharMap[], endCharMap[];
	ArrayList<String> list;

	public Chain(String string) {
		this.length = 0;
		this.startCharMap = new short[26];
		this.endCharMap = new short[26];
		this.list = new ArrayList<String>();
		this.start = string.charAt(0);
		this.add(string);
	}

	public void add(String string) {
		this.list.add(string);
		this.length++;
		this.startCharMap[string.charAt(0) - 'A']++;
		this.end = string.charAt(string.length() - 1);
		this.endCharMap[this.end - 'A']++;
	}

	@Override
	public int hashCode() {
		String result1 = start + "" + end;
		return result1.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chain other = (Chain) obj;
		if (end != other.end)
			return false;
		if (start != other.start)
			return false;
		return true;
	}
}

 class BruteForceChaining {
	public static void main(String[] args) throws IOException {
		File f = new File(args[0]); // File access Object to Dictionary
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader(f));
		String strLine = "", filedata = "", current;
		StringTokenizer stk;
		LinkedHashSet<String> input;
		int N, M, counter = 0;
		while ((strLine = br.readLine()) != null) {
			filedata += strLine + " ";
		}
		stk = new StringTokenizer(filedata);
		N = Integer.parseInt(stk.nextToken());
		M = N;
		input = new LinkedHashSet<String>();
		while (stk.hasMoreTokens() && N != 0) {
			input.add(stk.nextToken());
			N--;
		}
		HashMap<Chain, Chain> mapChains = new HashMap<Chain, Chain>();
		Chain tempChain = null;
		do {
			current = getStartValue(input, M);
			if (current != null) {
				counter++;
				tempChain = new Chain(current);
				System.out.println(current);
				while ((current = popValue(input,
						current.charAt(current.length() - 1))) != null) {
					System.out.println(current);
					counter++;
					tempChain.add(current);
				}
				//System.out.println(counter);
				M-=counter;
				counter=0;
				mapChains.put(tempChain, tempChain);
				break;
			}
		} while(M!=0);
		br.close();
	}

	private static String getStartValue(LinkedHashSet<String> input, int M) {
		Iterator<String> it = input.iterator();
		Random n = new Random();
		int start = n.nextInt() % M;
		while (it.hasNext()) {
			start--;
			String temp = it.next();
			if (start == 0) {
				input.remove(temp);
				return temp;
			}

		}
		return null;
	}

	private static String popValue(LinkedHashSet<String> input, char start) {
		Iterator<String> it = input.iterator();
		while (it.hasNext()) {
			String temp = it.next();
			if (temp.charAt(0) == start) {
				input.remove(temp);
				return temp;
			}

		}
		return null;
	}

}
