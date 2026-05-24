package two.pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class for computing the intersection of two sorted integer arrays.
 *
 * <p>All methods are static; this class is not intended to be instantiated.
 *
 * <p><b>Precondition:</b> Input arrays must be sorted in non-decreasing order.
 * Passing unsorted arrays produces undefined behaviour — sort order is not validated.
 */
public class ArrayIntersection {
	 /**
     * Computes the intersection of two sorted integer arrays using a two-pointer approach.
     *
     * <p>The returned array contains every element that appears in both {@code arr1} and
     * {@code arr2}, in ascending order. Duplicate values are included up to the minimum
     * frequency across the two arrays — e.g. if {@code 1} appears three times in
     * {@code arr1} and twice in {@code arr2}, the result contains {@code 1} twice.
     *
     * <p><b>Complexity:</b> O(n + m) time, O(min(n, m)) space,
     * where {@code n = arr1.length} and {@code m = arr2.length}.
     *
     * @param arr1 the first sorted (non-decreasing) integer array; must not be {@code null}
     * @param arr2 the second sorted (non-decreasing) integer array; must not be {@code null}
     * @return a new {@code int[]} containing the intersection elements in ascending order;
     *         never {@code null} — returns an empty array when there is no overlap or
     *         either input is empty
     * @throws IllegalArgumentException if {@code arr1} or {@code arr2} is {@code null}
     *
     * @example
     * <pre>
     *   computeIntersection(new int[]{1,2,3}, new int[]{1,3,5}) // → [1, 3]
     *   computeIntersection(new int[]{1,1,1}, new int[]{1,1})   // → [1, 1]
     *   computeIntersection(new int[]{},      new int[]{1,2,3}) // → []
     * </pre>
     */
	public static int[] computeIntersection(int[] arr1, int[] arr2) {
		// Guard: reject null inputs immediately — a null array cannot be iterated.
		if (arr1 == null || arr2 == null) {
			throw new IllegalArgumentException("Invalid input! array is null");
		}
		
		// Short-circuit: if either array is empty there can be no common elements.
		if (arr1.length == 0 || arr2.length == 0) {
			int[] res = {};
			return res;
		}
		
		// leftPtr scans arr1; rightPtr scans arr2.
        // Both start at index 0 and advance based on element comparison.
		int leftPtr = 0, rightPtr = 0;
		
		// Collect matched elements before the final array size is known.
		List<Integer> intersects = new ArrayList<>();
		
		// Two-pointer scan: advance through both arrays simultaneously.
        // Stops as soon as either pointer reaches the end of its array.
		while (true) {
			// Termination: one (or both) arrays fully consumed — no more matches possible.
			if (leftPtr == arr1.length || rightPtr == arr2.length) {
				break;
			}
			
			if (arr1[leftPtr] == arr2[rightPtr]) {
				// Match found: record the element and advance both pointers.
				intersects.add(arr1[leftPtr]);
				leftPtr++;
				rightPtr++;
			} else if (arr1[leftPtr] < arr2[rightPtr]) {
				// arr1's current element is smaller; it cannot appear later in arr2
                // (arr2 is sorted), so skip it and move arr1 forward.
				leftPtr++;
			} else {
				// arr2's current element is smaller; symmetrically advance arr2.
				rightPtr++;
			}
		}
		
		// Copy the matched elements from the ArrayList into a plain int[].
		int[] results = new int[intersects.size()];
		int indexPtr = 0;
		for (Integer element : intersects) {
			results[indexPtr] = element;
			indexPtr++;
		}
		return results;
	}
}