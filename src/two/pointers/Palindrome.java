package two.pointers;

public class Palindrome {
	
	// Returns true only for ASCII letters (a-z, A-Z)
    // Digits, punctuation, whitespace, and non-ASCII characters all return false
	private static boolean isLetter(char ch) {
		if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
			return true;
		}
		return false;
	}
	
	// Determines whether the letter-characters of str form a palindrome
	// Non-letter characters are ignored; comparison is case-insensitive
	// Returns false for empty strings and strings with no letters; throws on null
	public static boolean isPalindrome(String str) {
		// Reject null input immediately — a null reference is not a valid string
		if (str == null) {
			throw new IllegalArgumentException("Invalid input! null");
		}
		
		// An empty string has no letters, so it cannot be a palindrome
		if (str.length() == 0) {
			return false;
		}
		
		// Two pointers that will advance inward from each end of the string
		int leftPtr = 0, rightPtr = str.length() - 1;
		
		// Tracks whether all compared letter-pairs have matched so far.
		boolean success = true;
		
		// A string with no letters at all (e.g. "123", "!?") is not a palindrome
        // This flag becomes true as soon as any letter is encountered
		boolean containsAtleastOneLetter = false;
		
		// Main loop: continue while the pointers have not crossed
		while (leftPtr <= rightPtr) {
            // Advance leftPtr right until it lands on a letter (or runs off the end)
			while (leftPtr <= str.length() - 1) {
				if (isLetter(str.charAt(leftPtr))) {
					containsAtleastOneLetter = true;
					break;
				}
				leftPtr++; // skip non-letter character
			}
			
            // Advance rightPtr left until it lands on a letter (or runs off the start)
			while (rightPtr >= 0) {
				if (isLetter(str.charAt(rightPtr))) {
					containsAtleastOneLetter = true;
					break;
				}
				rightPtr--; // skip non-letter character
			}
			
            // If the pointers have crossed, every letter has been matched — we're done
			if (leftPtr > rightPtr) {
				break;
			}
			
			// Compare the letters at both pointers, ignoring case
            // A mismatch means the string is not a palindrome
			if (Character.toLowerCase(str.charAt(leftPtr)) != Character.toLowerCase(str.charAt(rightPtr))) {
				success = false;
				break;
			}
			// Matched pair — move both pointers inward for the next comparison
			leftPtr++;
			rightPtr--;
		}
		
        // Both conditions must hold: every letter-pair matched AND at least one letter exists
		return success && containsAtleastOneLetter;
	}
}