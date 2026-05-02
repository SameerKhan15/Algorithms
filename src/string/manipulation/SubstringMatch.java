package string.manipulation;

public class SubstringMatch {
	/*
	 * Using Rolling Hash Algorithms. See the attached README for details on the algorithm
	 */
	private static int BASE = 257;
	private static long MOD = 1000000007;
	
	private static long computeCharVal(char a, int pow) {
		long val = 1;
		for (int i = 0 ; i < pow ; i++) {
			val = (val * BASE) % MOD;
		}
		return val = (val * (int) a) % MOD;
	}
	
	private static long computeHash(String input) {
		long hash = 0;
		int pow = input.length() - 1;
		for (int a = 0 ; a < input.length() ; a++) {
			hash = (hash + computeCharVal(input.charAt(a), pow)) % MOD;
			pow--;
		}
		return hash;
	}
	
	private static long computePower(int pow) {
		long val = 1;
		for (int i = 0 ; i < pow ; i++) {
			val = (val * BASE) % MOD;
		}
		return val;
	}
	
	private static long computeRollingHash(char outgoingChar, char incomingChar, long oldHash, long highestPower) {
		long outgoingVal = (highestPower * (int) outgoingChar) % MOD;
		long subtracedVal = (oldHash - outgoingVal) < 0 ? (oldHash - outgoingVal + MOD) 
				: (oldHash - outgoingVal);
		long newBase = (subtracedVal * BASE) % MOD;
		return (newBase + computeCharVal(incomingChar, 0)) % MOD;
	}
	
	public static int findIndex(String str, String searchKeyword) throws IllegalArgumentException {
		if (str == null || searchKeyword == null) {
			throw new IllegalArgumentException("Invalid input. One or more inputs are null or empty");
		}
		
		if (str.length() == 0 || searchKeyword.length() == 0 || str.length() < searchKeyword.length()) {
			return -1;
		}
		
		int strSlidingWindowStartIndex = 0;
		
		// Compute one-time full-compute hash for the keyword string
		long searchKeywordHash = computeHash(searchKeyword);
		
		long highestPower = computePower(searchKeyword.length() - 1);
		
		// -1 denotes that hash is not yet computed for the initial sliding window
		long strSlidingWindowHash = -1; 
		
		while (true) {
			// This ptr marks the (zero-indexed) end location of the str sliding window 
			int strSlidingWindowEndPos = strSlidingWindowStartIndex + searchKeyword.length() - 1;
			
			// Note that substring function is end_index-exclusive, hence +1
			if (strSlidingWindowHash == -1) {
				// initial (first) sliding window requires full-compute hash
				strSlidingWindowHash = computeHash(str.substring(strSlidingWindowStartIndex, strSlidingWindowEndPos+1));
			}
			
			// we perform string comparison only if hashes match. note that hash equality in itself is not sufficient, due to the possibility of hash collision  
			if (searchKeywordHash == strSlidingWindowHash && searchKeyword.equals(str.substring(strSlidingWindowStartIndex, strSlidingWindowEndPos+1))) {
				return strSlidingWindowStartIndex;
			}
			
			if (strSlidingWindowStartIndex+searchKeyword.length() == str.length()) {
				// we have exhausted the search
				break;
			}
			
			strSlidingWindowHash = computeRollingHash(str.charAt(strSlidingWindowStartIndex), 
					str.charAt(strSlidingWindowStartIndex + searchKeyword.length()), 
					strSlidingWindowHash, highestPower);
			strSlidingWindowStartIndex++;
		}
		return -1; // -1 denotes no match
	}
}