/**
 * Guy Moore
 * Instructor: Randy Scovil
 * Cuesta College CIS 232 Fall 2015
 * Lab 6
 */

import java.util.Comparator;
import java.util.ArrayList;
import java.util.Random;

public class L6_232Gmoo
{
	public static void main(String[] args)
	{
		Point[] arr = new Point[10];
		for(int x = 0; x < 10; x++)
		{
			arr[x] = new Point();
		}
		outputArray(arr, 3);

		AscendingComparator<Point> c = new AscendingComparator<Point>();
		sort(arr, c);

		outputArray(arr, 3);

		DescendingComparator<Point> dc = new DescendingComparator<Point>();
		sort(arr, dc);

		outputArray(arr, 3);
	}

	static class AscendingComparator<AnyType extends Comparable<? super AnyType>>
		implements Comparator<AnyType>
	{
		public int compare(AnyType o1, AnyType o2)
		{
			return o1.compareTo(o2);
		}
	}

	static class DescendingComparator<AnyType extends Comparable<? super AnyType>>
		implements Comparator<AnyType>
	{
		public int compare(AnyType o1, AnyType o2)
		{
			return -(o1.compareTo(o2));
		}
	}


	public static <AnyType> void sort(AnyType[] arr,
		Comparator<? super AnyType> cmp)
	{
		System.out.println("Sorting.");
		for(int x = 0; x < arr.length - 1; x++)
		{
			int lowestIndex = x;
			for(int y = x + 1; y < arr.length; y++)
			{
				if(cmp.compare(arr[lowestIndex], arr[y]) == 1)
				{
					lowestIndex = y;
				}
			}

			if(lowestIndex != x)
			{
				AnyType tmp = arr[x];
				arr[x] = arr[lowestIndex];
				arr[lowestIndex] = tmp;
			}
		}
	}

	public static <AnyType> void outputArray(AnyType[] arr, int perLine)
	{
		int currentLine = 1;
		for(int x = 0; x < arr.length; x++, currentLine++)
		{
			if(currentLine == perLine)
			{
				System.out.println(arr[x]);
				currentLine = 0;;
			}
			else
			{
				System.out.print(arr[x] + " ");
			}
		}

		if(currentLine != 1)
		{
			System.out.println("");
		}
	}
}

class Point implements Comparable<Point>
{
	private double x;
	private double y;

	public Point()
	{
		Random r = new Random();
		this.x = r.nextDouble();
		this.y = r.nextDouble();
	}

	public Point(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	public int compareTo(Point o)
	{
		if(!(o instanceof Point))
		{
			// TODO: Throw a class cast exception.
			return 1;
		}

		if(o.getX() > x)
		{
			return -1;
		}
		else if(o.getX() == x)
		{
			if(o.getY() == y)
			{
				return 0;
			}
			else if(o.getY() > y)
			{
				return -1;
			}
			else
			{
				return 1;
			}
		}
		else
		{
			return 1;
		}
	}

	public boolean equals(Point o)
	{
		return true;
	}

	public String toString()
	{
		return "X: " + x + ", Y: " + y;
	}
}
