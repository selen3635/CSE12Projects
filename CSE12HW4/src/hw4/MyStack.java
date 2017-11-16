/**
 * Name:  Xiaolong Zhou
 * PID:   A13227137
 * Login: cs12wlt
 */
package hw4;

import java.util.EmptyStackException;

/**
 * This class using sinle linked list to create a stack like data structure
 * @author xiaolong zhou
 * @version 1.0
 * @since 2016-02-02
 */
public class MyStack<T> implements Stack_QueueInterface<T> {

  private DoubleEndedLL<T> myList;
  
  /**
   * This method is going to determine whether this is empty
   * @return return a boolean value
   */
  @Override
  public boolean isEmpty() {
   
    return myList.isEmpty();
  }

  /**
   * This method is going to add an element to list
   * @param T  represent an element
   */
  @Override
  public void addElement(T newItem) throws NullPointerException{
    
    // check whether item is null
    if(newItem == null)
    {
      throw new NullPointerException();
    }
    // add element to list
    myList.addLast(newItem);
  }

  /**
   * This method is going to remove element from list
   * @return return an element
   */
  @Override
  public T removeElement() throws EmptyStackException {
  
    // check if size is 0
    if(size() == 0)
    {
      throw new EmptyStackException();
    }
    return myList.removeLast();
  }

  /**
   * This method is going to return size of list
   * @return return an integer represent size
   */
  @Override
  public int size() {
 
    return myList.size();
  }
  
  /**
   * This is a constructor
   */
  public MyStack()
  {
    myList = new DoubleEndedLL<T>();
  }

}// end of MyStack class
