/*
 *  Lab15 | CIS 231 B
 *  Cuesta College | Randy Scovil
 *  Due: 12:00 6/11/2014
 */

#include <stdio.h>

#define CITY_STRING_LENGTH 31
#define DAY_MAX 366
#define DAY_MIN 1
#define DATA_MIN 1
#define DATA_MAX 20

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
void printTempInfo( struct TempInfo info );
int intInput( int min, int max );
double doubleInput();
int getRange( int rangeMin, int rangeMax );


int main(int argc, char * argv )
{
	//Variables
	struct TempInfo myInfo[20];
	struct TempInfo myStruct;

	//Get the number of temperature points.
	int range = getRange( DATA_MIN, DATA_MAX );

	//Let the user set the temperature points.
	int i;
	for( i = 0; i < range; i++ )
	{
		myInfo[i] = inputTempInfo( myStruct );
	}
	
	//Now, to print.
	printf("\n\nYour data set:\n");
	for( i = 0; i < range; i++ )
	{
		printTempInfo( myInfo[i] );		
	}

	return 0;
}

struct TempInfo inputTempInfo( struct TempInfo info )
{	
	//Clear buffer.
	char temp;
	while( (temp = getchar()) != '\n' && temp != EOF );
	
	//Get the city.
	printf("Please enter the city name.\n");
	strInput( info.city, CITY_STRING_LENGTH );	
	 
	//Get day.
	printf("Please enter the day.\n");	
	info.day = intInput( DAY_MIN, DAY_MAX );	
	
	//Get the temperature.
	printf("Please enter the temperature.\n");
	info.tempFahr = doubleInput();

	//Convert Fahrenheit to Celsius.
	info.tempCels =  convertFahrToCels(info.tempFahr);

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
	str[i] = '\0';
}

void printTempInfo( struct TempInfo info )
{
	printf("In the city %s, ", info.city);
	printf("on day number %i, ", info.day);
	printf("the temperature in Fahrenheit was %.1lf ", info.tempFahr);
	printf("the temperature in Celsius was %.1lf\n", info.tempCels);
}

int intInput( int min, int max )
{
	int temp;
	scanf("%i", &temp);
	
	while( temp < min || temp > max )
	{		
		getchar();  //prevents scanf from going bonkers
		printf("Sorry that was not valid, please enter an integer "
			   "between the range of %i and %i.\n", min, max); 		
		scanf("%i", &temp);
	}
}

double doubleInput()
{
	double temp;
	scanf("%lf", &temp);
	return temp;
}	

int getRange( int rangeMin, int rangeMax ) 
{ 
    printf("Specify how many information sets you would like to enter" 
           " from %i to %i\n", rangeMin, rangeMax); 
   
	return intInput( rangeMin, rangeMax ); 
}

