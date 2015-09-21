/**
 * Guy Moore
 * Randy Scovil
 * Assignment 1
 * Cuesta College
 */

public class A1232GmooEquilateralTriangle extends A1232GmooShape
{
	private double sideLength;

	public A1232GmooEquilateralTriangle(double sideLength)
	{
		this.sideLength = sideLength;
	}

	public double area()
	{
		return (sideLength * sideLength) / 2;
	}

	public double perimeter()
	{
		return sideLength * 3;
	}

	public String toString()
	{
		return "EquilateralTriangle " + sideLength;
	}
}
