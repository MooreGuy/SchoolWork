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
  int high;
  int low;
  int aboveAverage = 0;
  int belowAverage = 0;
  int onAverage = 0;
  double average;
  double sum = 0;
  double doubleArray[25];
  int x;
  
  //Gets the 25 values and assigns them to an array spot.
  for(x = 1; x < 26; x++)
  {
    printf("Please enter a double value for the %i", x);
    if(x > 3 || x == 1)
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
  
  //Prints the values both backwards and forwards in the array.
  printf("\n\nThe values in order are: \n");
  for(x = 0; x < 25; x ++)
  {
    printf("%0.1lf ", doubleArray[x]);
    sum += doubleArray[x];
  }
  printf("\n\nThe values in reverse order are: \n");
  for(x = 24; x > -1; x--)
  {
    printf("%0.1lf ", doubleArray[x]);
  }
  average = sum/25;
  printf("\n\nThe average value is %0.1lf.\n", average);
  
  high = doubleArray[0];
  low = doubleArray[0];
  for(x = 0; x < 25; x++)
  {
    //Find the numbers above, below, and on average. Increment the
    //corrosponding variable.
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
    //Check for the highest and lowest numbers.
    if(doubleArray[x] > high)
    {
      high = doubleArray[x];
    }
    if(doubleArray[x] < low)
    {
      low = doubleArray[x];
    }
  }
  
  if(belowAverage != 1)
  {
    printf("There are %i values below the average.\n", belowAverage);
  }
  else
  {
    printf("There is %i value below the average.\n", belowAverage);
  }
  if(aboveAverage != 1)
  {
    printf("There are %i values above the average.\n", aboveAverage);
  }
  else
  {
    printf("There is %i value above the average.\n", aboveAverage);
  }
  if(onAverage != 1)
  {
    printf("There are %i values equal to the average.\n", onAverage);
  }
  else
  {
    printf("There is %i value equal to the average.\n", onAverage);
  }
  
  
  printf("The highest value is %i, and the lowest is %i.\n", high, low); 
  system("PAUSE");	
  return 0;
}
