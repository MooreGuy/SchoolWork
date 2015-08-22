/**
 * Author: Guy Moore
 * Course Instructor: Randy Scovil
 * Project: Lab 2 CIS232
 * Due: 24-8-15
 */

import java.util.StringTokenizer;
import java.util.NoSuchElementException;

class L2_232Gmoo
{
	public static void main( String [ ] args )
	{
		Date d1 = new Date( );
		Date d2 = new Date( 1, 1, 1998 );
		// change values for this constructor
		Date d3 = new Date( 8, 18, 2010 );
		// indented code is code you add
		Date d4 = new Date("10 10 2010");
		d1.setYear(2010);
		d1.setMonth(8);
		d1.setDay(18);
		System.out.println( "Date 1: " + d1 );
		System.out.println( "Date 2: " + d2 );
		System.out.println( "Date 3: " + d3 );
		System.out.println( "Date 4: " + d4 );
		System.out.println( "Date1==Date2?: " + d1.equals( d2 ) );
		System.out.println( "Date1==Date3?: " + d1.equals( d3 ) );
		System.out.println( "Date3==Date4?: " + d4.equals( d3 ) );
		System.out.println("Date 1's year is: " + d1.getYear());
		System.out.println("Date 1's month is: " + d1.getMonth());
		System.out.println("Date 1's day is: " + d1.getDay());
		System.out.println("Date 4's year is: " + d4.getYear());
		System.out.println("Date 4's month is: " + d4.getMonth());
		System.out.println("Date 4's day is: " + d4.getDay());
	}
}

/**
 * Date class from Mark Allen Weiss
 */
// Minimal Date class that illustrates some Java features
// No error checks or javadoc comments
class Date
{
	// Zero-parameter constructor
	public Date()
	{
		month = 1;
		day = 1;
		year = 2015;
	}

	// Single parameter constructor
	public Date(String dateString)
	{
		// Clean input from user.
		dateString = dateString.trim();

		StringTokenizer stk = new StringTokenizer(dateString);
		try
		{
			month = Integer.parseInt(stk.nextToken("/"));
			day = Integer.parseInt(stk.nextToken("/"));
			year = Integer.parseInt(stk.nextToken("/"));
		}
		catch(NoSuchElementException nse) {
			System.out.println("Malformed date string. Format is month/day/year. Defaulting to 1/1/2015.");
			month = 1;
			day = 1;
			year = 2015;
		}
		catch(NumberFormatException nfe) {
			System.out.println("Malformed date string. Dates must be integer numbers. Defaulting to 1/1/2015");
			month = 1;
			day = 1;
			year = 2015;
		}
	}

	// Three-parameter constructor
	public Date(int theMonth, int theDay, int theYear)
	{
		month = theMonth;
		day = theDay;
		year = theYear;
	}

	// Return true if two equal values
	public boolean equals(Object rhs)
	{
		if(!(rhs instanceof Date))
			return false;
		Date rhDate = (Date)rhs;
			return rhDate.month == month && rhDate.day == day &&
				rhDate.year == year;
	}

	// Conversion to String
	public String toString()
	{
		return month + "/" + day + "/" + year;
	}

	public int getDay()
	{
		return day;
	}

	public int getMonth()
	{
		return month;
	}
	public int getYear()
	{
		return year;
	}

	public void setDay(int day)
	{
		this.day = day;
	}
	public void setMonth(int month)
	{
		this.month = month;
	}
	public void setYear(int year)
	{
		this.year = year;
	}

	// Fields
	private int month;
	private int day;
	private int year;
}
