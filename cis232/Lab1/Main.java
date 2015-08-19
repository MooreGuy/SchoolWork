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
		ArrayList<Integer> nums = getIntInput();
		if(nums.size() == 0)
		{
			System.out.println("No numbers entered.");
			return;
		}

		displayTotalElements(nums);
		sortIntsAscending(nums);
		displayAllValuesAsc(nums);
		displayLowHigh(nums);
		displayAverage(nums);
		displayMean(nums);
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
			Pattern p = Pattern.compile("-?[0-9]+");
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
		System.out.print("The total numbers input:");
		System.out.printf("%16d\n", arr.size());

		System.out.println("\n");
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

		System.out.println("\n");
	}

	private static void displayLowHigh(ArrayList<Integer> arr)
	{
		System.out.print("The lowest number:");
		System.out.printf("%22d\n", arr.get(0));
		System.out.print("The highest number:");
		System.out.printf("%21d\n", arr.get(arr.size() - 1));
		System.out.println("\n");
	}

	private static void displayAverage(ArrayList<Integer> arr)
	{
		int sum = 0;
		for(int i = 0; i < arr.size(); i++)
		{
			sum += arr.get(i);
		}

		System.out.print("The average is:");
		System.out.printf("%25.2f\n", (double)sum / arr.size());

		System.out.println("\n");
	}

	private static void displayMean(ArrayList<Integer> arr)
	{
		int freq = 1;
		int mode = arr.get(0);
		int curNum = arr.get(0);
		int curFreq = 1;
		for(int i = 1; i < arr.size(); i++)
		{
			if(curNum != arr.get(i))
			{
				if(curFreq > freq)
				{
					mode = curNum;
					freq = curFreq;
				}
				curFreq = 1;
				curNum = arr.get(i);
			}
			else
			{
				curFreq++;
			}
		}

		System.out.print("The mode is:");
		System.out.printf("%28d\n", mode);
		System.out.print("The frequency of the mode is:");
		System.out.printf("%11d\n", freq);
		System.out.println("\n");
	}
}
