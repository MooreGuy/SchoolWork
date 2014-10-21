#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[])
{
  int a = 2, c = 3;
  double b = 4.7, d = 4.2;
  
  int answer1 = b + a / c - d;
  double answer2 = (a + b) / c - d;
  int answer3 = d * -b - (double) (c / a);
  double answer4 = a - b * (c % (int) d);
  double answer5 = d / a++ + (b * --c);
  
  printf(" answer 1 is %i\n", answer1);
  printf (" answer 2 is %.2lf\n", answer2);
  printf(" answer 3 is %i\n", answer3);
  printf(" answer 4 is %.2lf\n", answer4);
  printf("answer 5 is %.2lf\n", answer5);
  system("PAUSE");
  return 0;
}
