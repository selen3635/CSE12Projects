/**
  * Name: Xiaolong Zhou
  * PID: A13227137
  * Log in: cs12wlt
  */
package hw1;

/**
 * A class that implements a simple statistic tracking array
 * 
 * @version 1.0
 * @author Xiaolong Zhou
 * @since 2016-01-04
 */

public class CounterStat {

	// constant variable
	private static final int percent = 100;
	
	// private array of size 3
	private int[] Stat = new int[3];

	/**
	 * creates an empty array
	 */
	public CounterStat() {
		// Nothing to do here
	}

	/**
	 * Creates an array with specified values
	 * 
	 * @param first:
	 *            assigned to the first cell
	 * @param second:
	 *            assigned to the second cell
	 * @param third:
	 *            assigned to the third cell
	 */
	public CounterStat(int first, int second, int third) {
		Stat[0] = first;
		Stat[1] = second;
		Stat[2] = third;
	}

	/**
	 * Calculates the number of games played
	 * 
	 * @return The total number of played games
	 */
	public int getTotalGames() {
		return Stat[0] + Stat[1] + Stat[2];
	}

	/**
	 * Increments the number of games won by a user
	 */
	public void incrementUserWins() {
		Stat[0]++;
	}

	/**
	 * Increments the number of games won by a computer
	 */
	public void incrementComputerWins() {
		Stat[1]++;
	}

	/**
	 * Increments the number of ties between a user and a computer
	 */
	public void incrementTies() {
		Stat[2]++;
	}

	/**
	 * This method is going to calculate win rate for user, computer and tie.
	 * @param choice:
	 *            depending on the value of choice the corresponding average
	 *            (percent) is returned: if choice is 0, return the average for
	 *            a user if choice is 1, return the average for a computer if
	 *            choice is 2, return the average for ties otherwise return -1
	 * @return percentage of won games or ties, depending on the parameter
	 *         choice.
	 */
	public int averageGames(int choice) {
		// check whether total game play is 0
		if (getTotalGames() == 0) {

			// if yes return 0
			return 0;
		}
		// check whether choice is 0
		else if (choice == 0) {

			// return average for user
			return Stat[0] * percent / getTotalGames();
		}

		// check whether choice is 1
		else if (choice == 1) {

			// return average for computer
			return Stat[1] * percent / getTotalGames();
		}

		// check whether choice is 2
		else if (choice == 2) {

			// return average for tie
			return Stat[2] * percent / getTotalGames();
		}

		else
			return 0;

	}

	/**
	 * Prints the statistics message
	 */
	public void printStats() {
		System.out.println("I won: " + averageGames(1) + "%   You won: " +
	    averageGames(0) + "%    We tied: "+ averageGames(2) + "%\n");
	}

	/**
	 * Resets the statistic array if the player wants to play again
	 */
	public void reset() {
		Stat[0] = 0;
		Stat[1] = 0;
		Stat[2] = 0;

	}

	/**
	 * Reset wrong method. This is going to fail when we test on this method.
	 */
	public void resetWrong() {
		Stat[0] = 1;
		Stat[1] = 1;
		Stat[2] = 1;
	}

}
