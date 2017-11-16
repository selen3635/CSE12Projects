/**
 * Name:  Xiaolong Zhou
 * PID:   A13227137
 * Login: cs12wlt
 */
package hw4;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

/**
 * A class to test DoubleEndedLL class
 * @author xiaolong zhou
 * @version 1.0
 * @since 2016-02-02
 */
public class DoubleEndedLLTester
{

  // create new doubleEndedLL reference
  private DoubleEndedLL<Integer> empty;
  private DoubleEndedLL<Integer> several;
  private DoubleEndedLL<String> slist;

  //constant variable
  static final int DIM = 5;


  /**
   * Standard Test Fixture. An empty list, a list with one entry (0) and
   * a list with several entries( 0, 1, 2)
   */
  @Before
  public void setUp()
  {
    // set reference point to DoubleEndedLL object
    empty = new DoubleEndedLL<Integer>();
    several = new DoubleEndedLL<Integer>();
    slist = new DoubleEndedLL<String>();

    //List: 1, 2, 3,..., DIM
    for ( int i = DIM; i > 0; i --)
    {

      several.addFirst(new Integer(i));
    }

    // List:" First", "Last"
    slist.addFirst("Last");
    slist.addFirst("First");

  }// end of set up

  /**
   * This is going to test empty method
   */
  @Test
  public void testEmpty()
  {

    assertTrue("empty is empty", empty.isEmpty());
    assertTrue("several is not empty", !several.isEmpty());
    assertTrue("slist is not empty", !slist.isEmpty());
  }

  /**
   * This method is going to test constructor
   */
  @Test
  public void testConstructor()
  {

    assertEquals("Check constructor", 0, empty.size());
  }

  /**
   * This method is going to test size method
   */
  @Test
  public void testSize()
  {
    assertEquals("Check size method", 0, empty.size());
    assertEquals("Check size method", 5, several.size());
    assertEquals("Check size method", 2, slist.size());
  }
  
  /**
   * This method is going to test addFirst method
   */
  @Test
  public void testAddFirst()
  {
    // add element to empty list
    empty.addFirst(new Integer(8));
    empty.addFirst(new Integer(10));
    empty.addFirst(new Integer(12));
    int rmFirst = empty.removeFirst();
    assertEquals("Check add first with remove first method"
        , 12, rmFirst);
    
    int rmFirst2 = empty.removeFirst();
    assertEquals("Check add first with remove first method"
        , 10, rmFirst2);
    
    //add element to slist list
    slist.addFirst("two");
    slist.addFirst("four");
    slist.addFirst("six");
    String rmFirst3 = slist.removeFirst();
    assertEquals("Check add first with remove first method"
        , "six", rmFirst3);
    
    slist.removeFirst();
    slist.removeFirst();
    String rmFirst4 = slist.removeFirst();
    assertEquals("Check add first with remove first method"
        , "First", rmFirst4);
  }
  
  /**
   * This method is going to test addFirst exception
   */
  @Test
  public void testAddFirstException()
  {
    try
    {
      slist.addFirst(null);
      fail("Should have generated an exception");
      
    }
    catch(NullPointerException e)
    {
      
    }
  }
  
  /**
   * This method is going to test addLast method
   */
  @Test
  public void testAddLast()
  {
    // add element to several
    several.addLast(new Integer(6));
    several.addLast(new Integer(7));
    
    assertEquals("Check addLast Method", 
        new Integer(7), several.removeLast());
    
    // add element to slist
    slist.addLast("Last1");
    slist.addLast("Last2");
    
    assertEquals("Check addLast Method", "Last2", slist.removeLast());
    
  }
  
  /**
   * This method is going to test addLast method exception
   */
  @Test
  public void testAddLastException()
  {
    try
    {
      slist.addLast(null);
      fail("Should have generated an exception");
    }
    catch(NullPointerException e)
    {
      
    }
  }
  
  /**
   * This method is going to test removeFirst method
   */
  @Test
  public void testRemoveFirst()
  {
  
    assertEquals("Check removeFirst Method",
        new Integer(1), several.removeFirst());
    
    assertEquals("Check removeFirst Method",
        new Integer(2), several.removeFirst());
    
    assertEquals("Check removeFirst Method",
        "First", slist.removeFirst());
    
  }
  
  /**
   * This method is going to test removeFirst exception
   */
  @Test
  public void testRemoveFirstException()
  {
    try
    {
      empty.removeFirst();
      fail("Shouled have generated an exception");
    }
    catch(NullPointerException e)
    {
      
    }
  }
  
  /**
   * This method is going to test removeLast
   */
  @Test
  public void testRemvoeLast()
  {
    assertEquals("Check removeLast Method",
        new Integer(5), several.removeLast());
    assertEquals("Check removeLast Method",
        new Integer(4), several.removeLast());
    
    
    slist.removeLast();
    assertEquals("Check", "First", slist.removeLast());
    
  }
  
  /**
   * This method is going to test removeLast exception
   */
  @Test
  public void testRemoveLastException()
  {
    try
    {
      empty.removeLast();
      fail("Should have generated an exception");
      
    }
    catch(NullPointerException e)
    {
      
    }
  }
  
  
}//end of DoubleEndedLLTester
