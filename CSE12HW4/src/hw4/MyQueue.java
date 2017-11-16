/**
 * Name:  Xiaolong Zhou
 * PID:   A13227137
 * Login: cs12wlt
 */
package hw4;

import java.util.*;

/**
 * A class which define some queue method
 * @author xiaolong zhou
 * @version 1.0
 * @since 2016-02-02
 */
public class MyQueue<T> implements Stack_QueueInterface<T>{

  //instance variable , create an array
  private T[] myArray;
  
  // constant variables
  private static final int ARRAYSIZE = 5;
  private static final int TWO =2;
  
  // instance variables, represent front and rear of array
  private int front;
  private int rear;
  
  
  /**
   * This method is going to check whether array is empty
   */
  @Override
  public boolean isEmpty() {
    
    return (front == rear);
  }

  /**
   * This method is going to add an element to rear of array
   * @param T  an element
   */
  @SuppressWarnings("unchecked")
  @Override
  public void addElement(T newItem) throws NullPointerException {
    
    // check whether item is null
    if(newItem ==null)
    {
      throw new NullPointerException();
    }
    
    // add item to rear of array
    myArray[rear] = newItem;
    
    //increment of rear index
    rear++;
    
    // check if rear at the end of array
    if(rear == myArray.length)
    {
      rear = 0;
    }
    
    // check if front index equal to rear index
    if ( front == rear) 
    {
      int counter = 0;
      
      // double array size
      T[] temp = (T[]) new Object[myArray.length * TWO];
      
      // copy old array to a temp array
      for(int i = front; i < myArray.length; i++)
      {  
        temp[counter] = myArray[i];
        counter++;
      }
      for(int j = 0; j < rear; j++)
      {
        temp[counter] = myArray[j];
        counter++;
      }
      // assign temp array to array
      myArray = temp;
      
      //reset front to 0
      front = 0;
      rear = counter;
    }
  }

  /**
   * This method is going to remove element from front of array
   * @return return an element
   */
  @Override
  public T removeElement() throws NoSuchElementException {
    
    // check if array is empty
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    T rmelem = myArray[front];
    //increment front index
    front++;
    
    // check if front index equal to lenght of array
    if ( front == myArray.length)
    {
      front = 0;
    }
    
    return rmelem;
  }

  /**
   * This method is going to return size of array
   * @return return an integer represent size of array
   */
  @Override
  public int size() {
    
    int nelems = 0;
    
    // count element on array
    if ( front < rear)
    {
      for( int i = front; i < rear; i++)
      {
        nelems++;
      }
   
    }
    else if( front > rear)
    {
      for ( int i = front; i < myArray.length; i++)
      {
        nelems++;
      }
      for( int j = 0; j< rear; j++)
      {
        nelems++;
      }
      
    }
    //return number of elements
    return nelems;
  }
  
  /**
   * This is a default constructor
   */
  @SuppressWarnings("unchecked")
  public MyQueue()
  {
    myArray = (T[]) (new Object[ARRAYSIZE]);
    
  }
  

}
