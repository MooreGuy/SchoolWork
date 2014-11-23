import java.util.*;

public class Lab17GMoo
{
	public static void main(String[] args)
	{
		int [] myNumbers;

		System.out.println("Guy Moore");
		
		myNumbers = randomNumbers(2); 
		System.out.println(myNumbers[0]);
			
	}

	/* Takes in a integer parameter and returns an integer array filled
	 * with randomly created values the size of the parameter.
	 * 
	 * First creates the array, then creates a Random object to create the
	 * random numbers to fill the array.
	 * Next, it loops through the array adding randomly generated numbers
	 * to each element
	 * Finaly, the method returns the arary.
	 */	
	public static int[] randomNumbers( int size )
	{
		int [] numbers = new int[size];
		Random gen = new Random();	

		for( int i = 0; i < size; i++ )
		{
			numbers[i] = gen.nextInt();
		}
	
		return numbers;		
	}

	/* 
	 * Prints the out the array numbers, with each line having as many
	 * numbers as the parameter numPerLine.
	 *
	 * Loops through first loop to keep track of the entire array, and
	 * then the second loop keeps track of the numbers in the current line.
	 */
	public static void printArray( int[] numbers, int numPerLine )
	{
		//Goes through entire array..
		for( int i = 0; i < numbers.length; i++ )
		{
			//Keeps track of how many numbers should be printed per line.
			for( int k = 0; k < numPerLine; k++ )
			{
				System.out.print(numbers[i] + " ");
			} 
			
			//Return to next line.	
			System.out.println("");
		}
	}
}
