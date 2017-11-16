/**
 * Name:   Xiaolong Zhou
 * PID:    A13227137
 * Login:  cs12wlt
 */

package hw6;

/**
 * A heap interface with some method header.
 * @author xiaolongzhou
 * @version 1.0
 * @since 2018-02-30
 * @param <T>  generic type
 */
public interface dHeapInterface <T>{

  /**
   * Return number of elements stored in the heap
   * @return int   return an integer number
   */
  public int size();
  
  /**
   * Remove and returns the smallest element stored in the Heap. If heap
   *is empty, this method throws a  NoSuchElementException
   * @return T  return an element
   */
  public T removeSmallest();
  
  /**
   * Adds a specific element to the heap; o can't be  null. Resize the storage if full.
   * @param o   represent an element
   */
  public void add(T o);
  

}// end of interface
