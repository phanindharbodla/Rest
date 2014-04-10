package hackathon;
//Phanindhar Bodla
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Eulearian {
	static int first[];
	static int last[];
	static boolean a[][];
	static boolean flag[];
 
	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(br.readLine());
		int num;
		String input[];
		int firstAt;
		int lastAt;
		while (cases-- > 0) {
			first = new int[100];
			last = new int[100];
			flag = new boolean[100];
			a = new boolean[100][100];
			num = Integer.parseInt(br.readLine());
			for (int i = 0; i < num; i++) {
				input = br.readLine().split(" ");
				firstAt = Integer.parseInt(input[0]);
				lastAt = Integer.parseInt(input[1]);
				
				first[firstAt]++;
				
				last[lastAt]++;
				flag[firstAt] = true;
				flag[lastAt] = true;
				a[firstAt][lastAt] = true;
			}
			solve();
		}
	}
 
	private static void solve() {
		int start = check();
		int wc = 0;
		int cnt = 0;
		int i = 0;
		boolean isPossible = false;
		boolean visited[] = new boolean[100];
		if (start != -1) {
			while (i < 100) {
				if (flag[i])
					wc++;// total count of the nodes 
				i++;
			}
			i = 0;
			while (!flag[i] && i < 100) // pointing i to the first node of our graph 
				i++;
			visited[i] = true; // marking it as visited 
			connect(i, visited);// this is a method which passes from all the possible edges and mark them visited using Depth first search
			i = 0;
			while (i < 100) { // now we count the number of visited nodes in count cnt 
				if (visited[i])
					cnt++;
				i++;
			}
			if (wc == cnt)
				System.out.println("yes"); // visited count  matches total count
			else
				System.out.println("no"); // some unvisited nodes exist 
 
			isPossible = true;
 
		}
		if (!isPossible)
			System.out.println("no");
	}
 
	private static void connect(int ii, boolean[] visited) {
		for (int i = 0; i < 100; i++) {
			if (a[i][ii] || a[ii][i]) {
				if (!visited[i]) {
					visited[i] = true;
					connect(i, visited);
				}
			}
		}
	}
 
	private static int check() {
		int start = -1;
		int end = -1;
		int diff;
		for (int i = 0; i < 100; i++) {
			diff = first[i] - last[i];// difference between no of occurrences at start and end point
			if (diff == 1) { // shows the sign of a the starting point occurrence 
				if (start == 1) // which means we already got a start point 
					return -1; // multiple start point not possible in a trail
				start = 1;// here i is the stating point
				continue;
			}
			if (diff == -1) { // shows the sign of a the ending point occurrence 
				if (end == 1) //   which means we already got a end point
					return -1; //  multiple end point not possible in a trail
				end = 1;// here i is the ending point 
				continue;
			}
			if (diff > 0) { // diff can't be more than zero for non start and end points 
				return -1; // not a trail
			}
		}
		return 0; // could be a trail
	}
}