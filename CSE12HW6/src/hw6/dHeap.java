/**
 * Name:   Xiaolong Zhou
 * PID:    A13227137
 * Login:  cs12wlt
 */

package hw6;

import java.util.*;

/**
 * A dheap class that you can add or remove element from heap.
 * @author xiaolongzhou
 * @version 1.0
 * @since 2018-02-30
 * @param <T>  generic type
 */
class dHeap <T extends Comparable <? super T>> implements dHeapInterface<T> {

  private static final int TWO =2;
  // private int heapSize;
  private int d;
  private int nelem;
  private T[] myArray;
  /**
   * The constructor  takes one argument: an initial capacity
   * Used when binary heap is needed.
   * @param heapsize   this represent capacity of heap
   */
  @SuppressWarnings("unchecked")
  public dHeap (int heapSize) throws IllegalArgumentException
	{
    if ( heapSize < 0 )
    {
      throw new IllegalArgumentException();
    }
    //this.heapSize = heapSize;
    d = TWO;
    
    myArray = (T[]) new Comparable[heapSize];
	}

  /**
   * The constructor takes two arguments: an initial capacity
   * and the number of children, d
   * if d is less than one, throw IllegalArgumentException();
   * @param heapSize   this represent capacity of heap
   * @param d     this represent number of children d
   */
  @SuppressWarnings("unchecked")
  public dHeap (int d, int heapSize) throws IllegalArgumentException
  {
    if ( d < 1)
    {
      throw new IllegalArgumentException();
    }
    if ( heapSize < 0 )
    {
      throw new IllegalArgumentException();
    }
    
    //this.heapSize = heapSize;
    this.d = d;
    
    myArray = (T[]) new Comparable[heapSize];
  }

  /**
   * This method is going to return size of array
   * @return int    return an integer number
   */
  public int size()
  {
    return nelem;
    
  }
	
  /**
   * This method is going to add a data to heap. Increase array size if need
   * @param data   an element will be added to heap
   */
  @SuppressWarnings("unchecked")
  public void add (T data) throws NullPointerException
  {
    // check whether data is null
    if ( data == null)
    {
      throw new NullPointerException();
    }
    
    // increase array size if array if full
    if ( nelem == myArray.length)
    {
      T[] tempArray = (T[]) new Comparable[myArray.length * TWO + 1];
      
      for ( int i = 0; i < myArray.length; i++)
      {
        tempArray[i] = myArray[i];
      }
      
      myArray = tempArray;
      
    }
    
    // add data to the end of array
    myArray[nelem] = data;
    
    bubbleUp(nelem);

    nelem++;
  }

  /**
   * This method is going to remove a smallest element from heap.
   * @return T  return a T type of element
   */
  public T removeSmallest() throws NoSuchElementException
  {
    if ( size() == 0)
    {
      throw new NoSuchElementException();
    }
    
    T temp = myArray[0];
    
    myArray[0] = myArray[nelem-1];
       
    
    nelem--;
 
    trickleDown(0);
    
    return temp;
    
  }
	
  /**
   * This method is going to swap two elements if value of parent is bigger than child
   * @param index  index of array
   */
  private void trickleDown(int index)
  {
    int minChildIndex = d*index + 1;

    if(size() == 0 || size() == 1)
    {
      return;
    }
    if( minChildIndex > (size() -1))
    {
      return;
    }
    else
    {
      T tempMin = myArray[minChildIndex];
      for( int i = minChildIndex; i <= d*index + d; i++)
      {
        
        if(i <=  size() - 1)         
        {
          if (tempMin.compareTo(myArray[i]) > 0)
          {
            tempMin = myArray[i];
          
            minChildIndex = i;
          }
        }
      }// end of for loop
      
    }
    
    if(myArray[index].compareTo(myArray[minChildIndex]) > 0)
    {
      T temp = null;
      
      temp = myArray[index];
      myArray[index] = myArray[minChildIndex];
      myArray[minChildIndex] = temp;
    }
    else {
      return;
    }
    
    // recursive call
    trickleDown(minChildIndex);
  }
	
  /**
   * This method is going to swap two elements if value of child is less than parent value.
   * @param index  index of array
   */
  private void bubbleUp(int index)
	{
 
    if ( index > 0 && myArray[((index -1) / d)].compareTo(myArray[index]) > 0)
    {
      T temp = null;
      
      // swap parent element with child element
      temp = myArray[(index -1) / d];
      myArray[(index - 1) / d] = myArray[index];
      myArray[index] = temp;
      
      // recursive call 
      bubbleUp((index - 1) / d);     
    }
	  
  }
  
  public void printArray()
  {
    System.out.print("[");
    for(int i =0; i < size(); i++)
    {
      System.out.print(myArray[i] + ", ");
  }
    System.out.print("]\n");
  }
  

}// end of dHeap class
