package two.pointers.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import two.pointers.TwoSum;

public class TwoSumTest {
	
	// Null input
	
	@Test
	public void testNullInput() {
		TwoSum twoSum = new TwoSum();
		assertThrows(IllegalArgumentException.class, () -> twoSum.isTwoSum(null));
	}
	
	@Test
	public void testNullExceptionMessage() {
		TwoSum twoSum = new TwoSum();
		IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class, () -> twoSum.isTwoSum(null));
        assertTrue(ex.getMessage().toLowerCase().contains("null"));
	}
	
	// Unsorted input
	
	@Test
	public void testUnsorted() {
		TwoSum twoSum = new TwoSum();
		assertThrows(IllegalArgumentException.class,
                () -> twoSum.isTwoSum(new int[]{3, 1, 2}));
		
		assertThrows(IllegalArgumentException.class,
                () -> twoSum.isTwoSum(new int[]{5, 1, 2, 3}));
		
		assertThrows(IllegalArgumentException.class,
                () -> twoSum.isTwoSum(new int[]{1, 2, 3, 2}));
		
		assertThrows(IllegalArgumentException.class,
                () -> twoSum.isTwoSum(new int[]{5, 4, 3, 2, 1}));
	}
	
	@Test
	public void testUnsortedExceptionMessage() {
		TwoSum twoSum = new TwoSum();
		  IllegalArgumentException ex = assertThrows(
                  IllegalArgumentException.class,
                  () -> twoSum.isTwoSum(new int[]{3, 1, 2}));
          assertTrue(ex.getMessage().toLowerCase().contains("sorted"));
	}
	
	// Edge cases: empty and single-element
	
	@Test
	public void testEmptyArray() {
		TwoSum twoSum = new TwoSum();
		TwoSum.Response response = twoSum.isTwoSum(new int[]{});
		assertFalse(response.isSuccess());
        assertEquals(-1, response.getLeftIndex());
        assertEquals(-1, response.getRightIndex());
	}
	
	@Test
	public void testSingleElement() {
		TwoSum twoSum = new TwoSum();
		TwoSum.Response response = twoSum.isTwoSum(new int[]{0});
        assertFalse(response.isSuccess());
        assertEquals(-1, response.getLeftIndex());
        assertEquals(-1, response.getRightIndex());
	}
	
	@Test
	public void testSingleNonZeroElement() {
		TwoSum twoSum = new TwoSum();
		TwoSum.Response response = twoSum.isTwoSum(new int[]{5});
        assertFalse(response.isSuccess());
	}
	
	// Happy path: pair found
	
	@Test
	public void testSortedWithDuplicates() {
		TwoSum twoSum = new TwoSum();
		int[] arr = {-5, -2, -1, 1, 1, 10};
        TwoSum.Response response = twoSum.isTwoSum(arr);
        
        assertTrue(response.isSuccess());
        assertEquals(0, arr[response.getLeftIndex()] + arr[response.getRightIndex()]);
	}
	
	@Test
	public void testTwoZeros() {
		TwoSum twoSum = new TwoSum();
		int[] arr = {-3, 0, 0, 1, 2};
        TwoSum.Response response = twoSum.isTwoSum(arr);

        assertTrue(response.isSuccess());
        assertEquals(0, arr[response.getLeftIndex()] + arr[response.getRightIndex()]);
	}
	
	@Test
	public void testPairAtBothEnds() {
		TwoSum twoSum = new TwoSum();
		int[] arr = {-7, -3, 0, 2, 7};
        TwoSum.Response response = twoSum.isTwoSum(arr);

        assertTrue(response.isSuccess());
        assertEquals(0, response.getLeftIndex());
        assertEquals(4, response.getRightIndex());
	}
	
	@Test
	public void testTwoElementPair() {
		TwoSum twoSum = new TwoSum();
		int[] arr = {-4, 4};
        TwoSum.Response response = twoSum.isTwoSum(arr);

        assertTrue(response.isSuccess());
        assertEquals(0, response.getLeftIndex());
        assertEquals(1, response.getRightIndex());
	}
	
	@Test
	public void testDuplicateValuePair() {
		TwoSum twoSum = new TwoSum();
		int[] arr = {-3, -3, 3, 3};
        TwoSum.Response response = twoSum.isTwoSum(arr);

        assertTrue(response.isSuccess());
        assertEquals(0, arr[response.getLeftIndex()] + arr[response.getRightIndex()]);
	}
	
	@Test
	public void testIndexOrdering() {
		TwoSum twoSum = new TwoSum();
		int[] arr = {-6, -3, -1, 3, 5, 9};
        TwoSum.Response response = twoSum.isTwoSum(arr);

        assertTrue(response.isSuccess());
        assertTrue(response.getLeftIndex() < response.getRightIndex());
	}
	
	@Test
	public void testLargeValues() {
		TwoSum twoSum = new TwoSum();
		int[] arr = {-1000000, -500, 500, 1000000};
        TwoSum.Response response = twoSum.isTwoSum(arr);

        assertTrue(response.isSuccess());
        assertEquals(0, arr[response.getLeftIndex()] + arr[response.getRightIndex()]);
	}
	
	// Negatives: pair not found
	
	@Test
	public void testMixedArray() {
		TwoSum twoSum = new TwoSum();
		int[] arr = {-5, -3, -1, 0, 2, 4, 6};
		TwoSum.Response response = twoSum.isTwoSum(arr);
		assertFalse(response.isSuccess());
		
		int[] arr2 = {-5, -2, 1, 3, 6};
		response = twoSum.isTwoSum(arr2);
		assertFalse(response.isSuccess());
        assertEquals(-1, response.getLeftIndex());
        assertEquals(-1, response.getRightIndex());
	}
	
	@Test
	public void testAllPositive() {
		TwoSum twoSum = new TwoSum();
		TwoSum.Response response = twoSum.isTwoSum(new int[]{1, 2, 3, 4, 5});
		 
        assertFalse(response.isSuccess());
        assertEquals(-1, response.getLeftIndex());
        assertEquals(-1, response.getRightIndex());
	}
	
	@Test
	public void testTwoElementNoMatch() {
		TwoSum twoSum = new TwoSum();
		TwoSum.Response response = twoSum.isTwoSum(new int[]{-3, 5});
		assertFalse(response.isSuccess());
	}
	
	@Test
	public void testAllNegative() {
		TwoSum twoSum = new TwoSum();
		TwoSum.Response response = twoSum.isTwoSum(new int[]{-5, -4, -3, -2, -1});
		 
        assertFalse(response.isSuccess());
        assertEquals(-1, response.getLeftIndex());
        assertEquals(-1, response.getRightIndex());
	}
}