/*
 *	Guy Moore
 *	Assignment 4 | CIS 231 B
 *	Cuesta College | Randy Scovil
 	Due: 12/9 start of class
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define STRING_LENGTH 81


void strInput(char str[], int maxChars);
struct node *  getTemps(  );
void promptStrInp( char str[] );
struct node * getTokens( char str[] );

int main()
{
	int * temps;
	
	getTemps();

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
	printf("Finished getting the string\n");
}

struct node * getTemps()
{

	//Get the first input.	
	char tempString[STRING_LENGTH];

	promptStrInp( tempString );	

	if( tempString[0] != '\0')
	{
		//Set the head of the temperature linked list.
		head = getTokens( tempString );
	}	
	current = head;

	//Continue to get input until user enters an empty string
	while ( tempString[0] != '\0' )
	{		
		//Get the tokens from the string
		new = getTokens( tempString );	
		
		current->next = new;

		//Get string with prompted input.
		promptStrInp( tempString );	
	}
	
	return head;
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
int getTokens( char str[], int **temps, int totalTemps, int currentSize)
{
	//Temporary storage of the string
	char * currentString;
	
	//initial tokenization of the string
	currentString = strtok( str, "	 -" );		

	//Check to make sure this string, and consecutive strings, are not null.
	while( currentString != '\0' )
	{
		if( totalTemps > currentSize ) 
		{
			realloc( *temps, ( sizeof **temps ) * ( totalTemps * 2 ) );
		}
		//Assign the temperature integer to the pointer at the next temp
		//number.
		(*temps)[ **totalTemps ] = atoi(currentString);

		currentString = strtok( NULL, "	 -" );
	}
	
	return ;
}	
