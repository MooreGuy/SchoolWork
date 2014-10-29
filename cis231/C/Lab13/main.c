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
				int * combinedLowerCase, int * combinedNumeric, 
				int * combinedAlphanumeric, int * combinedOther );
int getStringLength( char string[]);

int main(int argc, char * argv)
{
	//Variables
	char string[ARRAY_LENGTH];	

	int combinedAlphabetic = 0;	
	int combinedLowerCase = 0;
	int combinedNumeric = 0;
	int combinedAlphanumeric = 0;
	int combinedOther = 0;

	//Loop stringChecker	
	int i;
	for( i = 0; i < NUM_OF_STRINGS; i++ )
	{
		getString(string);
		checkString( string, &combinedAlphabetic, &combinedLowerCase,
			 &combinedNumeric, &combinedAlphanumeric, &combinedOther );
	}	
	return 0;
}

void getString( char string[] )
{
	int i = 0;
	while((string[i] = getchar()) != '\n' && i < ARRAY_LENGTH)
	{
		i++;
	}	
	string[i] = '\0';
}


int checkString( char string[], int * combinedAlphabetic,
					int * combinedLowerCase, int * combinedNumeric,
					int * combinedAlphanumeric, int * combinedOther)
{
	int stringLength = getStringLength(string);
	if( stringLength < 15 )
	{
		return -1;
	}	
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
	}
	
	return 0;
}	

int getStringLength( char string[])
{
	return 0;
}	
