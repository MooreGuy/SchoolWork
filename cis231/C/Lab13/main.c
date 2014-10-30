/*
 *	Guy Moore
 *	Lab13 | CIS 231 B
 *	Cuesta College | Randy Scovil
 *	Due: 12:00 30/10/2014
 */

#include <stdio.h>

#define ARRAY_LENGTH 81
#define NUM_OF_STRINGS 5

void getString( char string[] );
int checkString( char  string[],  int * combinedAlphabetic, 
				int * combinedLowerCase, int * combinedUpperCase,
				int * combinedNumeric, int * combinedAlphanumeric,
				int * combinedOther );
int getStringLength( char string[]);
int checkIfZero(int lowercase, int uppercase, int alphabetic, 
				int alphanumeric, int numeric, int other);
void printValues(int lowercase, int uppercase, int alphabetic, 
				 int alphanumeric, int numeric, int other);

int main(int argc, char * argv)
{
	//Variables
	char string[ARRAY_LENGTH];	

	int combinedAlphabetic = 0;	
	int combinedLowerCase = 0;
	int combinedUpperCase = 0;
	int combinedNumeric = 0;
	int combinedAlphanumeric = 0;
	int combinedOther = 0;

	
	//Loop stringChecker	
	int i;
	for( i = 0; i < NUM_OF_STRINGS; i++ )
	{	
		getString(string);
		while( checkString( string, &combinedAlphabetic,
			   &combinedLowerCase, &combinedUpperCase,
			   &combinedNumeric, &combinedAlphanumeric,
			   &combinedOther ))
		{
			printf("Enter a valid string.\n");
			getString(string);
		}
	}		
	
	//Finished, print out data.	
	printf("The combined string values are:\n");	
	printValues(combinedLowerCase, combinedUpperCase,
					combinedAlphabetic, combinedAlphanumeric,
						combinedNumeric, combinedOther);

	return 0;
}

//Gets a string from input.
void getString( char string[] )
{
	int i = 0;
	while((string[i] = getchar()) != '\n' && i < ARRAY_LENGTH)
	{
		i++;
	}	
	string[i] = '\0';
}

//Checks if the string is valid and the characters within it.
int checkString( char  string[],  int * combinedAlphabetic, 
				int * combinedLowerCase, int * combinedUpperCase,
				int * combinedNumeric, int * combinedAlphanumeric,
				int * combinedOther )
{
	//Get string length and output for the user.
	int stringLength = getStringLength(string);
	printf("The length of the string is %i.\n", stringLength);

	//Check to make sure the string isn't too short, or there
	// would be no point in counting the characters.
	if( stringLength < 15 )
	{
		printf("\nError! Invalid string length. String must be at least"
			  " 15 characters!\n");
		return 1;
	}	

	//Instantiate values to check just this string.
	int alphabetic = 0, lowercase = 0, uppercase = 0, numeric = 0,
		 alphanumeric = 0, other = 0;
	
	int i;
	char currentVal;
	for( i = 0; i < stringLength; i ++ )
	{
		currentVal = string[i];  
		if( currentVal >= 65 && currentVal <= 122 )
		{
			if( currentVal <= 90 )
			{
				uppercase++;
				alphabetic++;
				alphanumeric++;
			}
			else if( currentVal >= 97 )
			{
				lowercase++;
				alphabetic++;
				alphanumeric++;
			}
			else
			{
				other++;
			}
		}
		else if( currentVal >= 48 && currentVal <= 57 )
		{
			numeric++;
			alphanumeric++;
		}
		else
		{
			other++;
		}
	}	

	//Now check to make sure the string contains the needed characters
	if (checkIfZero(lowercase, uppercase, alphabetic, alphanumeric,
		numeric, other))	
	{
		* combinedLowerCase += lowercase;
		* combinedUpperCase += uppercase;
		* combinedAlphabetic += alphabetic;
		* combinedAlphanumeric += alphanumeric;
		* combinedNumeric += numeric;
		* combinedOther += other;
		
		printf("This current string has:\n");
		printValues(lowercase, uppercase, alphabetic, alphanumeric,
					numeric, other);
	}
	else
	{
		printf("\nError! The string must contain a lowercase, uppercase"
			   ", number and other\ncharacter\n");
		return 1;
	}	

	//If the string is good to go, then return 0.		
	return 0;
}	

int getStringLength( char string[] )
{
	int i = 0;
	while(string[i])
	{
		i++;
	} 
	return i;
}	

// Returns 1 if everything has a value, or returns 0 if something
// doesn't.
int checkIfZero(int lowercase, int uppercase, int alphabetic, 
				int alphanumeric, int numeric, int other)
{
	if( ! other)
	{
		return 0;
	}
	else if( ! lowercase)
	{
		return 0;
	}
	else if( ! uppercase)
	{
		return 0;
	}
	else if( ! numeric)
	{
		return 0;
	}
	else
	{
		return 1;
	}
}	

void printValues(int lowercase, int uppercase, int alphabetic, 
				 int alphanumeric, int numeric, int other)
{
	printf("%i alphabetic\n"
		   "%i lowercase\n"
		   "%i uppercase\n"
		   "%i numeric\n"
		   "%i alphanumeric\n"
		   "%i other\n\n", alphabetic, lowercase, uppercase, 
			 numeric, alphanumeric, other);
}
