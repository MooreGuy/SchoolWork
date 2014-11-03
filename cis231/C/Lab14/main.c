/*
 *  Guy Moore
 *  Lab14 | CIS 231 B 
 *  Cuesta College | Randy Scovil 
 *  Due: 12:00 30/10/2014 
 */

#include <stdio.h>
#include <string.h>

#define MAX_ARRAY_LENGTH 20

void strInput(char str[], int maxChars);
void tokenizeArray(char str[], char * tokens[], int maxChars, 
					int * numOfInts);

int main(int argc, char * argv)
{
	char str[MAX_ARRAY_LENGTH + 1];
	char * tokens[MAX_ARRAY_LENGTH + 1];
	int * intTokens;
	int numOfInts = 0;
	strInput(str, MAX_ARRAY_LENGTH);
	tokenizeArray(str, tokens, MAX_ARRAY_LENGTH, &numOfInts); 
	
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

	* numOfInts = i - 1;	
}	
