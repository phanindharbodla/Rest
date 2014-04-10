package hackathon;

import java.io.IOException;
import java.util.Arrays;

public class FairDistribution {

	
	private static int index;
	public static void main(String[] args) throws  IOException {
		   int a[],i,j,n,k,prev,hist;
	        java.io.BufferedReader in = new java.io.BufferedReader (new java.io.InputStreamReader (System.in));
	        k=Integer.parseInt(in.readLine());
	        n=Integer.parseInt(in.readLine());
	        String ip[]=in.readLine().split(" ");
	        a=new int[n];
	        for(i=0;i<n;i++)
	        {    a[i]=Integer.parseInt(ip[i]);
	        //System.out.println(a[i]);
	        }
	        Arrays.sort(a);
	        int cache[][] = new int [k][n];
	        cache[0] = a;
	        for(i=0;i<n;i++)
	        {
	        System.out.println(cache[0][i]);
	        }
	        for(i=0;i<n-1;i++)
	        {
	        cache[1][i]= cache[0][i+1]-cache[0][i];
	        }
	        for(i=0;i<n;i++)
	        {
	        cache[0][i]=0;
	        }
	        int temp =2;
	        for(i=0;i<n-temp;i++)
	        {
	        	for(j=2;j<k;j++)
		        {
	        		hist = cache[j-2][i];
	        		prev = cache[j-1][i];
	        		cache[j][i]=(prev<<1)-hist+((j)*cache[1][j-1]);
		        }temp++;
	        }
	        int minIndex = 0, minValue=0;
	        
	        for(j=0;j<k;j++)
	        {
	        	System.out.println();
	        	for(i=0;i<n;i++)
		        {
	        		System.out.print(cache[j][i]+" ");
		        }
	        }
	        //System.out.println(cache[j][minIndex]);
	        increasing(null);
	        
	}
	public static Integer increasing(Integer[] numbers) {
		Integer  inc=1;
		for (int i=1; i < numbers.length; i++)
		    if (numbers[i-1] >= numbers[i]) {
		        inc = 0;
		        break;
		    }
		return inc;
		}
}
