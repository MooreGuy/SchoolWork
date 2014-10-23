/*
    Guy Moore
    Assignment 2 | CIS 231 B
    Cuesta College | Randy Scovil
    Due: 12:00 28/10/2014
*/

#include <stdio.h>

void getNumber(int * numTemps);
int getTemperature(double temps[], int numTemps);
void printTemps(const double temps[], int arraySize);

int main(void)
{
    int numTemps;
    //TODO: Ask Randy if declaring arrays with variables
    // is good practice 
    double temps[numTemps];

    getNumber(&numTemps);
    getTemps(temps, numTemps);
    printTemps(temps, numTemps);

    return 0;
}

void getNumber(int * numTemps)
{
    printf("Specify how many temperatures you would like to enter"
           " from 1 to 40\n");
    scanf("%i", numTemps);

    //Check for invalid input, and request another number
    //if out of range.
    while(!(* numTemps <= 40) || !(* numTemps >= 1))
    {		
	printf("Invalid input!\n");
        printf("The range of temperatures can only be between"
	       " 1 and 40 (inclusive).\n");
	printf("\nEnter a valid number\n");
	
	getchar(); 
	scanf("%i", numTemps); 
    }
}

int getTemps(double temps[], int numTemps)
{
    printf("Enter a Fahrenheit temperature from 1 to 40 "
           "(inclusive).\n");

    int i;
    for ( i = 0; i < numTemps; i++)
    {
        printf("Please enter a value for temperature %i.\n", i);
	scanf("%lf", temps[i]);
        
	//Check for valid input, and request a new number if invalid
	while(!(temps[i] >= -200.0) || !(temps[i] <= 300.0))
	{
	    printf("Invalid input!\n");
	    printf("The range of the temperature can only be between"
		   " -200.0 and 300.0 Fahrenheit.\n");
	    printf("\nEnter a valid temperature\n");
	    
	    scanf("%lf", temps[i]); 
	}
    }
}

void printTemps(const double temps[], int arraySize)
{
    int i;
}
