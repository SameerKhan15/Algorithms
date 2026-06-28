package two.pointers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Provides a utility method for performing a three-way merge of sorted integer
 * arrays with duplicate elimination.
 *
 * <p>All input arrays must be sorted in non-decreasing order. The merge
 * produces a single sorted array containing every value that appears across
 * the three inputs, with each distinct value included exactly once regardless
 * of how many times or in how many arrays it occurs.
 *
 * <p>Example:
 * <pre>{@code
 * int[] a = {2, 3, 3, 4, 5, 7};
 * int[] b = {3, 3, 9};
 * int[] c = {3, 3, 9};
 * int[] result = ThreeWayMergeWithoutDupes.merge(a, b, c);
 * result → [2, 3, 4, 5, 7, 9]
 * }</pre>
 */
public class ThreeWayMergeWithoutDupes {
	public static int[] merge(int[] arr1, int[] arr2, int[] arr3) {
		// Reject null inputs immediately to avoid NullPointerExceptions downstream
		if (arr1 == null || arr2 == null || arr3 == null) {
			throw new IllegalArgumentException("One or more inputs are null");
		}
		
		// All three arrays must be sorted; the merge algorithm depends on this invariant
		if (!isSorted(arr1) || !isSorted(arr2) || !isSorted(arr3)) {
			throw new IllegalArgumentException("One or more input arrays are not sorted"); 
		}
		
		/* 
		 * AtomicInteger pointers track the current read position in each array;
		 * stored in AtomicInteger so they can be passed by reference into the TreeMap
		 * and incremented through the map entry later 
		 */
		AtomicInteger ptr1 = new AtomicInteger(0), ptr2 = new AtomicInteger(0), ptr3 = new AtomicInteger(0);
		
		// Accumulates the deduplicated, sorted output values before conversion to int[]
		List<Integer> mergedArr = new ArrayList<>();
		
		// Tracks the last value written to mergedArr so consecutive duplicates can be skipped
		AtomicReference<Integer> lastSeenVal = new AtomicReference<>(null);
		while (true) {
			// Assume all pointers are exhausted until proven otherwise
			boolean exit = true;
			
			/* 
			 * TreeMap keeps candidate values in ascending key order, giving us the 
			 * minimum current element at firstEntry() in O(log n); each entry maps 
			 * a candidate value to the pointer of the array it came from 
			 */
			Map<Integer, AtomicInteger> elements = new TreeMap<>();
			
			/* Add the current front element of each array that still has remaining elements;
			 * if a value from multiple arrays is identical, the TreeMap collapses them to
             * one entry — the pointer stored will be whichever array was inserted last,
             * but that is fine because all such arrays hold the same value and one
             * increment is enough to advance past it for this round 
             */
			if (ptr1.get() < arr1.length) {
				elements.put(arr1[ptr1.get()], ptr1);
				exit = false;
			}
			
			if (ptr2.get() < arr2.length) {
				elements.put(arr2[ptr2.get()], ptr2);
				exit = false;
			}
			
			if (ptr3.get() < arr3.length) {
				elements.put(arr3[ptr3.get()], ptr3);
				exit = false;
			}
			
			if (exit) {
				break;
			}
			
			// firstEntry() returns the smallest current value across all three arrays
			Map.Entry<Integer, AtomicInteger> first = ((TreeMap<Integer, AtomicInteger>) elements).firstEntry();
			
			/* Only append the value if it differs from the last one written,
			 * eliminating duplicates that appear within or across arrays
			 */
			if (lastSeenVal.get() == null || !lastSeenVal.get().equals(first.getKey())) {
				mergedArr.add(first.getKey());
				lastSeenVal.set(first.getKey());
			}
			
			/* Advance the pointer of the array that supplied the minimum value 
			 * so the next iteration considers its following element 
			 */
			first.getValue().incrementAndGet();
		}
		
		// Convert the ArrayList to a primitive int[] for the return type
		int[] result = new int[mergedArr.size()];
		int resultIndex = 0;
		Iterator<Integer> mergedArrIter = mergedArr.iterator();
		while (mergedArrIter.hasNext()) {
			result[resultIndex] = mergedArrIter.next();
			resultIndex++;
		}
		return result;
	}
	
	/* Returns true if arr is sorted in non-decreasing order; checks each
     * adjacent pair and short-circuits on the first out-of-order pair found 
     */
	private static boolean isSorted(int[] arr) {
		for (int i = 0 ; i < arr.length - 1 ; i++) {
			if (arr[i] > arr[i+1]) {
				return false;
			}
		}
		return true;
	}
}