package two.pointers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Utility class for finding integers that are "missing" from a sorted array
 * within a given inclusive range.
 */
public class MissingNumbers {
	
	/**
	 * Returns every integer in the inclusive range [low, high] that does not
	 * appear in {@code arr}.
	 *
	 * <p>Preconditions enforced by this method:</p>
	 * <ul>
	 *   <li>{@code arr} must not be null.</li>
	 *   <li>{@code low} must be less than or equal to {@code high}.</li>
	 *   <li>{@code arr} must already be sorted in ascending order
	 *       (duplicates are allowed).</li>
	 * </ul>
	 *
	 * <p>Time complexity: O(n + range) where n = arr.length and
	 * range = (high - low + 1), since the array is initially positioned (scanned) for the low value AND 
	 * every value in the target range is visited once.</p>
	 *
	 * @param arr  a sorted (ascending), non-null array of integers, may contain duplicates
	 * @param low  the lower bound of the range to check (inclusive)
	 * @param high the upper bound of the range to check (inclusive)
	 * @return a new array containing, in ascending order, every integer in
	 *         [low, high] that is not present in {@code arr}
	 * @throws IllegalArgumentException if {@code arr} is null, if
	 *         {@code low > high}, or if {@code arr} is not sorted ascending
	 */
	public static int[] findMissingNumbers(int[] arr, int low, int high) {
		// --- Input validation ---
		if (arr == null) {
			throw new IllegalArgumentException("Invalid input! null encountered");
		}
		
		if (low > high) {
			throw new IllegalArgumentException("Invalid input! low cannot be higher than high");
		}
		
		/* 
		 * Confirm arr is sorted ascending; the scanning logic below assumes
		 * this and will silently produce wrong results otherwise, so we fail 
		 * fast instead.
		 */
		for (int a = 0 ; a < arr.length - 1 ; a++) {
			if (arr[a] > arr[a+1]) {
				throw new IllegalArgumentException("Invalid input! array not sorted in desc -> asc order");
			}
		}
		
		/*
		 * Collected result; a List is used here since we don't know the 
		 * final size (number of missing values) until the scan completes.
		 */
		List<Integer> missingNumbers = new ArrayList<>();
		
		// rangeScanner walks every candidate integer from low to high
		int rangeScanner = low;
		
		// leftPtr tracks our current position within arr
		int leftPtr = 0;
		
		/*
		 * --- leftPtr initial positioning ---
		 * Skip past any leading array elements that fall below `low`,
		 * since they're outside the range we care about
		 */
		while(leftPtr < arr.length) {
			if (arr[leftPtr] < low) {
				leftPtr++;
			} else {
				break;
			}
		}
		
		/*
		 * --- Main scan ---
		 * Walk rangeScanner from low to high. At each step:
		 *  - if the current array element (at leftPtr) matches rangeScanner, that number is present,
		 *  so just advance leftPtr (and skip past any duplicates of the same value) without recording anything.
		 *  
		 *  - otherwise, rangeScanner's value isn't in arr, so record it as missing. 
		 */
		while (rangeScanner <= high) {
			if (leftPtr < arr.length && rangeScanner == arr[leftPtr]) {
				leftPtr++;
				while (leftPtr < arr.length && arr[leftPtr] == arr[leftPtr - 1]) {
					leftPtr++;
				}
			} else {
				// Current number not found in arr (or arr exhausted) -> missing
				missingNumbers.add(rangeScanner);
			}
			rangeScanner++;
		}
		
		// --- Convert result List<Integer> to int[] ---
		int[] result = new int[missingNumbers.size()];
		int resultIndexPtr = 0;
		Iterator<Integer> resultIter = missingNumbers.iterator();
		while (resultIter.hasNext()) {
			result[resultIndexPtr] = resultIter.next();
			resultIndexPtr++;
		}
		
		return result;
	}
}