/*
 *  Guy Moore
 *  Lab13 | CIS 231 B 
 *  Cuesta College | Randy Scovil 
 *  Due: 12:00 30/10/2014 
 */

#include <stdio.h>
#include <string.h>

#define MAX_ARRAY_LENGTH 10

void strInput(char str[], int maxChars);
void tokenizeArray(char str[], char * tokens[], int maxChars);

int main(int argc, char * argv)
{
	char str[MAX_ARRAY_LENGTH + 1];
	char * tokens[MAX_ARRAY_LENGTH + 1];
	strInput(str, MAX_ARRAY_LENGTH);
	tokenizeArray(str, tokens, MAX_ARRAY_LENGTH); 
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

void tokenizeArray(char str[], char * tokens[], int maxChars)
{
	int i = 0;
	while( (tokens[i] = strtok( str, " " )) != '\0')
	{
		printf("Did something %s \n", &tokens[i]);	
		i++;
	}
	
	i = 0;
	while( tokens[i] != '\0' )
	{
		printf("Here are your tokens %s\n", tokens[i]);
		i++;
	}
}	
