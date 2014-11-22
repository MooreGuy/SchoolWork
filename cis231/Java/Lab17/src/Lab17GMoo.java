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
	
	public static int [] randomNumbers( int size )
	{
		int [] numbers = new int[size];
		Random gen = new Random();	

		for( int i = 0; i < size; i++ )
		{
			numbers[i] = gen.nextInt();
		}
	
		return numbers;		
	}
}
