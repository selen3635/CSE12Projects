package hw1;

/**  
 * @author Xiaolong Zhou
 * @date Jan 08, 2016
 * @PID A13227137
 * Login: cs12wlt
 */

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Random;


/**
 *     This program is a game design for a person with computer. There are
 * 52 card with 4 differend kind of suit, and each suit has 13 different
 * cards from 1 to 13. When program execute, computer will get one card,
 * then will ask user to enter a number from 2 to 14 which will be card 
 * number for user.(14 means Ace). After this, program will determine 
 * winner. User can enter -1 to stop game, and game statistic will show up
 * , then user can enter "y" to play game again or "n" to exit program.
 */
public class SimpleWar {

  // constant variables
  private static final int RANGE = 13;
  private static final int CARNUM = 52;

  // create a new counterstat object
  private static CounterStat stat;

  // Variable to check whether user will play again
  private static String playAgain = "Y";

  /**
   *   This is main method for program, run as an application.
   * @param args This represent argument for command line
   */
  public static void main( String[] args )
	{
   
    //Use it to count the number of used KINDS
    int[] NumberOfUsedKinds = new int[RANGE]; 

		//String[] UsedCards = new String[carNum];
    //Use it to count used cards
	
    // string array for card suit    
	  String[] Suits = {"Hearts","Diamonds","Spades","Clubs"}; //0,1,2,3

    // string array for card's kinds 
	  String[] Kinds = {"Two","Three","Four","Five", "Six", //0, 1, 2, 3, 4
	    		          "Seven", "Eight", "Nine", "Ten", "Jack",  // 5,6,7,8,9
	    		          "Queen", "King", "Ace"}; //10, 11, 12

    // user it to count used cards
    LinkedList<String>selectedCards = new LinkedList<String>(); 

	  //stores computer moves
	  LinkedList<String> computerMoves = new LinkedList<String>(); 
	    
	  //stores users moves
	  LinkedList<String> userMoves = new LinkedList<String>(); 

    //suit and kind chosen randomly by a computer
	  int suitIndex, kindIndex;

    // integer enter by user 
    int userNum;

    // create new counterstat object with paramters
    stat = new CounterStat(0, 0, 0);


    //Scanner object that takes an input from a user 
    Scanner input = new Scanner(System.in);

    // To check whether user wanna play again
    while( playAgain.equals("Y") || playAgain.equals("y")) {

      // reset everything to begining
      stat.reset();
      selectedCards.clear();
      computerMoves.clear();
      userMoves.clear();

      // forever loop until user enter -1
      while ( true ) {

        // create a new random integer generater
        Random randomInteger = new Random();

        // generate random integers
        kindIndex = randomInteger.nextInt(RANGE);
        suitIndex = randomInteger.nextInt(4);

        // check whether card is already been used
        while( selectedCards.contains(Kinds[kindIndex] + " of " +
              Suits[suitIndex] )) {
          
          // while true, then generate another to number to get a new card
          kindIndex = randomInteger.nextInt(RANGE);
          suitIndex = randomInteger.nextInt(4);

        }

        // store card as a computer card history
        computerMoves.add(Kinds[kindIndex] + " of " + Suits[suitIndex]);
       
        // store card into selectedcards list
        selectedCards.add(Kinds[kindIndex] + " of " + Suits[suitIndex]);

        System.out.println();
        // print message for what card that computer has now
        System.out.println("My card is: " + Kinds[kindIndex] + " of " +
            Suits[suitIndex]);

        // print message and ask user to enter a number
        System.out.print("What is your card (kind)?(2-14, -1 to finish"
          + " the game): ");
 
        // let user enter a number
        userNum = input.nextInt();

        // check if user enter -1
        if ( userNum == -1 ) {

          // if yes, break this loop
          break;

        }


        //generate another suit index number for user
        suitIndex = randomInteger.nextInt(4);

        // check whether card is already been used
        while ( selectedCards.contains(Kinds[userNum - 2] + " of " +
          Suits[suitIndex])) {

          if ( selectedCards.contains(Kinds[userNum - 2] + " of Spades")
                && selectedCards.contains(Kinds[userNum - 2] +
                " of Hearts") && selectedCards.contains(Kinds[userNum - 2]
                + " of Clubs") && selectedCards.contains(Kinds[userNum -2]
                + " of Diamonds")) {
            // if yes, print message to tell user to enter another one
            System.out.println("All cards of this type have been played."
              + " Pick another one.");

          // let user a integer
          userNum = input.nextInt();

          }

          // check whether user enter -1
          if ( userNum == -1) {

            // break out
            break;
          }

          // generate another suit index number for user
          suitIndex = randomInteger.nextInt(4);
        }

        // check if user enter -1
        if ( userNum == -1 ) {

          break;
        }

        // otherwise show user's card
        System.out.println(Kinds[userNum -2] + " of " + Suits[suitIndex]);

        // store card as user card history
        userMoves.add(Kinds[userNum - 2] + " of " + Suits[suitIndex]);

        // store card into selected card list
        selectedCards.add(Kinds[userNum - 2] + " of " + Suits[suitIndex]);

        // check if user's card greater than computer
        if ( userNum > kindIndex + 2 ) {

          // increase win time for user
          stat.incrementUserWins();

          // print You won message
          System.out.println("You won");

        }

        // check if user's card less than computer
        else if ( userNum < kindIndex + 2) {

          // increase win time for computer
          stat.incrementComputerWins();

          // print I won message
          System.out.println("I won");
        }
        
        // check if user's card equal to computer
        else if ( userNum == kindIndex + 2) {

          // increase tie time
          stat.incrementTies();

          // print A tie message
          System.out.println("A tie");
        }
      
	    }

      // print play hidtory
      stat.printStats();
      System.out.println();
 
      // a loop to print user and computer card's history
      for( int i = 0; i < userMoves.size(); i ++) {

        // print message
        System.out.println("My Moves: " + computerMoves.get(i) +
            "  Your Moves: "
            + userMoves.get(i));

      }

      // print message to ask user whether play again
      System.out.print("Play again? ");
    
      //ask user to enter y or n
      playAgain = input.next();

    }

  // exit program when user enter n
  System.out.println("Bye, see you later!");   
  }	

}
