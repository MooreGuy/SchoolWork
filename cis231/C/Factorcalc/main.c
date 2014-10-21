#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[])
{
  int number;
  int factors[20];
  int x;
  int count = 0;
  int primeOnly;
  char answer[3];
  
  printf("Would you like to have only prime factors computed?\n");
  printf("Answer \"yes\" or \"no\".\n");
  scanf("%s", answer);
  if(strncmp(answer, "no", 2))
  {
    primeOnly = 1;
  }
  else if(strncmp(answer, "yes", 3))
  
  {
    primeOnly = 0;
  }
  else
  {
    printf("Invalid response, defaulting to all factors\n");
  }
  
  printf("Enter a number that you would like to factor.\n");;
  scanf("%i", &number);
  
  for(x = 2; x <= number; x++)
  {
    if(number % x == 0)
    {
      if(primeOnly)
        number /= x;
      factors[count] = x;
      count++;
    }
  }
  
  
  printf("Here are the factors: ");
  for(x = 0; x <
  
  count; x++)
  {
    printf("%i ", factors[x]);
  }
  printf("\n");
  
  
  system("PAUSE");	
  return 0;
}
