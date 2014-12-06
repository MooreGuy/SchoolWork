/*
 *	Guy Moore
 *	Assignment 4 | CIS 231 B
 *	Cuesta College | Randy Scovil
 *	Due: 12/9 start of class
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define STRING_LENGTH 81


void strInput(char str[], int maxChars);
int getTemps( int ** temps );
void promptStrInp( char str[] );
void getTokens( char str[], int **temps, int *totalTemps, int *currentSize);

int main()
{
	int * temps;	

	int totalTemps = getTemps( &temps );

	return 0;
}

void strInput(char str[], int maxChars)
{
	//Loop through until either we are at the max allowed characters, or 
	// a newline is entered.
	int i = 0;
	while( i <= maxChars && ( str[i]  = getchar() ) != '\n' )
	{
		i++;
	}	

	str[i] = '\0';
}

int getTemps( int ** temps )
{

	char tempString[STRING_LENGTH];              //String for input.
	int currentSize = 10;                        //Current size of malloc.
	int totalTemps = 0;                          //Total temps in malloc.
	
	//Assign the initial temperature array to a size of 10
	*temps = malloc( 10 * ( sizeof **temps ) ); 	

	//Get the first input.	
	promptStrInp( tempString );	

	//Continue to get input until user enters an empty string
	while ( tempString[0] != '\0' )
	{		
		//Get the tokens from the string
		getTokens( tempString, &(*temps), &totalTemps, &currentSize );	
		
		//Get string with prompted input.
		promptStrInp( tempString );	
	}

	//If the array has empty space shrink it down.
	if( currentSize != totalTemps )
	{
		//Reallocate to the ammount of temperatures.
		*temps = realloc( *temps, ( totalTemps * ( **temps ) ) );
	}

	//Return the total ammount of temperatures put into the temp pointer.
	return totalTemps;	
}

void promptStrInp( char str[] )
{	
	//Prompt input on the same line it is to be entered.
	printf("Please enter a value:");

	//Get string input.
	strInput( str, STRING_LENGTH );	
	
	//Seperate input lines
	printf("\n");
}

/*
 * Tokenize the string and add it to the other inputed temperatures.
 *
 * Create a token, then convert it to an int. Put this int in to the
 * temperatures.
 */
void getTokens( char str[], int **temps, int *totalTemps, int *currentSize)
{
	//Temporary storage of the string
	char * currentString;
	
	//initial tokenization of the string
	currentString = strtok( str, "	 -" );		

	//Check to make sure this string, and consecutive strings, are not null.
	while( currentString != '\0' )
	{
		//If we don't have enough space for another temp, then rellaoc
		if( *totalTemps > *currentSize ) 
		{
			*temps = realloc( *temps,
				 ( sizeof *(*temps) ) * ( *totalTemps *= 2 ) );
		}

		//Assign the temperature integer to the pointer with an index that
		//is one after the previous total temps.
		(*temps)[ ++(*totalTemps) ] = atoi(currentString);

		//Get next string.
		currentString = strtok( NULL, "	 -" );
	}	
}	
