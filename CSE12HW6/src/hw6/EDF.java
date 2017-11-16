/**
 * Name:   Xiaolong Zhou
 * PID:    A13227137
 * Login:  cs12wlt
 */
package hw6;
import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A class which is earliest deadline first
 * @author xiaolongzhou
 * @version 1.0
 * @since 2018-02-30
 */
public class EDF {

  /**
   * This is main method for program with one argument
   * @param args
   */
  public static void main(String[] args) {
  
    if(args.length != 1)
    {
      System.err.println("Incorrect number of arguments: "+args.length);
      System.err.println("java hw6.EDF <input-file>");
      System.exit(1); 
    }
    File file = new File(args[0]);
    MyPriorityQueue<Record> queue = new MyPriorityQueue<Record>(10);
    long current_time=0;
    try{
      
		  Scanner myScan = new Scanner(file);
		  
		  // check whether there is more content
		  while ( myScan.hasNext())
		  { 
	
		    // get next value from file
		    String scanValue = myScan.next();
		    
		    // check whether value is schedule
		    if( scanValue.equals("schedule"))
		    {
		  
		      Record myRec = new Record(myScan.next(), myScan.nextLong(), 
		          myScan.nextLong());
		      queue.add(myRec);
		      System.out.println(current_time+ ": adding " + myRec.toString());
		    } 
		    
		    // check whether value is run
		    else if(scanValue.equals("run"))
		    {
		      Record myRec2 = null;
		    
		      // get run time
		      long runTime = myScan.nextLong();
		  
          myRec2 = queue.poll();
          
          // check whether record is null or not
          if( myRec2 != null)
          {       
          
            //print state
            System.out.println(current_time+ ": "+ "busy with "+ 
            myRec2.toString());
          
		        while (myRec2 != null && current_time + 
		            myRec2.GetDuration() < runTime)
		        {
		          //update current time
		          current_time = current_time + myRec2.GetDuration();
		      
		          System.out.println(current_time +": done with "+ 
		          myRec2.toString(current_time));

		          if ( current_time != runTime)
		          {
		            // remove and return next value from queue
		            myRec2 = queue.poll();
		          
		            if (myRec2 != null)
		            {
		              // print state
		              System.out.println(current_time + ": busy with " + 
		              myRec2.toString());
		            }
		          }
		        
		        }// end of inner while
          
		        //check if current time bigger than run time
		        if ( myRec2 != null && current_time != runTime)
		        {
		          // get new record with new duration
		          Record myRec3 = new Record(myRec2, myRec2.GetDuration() - 
		              (runTime - current_time));
		          // update current time
		          current_time = runTime;
		          
		          // add it to queue
		          queue.add(myRec3);
              System.out.println(current_time + ": adding " + 
		          myRec3.toString());
		        }
           
		      }
          
		    }
		    else
		    {
		      myScan.close();
		      throw new NoSuchElementException();
		    }
		    
		  }
		  
		  myScan.close();
    }
    // catch the file not found exception
    catch(FileNotFoundException e)
    {
      System.err.println("Failed to open "+file);
      System.exit(1);
    }
    System.exit(0);
		
  }

}//end of class
