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
		String input = getInput("Enter shape type: ");

		Shape createdShape = createShape(input);
		System.out.println(createdShape.toString());
	}

	public static Shape createShape(String input)
	{
		if(input.equals("Circle"))
		{
			double radius = Double.parseDouble(getInput("Enter radius: "));
			return new Circle(radius);
		}
		else if(input.equals("Rectangle"))
		{
			double length = Double.parseDouble(getInput("Enter length: "));
			double width = Double.parseDouble(getInput("Enter width: "));
			return new Rectangle(length, width);
		}
		else if(input.equals("EquilateralTriangle"))
		{
			double sideLength = Double.parseDouble("Enter side length: ");
			return new EquilateralTriangle(sideLength);
		}
		else if(input.equals("RegularOctagon"))
		{
			double sideLength = Double.parseDouble(getInput("Enter side length: "));
			return new RegularOctagon(sideLength);
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
