/**
 * Name:  Xiaolong Zhou
 * PID:   A13227137
 * Login: cs12wlt
 */

package hw4;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.*;

/**
 *  This class is going to test MyStack class
 * @author xiaolong zhou
 * @version 1.0
 * @since 2016-02-02
 */
public class MyStackTester {
  
  //create new MyStack reference
  private MyStack<Integer> empty;
  private MyStack<Integer> several;
  private MyStack<String> slist;
  
  //constant variable
  static final int DIM = 5;
  
  /**
   * Standard Test Fixture. An empty list, a list with several
   * entries(0, 1, 2, 3, 4, 5)
   */
  @Before
  public void setUp()
  {
    
    //set reference point to MyStack object
    empty = new MyStack<Integer>();
    several = new MyStack<Integer>();
    slist = new MyStack<String>();
    
    // List: 5, 4, 3, ..., 1
    for ( int i = DIM; i > 0; i--)
    {
      several.addElement(new Integer(i));
    }
    
    //List : "First", "Last"
    slist.addElement("First");
    slist.addElement("Last");
    
  }// end of set up
  
  /**
   * This method is going to test whether list is empty
   */
  @Test
  public void testEmpty()
  {
    assertTrue("empty is empty", empty.isEmpty());
    assertTrue("several is not empty", !several.isEmpty());
    assertTrue("slist is not empty", !slist.isEmpty());
  }
  
  /**
   * This method is going to test size of list
   */
  @Test
  public void testSize()
  {
    assertEquals("Check several size", 5, several.size());
    assertEquals("Check slist size", 2, slist.size());
    assertEquals("Check empty size", 0, empty.size());
  }
  
  /**
   * This method is going to test addElement method
   */
  @Test
  public void testAddElement()
  {
    several.addElement(new Integer(6));
    several.addElement(new Integer(8));
    assertEquals("Check addElement method",
        new Integer(8), several.removeElement());
    
    slist.addElement("Final");
    assertEquals("Check addElement method", "Final", 
        slist.removeElement());
  }
  
  /**
   * This method is going to test addElement exception
   */
  @Test
  public void testAddElementException()
  {
    try
    {
      slist.addElement(null);
      fail("Should have generated an exception");
    }
    catch(NullPointerException e)
    {
      
    }
  }
  
  /**
   * This method is going to test removeElement method
   */
  @Test
  public void testRemoveElement()
  {
    several.removeElement();
    several.removeElement();
    assertEquals("Check remove method",
        new Integer(3), several.removeElement());
    
    //slist.addElement("Final");
    slist.removeElement();
    assertEquals("Check remove method", 
        "First", slist.removeElement());
  }
  
  /**
   * This method is going to test removeElement exception
   */
  @Test
  public void testRemoveElementException()
  {
    try
    {
      empty.removeElement();
      fail("Should have generated an exception");
    }
    catch(EmptyStackException e)
    {
      
    }
  }
}// end of class
