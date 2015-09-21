/**
 * Guy Moore
 * Randy Scovil
 * Assignment 1
 * Cuesta College
 */

public class Square extends Shape
{
	private double sideLength;

	public Square(double sideLength)
	{
		this.sideLength = sideLength;
	}

	public double perimeter()
	{
		return sideLength * 2;
	}

	public double area()
	{
		return sideLength * sideLength;
	}

	public String toString()
	{
		return "Square: " + sideLength;
	}
}
