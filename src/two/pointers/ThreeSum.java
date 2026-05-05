package two.pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Finds all unique triplets in an integer array that sum to zero
 * 
 * 	Uses a sorting + two-pointer approach to achieve O(n log n + n^2) = O(n^2) time complexity
 * 	The array is sorted first, then for each base element, two pointers scan inward from both ends
 * 	of the remaining sub-array to find pairs that sum to the negation of the base element. 
 * 
 * Duplicates are avoided by:
 *  Skipping repeated base elements in the outer loop
 *  Advancing both pointers past duplicates after each match
 * 
 * Example:
 *  Input: [-2, -2, -1, -1, 0, 1, 3]
 *  Output: ["-2, -1, 3", "-1, 0, 1"]
 *  
 * @param arr the input array of integers, must be non-null and have at least 3 elements. 
 * @return a list of strings, each representing a unique triplet in the format "a,b,c",
 *  where a+b+c = 0  
 * @throws IllegalArgumentException if arr is null or has fewer than 3 elements
 * 
 * @complexity Time: O(n log n + n^2) = O(n^2), sort is O(n log n), 
 *  two-pointer scan is O(n^2), O(n) per element, for n elements 
 * 	
 */
public class ThreeSum {
	
	public static List<String> getTriplets(int[] arr) {
		if (arr == null || arr.length < 3) {
			throw new IllegalArgumentException("invalid input");
		}
		
		// Sort enables two-pointer technique and duplicate detection
		Arrays.sort(arr);
		
		List<String> triplets = new ArrayList<>();
		
		// Outer loop fixes the base element, stops at length-2 since we need at least 2 more elements 
		for (int a = 0 ; a < arr.length - 2 ; a++) {
			int leftPtr = a+1;
			int rightPtr = arr.length - 1;
			int baseDigit = arr[a];
			
			// Skip duplicate base elements to avoid producing duplicate triplets
			if (a == 0 || baseDigit != arr[a-1]) {
				
				// Two-pointer scan: leftPtr moves right, rightPtr moves left
				while (true) {
					if (leftPtr >= rightPtr) {
						break; // Pointers have crossed - no more pairs to check
					}
					int sum = baseDigit + arr[leftPtr] + arr[rightPtr];
					if (sum == 0) {
						// Valid triplet found, record it
						triplets.add(baseDigit+","+arr[leftPtr]+","+arr[rightPtr]);
						
						// Advance leftPtr past any duplicate values
						while (leftPtr < arr.length - 1) {
							leftPtr++;
							if (arr[leftPtr - 1] != arr[leftPtr]) {
								break;
							}
						}
						
						//Retreat rightPtr past any duplicate values
						while (rightPtr > a + 1) {
							rightPtr--;
							if (arr[rightPtr + 1] != arr[rightPtr]) {
								break;
							}
						}
					} else if (sum < 0) {
						leftPtr++; // Sum too small - move left pointer right to increase sum
					} else if (sum > 0) {
						rightPtr--; // Sum too large - move right pointer left to decrease sum
					}
				}
			}
		}
		return triplets;
	}
}