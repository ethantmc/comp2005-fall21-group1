import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import CPUPlayer;
import DifficultyType;
import PlayerType;

/**
 * 
 */

/**
 * @author Ethan Mc Donald
 *
 */
class CPUPlayerTest {
	private CPUPlayer CPU1;
	private CPUPlayer CPU2;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach //overwrite for SetDifficulty safety.
	void setUp() throws Exception {
		CPU1 = new CPUPlayer("Charles",PlayerType.CPU,false,DifficultyType.EASY);
		CPU2 = new CPUPlayer("Jeffrey",PlayerType.CPU,true,DifficultyType.HARD);
	}
//Pretty much everything you'd want to test is tested by PlayerTest.

	/**
	 * Test method for {@link CPUPlayer#getDifficulty()}.
	 */
	@Test
	void testGetDifficulty() {
		assertEquals(DifficultyType.EASY, CPU1.getDifficulty(), 0);
		assertEquals(DifficultyType.HARD, CPU2.getDifficulty(), 0);
	}

	/**
	 * Test method for {@link CPUPlayer#setDifficulty(DifficultyType)}.
	 */
	@Test
	void testSetDifficulty() {
		CPU1.setDifficulty(DifficultyType.HARD); // TODO
		assertEquals(DifficultyType.HARD, CPU1.getDifficulty(), 0);
	}

}
