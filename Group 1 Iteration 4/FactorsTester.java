import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class FactorsTester {

	@Test
	void testFactors1()
	{	
		// TEST 1: should succeed because 1 is a factor of zero.
		assertTrue(FactorsUtility.factor(0,1));
	}

	@Test
	void testFactors2()
	{	
		// TEST 2: should succeed because 2 is NOT a factor of 1.
		assertFalse(FactorsUtility.factor(1,2));
	}

	@Test
	void testFactors3()
	{	
		// TEST 1: should succeed because 1 is a factor of 2.
		assertTrue(FactorsUtility.factor(2,1));
	}

	@Test
	void testGetFactors1()
	{	
		// TEST 1: should succeed as 2 is a valid value.
		ArrayList<Integer> expected = new ArrayList<Integer>();
		expected.add(1);
		assertEquals(expected, FactorsUtility.getFactors(2));
	}

	@Test
	void testGetFactors2()
	{	
		// TEST 2: should succeed as 1 returns an empty list.
		ArrayList<Integer> expected = new ArrayList<Integer>();
		//expected.removeAll();
		assertEquals(expected, FactorsUtility.getFactors(1));
	}
	@Test
	void testGetFactors3()
	{	
		// TEST 3: should succeed as 0 also returns an empty list.
		ArrayList<Integer> expected = new ArrayList<Integer>();
		//expected.removeAll();
		assertEquals(expected, FactorsUtility.getFactors(0));
	}

	@Test
	void testGetFactors4()
	{	
		// TEST 4: should throw illegal argument
		assertThrows(IllegalArgumentException.class, () -> FactorsUtility.getFactors(-1));
	}

	@Test
	void testGetFactors5()
	{	
		// TEST 5: should suceed with the list of factors
		ArrayList<Integer> expected = new ArrayList<Integer>();
		expected.add(1);
		expected.add(2);
		expected.add(3);
		expected.add(4);
		expected.add(6);
		assertEquals(expected, FactorsUtility.getFactors(12));
	}
	@Test
	void testPerfect1()
	{	
		// TEST 1: should throw the exception because the parameter value is less than 1
		assertThrows(IllegalArgumentException.class, () -> FactorsUtility.perfect(0));
	}

	@Test
	void testPerfect2()
	{	
		// TEST 2: should succeed because 1 is a valid parameter value, but is not a perfect number
		assertFalse(FactorsUtility.perfect(1));
	}

	@Test
	void testPerfect3()
	{	
		// TEST 3: should succeed because 6 is a valid parameter value, and is a perfect number
		assertTrue(FactorsUtility.perfect(6));
	}

	@Test
	void testPerfect4()
	{	
		// TEST 4: should succeed because 7 is a valid parameter value, but is not a perfect number
		// I've coded this using assertEquals to show that there's often more than one way to write a test 
		boolean expected = false;
		assertEquals(expected, FactorsUtility.perfect(7));
	}
}
