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
void printData( int * intTokens, int numOfInts );

int main(int argc, char * argv)
{
	char str[MAX_ARRAY_LENGTH + 1];
	char * tokens[MAX_ARRAY_LENGTH + 1];
	int * intTokens;
	int numOfInts;

	strInput( str, MAX_ARRAY_LENGTH );
	tokenizeArray( str, tokens, MAX_ARRAY_LENGTH, &numOfInts); 
	tokensToInts( tokens, &intTokens, numOfInts );	
	
	printData( intTokens, numOfInts );
	
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
	tokens[0] = strtok( str, " ,.-" );
	
	int i = 1;
	while( (tokens[i] = strtok( '\0', " ,.-" )) != '\0')
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
		//This is here for future reference:
		//       (* intTokens)[i] = *((*intTokens)+ i)
		*((*intTokens)+ i) = atoi(tokens[i]);	
		i++;
	}
}

void printData( int * intTokens, int numOfInts )
{
	int i, high = intTokens[0], low = intTokens[0], sum = 0, currentInt;
	for( i = 0; i < numOfInts; i++ )
	{
		currentInt = intTokens[i];	

		sum += currentInt;	
			
		if( currentInt < low )
		{
			low = currentInt;
		}
		if( currentInt > high )
		{
			high = currentInt;
		}
	}
	
	//Print everything.
	printf("\n\nThere were a total of %i numbers input.\n", numOfInts);
	printf("These are the numbers in the order of their input: ");
	for( i = 0; i < numOfInts; i++ )
	{
		printf("%i ", intTokens[i]);
	}
	printf("\n");
	printf("The sum of the numbers is %i.\n", sum);
	printf("The average of the numbers is %i.\n", sum/numOfInts);
	printf("The highest number was %i.\n", high);
	printf("The lowest number was %i.\n", low);
}
