/**
 * Author: Guy Moore
 * Course Instructor: Randy Scovil
 * Project: Lab 1 CIS232
 * Due: 19-8-15
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main
{
	public static void main(String[] args)
	{
		ArrayList<Integer> myArray = getIntInput();
		displayTotalElements(myArray);

		sortIntsAscending(myArray);
		displayAllValuesAsc(myArray);

	}

	/**
	 * Get input until an empty string is inputted, discard the
	 * empty string. Disregaurd non-numbers. Allow negatives.
	 * Don't allow something that doesn't fit in an Int.
	 */
	private static ArrayList<Integer> getIntInput()
	{
		System.out.println("Enter integers, or only enter to exit");

		ArrayList<Integer> numbers = new ArrayList<Integer>();
		Scanner in = new Scanner(System.in);

		String curString;
		while(in.hasNextLine() && !(curString = in.nextLine()).equals(""))
		{
			Pattern p = Pattern.compile("-?[1-9]+");
			Matcher m = p.matcher(curString);
			while(m.find())
			{
				String curGroup = m.group();
				try
				{
					numbers.add(Integer.parseInt(curGroup));
				}
				catch(Exception NumberFormatException)
				{
					System.out.println(curGroup + ": doesn't fit in an Int.");
				}
			}
		}

		return numbers;
	}

	private static void sortIntsAscending(ArrayList<Integer> arr)
	{
		int minIndex;
		for(int x = 0; x < arr.size() - 1; x++)
		{
			minIndex = x;
			for(int y = x + 1; y < arr.size(); y++)
			{
				if(arr.get(minIndex) > arr.get(y))
				{
					minIndex = y;
				}
			}

			if(x != minIndex)
			{
				int temp = arr.get(x);
				arr.set(x, arr.get(minIndex));
				arr.set(minIndex, temp);
			}
		}

	}

	private static void displayTotalElements(ArrayList<Integer> arr)
	{
		System.out.printf("%s", "The total numbers input:");
		System.out.printf("%20d\n", arr.size());

		System.out.println("");
	}

	private static void displayAllValuesAsc(ArrayList<Integer> arr)
	{
		System.out.println("All numbers in ascending order:");
		for(int curInt = 0; curInt < arr.size();  curInt++)
		{
			if(curInt % 10 == 0)
			{
				System.out.println("");
			}

			System.out.print(arr.get(curInt) + " ");

		}

		System.out.println("");
	}
}
