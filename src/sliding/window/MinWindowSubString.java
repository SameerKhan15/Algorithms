package sliding.window;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MinWindowSubString {
	
	public String compute(String s, String t) throws Exception {
		if (s == null || t == null) {
			throw new Exception("invalid input! one or more input strings are null!");
		}
		
		if (s.length() == 0 || t.length() == 0) {
			return "";
		}
		
		//initialize all the vars
		/* right-pointer is init'd to -1 to use it as a marker for no-match (includes the initial state, where we do not have a match yet) */
		int minWindowlPtr = 0, minWindowrPtr = -1;
		int currMinWindowlPtr = 0, currMinWindowrPtr = -1;
		int reqdCharsCount = 0, reqdCharsCountCurrentWindow = 0;
		
		Map<Character, AtomicInteger> countByCharsCurrentWindow = new HashMap<>();
		Map<Character, AtomicInteger> countByCharsReqd = new HashMap<>();
		
		//initialize required chars map
		for (int a = 0 ; a < t.length() ; a++) {
			if (!countByCharsReqd.containsKey(t.charAt(a))) {
				countByCharsReqd.put(t.charAt(a), new AtomicInteger());
				countByCharsCurrentWindow.put(t.charAt(a), new AtomicInteger());
			}
			countByCharsReqd.get(t.charAt(a)).incrementAndGet();
			reqdCharsCount++;
		}
		
		//sliding window algo
		while (true) {
			if (currMinWindowrPtr == s.length() - 1) {
				break;
			}
			
			currMinWindowrPtr++;
			Character rightMostCharInCurrentWindow = s.charAt(currMinWindowrPtr);
			//update count-by-chars map for the current window, if applicable
			if (countByCharsReqd.containsKey(rightMostCharInCurrentWindow)) {
				//update required-chars current window (if applicable)
				if (countByCharsCurrentWindow.get(rightMostCharInCurrentWindow).get() < countByCharsReqd.get(rightMostCharInCurrentWindow).get()) {
					reqdCharsCountCurrentWindow++;
				}
				
				countByCharsCurrentWindow.get(rightMostCharInCurrentWindow).incrementAndGet();
			}
			
			//check for the match
			if (reqdCharsCount == reqdCharsCountCurrentWindow) {
				//we have a match
				//compare current-window to min-window and update min-window if either this is a first matched window or the current window is < min-window
				if (minWindowrPtr == -1 || (currMinWindowrPtr - currMinWindowlPtr) < (minWindowrPtr - minWindowlPtr)) {
					minWindowrPtr = currMinWindowrPtr;
					minWindowlPtr = currMinWindowlPtr;
				}
				
				//left window shrink
				//initial (mandatory) shrink
				//update count-by-chars map, if applicable
				Character leftMostCharInCurrentWindow = s.charAt(currMinWindowlPtr);
				if (countByCharsReqd.containsKey(leftMostCharInCurrentWindow)) {
					//update required-chars current window (if applicable)
					if (countByCharsCurrentWindow.get(leftMostCharInCurrentWindow).get() <= countByCharsReqd.get(leftMostCharInCurrentWindow).get()) {
						reqdCharsCountCurrentWindow--;
					}
					countByCharsCurrentWindow.get(leftMostCharInCurrentWindow).decrementAndGet();
				}
				
				currMinWindowlPtr++;
				
				//subsequent shrink 
				while (currMinWindowlPtr <= currMinWindowrPtr) {
					//check for a match
					if (reqdCharsCount == reqdCharsCountCurrentWindow && (currMinWindowrPtr - currMinWindowlPtr) < (minWindowrPtr - minWindowlPtr)) {	
						minWindowrPtr = currMinWindowrPtr;
						minWindowlPtr = currMinWindowlPtr;
						
					}
					
					leftMostCharInCurrentWindow = s.charAt(currMinWindowlPtr);
					if (countByCharsReqd.containsKey(leftMostCharInCurrentWindow)) {
						if (countByCharsCurrentWindow.get(leftMostCharInCurrentWindow).get() <= countByCharsReqd.get(leftMostCharInCurrentWindow).get()) {
							break;
						}
						countByCharsCurrentWindow.get(leftMostCharInCurrentWindow).decrementAndGet();
					}
					currMinWindowlPtr++;
				}
			}
		}
		
		//print the final result
		StringBuilder result = new StringBuilder();
		for (int a = minWindowlPtr ; a <= minWindowrPtr ; a++) {
			result.append(s.charAt(a));
		}
		
		return result.toString();
	}
	
	public static void main(String[] args) {
		MinWindowSubString algo = new MinWindowSubString();
		String s1 = "ADOBECODEBANC", t1 = "ABC";
		
		try {
			String result = algo.compute(s1, t1);
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String s2 = "ADBCODEBANC";
		try {
			String result = algo.compute(s2, t1);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String s3 = "A";
		try {
			String result = algo.compute(s3, t1);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String s4 = "P";
		try {
			String result = algo.compute(s4, t1);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String s5 = "ABCFTP";
		try {
			String result = algo.compute(s5, t1);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String s6 = "ABCFTC";
		try {
			String result = algo.compute(s6, t1);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String s7 = "ABFTC";
		try {
			String result = algo.compute(s7, t1);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}