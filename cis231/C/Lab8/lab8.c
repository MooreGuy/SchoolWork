/*
    Guy Moore
    Lab 8
    9/11/2014
    Fall | 231 R. Scovil

*/
#include <stdio.h>
#include <stdlib.h>

int main()
{
   int sum = 0;
   int sumf = 1;
   int totalNumEntered = -1;
   double average = 0;
   int num = 0;
   int x, y;
   while(num >= 0)
   {
      totalNumEntered++;
      sum += num;
      printf("Enter positive integers, or enter a negative integer to exit\n");
      scanf("%i", &num);
   }
   if (totalNumEntered != 0)
   {
       printf("The total numbers entered is %i.\n", totalNumEntered);
       printf("The sum of the numbers entered is %i.\n", sum);
       printf("The average of the numbers entered is %0.2lf.\n",
                  (double) sum/totalNumEntered);
   }
   else
   {
       printf("There were no valid numbers entered\n");
   }
   num = 0;
   sum = 0;
   for(x = 0; x <= 5;x++)
   {
         printf("Please enter a number an integer number.\n");
         scanf("%i", &num);
         for(y = 1; y <= num; y++)
         {
               sum += y;;
               sumf *= y;
         }
         printf("The series of %i is %i.\n", num, sum);
         printf("The factorial of %i is %i.\n", num, sumf);
         sumf = 1;
         sum = 0;
   }
   
   
   system("PAUSE");	
   return 0;
}
