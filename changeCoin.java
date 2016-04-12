import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class changeCoin {
	static Integer[] d = {1, 5, 10, 25};			// array of denominations to be used
	
	public static void main (String[] args) {

		// take usr input from here
		Scanner input = new Scanner (System.in);
		System.out.println("Please enter your coin:");
		int coin = input.nextInt();
		if (coin < 0) {
			System.out.println("Please enter a +ve integer representing Coin to Change.");
			return;
		}

		sortDenominations(d);
		System.out.println("\nBy Greedy Approach:");
		greedyChange(d, coin);
		System.out.println("\nBy Dynamic Programming:");
		dynamicProgChange(d, coin);
	}


	/* Sorting deno array in descending order before use, 
	 * so that we start with the largest denomination */
	public static Integer[] sortDenominations (Integer[] d){
		Arrays.sort(d, Collections.reverseOrder());			
		return d;
	}
		
	/* The Iterative Approach */
	public static ArrayList<Integer> greedyChange(Integer[] d, int coinToChange)	
	{					
		System.out.println("Coin to Change: "+coinToChange);			
	    ArrayList<Integer> change = new ArrayList<Integer>();		    // create an array list to store changes bc dynamic
		
		for (int i = 0; i < d.length; i++)		// iterate over each denomination
		{	
			while (d[i] <= coinToChange)					
			{		
				System.out.print("Change: "+coinToChange+" - "+d[i]);	
				coinToChange = coinToChange - d[i];	
				System.out.print(" = "+coinToChange+"\n");	
				change.add(d[i]);
			}
			if (coinToChange == 0)		// no need of extra iterations
				break;	
		}
	      System.out.println("Min No. of Coins: "+change.size()+""+change);
	      return change;
	}
	
	/* The Recursive Approach */
	public static ArrayList<Integer> dynamicProgChange(Integer[] d, int coinToChange){

		// create an array list to store changes bc dynamic
	    ArrayList<Integer> change = new ArrayList<Integer>();		  

		// table[i] will be storing the number of solutions for
		// value i. We need n+1 rows as the table is constructed
		// in bottom up manner using the base case (n = 0)

		int[] table = new int[coinToChange+1];   
		
		// Initialize all table values with a large number
		for (int i = 0; i < coinToChange+1; i++)
			table[i] = 100;	 		

		// Base case (If given value is 0)
		table[0] = 0;

		// Pick all coins one by one and update the table[] values
		// after the index greater than or equal to the value of the
		// picked coin
		for (int i = 0; i < d.length; i++) {
			for (int j = d[i]; j < coinToChange; j++) {
			    if(table[j] > (1+table[j-d[i]]) )
			    {
				    table[j] = 1+table[j-d[i]];
				    change.add(d[i]);
			    }
			}
		}
		    
	    /* for (int i = 0; i < coinToChange+1; i++)
		    System.out.println("Table ["+i+"]: "+table[i]); */
	     
	    System.out.println("Min No. of Coins: "+change.size()+""+change);    
	    return change;
	}
}

