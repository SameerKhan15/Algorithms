package two.pointers;

import java.util.Arrays;

/**
 * Utility class for merging two sorted integer arrays into a single sorted array.
 */
public class MergeSortedArrays {
	
	/**
     * Checks whether the given array is sorted in non-decreasing order.
     * Arrays with fewer than 2 elements are trivially sorted.
     *
     * @param arr the array to check
     * @return true if sorted, false otherwise
     */
	private static boolean isSorted(int[] arr) {
		if (arr.length < 2) {
			return true;
		}
		
		for (int i = 0 ; i < arr.length - 1 ; i++) {
			if (arr[i] > arr[i+1]) {
				return false;
			}
		}
		return true;
	}
	
	/**
     * Merges two sorted integer arrays into a new sorted array.
     *
     * Both input arrays must be non-null and sorted in non-decreasing order.
     * If either array is empty, a defensive copy of the other is returned.
     *
     * @param arr1 the first sorted array
     * @param arr2 the second sorted array
     * @return a new sorted array containing all elements from arr1 and arr2
     * @throws IllegalArgumentException if either input is null or unsorted
     */
	public static int[] merge(int[] arr1, int[] arr2) {
		
		// Reject null inputs immediately
		if (arr1 == null || arr2 == null) {
			throw new IllegalArgumentException("Invalid input! null encountered");
		}
		
		// Validate both arrays are sorted before proceeding
		if (!isSorted(arr1)) {
			throw new IllegalArgumentException("arr1 not sorted!");
		}
		
		if (!isSorted(arr2)) {
			throw new IllegalArgumentException("arr2 not sorted!");
		}
		
		/* 
		 * If one array is empty, return a defensive copy of the other 
		 * to prevent the caller from mutating our input through the returned reference
		 */
		if (arr1.length == 0) {
			return Arrays.copyOf(arr2, arr2.length);
		}
		
		if (arr2.length == 0) {
			return Arrays.copyOf(arr1, arr1.length);
		}
		
		int leftPtr = 0, rightPtr = 0, mergedArrPtr = 0;
		int[] mergedArray = new int[arr1.length + arr2.length];
		
        // Traverse both arrays, always picking the smaller of the two current elements
		while (leftPtr < arr1.length || rightPtr < arr2.length) {
			if (leftPtr < arr1.length && rightPtr < arr2.length) {
				// Both pointers still have elements to compare
				if (arr1[leftPtr] <= arr2[rightPtr]) {
					mergedArray[mergedArrPtr] = arr1[leftPtr];
					leftPtr++;
					mergedArrPtr++;
				} else {
					mergedArray[mergedArrPtr] = arr2[rightPtr];
					rightPtr++;
					mergedArrPtr++;
				}
			} else {
                // One side is exhausted — drain the remaining elements from the other
				if (leftPtr < arr1.length) {
					mergedArray[mergedArrPtr] = arr1[leftPtr];
					leftPtr++;
				} else {
					mergedArray[mergedArrPtr] = arr2[rightPtr];
					rightPtr++;
				}
				mergedArrPtr++;
			}
		}
		return mergedArray;
	}
}