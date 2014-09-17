/*
 Lab 4 - Debugging test
 CIS 231 / Cuesta College
 Guy Moore
*/
#include <stdio.h>
#include <stdlib.h>
#define SOME_CONSTANT 15.0

int main(int argc, const char *argv[])
{
    
 int amount = 25;
 int somethingElse =  2;
 double firstDubStep = 0.5;
 double secondDub = 16.0;
 
 printf("Is SOME_CONSTANT 15? It's %i\n\n",SOME_CONSTANT);
 
 printf("I'm pretty sure amount is...25? It's %i\n\n", 
amount);
 
 printf("The dubs' product should be 8.0, is it? Product: "
"%lf\n\n", secondDub*firstDubStep);
 printf("Amount divided by something else needs to be 12, "
"right now it's: %i\n", amount/somethingElse);
 system("PAUSE");
 return 0;
}
