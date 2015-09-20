/*
 * Guy Moore
 * Lab 5
 * 8/31/15
 *
 * Randy Scovil | Cuesta College | CIS 232
 */
import java.util.ArrayList;
import java.util.Random;

class L5GMoo
{
	public static void main(String[] args)
	{
		ArrayList<String> arrStr = new ArrayList<String>();
		ArrayList<Integer> arrInt = new ArrayList<Integer>();
		ArrayList<Double> arrDoub = new ArrayList<Double>();

		RandomString ranStr = new RandomString();
		for(int x = 0; x < 10; x++)
		{
			arrStr.add(ranStr.nextString(8));
			arrInt.add(ranStr.nextInt());
			arrDoub.add(ranStr.nextDouble());
		}

		displayList(arrStr);
		displayList(arrInt);
		displayList(arrDoub);

		Lab5Sort(arrStr);
		Lab5Sort(arrInt);
		Lab5Sort(arrDoub);

		displayList(arrStr);
		displayList(arrInt);
		displayList(arrDoub);
	}

	public static <T> void displayList(ArrayList<T> arr)
	{
		for(T ele : arr)
		{
			System.out.println(ele);
		}
	}

	public static <AnyType extends Comparable <? super AnyType>>
		void Lab5Sort(ArrayList<AnyType> gAL)
	{
		for(int x = 0; x < gAL.size() - 1; x++)
		{
			int lowestIndex = x;
			for(int y = x + 1; y < gAL.size(); y++)
			{
				if(gAL.get(lowestIndex).compareTo(gAL.get(y)) > 0)
				{
					lowestIndex = y;
				}
			}

			if(lowestIndex != x)
			{
				AnyType tmp = gAL.get(lowestIndex);
				gAL.set(lowestIndex, gAL.get(x));
				gAL.set(x, tmp);
			}
		}

	}
}

class RandomString extends Random
{
	private static final long serialVersionUID = 1L;

	public RandomString()
	{
		super();
	}

	/**
	 * Takes random ints and builds them into a string.
	 */
	public String nextString(int length)
	{
		StringBuilder buffer = new StringBuilder();
		for (int x = 0; x < length; x++)
		{
			buffer.append((char)this.nextInt());
		}

		return buffer.toString();
	}
}
