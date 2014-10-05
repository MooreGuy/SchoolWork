#include <stdio.h>
#include <stdlib.h>

#define FILE_DIRECTORY "C:\\Hello.txt"

int main(int argc, char *argv[])
{
  FILE *file = fopen(FILE_DIRECTORY, "r" );
  char myPhrase[20] = {"0"};
  int myInts[20] = {};
  
  int x = 0;
  for( x = 0; x < 20; x ++)
  {
    printf("%i", myInts[x]);
  }
  printf("\n");
  for( x= 0; x < 20; x++)
  {
    fscanf(file, "%i", &myInts[x]);
  }
  
  for( x = 0; x < 20; x ++)
  {
    printf("%i", myInts[x]);
  }
  printf("\n");
  
  system("PAUSE");	
  return 0;
}
