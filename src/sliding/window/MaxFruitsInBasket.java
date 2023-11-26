package sliding.window;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MaxFruitsInBasket {

	private class Result {
		private int lPtr = -1, rPtr = -1;
		
		private Result(int lPtr, int rPtr) {
			this.lPtr = lPtr;
			this.rPtr = rPtr;
		}
	}
	
	public Result compute(int[] fruits, int numBaskets) throws Exception {
		if (fruits == null || fruits.length == 0) {
			throw new Exception("Invalid input. fruits array is either null or empty.");
		}
		
		if (numBaskets < 1) {
			throw new Exception("Invalid input. Number of Baskets < 1.");
		}
		
		int lPtr = 0, rPtr = -1;
		AtomicInteger longestWindowlPtr = new AtomicInteger(0), longestWindowrPtr = new AtomicInteger(0);
		Map<Integer, AtomicInteger> countByFruitType = new HashMap<>();
		
		while (true) {
			if ((rPtr + 1) > (fruits.length - 1)) {
				break;
			}
			
			rPtr++;
			
			if (countByFruitType.containsKey(fruits[rPtr])) {
				countByFruitType.get(fruits[rPtr]).incrementAndGet();	
				updatePointer(lPtr, rPtr, longestWindowlPtr, longestWindowrPtr);
			} else {
				if (countByFruitType.size() == numBaskets) {
					updatePointer(lPtr, rPtr - 1, longestWindowlPtr, longestWindowrPtr);
					//trim the window from the left to remove the violation
					while (countByFruitType.size() == numBaskets) {
						int toBeRemovedFruitType = fruits[lPtr];
						AtomicInteger toBeRemovedFruitTypeCount = countByFruitType.get(toBeRemovedFruitType);
						if (toBeRemovedFruitTypeCount.get() == 1) {
							countByFruitType.remove(toBeRemovedFruitType);
						} else {
							toBeRemovedFruitTypeCount.decrementAndGet();
						}
						lPtr++;
					}
				} else {
					updatePointer(lPtr, rPtr, longestWindowlPtr, longestWindowrPtr);
				}
				countByFruitType.put(fruits[rPtr], new AtomicInteger(1));
			}
		}
		
		return new Result(longestWindowlPtr.get(), longestWindowrPtr.get());
	}
	
	private void updatePointer(int lPtr, int rPtr, AtomicInteger longestWindowlPtr, AtomicInteger longestWindowrPtr) {
		if ((rPtr - lPtr + 1) > (longestWindowrPtr.get() - longestWindowlPtr.get() + 1)) {
			longestWindowlPtr.set(lPtr);
			longestWindowrPtr.set(rPtr);
		} 
	}
	
	public static void main(String[] args) {
		MaxFruitsInBasket algo = new MaxFruitsInBasket();
		//fruits[0] is tree#0, fruits[1] is tree#1 ...
		//fruits[0] = 0 means tree#0 has fruit type0
		//fruits[1] = = means tree#1 has fruit type0
		//...
		try {
			int[] fruits = {0,0,2,2,3,4,4,4,5};
			Result res = algo.compute(fruits, 2);
			System.out.println(res.lPtr+","+res.rPtr);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			int[] fruits = {0,1,2,2,3,4,4,4,5};
			Result res = algo.compute(fruits, 2);
			System.out.println(res.lPtr+","+res.rPtr);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			int[] fruits = {0,1,2,3,4,5,6,7,8};
			Result res = algo.compute(fruits, 2);
			System.out.println(res.lPtr+","+res.rPtr);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			int[] fruits = {0,1,1,1,1,1,1,1,8};
			Result res = algo.compute(fruits, 2);
			System.out.println(res.lPtr+","+res.rPtr);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			int[] fruits = {0,1,1,1,1,1,1,1,8};
			Result res = algo.compute(fruits, 3);
			System.out.println(res.lPtr+","+res.rPtr);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}