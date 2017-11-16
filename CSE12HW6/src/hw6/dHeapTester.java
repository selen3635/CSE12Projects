/**
 * Name:   Xiaolong Zhou
 * PID:    A13227137
 * Login:  cs12wlt
 */

package hw6;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

/**
 * A class to test dHeap class methods
 * @author xiaolongzhou
 * @version 1.0
 * @since 2018-02-30
 */
public class dHeapTester {

  private dHeap<Integer> empty;
  private dHeap<Integer> several;
  private dHeap<String> slist;
  
  /**
   * Set up tester. An empty heap, and two heaps with elements.
   */
  @Before
  public void setUp()
  {
    empty = new dHeap<Integer>(0);
    several = new dHeap<Integer>(3,9);
    slist = new dHeap<String>(2, 6);
    
    for( int i = 2; i < 11; i++)
    {
      several.add(new Integer(i));
    }
    
    slist.add("C");
    slist.add("D");
    slist.add("E");
    slist.add("F");
    slist.add("G");
    slist.add("H");
    
  }// end of set up
  
  /**
   * This method is going to test size method
   */
  @Test
  public void testSize()
  {
    assertEquals("Check size method", 9, several.size());
    assertEquals("Check size method", 6, slist.size());
  }
  
  /**
   * This tester is going to test exception of ctor
   */
  @Test
  public void testdHeapCtorException()
  {
    try
    {
      new dHeap<Integer>(-1);
      fail("Should have generated an exception");
    }
    catch(IllegalArgumentException e)
    {
      
    }
  }
  
  /**
   * This tester is going to test exception of ctor
   */
  @Test
  public void testdHeapCtorException2()
  {
    try
    {
      new dHeap<String>(-1, -2);
      fail("Should have generated an exception");
    }
    catch(IllegalArgumentException e)
    {
      
    }
  }
  
  /**
   * This tester is going to test add method
   */
  @Test
  public void testAdd()
  {
    several.add(new Integer(1));
    several.add(new Integer(0));

    assertEquals("Check add method", new Integer(0), 
        several.removeSmallest());
  
    slist.add("B");
    slist.add("A");

    assertEquals("Check add method", "A", 
        slist.removeSmallest());
  }
  
  /**
   * This tester is going to test exception of add method
   */
  @Test
  public void testAddException()
  {
    try
    {
      several.add(null);
      fail("Shouled have generated an exception");
    }
    catch(NullPointerException e)
    {
      
    }
  }
  
  /**
   * This tester is going to test remove smallest method
   */
  @Test
  public void testRemoveSmallest()
  {
    several.removeSmallest();
    several.removeSmallest();
    several.removeSmallest();
    several.removeSmallest();
    several.removeSmallest();
    several.removeSmallest();
    assertEquals("Check remove method", new Integer(8),
        several.removeSmallest());
    
    slist.removeSmallest();
    slist.removeSmallest();
    assertEquals("Check remove method", "E", 
        slist.removeSmallest());
    
    dHeap<Integer> efd = new dHeap<Integer>(2, 3);
    
    efd.add(new Integer(1000));
    efd.add(new Integer(1400));
    efd.add(new Integer(1725));
    
    efd.printArray();
    
    efd.removeSmallest();
    
    efd.printArray();
    
  }
  
  /**
   * This tester is going to test exception of remove method
   */
  @Test
  public void testRemoveException()
  {
    try
    {
      empty.removeSmallest();
      fail("Should have generated an exception");
    }
    catch(NoSuchElementException e)
    {
      
    }
  }

}// end of class
