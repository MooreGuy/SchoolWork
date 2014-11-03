/*
 *  Guy Moore
 *  Lab13 | CIS 231 B 
 *  Cuesta College | Randy Scovil 
 *  Due: 12:00 30/10/2014 
 */

#include <stdio.h>

void strInput(char str[], int maxChars);

int main(int argc, char * argv)
{
	char str[80];
	strInput(str, 10);
	printf("Input: %s\n", str);
}

void strInput(char str[], int maxChars)
{
	//Clear buffer.
	while( getchar() != '\0');

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
