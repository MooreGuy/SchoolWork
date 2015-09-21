/**
 * Guy Moore
 * Randy Scovil
 * Assignment 1
 * Cuesta College
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class A1232GMoo
{
	private static final String[] options = {
		"quit",
		"create shape",
		"list shapes"
	};
	private static ArrayList<Shape> shapes = new ArrayList<Shape>();

	public static void main(String[] args)
	{
		// The program exits through System.exit, so this works.
		while(true) {
			printMenu();
			handleOption(getInput("Select an option: "));
		}
	}

	public static void printMenu()
	{
		for(String option : options)
		{
			System.out.println(option);
		}
	}

	private static void handleOption(String selectedOption)
	{
		if(selectedOption.equals("quit")) {
			System.exit(0);
		}
		else if(selectedOption.equals("create shape"))
		{
			String input = getInput("Enter shape type: ");

			Shape createdShape = createShape(input);

			// Print out the shape and add it to the shapes list.
			System.out.println(createdShape.toString());
			shapes.add(createdShape);
		}
		else if(selectedOption.equals("list shapes"))
		{
			boolean validInput = false;
			do
			{
				String input = getInput(
					"Enter sort method, ascending or descending: ");
				if(input.equals("ascending"))
				{
					listShapes(shapes);
					validInput = true;
				}
				else if(input.equals("descending"))
				{
					listShapes(shapes);
					validInput = true;
				}
			}
			while(validInput == false);
		}
	}

	public static void listShapes(List<Shape> shapes)
	{
		for(Shape shape : shapes)
		{
			System.out.println(shape.toString());
		}
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
