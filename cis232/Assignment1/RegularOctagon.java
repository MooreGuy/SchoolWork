/**
 * Guy Moore
 * Randy Scovil
 * Assignment 1
 * Cuesta College
 */
import java.util.Random;

public class RegularOctagon extends Shape
{
	private double sideLength;

	public RegularOctagon(double sideLength)
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
