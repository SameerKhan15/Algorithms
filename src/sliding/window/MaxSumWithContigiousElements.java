package sliding.window;

public class MaxSumWithContigiousElements {
	
	public int getMaxSum(int[] array) throws Exception {
		if (array == null || array.length == 0) {
			throw new Exception("input array is null is  or empty");
		}
		
		int maxSum=array[0];
		int currSum = maxSum;
		int lPtr=0, rPtr=0;
		
		while (true) {
			if ((rPtr + 1) > (array.length - 1)) {
				break;
			}
			
			rPtr++;
			
			if ((currSum + array[rPtr]) >= currSum) {
				currSum = currSum + array[rPtr];
				
				if ((currSum - array[lPtr]) > currSum) {
					currSum = currSum - array[lPtr];
					lPtr++;
				}
				
				if (currSum > maxSum) {
					maxSum = currSum;
				}
			} else {
				lPtr = rPtr;
				currSum = array[lPtr];
			}
		}
		
		return maxSum;
	}
	
	public void printArray(int[] array) {
		StringBuilder str = new StringBuilder();
		for (int a = 0 ; a < array.length ; a++) {
			str.append(array[a]+" ");
		}
		
		System.out.println(str.toString());
	}
	
	public static void main(String args[]) {
		MaxSumWithContigiousElements algo = new MaxSumWithContigiousElements();
		
		/* testcase1 */
		try {
			int[] a1 = {0, 1, -4, 5, 6, -2, 2};
			System.out.println("=======Testcase1 InputArray ======");
			algo.printArray(a1);
			System.out.println("=======Output ======");
			System.out.println(algo.getMaxSum(a1)+"\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/* testcase2 */
		try {	
			int[] a2 = {0, 10, -4, 5, 6, -2, 2};
			System.out.println("=======Testcase2 InputArray ======");
			algo.printArray(a2);
			System.out.println("=======Output ======");
			System.out.println(algo.getMaxSum(a2));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/* testcase3 */
		try {
			int[] a3 = {-1, 2, 3, 6, 9, -9, 10, 11, -2};
			System.out.println("=======Testcase3 InputArray ======");
			algo.printArray(a3);
			System.out.println("=======Output ======");
			System.out.println(algo.getMaxSum(a3));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/* testcase4 */
		try {
			int[] a4 = {-2};
			System.out.println("=======Testcase4 InputArray ======");
			algo.printArray(a4);
			System.out.println("=======Output ======");
			System.out.println(algo.getMaxSum(a4));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/* testcase5*/
		try {
			int[] a5 = {};
			System.out.println("=======Testcase5 InputArray ======");
			algo.printArray(a5);
			System.out.println("=======Output ======");
			System.out.println(algo.getMaxSum(a5));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/* testcase6 */
		try {
			int[] a6 = {-1, 4, 3, 6, 9, -9, 10, 11, -2};
			System.out.println("=======Testcase6 InputArray ======");
			algo.printArray(a6);
			System.out.println("=======Output ======");
			System.out.println(algo.getMaxSum(a6));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}