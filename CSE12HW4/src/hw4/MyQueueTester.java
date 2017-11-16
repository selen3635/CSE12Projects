/**
 * Name:  Xiaolong Zhou
 * PID:   A13227137
 * Login: cs12wlt
 */
package hw4;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.*;

/**
 * This class is going to test MyQueue class
 * @author xiaolong zhou
 * @version 1.0
 * @since 2016-02-02
 */
public class MyQueueTester {

  //create new MyQueue reference
  private MyQueue<Integer> empty;
  private MyQueue<Integer> several;
  private MyQueue<String> slist;
  
  //constant variable
  static final int DIM = 5;
  
  /**
   * Standard Test Fixture. An empty list, a list with
   * several entries
   */
  @Before
  public void setUp()
  {
    // set reference point to MyQueue object
    empty = new MyQueue<Integer>();
    several = new MyQueue<Integer>();
    slist = new MyQueue<String>();
    
    //List: 5, 4, 3,...,1
    for ( int i = DIM; i > 0; i--)
    {
      several.addElement(new Integer(i));
    }
    
    //List: "First", "Last"
    slist.addElement("Last");
    slist.addElement("First");
  }// end of set up
  
  /**
   * This method is going to test whether array is empty
   */
  @Test
  public void testEmpty()
  {
    assertTrue("empty is empty", empty.isEmpty());
    assertTrue("several is not empty", !several.isEmpty());
    assertTrue("slist is not empty", !slist.isEmpty());
    
  }
  
  /**
   * This method is going to test addElement method
   */
  @Test
  public void testAddElement()
  {
    // add element to several
    several.addElement(new Integer(9));
    several.addElement(new Integer(8));
    
    several.removeElement();
    several.removeElement();
    several.removeElement();
    several.removeElement();
    several.removeElement();
    assertEquals("Check addElement method",
        new Integer(9), several.removeElement());
    
    //add element to slist
    slist.addElement("football");
    slist.addElement("ball");
    
    slist.removeElement();
    slist.removeElement();
    slist.removeElement();
    assertEquals("Check addElement method",
        "ball", slist.removeElement());
    
  }
  
  /**
   * This method is going to test addElement exception
   */
  @Test
  public void testAddElementException(){
    
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
   * This method is going to teest removeElement
   */
  @Test
  public void testRemoveElement()
  {
    several.removeElement();
    assertEquals("Check removeElement method", 
        new Integer(4), several.removeElement());
    
    assertEquals("Check removeElement method", 
        "Last", slist.removeElement());
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
    catch(NoSuchElementException e)
    {
      
    }
  }
  
  /**
   * This method is going to test size method
   */
  @Test
  public void testSize() {
    
    empty.addElement(new Integer(1));
    empty.addElement(new Integer(2));
    
    assertEquals("Check size method", 2, empty.size());
    
    several.addElement(new Integer(8));
    
    assertEquals("Check size method", 6, several.size());
    
    slist.addElement("HI");
    
    assertEquals("Check size method", 3, slist.size());
  }
}
