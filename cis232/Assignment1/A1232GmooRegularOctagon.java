/**
 * Guy Moore
 * Randy Scovil
 * Assignment 1
 * Cuesta College
 */
import java.util.Random;

public class A1232GmooRegularOctagon extends A1232GmooShape
{
	private double sideLength;

	public A1232GmooRegularOctagon(double sideLength)
	{
		this.sideLength = sideLength;
	}

	public double perimeter()
	{

		return 8 * sideLength;
	}

	public double area()
	{
		return 2 * Math.exp(sideLength) * (1 + Math.sqrt(2));
	}

	public String toString()
	{
		return "RegularOctagonSideLength: " + sideLength;
	}
}
