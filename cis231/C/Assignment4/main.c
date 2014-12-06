/*
 *	Guy Moore
 *	Assignment 4 | CIS 231 B
 *	Cuesta College | Randy Scovil
 *	Due: 12/9 start of class
 */

#include <stdio.h>

#define STRING_LENGTH 81

struct temp 
{
	int temperature;
	temp * next;
};

void strInput(char str[], int maxChars);
int * temps getTemps(  );
void promptStrInp( char[] str );
int * getTokens( char[] str );

int main()
{


	return 0;
}

void strInput(char str[], int maxChars)
{
	//Get input.
	int i = 0;
	while( i <= maxChars && ( str[i]  = getchar() ) != '\n' )
	{
		i++;
	}	
	str[i] = '\0';
}

void getTemps()
{
	//String with defined string length to hold integer temperatures.
	char tempString[STRING_LENGTH];

	int 
	//Size of the integer temp string;
	int tempSize = 10;

	//Get the first input.
	promptStrInp( tempString );	

	//Continue to get input until user enters an empty string
	while ( tempString[0] != '\0' )
	{		
		//Tokens
		getTokens( tempString, &tempSize );
	
		//Get string with prompted input.
		promptStrInp( tempString );	
	}
	
}

void promptStrInp( char[] str )
{	
	//Prompt input on the same line it is to be entered.
	printf("Please enter a value:", i);

	//Get string input.
	strInput( myString, STRING_LENGTH );	
	
	//Seperate input lines
	printf("\n");
}

/*
 * Tokenize the string and add it to the other inputed temperatures.
 *
 * Create a token, then convert it to an int. Put this int in to the
 * temperatures.
 */
struct temp * getTokens( char[] str )
{
	
