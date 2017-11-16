/**
 * Name:   Xiaolong Zhou
 * PID:    A13227137
 * Login:  cs12wlt
 */
package hw8;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A class to check whether word is correctly spelled and give some
 * suggestions if possible.
 * @author xiaolongzhou
 * @version 1.0
 * @since 2020-02-30
 */
public class SpellChecker implements ISpellChecker {
  
  HashTable myTable = new HashTable();
  private static final int LETTERA = 97;
  private static final int LETTERZ = 122;

  /** Read a dictionary from the specified reader
   * 
   * import java.io.FileReader
   * 
   * spellChecker.readDictionary(new FileReader(filename));
   * 
   * @param reader a character stream
   */
  @Override
  public void readDictionary(Reader reader) {
    
    String myText = "";
    //create a new file writer
    FileWriter myWrite = null;
	  try {
	     // generate a test file 
       myWrite = new FileWriter("stats.txt");
       
    } catch (IOException e) {
      
      e.printStackTrace();
    }
	  
	  // create a new buffered reader
	  BufferedReader myReader;
	  
	  try
	  {
	    myReader = new BufferedReader(reader);
	    
	    // check if file still have more content
	    while( (myText = myReader.readLine()) != null)
	    {
	      // insert data to table
	      myTable.insert(myText);
	      
	      // check if table has been resized
        if( myTable.hasResized())
        {
          // print out data
          myWrite.write(myTable.getNumExpand() + " resize, " + 
            "load factor " + myTable.getLoadFac() + " " + myTable.myColli() 
            + " collisions\n" );
        }
	  
	    }
	    myWrite.close();
	  }
	  catch(IOException e)
	  {
	    System.out.println (e.toString());
      System.out.println("Could not find file " + reader);
	  }
	  
	  
	  
  }

  /** Check if the given word is properly spelled
   * 
   * If the given word is properly spelled return null.
   * If the word is not properly spelled, return all variants as
   * described by the homework write up. 
   * 
   * If no variants were found return an empty array.
   * 
   * @param word word to check
   * @return null (word spelled correctly), array of variants (if not), 
   * or empty array (if no variants)
   */
  @Override
  public String[] checkWord(String word) {
    
    // create a array list
    List<String> myList = new ArrayList<String>();
    
    // convert word to lower case
    String myWord = word.toLowerCase();
    boolean variant = false;
    
    // check if word is not found in dic
    if( myTable.lookup(myWord))
    {
      return null;
    }
    // try to find variants
    else
    {
      // convert myWord to a char array
      char[] myChar = myWord.toCharArray();
      
      //case 1:
      for( int i = 0; i < myWord.length(); i++)
      {
        // loop from char A to Z
        for( int j = LETTERA; j <= LETTERZ; j++)
        {
          myChar[i] = (char)j;
          
          String myString = new String(myChar);
          
          // check if dic has this string value
          if(myTable.lookup(myString))
          {
            variant = true;
            
            if(!myList.contains(myString))
            {
              // add it to list
              myList.add(myString);
            }
          }
        }
        
        // reset mychar
        myChar = myWord.toCharArray();
      }// end of outer loop
      
      //case 2:
      for( int i  = 0; i < myWord.length(); i++)
      {
        String part1 = myWord.substring(0, i);
        String part2 = myWord.substring(i + 1, myWord.length());
        
        String myString = part1 + part2;
        
        if(myTable.lookup(myString))
        {
          variant = true;
          
          if(!myList.contains(myString))
          {
            // add it to list
            myList.add(myString);
          }
        }
      }
      
      //case 3:
      for( int i = 0; i <= myWord.length(); i++)
      {
        for(int j = LETTERA; j <= LETTERZ; j++)
        {
          String part1 = myWord.substring(0, i);
          String part2 = myWord.substring(i, myWord.length());
          
          String myString = part1 + (char) j + part2;
          
          if(myTable.lookup(myString))
          {
            variant = true;
            
            if(!myList.contains(myString))
            {
              // add it to list
              myList.add(myString);
            }
          }
        }
      }
      
      //case 4:
      myChar = myWord.toCharArray();
      
      for( int i = 0; i < myWord.length() - 1; i++)
      {
        char temp;
        
        temp = myChar[i];
        myChar[i] = myChar[i + 1];
        myChar[i + 1] = temp;
        
        String myString = new String(myChar);
        
        // check if dic has this string value
        if(myTable.lookup(myString))
        {
          variant = true;
          
          if(!myList.contains(myString))
          {
            // add it to list
            myList.add(myString);
          }
        }
        myChar = myWord.toCharArray();
      }
      
      // check if it has variants
      if( variant)
      {
        //print out all variants
        System.out.print(myWord + ": ");
        for(int i = 0; i < myList.size(); i++)
        {
          if ( i != myList.size() -1)
          {
            System.out.print(myList.get(i) + ", ");
          }
          else
          {
            System.out.println(myList.get(i));
          }
        }
      }
      // if no variants print out original one
      else
      {
        System.out.println(myWord + ": ");
      }
      
      // create a new array to store data
      String[] myArray = new String[myList.size()];
      
      // copy data from list to my array
      for ( int i = 0; i < myList.size(); i++)
      {
        myArray[i] = myList.get(i);
      }
      return myArray;
    }
    

  }
	
  /**
   * This is main method for this program, will take two arguments
   * @param args
   */
  public static void main(String[] args) 
  {
    // check if argument if valid
    if(args.length != 2)
    {
      System.err.println("Incorrect number of arguments: " +
          args.length);
      System.err.println("java hw6.EDF <input-file>");
      System.exit(1);
    }
    
    // create a variable for spell checker
    SpellChecker myCheck = new SpellChecker();
    
    Reader myDic = null;
    try {
      
      // create a new reader that will read first argument
      myDic = new BufferedReader(new FileReader(args[0]));
      
      // read first argument
      myCheck.readDictionary(myDic);
      
      //create  new file
      File myFile = new File(args[1]);
      
      // create a new scanner
      Scanner myScan = new Scanner(myFile);
      
      // check if it has next
      while ( myScan.hasNext())
      {
        String myWord = myScan.next();
        
        // check word
        myCheck.checkWord(myWord);
      }
      
      myScan.close();
      
    } 
    //catch exceptions
    catch (FileNotFoundException e) {
      
      System.out.println("Failed to open " + myDic);
      System.exit(1);
    }
    
    
    
    
    

  }

}
