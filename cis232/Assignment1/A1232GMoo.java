import java.util.ArrayList;
import java.util.Scanner;

public class A1232GMoo
{
	public static void main(String[] args)
	{
		String input = getInput("Please enter shape dimensions.");
		System.out.println(input);
	}

	public static String getInput(String prompt)
	{
		ArrayList<String> sta = new ArrayList<String>();

		Scanner scan = new Scanner(System.in);
		if(scan.hasNextLine())
		{
			return scan.nextLine();
		}

		return null;
	}
}
