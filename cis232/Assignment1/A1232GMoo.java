/**
 * Guy Moore
 * Randy Scovil
 * Assignment 1
 * Cuesta College
 */

import java.util.ArrayList;
import java.util.Scanner;

public class A1232GMoo
{
	public static void main(String[] args)
	{
	}

	public static double getLength(String prompt)
	{
		double input = -1;

		Scanner scn = new Scanner(System.in);
		do
		{
			System.out.print(prompt);

			if(scn.hasNextDouble())
			{
				input = scn.nextDouble();
			}
		}
		while(input < 0);

		return input;
	}

	public static Shape createShape(String input)
	{
		if(input.equals("Circle"))
		{
			return new Circle(getLength("Enter radius: "));
		}
		else if(input.equals("Rectangle"))
		{
			double length = getLength("Enter length: ");
			double width = getLength("Enter width: ");
			return new Rectangle(length, width);
		}
		else if(input.equals("EquilateralTriangle"))
		{
			return new EquilateralTriangle(getLength("Enter side length: "));
		}
		else if(input.equals("RegularOctagon"))
		{
			return new RegularOctagon(getLength("Enter side length: "));
		}
		else if(input.equals("RegularHexagon"))
		{
			return new RegularHexagon(getLength("Enter side length: "));
		}
		else if(input.equals("Square"))
		{
			return new Square(getLength("Enter side length: "));
		}

		// No shape type for the given input exists.
		return null;
	}

	public static String getInput(String prompt)
	{
		System.out.print(prompt);

		ArrayList<String> sta = new ArrayList<String>();

		Scanner scan = new Scanner(System.in);
		if(scan.hasNextLine())
		{
			return scan.nextLine();
		}

		return null;
	}
}
