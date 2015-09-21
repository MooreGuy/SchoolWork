/**
 * Guy Moore
 * Randy Scovil
 * Assignment 1
 * Cuesta College
 */

public class A1232GmooSquare extends A1232GmooShape
{
	private double sideLength;

	public A1232GmooSquare(double sideLength)
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
