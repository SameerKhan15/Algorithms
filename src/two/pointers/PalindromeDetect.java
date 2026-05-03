package two.pointers;

public class PalindromeDetect {
	
	public static boolean isPalindrome(String str) {
		if (str == null) {
			throw new IllegalArgumentException("Invalid input: null argument");
		}
		
		int leftPtr = 0;
		int rightPtr = str.length() - 1;
		boolean isPalindrome = true;
		
		if (str.length() == 0) {
			return isPalindrome;
		}

		while (true) {
			if (leftPtr >= rightPtr) {
				break;
			}
			
			if (str.charAt(leftPtr) != str.charAt(rightPtr)) {
				isPalindrome = false;
				break;
			}
			
			leftPtr++;
			rightPtr--;
		}
		
		return isPalindrome;
	}
}