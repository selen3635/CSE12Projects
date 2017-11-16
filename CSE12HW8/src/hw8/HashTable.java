/**
 * Name:   Xiaolong Zhou
 * PID:    A13227137
 * Login:  cs12wlt
 */
package hw8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * which will implement all the hash table functionality.
 * @author xiaolongzhou
 * @version 1.0
 * @since 2018-02-30
 */
public class HashTable implements IHashTable {
  
  private static final int TWENTYS = 31;
  private static final double LOADFACT = 0.7;
  private static final int TWO = 2;
  private int nelem;
  private int mySize = 13;
  
  private String[] myTable = new String[mySize];
  private boolean[] booTable = new boolean[mySize];

  /**
   *  Inserts element  i  in the hash table.
   *  @return should return either “item successfully inserted," 
   *  or “item already present."
   */
  @Override
  public boolean insert(String value) throws NullPointerException{
  
    boolean done = false;

    // throw exception if value is null
    if ( value == null)
    {
      throw new NullPointerException();
    }
    
    
    double num = nelem;
    double size = mySize;
    
    // if number of element more than 70 percent of size
    if((num / size) > LOADFACT)
    {
      // increase size
      mySize = mySize * TWO;
      
      // crease temp array to store data
      String[] temp = new String[mySize];
      boolean[] temp1 = new boolean[mySize];
      for(int i = 0; i < mySize; i++)
      {
        temp1[i] =  false;
      }
      
      // rehash value and store it to temp table
      for(int j = 0; j < myTable.length; j++)
      {
        boolean jump = false;
        if(myTable[j] != null)
        {
          for( int k = 0; k < mySize && !jump; k++)
          {
            // rehash value
            int reHashVal = Math.abs((hashFunc(myTable[j]) + k* hashFunCRC(myTable[j]))
                % mySize);
            
            // check if it has been deleted
            if ( temp[reHashVal] == null && booTable[j] == false )
            {
              temp[reHashVal] = myTable[j];
              temp1[reHashVal] = false;
              
              jump = true;
            }
            //check if it has not been deleted
            else if( temp[reHashVal] == null && booTable[j] == true )
            {
              temp[reHashVal] = myTable[j];
              temp1[reHashVal] = true;
              
              jump = true;
              
            }
          }// end of inner for loop
        }
      }// end of outer for loop
      
      myTable = temp;
      booTable = temp1;
    }// end of rehash
    
    while(!done)
    {
      for( int i = 0; i < mySize; i++)
      {
        // find out hashed value using two hash function
        int hashVal = Math.abs((hashFunc(value) + i* hashFunCRC(value))
            % mySize);
        
        //check whether value already exist
        if (myTable[hashVal] != null && myTable[hashVal].equals(value))
        {
          System.out.println("item " + value + " already present");
          done = true;
          return false;
        }
        
        // check if it can be store at the position with hash value as index
        else if ( myTable[hashVal] == null || booTable[hashVal] == false )
        {
          myTable[hashVal] = value;
          booTable[hashVal] = true;
          nelem++;
          done = true;
  //        System.out.println(nelem);
          System.out.println("item "+value+ " successfully inserted" );
          return true;
        }
            
      }//end of for loop
      done = false;
      
    }// end of while


    return false;
    
  }
  

  /**
   * Use the hash table to determine where  i  is, 
   * delete it from the hash table. 
   * @return eturn either “item successfully deleted" or “item not found”.
   */
  @Override
  public boolean delete(String value) throws NullPointerException {
	  
    if( value == null)
    {
      throw new NullPointerException();
    }
    
    // a loop traverse through whole table and look for data
    for ( int i = 0; i < myTable.length; i++)
    {
      // find out hash value
      int hashVal = Math.abs((hashFunc(value) + i* hashFunCRC(value))
          % mySize);
      
      //check if value already exist
      if (myTable[hashVal] != null && myTable[hashVal].equals(value))
      {
        // set value at that position to null, and set it to false
       
        booTable[hashVal] = false;
        
        // print out message
        System.out.println("item " + value + " successfully deleted");
        
        nelem--;
        return true;
      }
      if(myTable[hashVal] == null)
      {
        return false;
      }
        
    }
    // print out message
    System.out.println("item " + value + " not found");
    return false;
  }

  /**
   * Uses the hash table to determine if  i  is in the data structure.
   * @return should return either “item found" or “item not found”.
   */
  @Override
  public boolean lookup(String value) throws NullPointerException{
  
    // check whether value is null 
    if ( value == null)
    {
      throw new NullPointerException();
    }
    
    // a loop traverse through whole table and look for data
    for ( int i = 0; i < myTable.length; i++)
    {
      // find out hash value
      int hashVal = Math.abs((hashFunc(value) + i* hashFunCRC(value))
          % mySize);
      
      //check if value already exist
      if (myTable[hashVal] != null && myTable[hashVal].equals(value)
          && booTable[hashVal] == true)
      {
        System.out.println("item " + value + " found");
        return true;
      }
      if(myTable[hashVal] == null)
      {
        return false;
      }
    }
    // print out message
    System.out.println("item " + value + " not found");
    return false;
  }

  /**
   * Print out the hash table.
   */
  @Override
  public void print() {
		
    // a loop to traverse hash table
    for( int i = 0; i < myTable.length; i++)
    {
      if(booTable[i] == true && myTable[i] != null )
      {
        // print out data
        System.out.println(i + ": " + myTable[i]);
      }
      else
        
        // print out index
        System.out.println(i + ":");
    }
		
  }

  /**
   * This is main method for this program.
   * @param args
   */
  public static void main(String[] args) 
  {
    //create new hash table
    HashTable myTable = new HashTable();
     
    // check if argument if valid
    if(args.length != 1)
    {
      System.err.println("Incorrect number of arguments: " +
          args.length);
      System.err.println("java hw6.EDF <input-file>");
      System.exit(1);
    }
    
    File file = new File(args[0]);
    
    try
    {
      
      Scanner myScan = new Scanner(file);
      
      // check whether there is more content
      while( myScan.hasNext())
      {
        //get next value from file
        String scanValue = myScan.next();
        
        // check if it requires to insert
        if( scanValue.equals("insert"))
        {
          String scanVal1 = myScan.next();
          String scanVal2 = scanVal1.substring(1, scanVal1.length() - 1);
          
          // insert data
          myTable.insert(scanVal2);
        }
        
        // check if it requires to lookup
        else if( scanValue.equals("lookup"))
        {
          String scanVal3 = myScan.next();
          String scanVal4 = scanVal3.substring(1, scanVal3.length() - 1);
          
          // lookup data
          myTable.lookup(scanVal4);
        }
        
        // check if it requires to delete
        else if( scanValue.equals("delete"))
        {
          String scanVal5 = myScan.next();
          String scanVal6 =  scanVal5.substring(1, scanVal5.length() - 1);
          
          //delete data
          myTable.delete(scanVal6);
        }
        
        // check if it requires to print table 
        else if ( scanValue.equals("print"))
        {
          // print table
          myTable.print();
        }
        
      }// end of while
      
      myScan.close();
    }// end of try
    catch(FileNotFoundException e )
    {
      System.out.println("Failed to open " + file);
      System.exit(1);
    }
    System.exit(0);
  }
  
  /**
   * This is a hash function which hash a key to array index
   * @param key
   * @return  return an integer which is hashed value
   */
  private int hashFunc(String key)
  {
    int hashVal = 0;
    
    // left to right
    for (int i = 0; i < key.length() ; i++)
    {
      //get char code
      int letter = key.charAt(i);
      
      // find hash value
      hashVal = (hashVal * TWENTYS + letter) % mySize;
    }
    
    return hashVal;
  }
  
  /**
   * This is a hash function called CRC.
   * @param str
   * @return return an integer which is hashed value
   */
  private int hashFunCRC(String str)
  {
    int hashVal = 0;
    
    for(int i = 0; i < str.length(); i++)
    {
      //left shift
      int leftShiftValue = hashVal << 5;
      
      //right shift
      int rightShiftValue = hashVal >> 27;
      
      // | is bitwise OR, ^ is bitwise XOR
      hashVal = ((leftShiftValue | rightShiftValue) ^ str.charAt(i)) % mySize;
    }

    //  check if hash value equal to 0
    if ( hashVal == 0)
      return ++hashVal;
    else
    return hashVal;
  }
  
}// end of class

