/*
 * Name: Xiaolong Zhou
 * PID: A13227137
 * Log in: cs12wlt
 */

package hw1;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * This program is going to test method on counterstat class.
 * @author xiaolong zhou 
 *       
 *
 */
public class CounterStatTester {

	// create a new object for counterstat
	private CounterStat stat;

	/*
	 * this sets up the test fixture. JUnit invokes this method before every
	 * testXXX method. The @Before tag tells JUnit to run this method before
	 * each test
	 */
	@Before
	/**
	 * This is going to set up tester
	 * @throws Exceptional
	 */
	public void setUp() throws Exception {
		stat = new CounterStat(1, 2, 3);
	}

	/* The @Test tag tells JUnit this is a test to run */
	@Test
	/**
	 * This is going to test on getTotalGames method.
	 */
	public void testgetTotalGames() {
		
		System.out.println("Checking getTotalGames");
		// check whether getTotalGames return same value as expected
		assertEquals(6, stat.getTotalGames());
	}

	@Test
	/**
	 * This is going to test methods of increment
	 */
	public void testIncrements() {
		System.out.println("Checking Proper Increment");
		// test increment method for computer
		stat.incrementComputerWins();
		assertEquals(7, stat.getTotalGames());
		
		// test increment method for user
		stat.incrementUserWins();
		assertEquals(8, stat.getTotalGames());
		
		// test tie increment method
		stat.incrementTies();
		assertEquals(9, stat.getTotalGames());
	}

	@Test
	/**
	 * This is going to test reset method
	 */
	public void testReset() {
		System.out.println("Checking Reset");
 
		// call reset method
		stat.reset();
		
		// test whether reset method works
		assertEquals(0, stat.getTotalGames());

	}

	@Test
	/**
	 * This is going to test average games
	 */
	public void testAverageGames() {

		System.out.println("Checking AverageGames");

		// test user average games
		assertEquals(1 * 100 / 6, stat.averageGames(0));
		
		// test computer average games
		assertEquals(2 * 100 / 6, stat.averageGames(1));
		
		// test tie average games
		assertEquals(3 * 100 / 6, stat.averageGames(2));

	}

	@Test
	/**
	 * This is going to test on resetwrong method
	 */
	public void testResetWrong() {

		System.out.println("Checking ResetWrong");

		// call resetwrong method
		stat.resetWrong();
		
		// test on resetwrong method, this should fail
		assertEquals(0, stat.getTotalGames());

	}
}
