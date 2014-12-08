/*
 *	Guy Moore
 *	Assignment 4 | CIS 231 B
 *	Cuesta College | Randy Scovil
 *	Due: 12/9 start of class
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>

#define STRING_LENGTH 81
#define OUTPUT_FILE "/home/gmoore/Git/SchoolWork/cis231/C/Assignment4/CHANGETHIS.txt"


void strInput(char str[], int maxChars);
int getTemps( int ** temps );
void promptStrInp( char str[] );
void getTokens( char str[], int **temps, int *totalTemps, int *currentSize);
void sortTemps( int * temps, int numTemps );
void printTemps( int * temps, int numTemps, FILE * file );
void getHighLow( int * temps, int numTemps, FILE * file );
void printName( FILE * file );
FILE* openFile( );
int getAverage( int * temps, int numTemps, FILE * file );
void getAboveBelow( int * temps, int numTemps, int average, FILE * file );
void getStandardDeviation( int * temps, int numTemps, int average, FILE * file );
void getMedian( int * temps, int numTemps, FILE * file );
void getMode( int * temps, int numTemps, FILE * file );
int getOccurrenceIndex( int currentTemp , int *numOccurrences,
	 int * occurrences );

int main()
{
	int * temps;	
	int totalTemps = getTemps( &temps );
	FILE * outputFile = openFile();

	//Out put my name.
	printName( outputFile );
	//Sort the temps so other functions work properly.
	sortTemps( temps, totalTemps );
	//Print out all of the temperatures in descending order.
	printTemps( temps, totalTemps, outputFile );
	//Decalre the average for use in other functions requiring it.
	int average = getAverage( temps, totalTemps, outputFile );
	//Get all of the output above, below or equal to the average.
	getAboveBelow( temps, totalTemps, average, outputFile );
	//Get the highest and lowest values.
	getHighLow( temps, totalTemps, outputFile );

	return 0;
}

void strInput(char str[], int maxChars)
{
	//Loop through until either we are at the max allowed characters, or 
	// a newline is entered.
	int i = 0;
	while( i <= maxChars && ( str[i]  = getchar() ) != '\n' )
	{
		i++;
	}	

	str[i] = '\0';
}

int getTemps( int ** temps )
{

	char tempString[STRING_LENGTH];              //String for input.
	int currentSize = 10;                        //Current size of malloc.
	int totalTemps = 0;                          //Total temps in malloc.
	
	//Assign the initial temperature array to a size of 10
	*temps = malloc( 10 * ( sizeof *(*temps) ) ); 	
	if(* temps == NULL)
	{
		 printf("Couldn't Allocate Memory\n");
	}
	
	//Get the first input.	
	promptStrInp( tempString );	

	//Continue to get input until user enters an empty string
	while ( tempString[0] != '\0' )
	{		
		//Get the tokens from the string
		getTokens( tempString, &(*temps), &totalTemps, &currentSize );	
		
		//Get string with prompted input.
		promptStrInp( tempString );	
	}

	//If the array has empty space shrink it down.
	if( currentSize != totalTemps )
	{
		int * buffer;
	
		buffer = realloc( *temps,  totalTemps *  sizeof *buffer );
		
		if( buffer == NULL )
		{
			printf("Couldn't allocate memory!\n");
		}
		else
		{
			//Reallocate to the ammount of temperatures.
			*temps = buffer; 
		}	
	}
	//Return the total ammount of temperatures put into the temp pointer.
	return totalTemps;	
}

void promptStrInp( char str[] )
{	
	//Prompt input for the next line.
	printf("Please enter integer values, 80 characters maximum:\n");

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
void getTokens( char str[], int **temps, int *totalTemps, int *currentSize)
{
	//Temporary storage of the string
	char * currentString;
	
	//initial tokenization of the string
	currentString = strtok( str, "	 -" );		

	//Check to make sure this string, and consecutive strings, are not null.
	while( currentString != '\0' )
	{
		//If we don't have enough space for another temp, then rellaoc
		if( *totalTemps == (*currentSize)-1 ) 
		{
			int * buffer;

			buffer = realloc( *temps, ( (*currentSize) *=2 )
				 * sizeof *buffer );
			
			if( buffer == NULL )
			{
				printf("Couldn't allocate memory!\n");
			}
			else
			{	
				*temps = buffer; 
			}

		}

		//Assign the temperature integer to the pointer with an index that
		//is one after the previous total temps.
		(*temps)[ (*totalTemps)++ ] = atoi(currentString);

		//Get next string.
		currentString = strtok( NULL, "	 -" );
	}	
}	

void sortTemps( int * temps, int numTemps)
{
	int i, compare, low, temp;	

	for( i = 0; i < numTemps - 1; i++ )
	{
			
		low = i;
			
		for( compare = i + 1; compare < numTemps; compare++ )
		{
			if( temps[compare] < temps[low] )
			{
				low = compare;
			}
			if( low != i )
			{
				temp = temps[low]; 
				temps[low] = temps[i];
				temps[i] = temp;	
			}
		}
	}
}

FILE*  openFile( )
{
	//Open the file that is defined in symbolic constant.
	FILE * file = fopen( OUTPUT_FILE, "w");

	//Check to make sure we could open the file.
	if( file == NULL )
	{
		printf("Could not open file to write.\n");
	}
	
	//Return the pointer to the opened file.
	return file;
}

/*
 *	Print all of the tempseratures in order that they exist in the array.
 */
void printTemps( int * temps, int numTemps, FILE * file )
{
	printf("Temperatures in degrees Fahrenheit:");

	int i;
	for( i = 0; i < numTemps; i++ )
	{
		printf("%i\n", temps[i]); 
	}		
}

/*
 *	Print out my name.
 */
void printName( FILE * file )
{
	printf("Guy Moore\n");
	fprintf( file, "Guy Moore\n");
}

/*
 *	Print out, and return the average for later use.
 */
int getAverage( int * temps, int numTemps, FILE * file )
{
	int i, sum = 0;	
	//Get the sum.
	for( i = 0; i < numTemps; i++ )
	{
		sum += temps[i];
	}
	
	//The average is now stored in sum.
	sum /= numTemps;

	printf("Average: %i\n", sum);
	fprintf( file, "Average: %i\n", sum);

	return sum;
}

void getAboveBelow( int * temps, int numTemps, int average, FILE * file )
{
	//If the difference of temp and average is greater than the 0.01
	//margin, then increment above. Now check if it is greater than the
	// lower end of the margin, if it is the set it to equals, otherwise,
	// it will be below. 
	int below = 0, above = 0, equal = 0, i;
	for( i = 0; i < numTemps; i++ )
	{
		if( temps[i] - average >= 0.01 )
		{
			above++;
		}
		else if( temps[i] - average > -0.01 )
		{
			equal++;
		}
		else
		{
			below++;
		}
	}
	
	//Output data to console.
	printf("Above\n========\n%i\n\nBelow\n========\n%i\n\nEqual\n========"
		   "\n%i\n\n", above, below, equal);
	//Output data to file.	
	fprintf( file, "Above\n========\n%i\n\nBelow\n========\n%i\n\nEqual\n"	
				   "========\n%i\n\n", above, below, equal);
			
}

/*
 *Finds and prints the Standard Deviation
 */
void getStandardDeviation( int * temps, int numTemps, int average,
	 FILE * file )
{
	//If the data set is equal to zero, then it doesn't deviate,
	//so outputing zero is valid and also avoids divide by zero
	if( numTemps < 2 )
	{
    	printf("Standard Deviation:%11.1i\n", 0);
	}

	else
	{
		double deviation = 0;
		int i;    
		
		//Find how far each temperature deviates from the average.
		for( i = 0; i < numTemps; i++ )
		{
			deviation += pow( temps[i] - average, 2);
		} 

		//Finally print the deviation divided by the number of
		//temperatures.
		printf("Standard Deviation:%11.1lf\n",
			 sqrt( deviation / (numTemps - 1 ) ) );
	}
}

/*
 * Print out the median, left value if odd.
 */
void getMedian( int * temps, int numTemps, FILE * file )
{
	//Get median, divide in half, integer division will make the left value,
	//the chosen value in case of an odd number.
	int median = temps[ numTemps / 2 ];

	//print mediant to console.
	printf( "Median\n======\n%i\n\n", median );
	//print median to file.
	fprintf( file, "Median\n======\n%i\n\n", median );
}

/*
 *	Gets the most occuring character by creating an array of assosciated
 *	tuples
 */
void getMode( int * temps, int numTemps, FILE * file )
{
	int i, occurrenceIndex, numOccurrences;

	//Array holding first the number, then the number of occurrences.
	//So [occurrence]index holds the value and [occurrence + 1] holds the
	//number of occurrences
	int * occurrences = malloc( ( numTemps * 2 ) * sizeof *occurrences );
	
	//First get where the number occurs in the occurrence array.
	for( i = 0; i < numTemps * 2; i += 2 )
	{	
		//Get the index in the occurrences array.
		occurrenceIndex = getOccurrenceIndex( temps[i], &numOccurrences,
			occurrences);

		//Increment the occurrences.
		occurrences[occurrenceIndex + 1]++;
	}	

	//Stores the value of the highest occurrence number.
	int highest = 0;

	i = 0;
	while( i < numOccurrences )
	{
		if( occurrences[i + 1] > highest )
		{
			highest = i;
		}
		i += 2;
	}
	
	printf( "Mode\n======\n%i\n\n", occurrences[highest] );
	fprintf( file, "Mode\n======\n%i\n\n", occurrences[highest] );
}

/*
 *	Search for the passed index in the occurrence array and return its
 *	index when it finds it.
 */
int getOccurrenceIndex( int currentTemp , int *numOccurrences,
	 int * occurrences )
{
	int i = 0;
	
	while ( i < *numOccurrences && currentTemp != occurrences[ i ] )	
	{
		i += 2;
	}

	if( i == *numOccurrences - 1 )
	{
		*numOccurrences += 2;
		occurrences[i] = currentTemp;
	}
	
	return  i;
}
		


/*
 *	Output the highest and lowest values in the array.
 *  since it is sorted, we can grab the first and last values instead of
 *  iterating.
 */
void getHighLow( int * temps, int numTemps, FILE * file )
{
	//Output first value in array (low) then last value (high).
	printf("Low:\n======\n%i\n\nHigh:\n======\n%i\n\n", temps[0],
		temps[numTemps-1]);
	//Output high then low to file.
	fprintf( file, "Low:\n======\n%i\n\nHigh:\n======\n%i\n\n", temps[0],
		temps[numTemps-1]);
}

