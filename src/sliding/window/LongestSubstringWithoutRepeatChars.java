package sliding.window;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatChars {

	private class Result {
		private int lPtr = -1, rPtr = -1;
		
		private Result(int lPtr, int rPtr) {
			this.lPtr = lPtr;
			this.rPtr = rPtr;
		}
	}
	
	public Result compute(char[] chars) throws Exception {
		if (chars == null || chars.length == 0) {
			throw new Exception("Invalid input. Supplied array is either null or empty.");
		}
		
		int lPtr = 0, rPtr = -1;
		int longestWindowlPtr = 0, longestWindowrPtr = 0; 
		Set<Character> charsInCurrentWindow = new HashSet<>();
		
		while (true ) {
			if ((rPtr + 1) > (chars.length - 1)) {
				break;
			}
			
			rPtr++;
			
			if (charsInCurrentWindow.contains(chars[rPtr])) {
				if ((rPtr - lPtr) > (longestWindowrPtr - longestWindowlPtr + 1)) {
					longestWindowlPtr = lPtr;
					longestWindowrPtr = (rPtr - 1);
				}
				
				//shrink the left window in order to form a new window
				while (lPtr < rPtr) {
					if (chars[lPtr] == chars[rPtr]) {
						lPtr++;
						break; //we do NOT remove the chars[lPtr] here, because the rPtr is already at this char, hence we need to keep this char in the set 
					}
					
					charsInCurrentWindow.remove(chars[lPtr]);
					lPtr++;
					
				}
			} else {
				charsInCurrentWindow.add(chars[rPtr]);
				if ((rPtr - lPtr + 1) > (longestWindowrPtr - longestWindowlPtr + 1)) {
					longestWindowlPtr = lPtr;
					longestWindowrPtr = rPtr;
				}
			}
		}
		
		return new Result(longestWindowlPtr, longestWindowrPtr);
	}
	
	public static void main(String[] args) {
		LongestSubstringWithoutRepeatChars algo = new LongestSubstringWithoutRepeatChars();
		
		try {
			//testcase1
			char[] chars = {'p', 'a', 'p', 'c', 'd', 'f', 'g', 'a', 'p', 'a'};
			Result res = algo.compute(chars);
			System.out.println(res.lPtr+","+res.rPtr);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			//testcase2
			char[] chars = {'p', 'a', 'c', 'd', 'f', 'g', 'a', 'p', 'a'};
			Result res = algo.compute(chars);
			System.out.println(res.lPtr+","+res.rPtr);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			//testcase3
			char[] chars = {'p', 'p', 'p', 'd'};
			Result res = algo.compute(chars);
			System.out.println(res.lPtr+","+res.rPtr);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			//testcase4
			char[] chars = {'p', 'p', 'p', 'd','l'};
			Result res = algo.compute(chars);
			System.out.println(res.lPtr+","+res.rPtr);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			//testcase5
			char[] chars = {'p'};
			Result res = algo.compute(chars);
			System.out.println(res.lPtr+","+res.rPtr);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}