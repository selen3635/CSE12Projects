/**
 * Name:   Xiaolong Zhou
 * PID:    A13227137
 * Login:  cs12wlt
 */
package hw6;

/**
 * A class that record data and will print data
 * @author xiaolongzhou
 * @version 1.0
 * @since 2018-02-30
 */
public class Record implements Comparable<Record> {

  private String process;
  private long deadline; 
  private long duration; 

  /**
   * constructor to create a new record
   * given the name of the process,
   * deadline and duration
   */
  Record (String process, long deadline, long duration)
  {
    this.process = process;
    this.deadline = deadline;
    this.duration = duration;
  }

   /**
    * constructor to create a new record
    * from the existing record and new
    * duration
    * @param r  record
    * @param duration  
    */
  Record (Record r, long duration)
  {
    this.process=r.process;
    this.deadline=r.deadline;
    this.duration=duration;
  }
	
  /**
   * This method is going to get duration time
   * @return long
   */
  public long GetDuration()
  {
    return duration;
  }
	
  /**
   * This method is going to return a string 
   * @return String
   */
  public String toString()
  {
    return process+" with deadline "+deadline+" and duration "+duration;
  }
  
  /**
   * This method is going to return a string
   * @param current_time
   * @return String
   */
  public String toString(long current_time)
  {
    if(current_time > deadline) return process + " (late)";
    return process;
  }
	
  @Override
  public int compareTo(Record o) throws NullPointerException
  {
    if ( o == null)
    {
      throw new NullPointerException();
    }
	  if ( this.deadline < o.deadline)
	  {
	    return -1;
	  }
	  else if ( this.deadline == o.deadline)
	  {
	    return 0;
	  }
	  else  
	    return 1;

  }
}//end of class
