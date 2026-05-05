package two.pointers;

public class SmallerPrefixes {
	
	/*
	 * for every k in the range 1 <= k <= n/2: 
	 *  return true if sum of first k elements is smaller than the sum of the first 2k elements
	 *   
	 * ex1: arr = [1,2,2,-1]
	 * True
	 * 
	 * ex2: arr = [1,2,-2,1,3,5]
	 * False
	 * 
	 * n/2 (outer loop) + n (inner loop total) = 3n/2
	 * Drop the constant → O(n)
	 */
	public static boolean hasSmallerPrefix(int[] arr) {
		if (arr.length % 2 != 0 || arr.length < 2) {
			throw new IllegalArgumentException();
		}
		
		int leftPtr = 0;
		int rightPtr = 0;
		long leftSum = 0;
		long rightSum = 0;
		boolean isSmallerPrefix = true;
		
		while (leftPtr < arr.length / 2) {
			leftSum += arr[leftPtr]; 
			int rightPtrTarget = (leftPtr + 1) * 2;
			for (int i = rightPtr ; i < rightPtrTarget ; i++) {
				rightSum += arr[i];
			}
			rightPtr = rightPtrTarget;
			
			if (leftSum >= rightSum) {
				isSmallerPrefix = false;
				break;
			}
			leftPtr++;
		}
		return isSmallerPrefix;
	}
}