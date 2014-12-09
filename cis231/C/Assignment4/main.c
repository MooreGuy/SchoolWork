/*
 *	Guy Moore
 *	Assignment 4 | CIS 231 B *	Cuesta College | Randy Scovil
 *	Due: 12/9 start of class
 */


#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>


#define OUTPUT_FILE "/home/gmoore/Git/SchoolWork/cis231/C/Assignment4/CHANGETHIS.txt"
#define STRING_LENGTH 81
#define PRINT_LENGTH 30
#define TEMP_SPACING 12


void strInput(char str[], int maxChars);
int getTemps( int ** temps );
void promptStrInp( char str[] );
void getTokens( char str[], int **temps, int *totalTemps, int *currentSize);
void sortTemps( int * temps, int numTemps );
void printTemps( int * temps, int numTemps, FILE * file );
void getHighLow( int * temps, int numTemps, FILE * file );
void printName( FILE * file );
FILE* openFile( );
double getAverage( int * temps, int numTemps, FILE * file );
void getAboveBelow( int * temps, int numTemps, double average,
	 FILE * file );
void getStandardDeviation( int * temps, int numTemps, double average,
	 FILE * file );
void getMedian( int * temps, int numTemps, FILE * file );
void getMode( int * temps, int numTemps, FILE * file );
int getOccurrenceIndex( int currentTemp , int *numOccurrences,
	 int * occurrences );
int getHighestOccurrence( int numOccurrences, int * occurrences );
void printNumTemps( int numTemps, FILE * file );
void freeMem( int * temps, FILE * file );



int main()
{
	int * temps;	
	int totalTemps = getTemps( &temps );
	FILE * outputFile = openFile();
	double average;

	//Out put my name.
	printName( outputFile );

	//Print out the number of temperatures entered.
	printNumTemps( totalTemps, outputFile );

	//Sort the temps so other functions work properly.
	sortTemps( temps, totalTemps );

	//Print out all of the temperatures in descending order.
	printTemps( temps, totalTemps, outputFile );

	//Get the highest and lowest values.
	getHighLow( temps, totalTemps, outputFile );

	//Declare the average for use in other functions requiring it.
	average = getAverage( temps, totalTemps, outputFile );

	//Get all of the output above, below or equal to the average.
	getAboveBelow( temps, totalTemps, average, outputFile );

	//Get the standard deviation
	getStandardDeviation( temps, totalTemps, average, outputFile );
	
	//Get the median or center value, left value if odd.
	getMedian( temps, totalTemps, outputFile );

	//Get the most common number
	getMode( temps, totalTemps, outputFile );

	//Free the memory and file that we were using.
	freeMem( temps, outputFile );


	return 0;
}



/*
 *	Prints out the number of integer temperatures entered.
 */
void printNumTemps( int numTemps, FILE * file )
{
	//Avoid redundant math.
	int printLength = PRINT_LENGTH - 23;

	//Print out numTemps
	printf( "Number of Temperatures:%*i\n\n", printLength, numTemps );
	fprintf( file, "Number of Temperatures:%*i\n\n", printLength, numTemps );	

}

/*
 * Safe string input method, fisrt seen in Lab13.
 */
void strInput(char str[], int maxChars)
{
	//Loop through until either we are at the max allowed characters, or 
	// a new line is entered.
	int i = 0;
	while( i <= maxChars && ( str[i]  = getchar() ) != '\n' )
	{
		i++;
	}	

	str[i] = '\0';
}

/*
 *	Get all of the temperatures that the user wants to enter and insert 
 *	them into the integer the pointer temps.
 * 	First allocate memory for temps. Then get a string from the user,
 * 	tokenize the string, then put it into our temps.
 */
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
			//Reallocate to the amount of temperatures.
			*temps = buffer; 
		}	
	}
	//Return the total amount of temperatures put into the temp pointer.
	return totalTemps;	
}

void promptStrInp( char str[] )
{	
	//Prompt input for the next line.
	printf("Please enter integer temperatures, 80 characters maximum:\n");

	//Get string input.
	strInput( str, STRING_LENGTH );	
	
	//Separate input lines
	printf("\n");
}

/*
 * Tokenize the string and add it to the other input temperatures.
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
		//If we don't have enough space for another temp, then realloc
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

/*
 *	Sort the passes array into descending order with selection sort,
 *	so highest first.
 */
void sortTemps( int * temps, int numTemps)
{
	int i, compare, high, temp;	

	for( i = 0; i < numTemps - 1; i++ )
	{
			
		high = i;
			
		for( compare = i + 1; compare < numTemps; compare++ )
		{
			if( temps[compare] > temps[high] )
			{
				high = compare;
			}
		}
		
		if( high != i )
		{
			temp = temps[high]; 
			temps[high] = temps[i];
			temps[i] = temp;	
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
 *	Print all of the temperatures in order that they exist in the array.
 */
void printTemps( int * temps, int numTemps, FILE * file )
{
	printf("Temperatures in degrees Fahrenheit:");
	fprintf( file, "Temperatures in degrees Fahrenheit:");

	//Avoid hitting array twice
	int currentTemp;

	//Loop through and print.
	int i;
	for( i = 0; i < numTemps; i++ )
	{
		//Make new row every five.
		if( i % 5 == 0 )
		{
			printf("\n");
			fprintf( file, "\n" );
		}
		
		currentTemp = temps[i];
			
		//print out temperatures.
		printf( "%*i", TEMP_SPACING, currentTemp); 
		fprintf( file,"%*i", TEMP_SPACING, currentTemp);
	}		
	
	//Add new line at the end to separate it from next output.
	printf("\n\n");
	fprintf(file, "\n\n");
}

/*
 *	Print out my name.
 */
void printName( FILE * file )
{
	printf("Guy Moore\n\n");
	fprintf( file, "Guy Moore\n\n");
}

/*
 *	Print out, and return the average for later use.
 */
double getAverage( int * temps, int numTemps, FILE * file )
{
	int i, sum = 0;	
	//Get the sum.
	for( i = 0; i < numTemps; i++ )
	{
		sum += temps[i];
	}
	
	//Void redundant math.
	double average = (double) sum/numTemps;

	//Print average. To console and to file.
	printf( "Average: %*.1f\n\n", PRINT_LENGTH - 7, average );
	fprintf( file, "Average: %*.1f\n\n", PRINT_LENGTH - 7, average );

	return average;
}

/*	If the difference of temp and average is greater than the 0.01
 *	margin, then increment above. Now check if it is greater than the
 *	lower end of the margin, if it is the set it to equals, otherwise,
 *	it will be below. 
 */
void getAboveBelow( int * temps, int numTemps, double average, FILE * file )
{
	int below = 0, above = 0, equal = 0, i, currentTemp;

	for( i = 0; i < numTemps; i++ )
	{
		//Store the current temp so we don't hit the array more than once
		//per loop cycle.
		currentTemp = temps[i];

		//Check if it is greater than 0.01
		if( (currentTemp - average) >= 0.01 )
		{
			above++;
		}
		//Check if it is inbetween 0.01 and -0.01
		else if( (currentTemp - average) > -0.01 )
		{
			equal++;
		}
		//If it is not greater than 0.01 and not inbetween 0.01, and -0.01,
		// then it has to be below.
		else
		{
			below++;
		}
	}
	
	//Since all the output buffer lengths are the same, hold the length in
	// an int and access it when needed
	int spacing = PRINT_LENGTH - 6;	

	//Output data to console.
	printf("Above:%*i\nEqual:%*i\nBelow:%*i\n\n", spacing, above, spacing, equal, spacing, below );

	//Output data to file.	
	fprintf( file, "Above:%*i\nEqual:%*i\nBelow:%*i\n\n", spacing, above, spacing, equal, spacing, below );
			
}

/*
 *Finds and prints the Standard Deviation
 */
void getStandardDeviation( int * temps, int numTemps, double average,
	 FILE * file )
{
	
	//Store print length to avoid redundant math.
	int printLength = PRINT_LENGTH - 17;

	//If the data set is equal to zero, then it doesn't deviate,
	//so outputing zero is valid and also avoids divide by zero
	if( numTemps < 2 )
	{
    	printf("Standard Deviation:%*.1i\n", printLength, 0);
		fprintf( file, "Standard Deviation:%*.1i\n", printLength, 0 );
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
		printf("Standard Deviation:%*.1lf\n\n", printLength,
			 sqrt( deviation / (numTemps - 1 ) ) );
		fprintf( file, "Standard Deviation:%*.1lf\n\n", printLength,
			 sqrt( deviation / (numTemps - 1 ) )); 
	}
}

/*
 * Print out the median, left value if odd.
 */
void getMedian( int * temps, int numTemps, FILE * file )
{
	int median;
	
	//Get median, divide in half, integer division will make the left value,
	//the chosen value in case of an odd number.
	//If even.
	if( numTemps % 2 == 0 )
	{
		median = temps[ ( numTemps - 1 ) / 2 ];
	}
	//if odd.
	else 
	{
		median = temps[ numTemps / 2];
	}

	//Store print length to avoid redundant math.
	int printLength = PRINT_LENGTH - 7;

	//print mediant to console.
	printf( "Median:%*i\n\n", printLength, median );
	//print median to file.
	fprintf( file, "Median:%*i\n\n", printLength, median );
}

/*
 *	Gets the most occurring character by creating an array of associated
 *	tuples
 */
void getMode( int * temps, int numTemps, FILE * file )
{
	int i, occurrenceIndex, numOccurrences = 0;

	//Array holding first the number, then the number of occurrences.
	//So [occurrence]index holds the value and [occurrence + 1] holds the
	//number of occurrences

	int * occurrences = malloc( ( numTemps * 2 ) * sizeof  *occurrences );

	//First get where the number occurs in the occurrence array.
	for( i = 0; i < numTemps; i++ )
	{	
		//Get the index in the occurrences array.
		occurrenceIndex = getOccurrenceIndex( temps[i], &numOccurrences,
			occurrences);

		//Increment the occurrences.
		occurrences[occurrenceIndex + 1] += 1;

	}	

	int highest = getHighestOccurrence( numOccurrences, occurrences );

	//Stores the value of the highest occurrence number.
	
	int printLength = PRINT_LENGTH - 5;
	printf( "Mode:%*i\n\n", printLength, highest );
	fprintf( file, "Mode:%*i\n\n", printLength, highest);

	//Free memory that we were using.
	free( occurrences );
}

/*
 *	Runs through the array of associated tuples, and figures out what the
 *	occurrence with the most occurrences is. Returns the temperature of the
 *	most occurring temp.
 */
int getHighestOccurrence( int numOccurrences, int * occurrences )
{	
	int highest = 0, highNumber = 0;

	int i = 0;
	//Increment and multiply by two so we can properly check the tuples
	while( i < numOccurrences * 2)
	{
		//Check the number of occurrences for the current tuple.
		if( occurrences[i + 1] > highest )
		{
			//If it is the highest, set the highest occurrence to the
			//temperature in the occurrences tuple.
			highNumber = occurrences[i];

			//Keep the highest occurrence count in the touple to the new
			//highest, so the next run in the loop can keep track can
			//compare the new highest occurrence count.
			highest = occurrences[i+1];
		}

		//Increment by two since we are using tuples.
		i += 2;
	}

	//Return the most occurring occurrence.
	return highNumber;
}

/*
 *	Search for the passed index in the occurrence array and return its
 *	index when it finds it.
 */
int getOccurrenceIndex( int currentTemp , int *numOccurrences,
	 int * occurrences )
{
	int i = 0;
	
	//Search through the tuple array by twos.
	while ( i/2 < *numOccurrences && currentTemp != occurrences[ i ] )	
	{
		i += 2;
	}

	//If it is not in the array we must add it.
	if( i/2 == *numOccurrences )
	{
		*numOccurrences += 1;
		occurrences[i] = currentTemp;
	}
	
	//Return the index at which the occurrence is located.
	return  i;
}
		


/*
 *	Output the highest and lowest values in the array.
 *  since it is sorted, we can grab the first and last values instead of
 *  iterating.
 */
void getHighLow( int * temps, int numTemps, FILE * file )
{

	//Avoid redundant maths by holding print lengths.
	int printLength1 = PRINT_LENGTH - 5;
	int printLength2 = PRINT_LENGTH - 4;

	//Output first value in array (low) then last value (high).
	printf("High:%*i\nLow:%*i\n\n", printLength1, temps[0],
		printLength2,  temps[ numTemps - 1 ]);
	//Output high then low to file.
	fprintf( file, "High:%*i\nLow:%*i\n\n", printLength1,
		temps[0], printLength2, temps[ numTemps - 1 ]);
}

/*
 *	Free up memory and close file.
 */
void freeMem( int * temps, FILE * file )
{
	//Free the memory used to store temps.
	free(temps);
	
	//Close file that we were writing to.
	fclose(file);
}
