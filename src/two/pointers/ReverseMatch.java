package two.pointers;

/**
 * Utility class for performing a reverse match check on a string.
 *
 * <p>A string is considered a reverse match if:
 * <ul>
 *   <li>The lowercase letters read left-to-right correspond, case-insensitively,
 *       to the uppercase letters read right-to-left</li>
 *   <li>The count of lowercase and uppercase letters are equal</li>
 *   <li>At least one such matching pair exists</li>
 * </ul>
 *
 * <p>Non-letter characters (digits, punctuation, spaces, etc.) are ignored
 * during matching.
 *
 * <p>Examples:
 * <pre>
 *   "haDrRAHd" → true   (lowercase l-to-r: h,a,r  matches uppercase r-to-l: H,A,R)
 *   "haHrARDd" → false  (uppercase 'H' is in the middle, disrupting the mirror)
 *   "a,A"      → true   (comma ignored; a matches A)
 *   ",,,"      → false  (no letters present)
 * </pre>
 */
public class ReverseMatch {
	
	/**
     * Determines whether the given string satisfies the reverse match condition.
     *
     * <p>Scans lowercase letters from left to right and uppercase letters from
     * right to left, pairing them sequentially and comparing case-insensitively.
     * Non-letter characters are skipped by both scanners.
     *
     * @param str the string to evaluate; must not be {@code null}
     * @return {@code true} if the string is a reverse match, {@code false} otherwise
     * @throws IllegalArgumentException if {@code str} is {@code null}
     *
     * @see Character#toLowerCase(char)
     */
	public static boolean match(String str) {
		if (str == null) {
			throw new IllegalArgumentException("Invalid input! null encountered");
		}
		
		if (str.length() < 2) {
			return false;
		}
		
		int totalNumLeftChars = 0;
		int totalNumRightChars = 0;
		int numMatched = 0;
		int lPtr = 0, rPtr = str.length() - 1;
		while(lPtr < str.length() || rPtr > -1) {
			Character leftCharToProcess = null;
			while (lPtr < str.length()) {
				if (isLowerCase(str.charAt(lPtr))) {
					totalNumLeftChars++;
					leftCharToProcess = str.charAt(lPtr);
					lPtr++;
					break;
				} else {
					lPtr++;
				}
			}
			
			Character rightCharToProcess = null;
			while (rPtr > -1) {
				if (isUpperCase(str.charAt(rPtr))) {
					totalNumRightChars++;
					rightCharToProcess = str.charAt(rPtr);
					rPtr--;
					break;
				} else {
					rPtr--;
				}
			}
			
			if (leftCharToProcess != null && rightCharToProcess != null) {
				if (Character.toLowerCase(leftCharToProcess) == Character.toLowerCase(rightCharToProcess)) {
					numMatched++;
				} else {
					break;
				}
			}
		}
		
		return numMatched > 0 && (numMatched == totalNumLeftChars) && (totalNumLeftChars == totalNumRightChars);
	}
	
	/**
     * Returns {@code true} if the given character is a lowercase ASCII letter (a-z).
     *
     * @param ch the character to test
     * @return {@code true} if {@code ch} is between 'a' and 'z' inclusive
     */
	private static boolean isLowerCase(char ch) {
		return ch >= 'a' && ch <= 'z';
	}
	
	/**
     * Returns {@code true} if the given character is an uppercase ASCII letter (A-Z).
     *
     * @param ch the character to test
     * @return {@code true} if {@code ch} is between 'A' and 'Z' inclusive
     */
	private static boolean isUpperCase(char ch) {
		return ch >= 'A' && ch <= 'Z';
	}
}