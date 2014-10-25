/*
    Guy Moore
    Assignment 2 | CIS 231 B
    Cuesta College | Randy Scovil
    Due: 12:00 28/10/2014
*/

#include <stdio.h>

#define TEMP_ARRAY_SIZE 40

void getNumber(int * numTemps);
int getTemperature(double temps[], int numTemps);
int getTemps(double temps[], int numTemps);
void printHeader();
void printTemps(const double temps[], int arraySize);
double convertFahrToCels(double fahrTemp);

int main(void)
{
    int numTemps; 
    double temps[TEMP_ARRAY_SIZE];
    
    getNumber(&numTemps);
    getTemps(temps, numTemps);
    
    printHeader();
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
    printf("Now enter Fahrenheit temperatures from -200.0 to 300.0"
           "(.\n");

    int i;
    for ( i = 0; i < numTemps; i++)
    {
        printf("Please enter a value for temperature %i.\n", i);
	scanf("%lf", &temps[i]);
        
	//Check for valid input, and request a new number if invalid
	while(!(temps[i] >= -200.0) || !(temps[i] <= 300.0))
	{
	    printf("Invalid input!\n");
	    printf("The range of the temperature can only be between"
		   " -200.0 and 300.0 Fahrenheit.\n");
	    printf("\nEnter a valid temperature\n");
	    
	    getchar(); 
	    scanf("%lf", temps[i]); 
	}
    }
}

/* 
 *  Prints the project header with the assignment name and then my name
 */
void printHeader()
{
    printf("Assignment 3 | Guy Moore\n");
}


/*
 *  Prints the temperatures in both Fahrenheit and Celsius units.
 *  The temperatures will be divided by a divider that is 6
 *  characters long and is alligned 2 places further than the 
 *  decimal place to look more similiar to the sample output
 *  for the project.
 */
void printTemps(const double temps[], int arraySize)
{
    char * fahrString = "Fahr";
    char * celsString = "Cels";
    char * divider = "======";

    printf("%30s", fahrString);
    printf("%10s\n", celsString);    
    
    //Print the divider to split values.
    //The divider will be 6 equals signs and extend 2 past the decimal
    printf("%31s", divider);
    printf("%10s\n", divider);

    //Print out the temperatures
    int i;
    for( i = 0; i < arraySize; i++)
    {
	printf("%30.1lf", temps[i]);
	printf("%10.1lf", convertFahrToCels(temps[i]));
	printf("\n");
    }	

    //Print the divider again to show the end of the temperature values
    printf("%31s", divider);
    printf("%10s\n", divider);
}

double convertFahrToCels(double fahrTemp)
{
    return (fahrTemp - 32) * 5 / 9;
}
