package two.pointers;

/**
 * Solves the Two Sum Zero problem using a two-pointer approach.
 *
 * Given a sorted integer array, finds two elements that sum to zero
 * and returns their indices. Requires the input array to be sorted
 * in ascending order.
 *
 * Time complexity:  O(n) — single pass with two pointers
 * Space complexity: O(1) — no additional data structures used
 */
public class TwoSum {
	
	/**
     * Encapsulates the result of a two-sum lookup.
     *
     * On success, holds the left and right indices of the two elements
     * that sum to zero. On failure, indices default to -1.
     */
	public class Response {
		boolean success = false;
		int leftIndex = -1, rightIndex = -1;
		
		/**
         * Constructs a successful response with the matching pair's indices.
         *
         * @param success   true if a valid pair was found
         * @param leftIndex index of the left element in the pair
         * @param rightIndex index of the right element in the pair
         */
		private Response(boolean success, int leftIndex, int rightIndex) {
			this.success = success;
			this.leftIndex = leftIndex;
			this.rightIndex = rightIndex;
		}
		
		/**
         * Constructs a failure response.
         * success defaults to false, indices default to -1.
         */
		private Response() {}
		
		/** @return true if a pair summing to zero was found */
		public boolean isSuccess() {
			return success;
		}
		
		/** @return index of the left element, or -1 if no pair was found */
		public int getLeftIndex() {
			return leftIndex;
		}
		
		/** @return index of the right element, or -1 if no pair was found */
		public int getRightIndex() {
			return rightIndex;
		}
	}
	
	/**
     * Searches a sorted array for two elements that sum to zero.
     *
     * Uses a two-pointer technique: one pointer starts at the left (smallest)
     * end and one at the right (largest) end. At each step:
     *   - If the sum is zero, a pair is found.
     *   - If the sum is negative, advance the left pointer to increase the sum.
     *   - If the sum is positive, retreat the right pointer to decrease the sum.
     *
     * @param arr a sorted (ascending) integer array to search
     * @return a Response indicating success/failure and the pair's indices
     * @throws IllegalArgumentException if arr is null or not sorted in ascending order
     */
	public Response isTwoSum(int[] arr) {
		// Guard: null input is not accepted
		if (arr == null) {
			throw new IllegalArgumentException("Invalid input! null encountered");
		}
		
		// Guard: a pair requires at least two elements
		if (arr.length < 2) {
			return new Response();
		}
		
        // Guard: algorithm is only valid on sorted input; validate ascending order
		for (int i = 0 ; i < arr.length - 1 ; i++) {
			if (arr[i] > arr[i+1]) {
				throw new IllegalArgumentException("Invalid input! arr is not sorted");
			}
		}
		
		int leftPtr = 0, rightPtr = arr.length - 1;
		
		// Converge pointers inward until they meet
		while (leftPtr < rightPtr) {
			// Pair found: return indices immediately
			if (arr[leftPtr] + arr[rightPtr] == 0) {
				Response result = new Response(true, leftPtr, rightPtr);
				return result;
			}
			
			if (arr[leftPtr] + arr[rightPtr] < 0) {
				leftPtr++; // Sum too low — move left pointer right to increase it
			} else {
				rightPtr--; // Sum too high — move right pointer left to decrease it
			}
		}
		
		// Pointers met without finding a valid pair
		return new Response();
	}
}