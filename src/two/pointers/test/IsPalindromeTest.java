package two.pointers.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import two.pointers.PalindromeDetect;

public class IsPalindromeTest {
	
	// 1. Null / Empty
	
	@Test
	public void testNullInput() {
		assertThrows(IllegalArgumentException.class, () -> PalindromeDetect.isPalindrome(null));
	}
	
	@Test
    public void testEmptyString() {
        assertTrue(PalindromeDetect.isPalindrome(""));
    }
	
	// 2. Single character
	
	@Test
	public void testSingleCharacter() {
		assertTrue(PalindromeDetect.isPalindrome("a"));
	}
	
	@Test
	public void testSingleDigit() {
		assertTrue(PalindromeDetect.isPalindrome("1"));
	}
	
	// 3. Valid palindromes
	
	@Test
	public void testEvenLengthPalindrome() {
		assertTrue(PalindromeDetect.isPalindrome("abba"));
	}
	
	@Test
	public void testOddLengthPalindrome() {
		assertTrue(PalindromeDetect.isPalindrome("racecar"));
	}
	
	@Test
	public void testAllSameCharacters() {
		assertTrue(PalindromeDetect.isPalindrome("aaaa"));
	}
	
	@Test
	public void testNumericPalindrome() {
		assertTrue(PalindromeDetect.isPalindrome("12321"));
	}
	
	@Test
	public void testTwoSameCharacters() {
		assertTrue(PalindromeDetect.isPalindrome("aa"));
	}
	
	// 4. Non-palindromes
	
	@Test
	public void testSimpleNonPalindrome() {
		assertFalse(PalindromeDetect.isPalindrome("hello"));
	}
	
	@Test
	public void testTwoDifferentCharacters() {
		assertFalse(PalindromeDetect.isPalindrome("ab"));
	}
	
	@Test
	public void testNearPalindrome() {
		assertFalse(PalindromeDetect.isPalindrome("racecars"));
	}
	
	@Test
	public void testNumericNonPalindrome() {
		assertFalse(PalindromeDetect.isPalindrome("12345"));
	}
	
	// 5. Case sensitivity
	
	@Test
	public void testCaseSensitive() {
		assertFalse(PalindromeDetect.isPalindrome("Aba"));
	}
	
	@Test
	public void testAllUpperCasePalindrome() {
		assertTrue(PalindromeDetect.isPalindrome("RACECAR"));
	}
	
	// 6. Special characters & spaces
	
	@Test
	public void testPalindromeWithSpaces() {
		assertTrue(PalindromeDetect.isPalindrome("a b a"));
	}
	
	@Test
	public void testStringWithSpaces() {
        assertFalse(PalindromeDetect.isPalindrome("hello world"));
    }
	
	@Test
	public void testPalindromeWithSpecialChars() {
        assertTrue(PalindromeDetect.isPalindrome("!@!"));
    }
	
	@Test
	public void testOnlySpaces() {
        assertTrue(PalindromeDetect.isPalindrome("   "));
    }
}