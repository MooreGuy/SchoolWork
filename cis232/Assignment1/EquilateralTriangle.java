public class EquilateralTriangle extends Shape
{
	private double sideLength;

	public EquilateralTriangle(double sideLength)
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
