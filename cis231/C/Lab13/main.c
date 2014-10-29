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
	for( i = 0; i <= NUM_OF_STRINGS; i++ )
	{
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
	return 0;
}	
