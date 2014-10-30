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
		while( checkString( string, &combinedAlphabetic,
			   &combinedLowerCase, &combinedUpperCase,
			   &combinedNumeric, &combinedAlphanumeric,
			   &combinedOther )
		{
			getString(string);
		}
	}		

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
	printf("The length of this string is %i.\n", stringLength);

	//Check to make sure the string ist't to short, or there
	// would be no point in counting the characters.
	if( stringLength < 15 )
	{
		return -1;
	}	

	//Instantiate values to check just this string.
	int alphabetic = 0, lowercase = 0, uppercase = 0, numeric = 0,
		 alphanumeric = 0, other = 0;
	
	int i;
	for( i = 0; i < stringLength; i ++ )
	{
		char currentVal = string[i];  
		
		if( currentVal >= 65 && currentVal <= 122 )
		{
			if( currentVal <= 90 )
			{
				uppercase++;
				alphabetic++;
				alphanumeric++;
			}
			else if( currentVal >= 122 )
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
		}
		else
		{
			other++;
		}
		
		if (checkIfZero(lowercase, uppercase, alphabetic, alphanumeric,
			numeric, other))	
		{
			combinedLowerCase += lowercase;
			combinedUpperCase += uppercase;
			combinedAlphabetic += alphabetic;
			combinedAlphanumeric += alphanumeric;
			combinedNumeric += numeric;
			combinedOther += other;
			printf("This current string has:\n"
				   " %i lowercase values\n"
				   "%i uppercase values\n"
				   "%i alphabetic values\n"
				   "%i alphanumeric values\n"
				   "%i numeric values\n"
				   "%i other values\n", lowercase, uppercase,
					 alphabetic, numeric, other);
		}
		else
		{
			return -1;
		}	
	}
	
	return 0;
}	

int getStringLength( char string[] )
{
	int i;
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
	if(other)
		return 0;
	else if(alphanumeric)
		return 0;
	else if(alphabetic)
		return 0;
	else if(numeric)
		return 0;
	else if(lowercase)
		return 0;
	else if(uppercase)
		return 0;
	else
		return 1;
}	

void printValues(
{
	printf(" has:\n"
		   " %i lowercase values\n"
		   "%i uppercase values\n"
		   "%i alphabetic values\n"
		   "%i alphanumeric values\n"
		   "%i numeric values\n"
		   "%i other values\n", lowercase, uppercase, alphabetic,
			 numeric, other);
