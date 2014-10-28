/*
    Guy Moore
    Assignment 2 | CIS 231 B
    Cuesta College | Randy Scovil
    Due: 12:00 28/10/2014
*/

#include <stdio.h>
#include <math.h>

#define TEMP_ARRAY_SIZE 40

void getNumber(int * numTemps);
int getTemperature(double temps[], int numTemps);
void getTemps(double temps[], int numTemps);
void printHeader();
void printTemps(const double temps[], int arraySize);
void getAverage(const double temps[], int arraySize,
    double * average);
void printAverage(double average);
void printHighLowAverage(const double temps[], int arraySize,
    double average);
void printStandardDeviation(const double temps[], int arraySize,
    double average);
double convertFahrToCels(double fahrTemp);

int main(void)
{
    int numTemps; 
    double temps[TEMP_ARRAY_SIZE];
    double average;
    
    getNumber(&numTemps);
    getTemps(temps, numTemps);
    getAverage(temps, numTemps, &average);
    
    printHeader();
    printTemps(temps, numTemps);
    printAverage(average);   
    printHighLowAverage(temps, numTemps, average);
    printStandardDeviation(temps, numTemps, average);
 
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

void getTemps(double temps[], int numTemps)
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
	    scanf("%lf", &temps[i]); 
	}
    }
}

/* 
 *  Prints the project header with the assignment name and then my name.
 */
void printHeader()
{
    char * header = "CIS 231 | Assignment 3 | Guy Moore";
    printf("\n\n%41s\n\n", header);
}


/*
 *  Prints the temperatures in both Fahrenheit and Celsius units.
 *  The temperatures will be divided by a divider that is 6
 *  characters long and is aligned 2 places further than the 
 *  decimal place to look more similar to the sample output
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
    //The divider will be 6 equals signs and extend 2 past the decimal.
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


/*
 *  Get average will get set the double * average to the average of
 *  all of the temperatures in the array.
 */
void getAverage(const double temps[], int arraySize,
    double * average)
{
    //Erase garbage values in the average.
    *average = 0;
    
    //Add all of the temps and then divide by the amount to find
    //the average temperature.
    int i;
    for( i = 0; i < arraySize; i++)
    {
	*average += temps[i];	
    }
    *average = *average/arraySize;
}


/*
 *  Prints the average of the temperatures.
 */
void printAverage(double average)
{
    printf("Average:%22.1lf%10.1lf\n\n",average, convertFahrToCels(average));
}


/*
 *  Prints the highest and lowest temperatures and then outputs how many
 *  temperatures are either above, below or equal to the average.
 */
void printHighLowAverage(const double temps[], int arraySize,
    double average)
{
    int i, numAboveAverage = 0, numBelowAverage = 0, numEqualToAverage = 0;
    double high = -200.0, low = 300.0, currentValue;
    
    for( i = 0; i < arraySize; i++)
    {
	currentValue = temps[i];
	
	//Find the highest and lowest temperatures.	
	if( currentValue > high )
	{
	    high = currentValue;
	}
	if( currentValue < low )
	{
	    low = currentValue;
	}
	
	//Find the amount of temperatures above, below, or
	//equal to the average.
	if( currentValue > average )
	{
	    numAboveAverage++;
	}
	else if( currentValue < average )
	{
	    numBelowAverage++;
	}
	else
	{
	    numEqualToAverage++;
	}
	
    }	 
    
    //Print highest and lowest values. 
    printf("High:%25.1lf%10.1lf\n\n", high, convertFahrToCels(high));
    printf("Low:%26.1lf%10.1lf\n\n", low, convertFahrToCels(low));
    
    //Print the number of times a temperature was above, below or equal
    //to the average.
    printf("Above Average:%14i\n", numAboveAverage);
    printf("Equal to Average:%11i\n", numEqualToAverage);
    printf("Below Average:%14.1i\n\n", numBelowAverage);  	
}


/*
 *Finds and prints the Standard Deviation
 */
void printStandardDeviation(const double temps[], int arraySize,
    double average)
{
    double deviation = 0;
    int i;    
    
    //Find how far each temperature deviates from the average.
    for( i = 0; i < arraySize; i++ )
    {
	deviation += pow(temps[i] - average, 2);
    } 

    //Finally print the deviation divided by the number of temperatures.
    printf("Standard Deviation:%11.1lf\n", sqrt(deviation/arraySize));
}

/*
 *  Converts Fahrenheit temperatures to Celsius.
 */
double convertFahrToCels(double fahrTemp)
{
    return (fahrTemp - 32) * 5 / 9;
}
