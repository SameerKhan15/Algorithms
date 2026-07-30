package two.pointers.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import two.pointers.MissingNumbers;

public class MissingNumbersTest {
	// ---------- Basic / happy-path cases ----------
	
	@Test
	public void variousMainCases() {
		int[] arr1 = {6, 9, 12, 15, 18};
		int[] result1 = MissingNumbers.findMissingNumbers(arr1, 9, 13);
		assertArrayEquals(new int[]{10, 11, 13}, result1);
		
		int[] arr2 = {9, 10, 12, 13, 15};
		int[] result2 = MissingNumbers.findMissingNumbers(arr2, 10, 13);
		assertArrayEquals(new int[]{11}, result2);
	}
	
	@Test
	public void noNumbersMissing() {
		int[] arr = {1, 2, 3, 4, 5};
		int[] result = MissingNumbers.findMissingNumbers(arr, 2, 4);
		assertArrayEquals(new int[]{}, result);
	}
	
	@Test
	public void allMissingNumbers() {
		int[] arr = {100, 200, 300, 400, 500};
		int[] result = MissingNumbers.findMissingNumbers(arr, 1, 5);
		assertArrayEquals(new int[]{1,2,3,4,5}, result);
	}
	
	// ---------- Boundary conditions on low/high ----------
	
	@Test
	public void singleValueRange() {
		int[] arr1 = {1, 5, 10};
		int[] result1 = MissingNumbers.findMissingNumbers(arr1, 5, 5);
		assertArrayEquals(new int[]{}, result1);
		
		int[] arr2 = {1, 10};
		int[] result2 = MissingNumbers.findMissingNumbers(arr2, 5, 5);
		assertArrayEquals(new int[]{5}, result2);
	}
	
	@Test
	public void lowGreaterThanHigh() {
		int[] arr = {1, 2, 3};
		assertThrows(
		        IllegalArgumentException.class,
		        () -> MissingNumbers.findMissingNumbers(arr, 10, 5)
		    );
	}
	
	@Test
    public void lowEqualsFirstArrayElement() {
        int[] arr = {5, 6, 7};
        int[] result = MissingNumbers.findMissingNumbers(arr, 5, 7);
        assertArrayEquals(new int[]{}, result);
    }
	
	@Test
    public void highEqualsLastArrayElement() {
        int[] arr = {1, 2, 9};
        int[] result = MissingNumbers.findMissingNumbers(arr, 8, 9);
        assertArrayEquals(new int[]{8}, result);
    }
	
	 // ---------- arr shape edge cases ----------
	
	@Test
    public void emptyArray_allNumbersInRangeAreMissing() {
        int[] arr = {};
        int[] result = MissingNumbers.findMissingNumbers(arr, 1, 3);
        assertArrayEquals(new int[]{1, 2, 3}, result);
    }
	
	@Test
    public void singleElementArray_matchesEntireRange() {
        int[] arr = {5};
        int[] result = MissingNumbers.findMissingNumbers(arr, 5, 5);
        assertArrayEquals(new int[]{}, result);
    }
	
	@Test
    public void allArrElementsBelowLow() {
        // leftPtr should skip past every element without index errors
        int[] arr = {1, 2, 3};
        int[] result = MissingNumbers.findMissingNumbers(arr, 10, 12);
        assertArrayEquals(new int[]{10, 11, 12}, result);
    }
	
	@Test
    public void allArrElementsAboveHigh() {
        int[] arr = {50, 60, 70};
        int[] result = MissingNumbers.findMissingNumbers(arr, 10, 12);
        assertArrayEquals(new int[]{10, 11, 12}, result);
    }
	
	// ---------- Negative numbers ----------
	
	@Test
    public void negativeRangeWithSomeMissing() {
        int[] arr = {-5, -3, -1};
        int[] result = MissingNumbers.findMissingNumbers(arr, -5, -1);
        assertArrayEquals(new int[]{-4, -2}, result);
    }
	
	@Test
    public void rangeSpanningNegativeToPositive() {
        int[] arr = {-2, 0, 2};
        int[] result = MissingNumbers.findMissingNumbers(arr, -2, 2);
        assertArrayEquals(new int[]{-1, 1}, result);
    }
	
	// ------ MISC -------------
	@Test
    public void duplicatesInArray() {
        int[] arr = {5, 5, 7};
        int[] result = MissingNumbers.findMissingNumbers(arr, 5, 7);
        assertArrayEquals(new int[]{6}, result);
    }
	
	@Test
    public void nullArray() {
        assertThrows(IllegalArgumentException.class, () ->
            MissingNumbers.findMissingNumbers(null, 1, 5));
    }
	
	@Test
	public void unsortedArray() {
		int[] arr = {9, 1, 5}; // not sorted ascending
		assertThrows(IllegalArgumentException.class, () ->
        MissingNumbers.findMissingNumbers(arr, 1, 5));
	}
}