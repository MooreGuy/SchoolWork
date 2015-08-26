import java.util.Random;

public class L4GMooRegOct extends Shape implements Comparable
{
	public static void main(String[] args)
	{
		L4GMooRegOct[] octArr = new L4GMooRegOct[10];

		Random ran = new Random(System.currentTimeMillis());
		for(int x = 0; x < octArr.length; x++)
		{
			octArr[x] = new L4GMooRegOct(ran.nextDouble());
		}
		
		Lab4Sort(octArr);

		for(L4GMooRegOct oct : octArr)
		{
			System.out.println(oct);
		}

	}

	public static void Lab4Sort(Comparable[] c)
	{
		int endLength = c.length - 1;
		for(int x = 0; x < endLength; x++)
		{
			int lowest = x;
			for(int y = x + 1; y < c.length; y++)
			{
				if(c[lowest].compareTo(c[y]) > 0)
				{
					lowest = y;
				}
			}

			if(lowest != x)
			{
				Object tmp = c[x];
				c[x] = c[lowest];
				c[lowest] = (Comparable)tmp;
			}
		}
	}

	public int compareTo(Object o)
	{
		if(!(o instanceof Shape))
		{
			throw new ClassCastException();
		}
		double oPerim = ((Shape)o).perimeter();
		double perim = perimeter();

		if(oPerim > perim)
		{
			return -1;
		}
		else if(oPerim < perim)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}

	private double sideLength;

	public L4GMooRegOct(double sideLength)
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
		return "SideLength: " + sideLength + ", Area: " + area() + ", Perimeter: " + perimeter() + ", Semiperimeter: " + semiperimeter();
	}
}

abstract class Shape
{
	public abstract double area();
	public abstract double perimeter();

	public double semiperimeter()
	{
		return perimeter() /2;
	}
}
