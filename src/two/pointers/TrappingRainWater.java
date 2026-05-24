package two.pointers;

public class TrappingRainWater {
	
	/**
	 * Calculates the total units of rainwater that can be trapped between walls
	 * in the given elevation map, using a two-pointer O(n) time, O(1) space approach.
	 *
	 * @param wallsHeight  non-null array of non-negative integers representing wall heights
	 * @return             total units of trapped water (as long to avoid overflow on large inputs)
	 * @throws IllegalArgumentException if wallsHeight is null
	 */
	public static long calculateTrappedWater(int[] wallsHeight) {
		// Guard: null input is invalid
		if (wallsHeight == null) {
			throw new IllegalArgumentException("Invalid input! array is null");
		}
		
		// Option 1: Validate upfront that all elements are non-negative
		for (int height : wallsHeight) {
		    if (height < 0) {
		        throw new IllegalArgumentException(
		            "Invalid input! negative wall height: " + height);
		    }
		}
		
		// An empty array or single element cannot trap any water		
		if (wallsHeight.length <= 1) {
			return 0;		
		}
		
		int lPtr = 0; // Left pointer, starts at leftmost wall
		int rPtr = wallsHeight.length - 1; // Right pointer, starts at rightmost wall
		long water = 0; // Accumulated trapped water (long prevents overflow)
		int maxLeftVal = 0, maxRightVal = 0; // Running max heights seen from each side
		while (true) {
	        // Termination: pointers have converged — all positions have been processed
			if (lPtr >= rPtr) {
				break;
			}
			
	        // Update running maximums before processing each pointer position
			if (wallsHeight[lPtr] > maxLeftVal) {
				maxLeftVal = wallsHeight[lPtr];
			}
			
			if (wallsHeight[rPtr] > maxRightVal) {
				maxRightVal = wallsHeight[rPtr];
			}
			
			if (wallsHeight[lPtr] <= wallsHeight[rPtr]) {
				/*
	             * The right wall is at least as tall as the left wall.
	             * The water level at lPtr is bounded by maxLeftVal (the shorter side).
	             * min(maxLeftVal, maxRightVal) == maxLeftVal here because maxRightVal
	             * is anchored by a wall >= wallsHeight[lPtr].
	             *
	             * Skip index 0: the leftmost boundary wall cannot hold water above itself —
	             * there is no wall to the left of it to form a basin.
	             */
				if (lPtr != 0) {
					water += Math.max(0, Math.min(maxLeftVal, maxRightVal) - wallsHeight[lPtr]);
				}
				lPtr++; // Move inward from the left
			} else {
				/*
	             * The left wall is strictly taller than the right wall.
	             * The water level at rPtr is bounded by maxRightVal (the shorter side).
	             * min(maxLeftVal, maxRightVal) == maxRightVal here.
	             *
	             * Skip last index: the rightmost boundary wall cannot trap water for
	             * the same reason as index 0 on the left.
	             */
				if (rPtr != 0) {
					water += Math.max(0, Math.min(maxLeftVal, maxRightVal) - wallsHeight[rPtr]);
				}
				rPtr--; // Move inward from the right
			}
		}
		return water;
	}
}