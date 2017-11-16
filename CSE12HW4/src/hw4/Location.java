/**
 * Name:  Xiaolong Zhou
 * PID:   A13227137
 * Login: cs12wlt
 */

package hw4;
/**
 * A class will get row and column, include methods
 * that can change direction and get new location.
 * @author xiaolongzhou
 *
 */
public class Location {

  // instance variable, represents rows
  protected int row;
	
  // instance variable, represents columns
	protected int column;

    /**
     * This constructor receive two parameter which are
     * current row and current column respectively, and 
     * will set row to current row and set column to
     * current column	
     * @param currRow  represent current row
     * @param currCol  represent current column
     */
    public Location(int currRow, int currCol) {
	
      // set row to current row
      row = currRow;
      // set column to current column
      column = currCol;
    }

    /**
	   * This method is going to get row number
	   * @return  return row number
	   */
    public int getRow() {
	    
      // return row number
      return row;
    }
	  
	  /**
	   * This method is going to geet column number
	   * @return  return column number
	   */
    public int getColumn() {
	    
      // return column number
      return column;
    }

	   
    /* LEFT, UP, RIGHT, DOWN */
	   
	  /**
	   * This method is going to change direction to the left
	   * @return return left location
	   */
    public Location left() {
	    
      // heading to left
      return new Location(row,column-1);
    }
	   
	  /**
	   * This method is going to change direction to up
	   * @return return up location,
	   */
    public Location up() {
	    
      // heading to up
      return new Location(row-1,column);
    } 
	   
	  /**
	   * This method is going to change direction to right
	   * @return return right location,
	   */
    public Location right() {
	    
      // heading to right
      return new Location(row,column+1);   
	      
    }
	  
	  /**
	   * This method is going to change direction to down
	   * @return return down location
	   */
    public Location down() {
	    
      // heading to downward
      return new Location(row+1,column);
    }


}
