/*
  Guy Moore
  Lab 10 / CIS231B
  Cuesta College / Randy Scovil
  Due: End of class on 9/18/2014
*/

#include <stdio.h>
#include <stdlib.h>

int main()
{
  int aboveAverage = 0;
  int belowAverage = 0;
  int onAverage = 0;
  double average;
  double sum = 0;
  double doubleArray[25];
  int x;
  
  for(x = 1; x < 26; x++)
  {
    printf("Please enter a double value for the %i", x);
    if(x > 3 || x ==1)
    {
      printf("st ");
    }
    else if(x == 3)
    {
      printf("rd ");
    }
    else
    {
      printf("nd ");
    }
    printf("array item.\n");
    
    scanf("%lf", &doubleArray[x-1]);
  }
  
  printf("\n\nThe values in order are:\n");
  for(x = 0; x < 25; x ++)
  {
    printf("%0.1lf ", doubleArray[x]);
    sum += doubleArray[x];
  }
  printf("\n\nThe values in reverse order are:\n");
  for(x = 24; x > -1 ; x--)
  {
    printf("%0.2lf ", doubleArray[x]);
  }
  printf("\n\nThe average value is %0.1lf.\n", sum/25);
  
  for(x = 0; x < 25; x++)
  {
    if(doubleArray[x] > average)
    {
      aboveAverage++;
    }
    else if(doubleArray[x] < average)
    {
      belowAverage++;
    }
    else
    {
      onAverage++;
    }
  }
  
    
  system("PAUSE");	
  return 0;
}
