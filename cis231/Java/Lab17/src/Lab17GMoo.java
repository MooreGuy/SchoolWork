import java.util.*;

public class Lab17GMoo
{
	public static void main(String[] args)
	{
		int [] myNumbers;

		System.out.println("Guy Moore");
		
		myNumbers = randomNumbers(10); 
		printArray( myNumbers, 2 );
		System.out.println(getLargest(myNumbers));
			
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
		//Goes through entire array. Doesn't increment i, because it is
		//printed in the next loop.
		for( int i = 0; i < numbers.length; )
		{
			//Keeps track of how many numbers should be printed per line.
			//Note that it must increment both i and k.
			for( int k = 0; k < numPerLine; k++, i++ )
			{
				System.out.print(numbers[i] + " ");
			} 
			
			//Return to next line.	
			System.out.println("");
		}
	}

	/*
	 * Takes in an integer array and searches for the largest number.
	 * Walks through the entire array sequentually, and compares the integer
	 * largest with every index to find the largest value, then returns it.
	 */
	public static int getLargest( int[] numbers )
	{
		int largest = numbers[0];

		for( int i = 1; i < numbers.length; i++ )
		{
			if( largest < numbers[i] )
			{
				largest = numbers[i];
			}
		}
		
		return largest;
	}
}
