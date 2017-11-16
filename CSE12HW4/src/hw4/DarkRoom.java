/**
 * Name:  Xiaolong Zhou
 * PID:   A13227137
 * Login: cs12wlt
 */

package hw4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * A class that will find out a path to get out of room using both stack
 * and queue method.
 * @author xiaolong zhou
 * @version 1.0
 * @since 2016-02-02
 */
public class DarkRoom implements DarkRoomInterface {

  // create a char array
  protected char [][] darkRoom;
  
  // variables represent rows and columns
  protected int numRows=0;
  protected int numCols;
  
  // instance variable to track the place that visited
  private int numVisited;


  /**
   * This method is going to receive a file as a parameter
   * and read file
   * @param fname, a file that is going to be read
   */
  public void readFromFile(String fname) {

    // create a String object of line
    String line;
    
    //create new type of bufferedReader
    BufferedReader inputStrem;
    StringTokenizer st;


    try {
      //initialize current row location
      int currentRow = 0;

      // read file
      inputStrem = new BufferedReader(new FileReader(fname));

      // check whether file contains more line
      while ((line = inputStrem.readLine()) != null) {
        
        // check whether number of row equal to 0
        if (numRows == 0) {
          
          // set line to string tokenizer
          st = new StringTokenizer(line);
          
          // get next token from this string token and
          //assign to numRows
          numRows = Integer.parseInt(st.nextToken());
          
          //get next token from this string token and
          //assign to numCols
          numCols = Integer.parseInt(st.nextToken());
          
          // create new dark room with rows and cols
          darkRoom = new char[numRows][numCols];
          
        }
        // check if length of line equal to 1
        else if (line.length() == 1)
          //jump out of loop
          break;
        
        else {
          // create two dimension array for dark room
          for (int c = 0; c < numCols; c++) {
            darkRoom[currentRow][c] = line.charAt(c);
          }
          //increment of current row
          currentRow ++;
        }
      }
		}//end of try
    // catch exceptions
    catch (IOException e) {
      
      //print out exception message
      System.out.println (e.toString());
      System.out.println("Could not find file " + fname);
    }

  }// end of readFromFile

  
  /**
   *  Method that returns the Location of "start"
   *  @return return a location represent start point
   */
  public Location findStart()
  {
    Location start = null;
    
    // a nested loop to find start point
    for( int i = 0; i < numRows; i++)
    {
      for ( int j = 0; j < numCols; j ++)
      {
        // check whether this location with S char
        if( darkRoom[i][j] == 'S')
        {
          // if yes, then set this to start location
          start = new Location(i, j);
        }
          
      }
    }
    return start;
  }

  /**
   * Method that checks if the goal was found
   * @param loc, represent location
   * @return return a boolean value to determine whether there is a door
   */
  public boolean isDoor (Location loc)
  {
    // check around this location to see if there is a door
    if ( darkRoom[loc.left().getRow()][loc.left().getColumn()] == 'D' ||
         darkRoom[loc.up().getRow()][loc.up().getColumn()] == 'D'||
         darkRoom[loc.right().getRow()][loc.right().getColumn()] == 'D' ||
         darkRoom[loc.down().getRow()][loc.down().getColumn()] == 'D')
      
      return true;
    
    return false;
  }


  /**
   * Method that checks if you can move
   * @param loc  represent a location
   * @return  return a boolean value to determine whether if can move
   */
  public boolean canMove(Location loc)
  {
    // check if this location is a space
    if(darkRoom[loc.getRow()][loc.getColumn()] == ' ')
      
      return true;
    
    return false;
  }

  /**
   * Marks explored (visited) positions
   * @param loc, represent a location
   */
  public void markVisited (Location loc)
  {
    // mark this location to a '.', represents visited
    darkRoom[loc.getRow()][loc.getColumn()] = '.';
    
    // keep track number of visited
    numVisited++;

  }

  /**
   * counts the number of visited positions
   * @return return a integer represent number of visited
   */
  public int countVisited()
  {
    
    return numVisited;
  }


  /**
   * removes marks from visiting (removes '.')
   */
  public void clear()
  {
    //a nested loop to clear all position with '.'
    for ( int i = 0; i < numRows; i++)
    {
      for( int j = 0; j < numCols; j ++)
      {
        //check if this position with '.'
        if( darkRoom[i][j] == '.')
          
          //set to space
          darkRoom[i][j] = ' ';
      }
    }
    //reset number of visited
    numVisited = 0;
  }

  //prints your array that represents a room
  public void printRoom()
	{
    //a nested loop to print room
    for ( int i = 0; i < numRows; i ++)
    {
      for ( int j = 0; j < numCols; j++)
      {
        // print out this position
        System.out.print(darkRoom[i][j]);
      }
      System.out.println();
    }

	}

	/**
	 * This method is going to find out ways to find door with stack and queue
	 * @param choice, represent stack or queue
	 */
	public void escapeDarkRoom(String choice){

	  // local variable represent a location and call find start
	  Location myLoc = findStart();
	
	  // create a new reference
	  Stack_QueueInterface<Location> storage = null;
	  
	  // check which method going to use( stack or queue
	  if("Stack".equals(choice))
	  {
	    // create a new my stack object
	    storage = new MyStack<Location>();
 	    
	  }
	  else if ("Queue".equals(choice))
	  {
	    storage = new MyQueue<Location>();  
	  }
	  
	  // add start location to storage
    storage.addElement(myLoc);	 
	 
    // a forever loop until find a door
    while(true)
	  //while(!isDoor(myLoc))
	  {
	    // pop out start location
	    myLoc = storage.removeElement();

      // check whether this location is a space
	    if( darkRoom[myLoc.getRow()][myLoc.getColumn()] == ' ')
	      
	      //if yes, then mark as visited
	      this.markVisited(myLoc);
	    
	    // check if there is a door around
      if(isDoor(myLoc))
      {
        break;
      }
      // check whether left of this location is a space
	    if(darkRoom[myLoc.left().getRow()][myLoc.left().getColumn()] == ' ')
	    {
	      storage.addElement(myLoc.left());
	    }
	    // check whether up of this location is a space
	    if(darkRoom[myLoc.up().getRow()][myLoc.up().getColumn()] == ' ')
	    {
	      storage.addElement(myLoc.up());
	    }
	    // check whether right of this location is a space
	    if(darkRoom[myLoc.right().getRow()][myLoc.right().getColumn()] == ' ')
	    {
	      storage.addElement(myLoc.right());
	    }
	    // check whether down of this location is a space
	    if(darkRoom[myLoc.down().getRow()][myLoc.down().getColumn()] == ' ')
	    {
	      storage.addElement(myLoc.down());
	    }

	    
	  }//end of while loop
	  
    // print data
	  printGoal(choice, this.countVisited(), storage.size());
	  
	  // print out room
	  printRoom();
	  
	  // clear all marked position
	  clear();
	  
  }//end of escapeDardRoom

	/**
	 * This method is going to print all data
	 * @param choice which method going to use, stack or queue
	 * @param stepsTaken  position visited
	 * @param positionsLeft  size of storage
	 */
  public void printGoal(String choice, int stepsTaken, int positionsLeft)
	{
	  System.out.println("Goal found (with " + choice + "): It took "
		    + stepsTaken + " explored positions");
		System.out.println("There is (are) " + positionsLeft
			    + " position(s) left to explore in " + choice);

	}

}
