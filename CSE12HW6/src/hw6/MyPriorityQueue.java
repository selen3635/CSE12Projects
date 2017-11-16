/**
 * Name:   Xiaolong Zhou
 * PID:    A13227137
 * Login:  cs12wlt
 */

package hw6;

/**
 * A class that implement binary heap.
 * @author xiaolongzhou
 * @version 1.0
 * @param <T>
 * @since 2018-02-30
 */
public class MyPriorityQueue<T extends Comparable<? super T>> {
  
  private dHeap<T> myQueue;
  
  /**
   * It is a constructor with one parameter
   * @param queueSize
   */
  public MyPriorityQueue(int queueSize)
  {
    myQueue = new dHeap<T>(queueSize);
    
  }
  
  /**
   * Inserts the specified element into this priority queue.
   * @param data
   * @throws NullPointerException
   */
  public void add(T data) throws NullPointerException
  {
    if ( data == null)
    {
      throw new NullPointerException();
    }
      
    myQueue.add(data);
  }
  
  /**
   * Retrieves and removes the head of this queue, or returns null 
   * if this queue is empty.
   * @return T
   */
  public T poll() 
  {
    if ( myQueue.size() == 0)
    {
      return null;
    }
    return myQueue.removeSmallest();   
  }

}
