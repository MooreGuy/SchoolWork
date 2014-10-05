/*
  Guy Moore
  Assignment 2 | CIS 231 B
  Cuesta College | Randy Scovil
  Due: 12:00 23/9/2014
*/
#include <stdio.h>
#include <stdlib.h>

int getGuessInput(int high, int low);   //Takes valid guesses

int main(int argc, char *argv[])
{
  int x;           //x will be a placeholder for tries.
  int timesGuessed;//This will hold the value of tries as a placeholder
  int tries;       //The number of tries allowed to be made
  int rangeHigh;   
  int rangeLow;
  int guess;       //The current guessed number the user has specified.
  int number;      //The random number the user is trying to guess.
  int running = 1; //Whether the program should continue to run or not.
  int victory = 0; //If the user guesses correctly, set to 1
  
  
  printf("Guy Moore\nAssignment 2 - Guesser Lite.\n\n\n");
  while(running) //The main program loop.
  {
    //Sets the tries, or exits if tries is negative
    printf("If you would like to play, enter a positive integer for the"
           " number of guesses you would like, or enter a negative number"
           " to exit.\n");
    scanf("%i", &tries);
    if(tries < 0)
    {
      printf("Thank you for playing! The game will now exit.\n");
      running = 0;
    }
      
    victory = 0;
    timesGuessed = 0;
    rangeHigh = 100;
    rangeLow = 1;
    int guessedNums[tries - 1];
    x = tries;
    number = rand() % 100 + 1;   //random number from 1-100
    //This is the beginning of the game loop
    while(tries > 0)
    {
      if(tries != 1)      //English grammar if statement.
        printf("\nYou have %i tries to guess the correct number.\n", tries);
      else
        printf("\nYou have %i try to guess the correct number.\n", tries);
        
      guess = getGuessInput(rangeHigh,rangeLow);
      guessedNums[tries - 1] = guess;
      
      if(guess > number)
      {
        printf("Sorry, but that's too high.\n\n");
        rangeHigh = guess;
      }
      else if(guess < number)
      {
        printf("Sorry, but that's too low.\n\n");
        rangeLow = guess;
      }
      else
      {
        victory = 1;
        tries = 0;
        printf("Congratulations! You guessed correctly!\n\n");
      } 
      timesGuessed++;
      tries--;
    }
    //Game loop finished, now print out tries and other info.
    
    if(running)
    {
      if(timesGuessed != 1)          //English grammar.
      {
        if(victory)
          printf("You guessed correctly in %i tries.\n",timesGuessed);
        else
          printf("Sorry, but you have no more guesses\n");
        printf("Here are your answers: ");
      }
      else
      {
        if(victory)
          printf("You guessed correctly in %i try.\n",timesGuessed);
        else
          printf("Sorry, but you have no more guesses.\n");
        printf("Here is your answer: ");
      }
      //Game ends and prints the guesses
      tries = x;
      while(x > tries - timesGuessed) 
      {
        printf("%i ", guessedNums[x - 1]);
        x--;
      }
      printf("\nThe correct answer was %i.\n", number);
      printf("\n\n");
    }
    
  }
  system("PAUSE");	
  return 0;
}
int getGuessInput(int high, int low)
{
  int input = -1;
  while (input < low || input > high)
  {
    printf("Please enter a valid number between %i and %i.\n", low, high); 
    scanf("%i", &input);
  }
  return input;
}
