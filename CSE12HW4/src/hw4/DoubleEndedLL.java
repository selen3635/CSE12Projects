/**
 * Name:  Xiaolong Zhou
 * PID:   A13227137
 * Login: cs12wlt
 */

package hw4;



/**
 * A double ended class with node. Include add and remove methods
 * 
 * @author xiaolong zhou
 * @version 1.0
 * @since 2016-02-02
 */
public class DoubleEndedLL<T> implements DoubleEndedLLInterface<T> {

  // instance variable to check number of elements
  private int nelems;

  // create new node objects
  private Node head;
  private Node tail;

  //create dummy node objects
  private Node headDummy = new Node(null);
  private Node tailDummy = new Node(null);


  /**
   *  A inner class. As a pointer, point to next node
   */
  protected class Node
  {

    // a new element
    T data;

    //create new node objects
    Node next;

    /**
     *  Constructor to create sinleton Node
     * @param element element to add, can be null
     */
    public Node(T element)
    {
      this.data = element;
      next = null;
    }

    /**
     *  Constructor to create singleton link with next node
     * @param element Element to add, can be null
     * @param nextNode successor Node, can be null
     */
    public Node(T element, Node nextNode)
    {

      this.data = element;
      this.next = nextNode;
    }

    /**
     * Set the next node in the list
     * @param n new next node
     */
    public void setNext(Node n)
    {

      next = n;
    }

    /**
     * Set the element
     * @param e new element
     */
    public void setElement(T e)
    {

      data = e;
    }

    /**
     * Accessor to get the next Node in the list
     */
    public Node getNext()
    {

      return next;
    }

    /**
     *  This method is going to get an element
     * @return a generic type
     */
    public T getElement()
    {

      return data;
    }


  } // end of Node class


  /**
   * only 0-argument constructor is define
   */
  public DoubleEndedLL()
  {
    // set number of element to 0
    nelems = 0;
    
    head = headDummy;
    tail = tailDummy;
    
    // create a empty list
    headDummy.setNext(tailDummy);

  }

  /**
   * This method is going to determine whether list is empty or not
   * @return  return a boolean value, true if empty otherwise false
   */
  public boolean isEmpty()
  {

    // check whether this is empty
    if ( nelems == 0 || headDummy.getNext() == tailDummy)
    {

      return true;
    }
    else
      return false;
  }

  /**
   * This method is going to return the size of list
   * @return return an integer represent size of list
   */
  public int size()
  {
    return nelems;

  }

  /**
   * This method is going to add an element to front of list
   * @param newItem   an element to be add
   * @throws NullPointerException
   */
  public void addFirst(T newItem) throws NullPointerException
  {
    Node newNode = new Node(newItem);

    // check whether new element is null
    if ( newItem == null )
    {
      throw new NullPointerException();
    }

    else
    {

      // add newItem to front of list
      newNode.setNext(headDummy.getNext());
      headDummy.setNext(newNode);
    }

    // increment number of elements
    nelems++;

  }

  /**
   * Add an element to the end of the list
   * @param newItem  an element to to add to list
   * @throws NullPointerException
   */
  public void addLast(T newItem) throws NullPointerException
  {
    Node cursor = head;
    Node newNode = new Node(newItem);

    // check whether newItem is null
    if( newItem == null) 
    {
      throw new NullPointerException();

    }

    else
    {
      // traverse to last element
      for( int i = 0; i < nelems; i++)
      {

        cursor = cursor.getNext();
      }

      // add newItem to the end of list
      cursor.setNext(newNode);
      newNode.setNext(tailDummy);

    }

    //increment number of elements
    nelems++;

  }

  /**
   * This method is going to remove an element from first of list
   * @return T , return a element
   */
  public T removeFirst() throws NullPointerException
  {
    Node removeNode = headDummy.getNext();
    T removeElement;

    // if size is 0, then throw exception
    if (this.size() == 0) {

      throw new NullPointerException();
    }
    else
    { 
      // remove element
      removeElement = removeNode.getElement();
      headDummy.setNext(removeNode.getNext());
    }

    // decrement number of element
    nelems--;
    return removeElement;

  }
  /**
   * This method is going to remove an element from last of list
   * @return T , return a element
   */
  public T removeLast() throws NullPointerException
  {
    Node removeNode = headDummy;

    // check if list is empty
    if(isEmpty() ) 
    {
      throw new NullPointerException();

    }
    else
    {
      for( int i = 1; i < nelems; i++) 
      {
        removeNode = removeNode.getNext();

      }

      //remove element
      T removeElement = removeNode.getNext().getElement();
      removeNode.setNext(tailDummy);

      // decrement number of element
      nelems--;
      return removeElement;

    }

  }
  


}// end of DoubleEndedLL class
