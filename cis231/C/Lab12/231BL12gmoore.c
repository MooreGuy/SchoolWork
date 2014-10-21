/*
  Guy Moore
  CIS 231B | Fall Semester
  Randy Scovil | Cuesta College
  Due by: 12:00 21/10/2014
*/

#include <stdio.h>
#include <stdlib.h>

void sortThree(int * a, int * b, int * c);

int main(int argc, char *argv[])
{
  int a, b, c;
  printf("Please enter three ints.\n");
  scanf("%i %i %i", &a, &b, &c);
  
  sortThree(&a, &b, &c);
  printf("The values sorted are: %i %i %i \n",a, b, c);
  
  
  system("PAUSE");	
  return 0;
}

void sortThree(int * a, int * b, int * c)
{
  int temp;
  if( *a > *b )
  {
    temp = *a;
    *a = *b;
    *b = temp;
  }
  if( *a > *c )
  {
    temp = *a;
    *a = *c;
    *c = temp;
  }
  if( *b > *c )
  {
    temp = *b;
    *b = *c;
    *c = temp;
  }
}
