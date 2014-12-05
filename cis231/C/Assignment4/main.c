/*
 *	Guy Moore
 *	Assignment 4 | CIS 231 B
 *	Cuesta College | Randy Scovil
 *	Due: 12/9 start of class
 */

#include <stdio.h>


void strInput(char str[], int maxChars);

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
