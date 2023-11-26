package sliding.window;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Anagrams {

	private class Result {
		private int lPtr = -1, rPtr = -1;
		
		private Result(int lPtr, int rPtr) {
			this.lPtr = lPtr;
			this.rPtr = rPtr;
		}
	}
	
	public List<Result> getAnagrams(char[] str, char[] anag) throws Exception {
		if (str == null || str.length == 0 || anag == null || anag.length == 0) {
			throw new Exception("Invalid input. One or both input chars are either null or empty.");
		}
		
		int lPtr = 0, rPtr = -1;
		List<Result> resultsList = new ArrayList<>();
		Set<Character> anagram = new HashSet<>();
		for (int i = 0 ; i < anag.length ; i++) {
			anagram.add(anag[i]);
		}
		
		Set<Character> matchedAnagramChars = new HashSet<>();
		
		while (true) {
			if ((rPtr + 1) > (str.length - 1)) {
				break;
			}
			
			rPtr++;
			
			if (anagram.contains(str[rPtr])) {
				if (!matchedAnagramChars.contains(str[rPtr])) {
					matchedAnagramChars.add(str[rPtr]);
				} else {
					//we have a dupe.  terminate the current window and advance the left pointer until the dupe is removed
					while (lPtr < rPtr) {
						if (str[lPtr] == str[rPtr]) {
							lPtr++;
							break;
						} else {
							matchedAnagramChars.remove(str[lPtr]);
						}
						lPtr++;
					}
				}
				
				if (matchedAnagramChars.size() == anagram.size()) {
					//we already have a anagram
					Result result = new Result(lPtr, rPtr);
					resultsList.add(result);
					matchedAnagramChars.remove(str[lPtr]);
					lPtr++;
				} 
			} else {
				matchedAnagramChars = new HashSet<>();
				if (rPtr+1 > str.length - 1) {
					break;
				} else {
					lPtr = rPtr+1;
				}
			}
		}
		
		return resultsList;
	}
	
	private void printArray(char[] chars) {
		for (int a = 0 ; a < chars.length ; a++) {
			System.out.print(chars[a]+" ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Anagrams algo = new Anagrams();
		try {
			char[] s = {'c','b','a','e','b','a','b','a','c','d'};
			char[] a = {'a','b','c'}; //anagram
			//answer: [0,2], [6,8]
			
			System.out.println("=====================================");
			algo.printArray(s);
			List<Result> results = algo.getAnagrams(s, a);
			Iterator<Result> resultsIter = results.iterator();
			while (resultsIter.hasNext()) {
				Result result = resultsIter.next();
				System.out.println(result.lPtr+","+result.rPtr);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			char[] s = {'c','b','a','b','c','e','b','z','b','a','c','d'};
			char[] a = {'a','b','c'}; //anagram
			//answer: [0,2], [2,4], [8,10]
			
			System.out.println("=====================================");
			algo.printArray(s);
			List<Result> results = algo.getAnagrams(s, a);
			Iterator<Result> resultsIter = results.iterator();
			while (resultsIter.hasNext()) {
				Result result = resultsIter.next();
				System.out.println(result.lPtr+","+result.rPtr);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}