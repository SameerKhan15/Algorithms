package two.pointers.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import two.pointers.MergeSortedArrays;

public class MergeSortedArraysTest {
	
	// Null Inputs
	
	@Test
	public void testMerge_arr1Null() {
		assertThrows(IllegalArgumentException.class,
                () -> MergeSortedArrays.merge(null, new int[]{1, 2}));
		
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> MergeSortedArrays.merge(null, new int[]{}));
        assertEquals("Invalid input! null encountered", ex.getMessage());
	}
	
	@Test
	public void testMerge_arr2Null() {
		assertThrows(IllegalArgumentException.class,
                () -> MergeSortedArrays.merge(new int[]{1, 2}, null));
	}
	
	@Test
	public void testMerge_bothArrsNull() {
		assertThrows(IllegalArgumentException.class,
                () -> MergeSortedArrays.merge(null, null));
	}
	
	// Unsorted Inputs
	
	@Test
	public void testMerge_arr1Unsorted() {
		assertThrows(IllegalArgumentException.class,
                () -> MergeSortedArrays.merge(new int[]{3, 1, 2}, new int[]{1, 2}));
	}
	
	@Test
	public void testMerge_arr2Unsorted() {
		assertThrows(IllegalArgumentException.class,
                () -> MergeSortedArrays.merge(new int[]{1, 2}, new int[]{3, 1, 2}));
	}
	
	@Test
	public void testMerge_arr1UnsortedException() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> MergeSortedArrays.merge(new int[]{3, 1}, new int[]{1, 2}));
        assertEquals("arr1 not sorted!", ex.getMessage());
	}
	
	@Test
	public void testMerge_arr2UnsortedException() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> MergeSortedArrays.merge(new int[]{1, 2}, new int[]{3, 1}));
        assertEquals("arr2 not sorted!", ex.getMessage());
	}
	
	// Empty Arrays
	
	@Test
	public void testMerge_bothArrsEmpty() {
        assertArrayEquals(new int[]{}, MergeSortedArrays.merge(new int[]{}, new int[]{}));
	}
	
	@Test
	public void testMerge_arr1Empty() {
		int[] arr2 = {1, 2, 3};
        int[] result = MergeSortedArrays.merge(new int[]{}, arr2);
        assertArrayEquals(arr2, result);
        assertNotSame(arr2, result); // must be a copy, not the same reference
	}
	
	@Test
	public void testMerge_arr2Empty() {
		int[] arr1 = {1, 2, 3};
        int[] result = MergeSortedArrays.merge(arr1, new int[]{});
        assertArrayEquals(arr1, result);
        assertNotSame(arr1, result); // must be a copy, not the same reference
	}
	
	@Test
	public void testMerge_arr1Empty_mutatingResult() {
		int[] arr2 = {1, 2, 3};
        int[] result = MergeSortedArrays.merge(new int[]{}, arr2);
        result[0] = 99;
        assertEquals(1, arr2[0]); // original must be untouched
	}
 	
	// Standard Merge Cases 
	
	@Test
	public void testMergeTwoSortedArrays_standardCase() {
		int[] arr1 = {1, 3, 4, 5};
        int[] arr2 = {2, 4, 4};
        int[] expected = {1, 2, 3, 4, 4, 4, 5};
        assertArrayEquals(expected, MergeSortedArrays.merge(arr1, arr2));
	}
	
	@Test
	public void testMergeNoOverlap_arr1BeforeArr2() {
		int[] arr1 = {1, 2, 3};
        int[] arr2 = {4, 5, 6};
        int[] expected = {1, 2, 3, 4, 5, 6};
        assertArrayEquals(expected, MergeSortedArrays.merge(arr1, arr2));
	}
	
	@Test
	public void testMergeNoOverlap_arr2BeforeArr1() {
		int[] arr1 = {4, 5, 6};
        int[] arr2 = {1, 2, 3};
        int[] expected = {1, 2, 3, 4, 5, 6};
        assertArrayEquals(expected, MergeSortedArrays.merge(arr1, arr2));
	}
	
	@Test
	public void testMergeIdenticalArrays() {
		int[] arr1 = {1, 2, 3};
        int[] arr2 = {1, 2, 3};
        int[] expected = {1, 1, 2, 2, 3, 3};
        assertArrayEquals(expected, MergeSortedArrays.merge(arr1, arr2));
	}
	
	@Test
	public void testMerge_allDupes() {
		assertArrayEquals(new int[]{5, 5, 5, 5, 5},
                MergeSortedArrays.merge(new int[]{5, 5, 5}, new int[]{5, 5}));
	}
	
	@Test
    public void testMerge_singleElementEach_inOrder() {
        assertArrayEquals(new int[]{1, 2},
                MergeSortedArrays.merge(new int[]{1}, new int[]{2}));
    }
	
	@Test
    public void testMerge_singleElementEach_reverseOrder() {
        assertArrayEquals(new int[]{1, 2},
                MergeSortedArrays.merge(new int[]{2}, new int[]{1}));
    }

    @Test
    public void testMerge_singleElementEach_equalValues() {
        assertArrayEquals(new int[]{3, 3},
                MergeSortedArrays.merge(new int[]{3}, new int[]{3}));
    }

    @Test
    public void testMerge_unequalLengths_longerLeft() {
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6},
                MergeSortedArrays.merge(new int[]{1, 2, 3, 4, 5}, new int[]{6}));
    }

    @Test
    public void testMerge_unequalLengths_longerRight() {
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6},
                MergeSortedArrays.merge(new int[]{1}, new int[]{2, 3, 4, 5, 6}));
    }
    
    // Negative and Boundary Values
    
    @Test
    public void testMerge_negativeNumbers() {
    	assertArrayEquals(new int[]{-5, -4, -3, -2, -1, 0},
                MergeSortedArrays.merge(new int[]{-5, -3, -1}, new int[]{-4, -2, 0}));
    }
    
    @Test
    public void testMerge_mixedNegativeAndPositive() {
        assertArrayEquals(new int[]{-3, -2, -1, 0, 2, 3, 4},
                MergeSortedArrays.merge(new int[]{-3, -1, 2, 4}, new int[]{-2, 0, 3}));
    }

    @Test
    public void testMerge_intMinAndIntMax() {
        assertArrayEquals(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE},
                MergeSortedArrays.merge(new int[]{Integer.MIN_VALUE}, new int[]{Integer.MAX_VALUE}));
    }
    
    // Output Integrity
    
    @Test
    public void testMerge_outputLength_equalsSumOfInputLengths() {
    	int[] arr1 = {1, 3, 5};
        int[] arr2 = {2, 4, 6};
        assertEquals(arr1.length + arr2.length, MergeSortedArrays.merge(arr1, arr2).length);
    }
}