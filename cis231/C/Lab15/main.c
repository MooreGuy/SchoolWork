/*
 *  Lab15 | CIS 231 B
 *  Cuesta College | Randy Scovil
 *  Due: 12:00 6/11/2014
 */

#include <stdio.h>

#define CITY_STRING_LENGTH 31
#define DAY_MAX 366
#define DAY_MIN 1

struct TempInfo
{
	char city[31];
	int day;
	double tempFahr;
	double tempCels;
};

struct TempInfo inputTempInfo( struct TempInfo info );
double convertFahrToCels( double fahrTemp );
void strInput( char str[], int maxChars ); 
void printTempInfo();
int intInput( int min, int max );
double doubleInput();

int main(int argc, char * argv )
{
	struct TempInfo myInfo[20];

//TESTING
	struct TempInfo testStruct;
	printf("This is the day %i.\n", inputTempInfo(testStruct).day);
//TESTING
	return 0;
}

struct TempInfo inputTempInfo( struct TempInfo info )
{			
	//char input[DAY_STRING_LENGTH];

	//Get day.
	printf("Please enter the day.\n");	
	info.day = intInput(DAY_MIN, DAY_MAX);	
	
	return info;
}


double convertFahrToCels(double fahrTemp)
{
     return (fahrTemp - 32) * 5 / 9;
}


void strInput(char str[], int maxChars) 
 { 
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

void printTempInfo()
{
}

int intInput( int min, int max )
{
	int temp;
	scanf("%i", &temp);
	
	while( temp < min && temp > max )
	{
		printf("Sorry that was not valid, please enter an integer between "
			   "the range of %i and %i.\n", min, max); 		
		scanf("%i", &temp);
	}
}
