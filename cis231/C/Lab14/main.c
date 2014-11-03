/*
 *  Guy Moore
 *  Lab14 | CIS 231 B 
 *  Cuesta College | Randy Scovil 
 *  Due: 12:00 30/10/2014 
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAX_ARRAY_LENGTH 20

void strInput(char str[], int maxChars);
void tokenizeArray(char str[], char * tokens[], int maxChars, 
					int * numOfInts);
void tokensToInts( char * tokens[], int ** intTokens, int numOfInts);

int main(int argc, char * argv)
{
	char str[MAX_ARRAY_LENGTH + 1];
	char * tokens[MAX_ARRAY_LENGTH + 1];
	int * intTokens;
	int numOfInts;

	strInput(str, MAX_ARRAY_LENGTH);
	tokenizeArray(str, tokens, MAX_ARRAY_LENGTH, &numOfInts); 
	tokensToInts( tokens, &intTokens, numOfInts );	
	
	int i = 0;
	while( i < numOfInts)
	{
		printf("This is the token %i: %i.\n", i, intTokens[i]);
		i++;
	}	
	
}

void strInput(char str[], int maxChars)
{
	//Clear buffer.
	//while( getchar() != '\0');

	//Get input.
	int i = 0;
	char currentCharacter;
	while( ( currentCharacter = getchar() ) != '\n' && i <= maxChars )
	{
		str[i] = currentCharacter;
		i++;
	}	
	str[i + 1] = '\0';
}

void tokenizeArray(char str[], char * tokens[], int maxChars, 
					int * numOfInts)
{
	tokens[0] = strtok( str, ", " );
	
	int i = 1;
	while( (tokens[i] = strtok( '\0', ", " )) != '\0')
	{
		i++;
	}	

	* numOfInts = i;	
}	

void tokensToInts( char * tokens[], int ** intTokens, int numOfInts)
{
	*intTokens = malloc( numOfInts * sizeof *intTokens );
	int i = 0;
	while( i < numOfInts )
	{
		//This is the syntax for both subscript and pointer addition,
		//They are seperate so I can reference this if I have problems
		//in the future.
		*((*intTokens)+ i) = atoi(tokens[i]);	
		printf("This is the delimiter %i: %i.\n", i, (* intTokens)[i]);
		i++;
	}
}
