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
		ArrayList<Integer> myArray = getIntegerInput();
		for(Integer myInt : myArray)
		{
			System.out.println(myInt);
		}
	}

	/**
	 * Get input until an empty string is inputted, discard the
	 * empty string. Disregaurd non-numbers. Allow negatives.
	 * Don't allow something that doesn't fit in an Integer.
	 */
	private static ArrayList<Integer> getIntegerInput()
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
					System.out.println(curGroup + ": doesn't fit in an Integer.");
				}
			}
		}

		return numbers;
	}
}
