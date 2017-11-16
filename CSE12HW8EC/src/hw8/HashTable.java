/**
 * Name:   Xiaolong Zhou
 * PID:    A13227137
 * Login:  cs12wlt
 */
package hw8;



/**
 * which will implement all the hash table functionality.
 * @author xiaolongzhou
 * @version 1.0
 * @since 2018-02-30
 */
public class HashTable implements IHashTable {
  
  // constant variables
  private static final int THIRTYONE = 31;
  private static final int FIVE =5;
  private static final double LOADFACT = 0.7;
  private static final int TWO = 2;
  
  // instance variables
  private int nelem;
  private int mySize = 13;
  private int numExpand;
  private boolean isResize;
  private int numColli;
  
  // arrays
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
    numColli = 0;

    // throw exception if value is null
    if ( value == null)
    {
      throw new NullPointerException();
    }
    
    float num = nelem;
    float size = mySize;
    
    // if number of element more than 70 percent of size
    if((num / size) > LOADFACT)
    {
      // increase size
      mySize = mySize * TWO;
      numExpand++;
      isResize = true;
      
      getLoadFac();
      
      // crease temp array to store data
      String[] temp = new String[mySize];
      boolean[] temp1 = new boolean[mySize];
      
      // set boolean temp array to false
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
            
            // check if it was deleted
            if ( temp[reHashVal] == null && booTable[j] == false )
            {
              temp[reHashVal] = myTable[j];
              temp1[reHashVal] = false;
              
              jump = true;
            }
            
            // check if it was not deleted
            else if( temp[reHashVal] == null && booTable[j] == true )
            {
              temp[reHashVal] = myTable[j];
              temp1[reHashVal] = true;
              
              jump = true;
            }
            //increse collision
            else
            {
              numColli++;
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
          //System.out.println("item " + value + " already present");
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

          //System.out.println("item "+value+ " successfully inserted" );
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
        
        nelem--;
        // print out message
        //System.out.println("item " + value + " successfully deleted");
        
        return true;
      }
      if(myTable[hashVal] == null)
      {
        return false;
      }
        
    }
    // print out message
    //System.out.println("item " + value + " not found");
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
        //System.out.println("item " + value + " found");
        return true;
      }
      if(myTable[hashVal] == null)
      {
        return false;
      }
    }
    //System.out.println("item " + value + " not found");
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
      hashVal = (hashVal * THIRTYONE + letter) % mySize;
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
      int leftShiftValue = hashVal << FIVE;
      
      //right shift
      int rightShiftValue = hashVal >> THIRTYONE;
      
      // | is bitwise OR, ^ is bitwise XOR
      hashVal = ((leftShiftValue | rightShiftValue) ^ str.charAt(i)) % mySize;
    }

    //  check if hash value equal to 0
    if ( hashVal == 0)
      return ++hashVal;
    else
    return hashVal;
  }
  
  /**
   * A method to return number of expand.
   * @return
   */
  public int getNumExpand()
  {
    return numExpand;
  }
  
  /**
   * A method to calculate load factor
   * @return
   */
  public float getLoadFac()
  {
    return (float) nelem / (float) mySize;
  }
  
  /**
   * A method to return number of collisions
   * @return
   */
  public int myColli()
  {
    return numColli;
  }
  
  /**
   * A method to check whether array has been resised
   * @return
   */
  public boolean hasResized()
  {
    boolean checkResize = isResize;
    
    //reset it to false
    isResize = false;
    
    return checkResize;
  }
}// end of class

