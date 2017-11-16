/**
 * Name:  Xiaolong Zhou
 * PID:   A13227137
 * Login: cs12wlt
 */
package hw4;

/**
 * A class with main method to all escapeDarkRoom
 * @author xiaolong zhou
 * @version 1.0
 * @since 2016-02-02
 */
public class Escape {

  private static DarkRoom myRoom = new DarkRoom();
  
  /**
   * A main method with argument, call escapeDarkRoom
   * @param args a parameter
   */
  public static void main( String[] args)
  {
    //String myPath ="/Users/xiaolongzhou/Desktop/CSE12HW/HW4/hw4/moreRooms";
  
    // call escapeDarkRoom
    myRoom.readFromFile(args[0]);
    myRoom.escapeDarkRoom("Stack");
    myRoom.escapeDarkRoom("Queue");
  }
}
