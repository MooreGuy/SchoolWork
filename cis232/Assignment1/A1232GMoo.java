/**
 * Guy Moore
 * Randy Scovil
 * Assignment 1
 * Cuesta College
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Comparator;

public class A1232GMoo
{
	// Variables to hold how the data should be presented to the user
	// The user inputs these before data is presented.
	private static String shapeCompareOption = "";
	private static String shapeSortOption = "";

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
			// Get the compare method
			String input;
			do
			{
				input = getInput("Enter compare method, area or perimeter: ");
			}
			while(!input.equals("area") && !input.equals("perimeter"));
			shapeCompareOption = input;

			// Get the group by
			do
			{
				input = getInput("Group by class yes or no? ");
			}
			while(!input.equals("yes") && !input.equals("no"));

			Comparator<Shape> comp;
			if(input.equals("yes"))
			{
				comp = new AscedingClassGroupComparator<Shape>();
			}
			else
			{
				comp = new AscendingComparator<Shape>();
			}

			sort(shapes, comp);
			listShapes(shapes);
		}
	}

	public static void listShapes(List<Shape> shapes)
	{
		String input;
		boolean validInput = false;
		do
		{
			input = getInput("Enter sort method, ascending or descending: ");
		}
		while(!input.equals("ascending") && !input.equals("descending"));

		if(input.equals("ascending"))
		{
			for(int x = 0; x < shapes.size(); x++)
			{
				System.out.println(shapes.get(x).toString());
			}
		}
		else
		{
			for(int x = shapes.size() - 1; x >= 0; x--)
			{
				System.out.println(shapes.get(x).toString());
			}
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

	static class AscendingComparator<AnyType>
		implements Comparator<Shape>
	{
		public int compare(Shape s1, Shape s2)
		{
			return compareShapes(s1, s2);
		}
	}

	// Sort the by class name and dimensions within the class name sort.
	static class AscedingClassGroupComparator<AnyType>
		implements Comparator<Shape>
	{
		public int compare(Shape s1, Shape s2)
		{
			int classNameCompare = s1.getClass().getName().compareTo(
				s2.getClass().getName());

			// If it is the same class name then compare the actual values
			if(classNameCompare == 0)
			{
				return compareShapes(s1, s2);
			}

			return classNameCompare;
		}
	}

	public static int compareShapes(Shape s1, Shape s2)
	{
		Double shape1Value;
		Double shape2Value;
		if(shapeCompareOption == "area")
		{
			shape1Value = new Double(s1.area());
			shape2Value = new Double(s2.area());
		}
		else
		{
			shape1Value = new Double(s1.perimeter());
			shape2Value = new Double(s2.perimeter());
		}

		return shape1Value.compareTo(shape2Value);
	}

	public static <AnyType> void sort(List<AnyType> arr,
		Comparator<? super AnyType> cmp)
	{
		for(int x = 0; x < arr.size() - 1; x++)
		{
			int lowestIndex = x;
			for(int y = x + 1; y < arr.size(); y++)
			{
				if(cmp.compare(arr.get(lowestIndex), arr.get(y)) == 1)
				{
					lowestIndex = y;
				}
			}

			if(lowestIndex != x)
			{
				AnyType tmp = arr.get(x);
				arr.set(x, arr.get(lowestIndex));
				arr.set(lowestIndex, tmp);
			}
		}
	}
}
