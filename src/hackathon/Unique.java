package hackathon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Unique {
	public static int[] rows;
	public static int[][] data ;
	public static void main(String[] args) throws IOException {
		File f = new File(args[0]); // File access Object to Dictionary
		BufferedReader br = new BufferedReader(new FileReader(f));
		String strLine = "";
		strLine = br.readLine();
		int R, C, temp;
		StringTokenizer stk = new StringTokenizer(strLine);
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		data = new int[R][C];
		temp = R;
		while (temp != 0) {
			temp--;
			strLine = br.readLine();
			stk = new StringTokenizer(strLine);
			for (int i = 0; i < C; i++) {
				data[temp][i] = Integer.parseInt(stk.nextToken());
			}
		}
		br.close();
		uniqueRows();
		for(int i=0;i<rows.length;i++)
		System.out.println(rows[i]);
	}

	private static void uniqueRows() {
		rows = new int[data.length];
		ArrayList<Integer> temp = new ArrayList<>();
		for (int i = 0; i < data.length; i++)temp.add(i);
		mapColumnData(temp, 0);
	}

	private static void mapColumnData(ArrayList<Integer> rowsList, int currentColumn) {
		HashMap<Integer, ArrayList<Integer>> hashes = new HashMap<>();
		for (int i = 0; i < rowsList.size(); i++) {
			if (hashes.get(data[rowsList.get(i)][currentColumn]) == null) {
				ArrayList<Integer> temp = new ArrayList<>();
				hashes.put(data[rowsList.get(i)][currentColumn], temp);
			}
			hashes.get(data[rowsList.get(i)][currentColumn]).add(rowsList.get(i));
		}
		currentColumn++;
		if (hashes.size() != 0) {
			for (ArrayList<Integer> value : hashes.values()) {
				if (value.size() == 1) {
					rows[value.get(0)] = value.get(0);
				} else {
					if(currentColumn<data[0].length)
					{
						mapColumnData( value, currentColumn);
					}
					else
					{
						for ( Integer integer : value) {
							rows[integer]=value.get(0);
						}
					}
				}
			}
		}
	}
}
