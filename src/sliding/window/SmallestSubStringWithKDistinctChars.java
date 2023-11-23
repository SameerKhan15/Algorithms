package sliding.window;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class SmallestSubStringWithKDistinctChars {

	private class Result {
		
		private Result(int lPtr, int rPtr) {
			this.lPtr = lPtr;
			this.rPtr = rPtr;
		}
		
		private int lPtr = -1, rPtr = -1;
	}
	
	private void addCharToSet(char a, Map<Character, AtomicInteger> countByChar) {
		if (!countByChar.containsKey(a)) {
			countByChar.put(a, new AtomicInteger());
		}
		
		countByChar.get(a).incrementAndGet();
	}
	
	private int trimCharSet(char[] chars, Map<Character, AtomicInteger> countByChar, int lPtr, int rPtr) {
		while (lPtr != rPtr) {
			AtomicInteger lPtrCharCount = countByChar.get(chars[lPtr]);
			if (lPtrCharCount.get() > 1) {
				lPtr++;
				lPtrCharCount.decrementAndGet();
			} else {
				break;
			}
		}
		
		return lPtr;
	}
	
	private void decrementChar(char c, Map<Character, AtomicInteger> countByChar) {
		AtomicInteger lPtrCharCount = countByChar.get(c);
		if (lPtrCharCount.get() == 1) {
			countByChar.remove(c);
		} else {
			lPtrCharCount.decrementAndGet();
		}
	}
	
	public Result compute(char[] chars, int numDistinctChars) throws Exception {
		if (chars == null || chars.length == 0) {
			throw new Exception("invalid input. chars is null or empty, or numDistinctChars < 1");
		}
		
		int lPtr = 0, rPtr = -1;
		int smallestWindowleftPtr = 0, smallestWindowrightPtr = -1;
		Map<Character, AtomicInteger> countByChar = new HashMap<>();
		
		while (true) {
			if ((rPtr + 1) > (chars.length - 1)) {
				break;
			}
			
			rPtr++;
			//add char to the set
			addCharToSet(chars[rPtr], countByChar);
			
			if (countByChar.size() == numDistinctChars) {
				if (smallestWindowrightPtr == -1) {
					smallestWindowrightPtr = rPtr;
				}
				
				lPtr = trimCharSet(chars, countByChar, lPtr, rPtr);
				
				if ((rPtr - lPtr + 1) < (smallestWindowrightPtr - smallestWindowleftPtr + 1)) {
					smallestWindowleftPtr = lPtr;
					smallestWindowrightPtr = rPtr;
				}
				
				decrementChar(chars[lPtr], countByChar);
				lPtr++;
			}
		}
		
		return new Result(smallestWindowleftPtr, smallestWindowrightPtr);
	}
	
	public static void main(String[] args) {
		SmallestSubStringWithKDistinctChars algo = new SmallestSubStringWithKDistinctChars();
		try {
			//testcase1
			char[] str1 = {'p','a','p','c','d','f','g','a','p','a'};
			Result result = algo.compute(str1, 3);
			System.out.println(result.lPtr+","+result.rPtr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			//testcase2
			char[] str2 = {'p','p','p','c','p','f','g','a','p','a'};
			Result result = algo.compute(str2, 3);
			System.out.println(result.lPtr+","+result.rPtr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			//testcase3
			char[] str3 = {'p','p','p','c','p','f','g','a','p','a'};
			Result result = algo.compute(str3, 2);
			System.out.println(result.lPtr+","+result.rPtr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			//testcase4
			char[] str4 = {'p','p','p','c','p','f','g','a','p','a'};
			Result result = algo.compute(str4, 1);
			System.out.println(result.lPtr+","+result.rPtr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			//testcase5
			char[] str5 = {'p','p','p','c','p','f','g','a','p','a'};
			Result result = algo.compute(str5, 100);
			System.out.println(result.lPtr+","+result.rPtr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			//testcase6
			char[] str6 = {'p','p','p','c','p','f','g','a','p','a'};
			Result result = algo.compute(str6, 0);
			System.out.println(result.lPtr+","+result.rPtr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			//testcase7
			char[] str7 = {};
			Result result = algo.compute(str7, 10);
			System.out.println(result.lPtr+","+result.rPtr);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			//testcase8
			char[] str8 = null;
			Result result = algo.compute(str8, 10);
			System.out.println(result.lPtr+","+result.rPtr);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}