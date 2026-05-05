package two.pointers.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import two.pointers.SmallerPrefixes;

public class hasSmallerPrefixTest {
	
	// 1. Exception Cases
	
	@Test(expected = IllegalArgumentException.class)
	public void testOddLengthArray() {
		SmallerPrefixes.hasSmallerPrefix(new int[]{1, 2, 3});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSingleElement() {
		SmallerPrefixes.hasSmallerPrefix(new int[]{1});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEmptyArray() {
		SmallerPrefixes.hasSmallerPrefix(new int[]{});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testOddArray() {
		SmallerPrefixes.hasSmallerPrefix(new int[]{1, 1, 2});
	}
	
	// 2. Core Logic
	
	@Test
	public void testAllPrefixesSmaller() {
		assertTrue(SmallerPrefixes.hasSmallerPrefix(new int[]{1, 2, 3, 4}));
		assertTrue(SmallerPrefixes.hasSmallerPrefix(new int[]{1,2,2,-1}));
		assertTrue(SmallerPrefixes.hasSmallerPrefix(new int[]{2, 1, 1, 0}));
		assertTrue(SmallerPrefixes.hasSmallerPrefix(new int[]{5, 1, 1, 0}));
		
		assertFalse(SmallerPrefixes.hasSmallerPrefix(new int[]{1,2,-2,1,3,5}));
		assertFalse(SmallerPrefixes.hasSmallerPrefix(new int[]{2, 1, 1, -10}));
		assertFalse(SmallerPrefixes.hasSmallerPrefix(new int[]{-5, -3, -1, 0}));
		assertFalse(SmallerPrefixes.hasSmallerPrefix(new int[]{0, 0, 0, 0}));
	}
}