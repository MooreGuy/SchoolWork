/**
 * Guy Moore
 * Randy Scovil
 * Assignment 1
 * Cuesta College
 */

public class A1232GmooRegularHexagon extends A1232GmooShape
{
	private double sideLength;

	public A1232GmooRegularHexagon(double sideLength)
	{
		this.sideLength = sideLength;
	}

	public double perimeter()
	{
		return sideLength * 6;
	}

	public double area()
	{
		return (sideLength * sideLength) * ((3 * Math.sqrt(3)) / 2);
	}

	public String toString()
	{
		return "RegularHexagon: " + sideLength;
	}
}
