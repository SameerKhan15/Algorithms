package two.pointers.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import two.pointers.Palindrome;

public class PalindromeTest {
	
	// Exception / edge cases
	
	@Test(expected = IllegalArgumentException.class) 
	public void testInputIsNull() {
		Palindrome.isPalindrome(null);
	}
	
	@Test
    public void testInputIsNullExceptionMessageContainsNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> Palindrome.isPalindrome(null));
        assertTrue(ex.getMessage().contains("null"));
    }
	
	@Test
    public void testEmptyString() {
        assertFalse(Palindrome.isPalindrome(""));
    }
	
	@Test
    public void testOnlyNonLetterCharacters() {
        assertFalse(Palindrome.isPalindrome(", , ?'"));
    }
	
	@Test
    public void testSingleNonLetterCharacter() {
        assertFalse(Palindrome.isPalindrome("'"));
    }
	
	// Single-character strings
	
	@Test
    public void testSingleLowercaseLetter() {
        assertTrue(Palindrome.isPalindrome("a"));
    }
 
    @Test
    public void testSingleUppercaseLetter() {
        assertTrue(Palindrome.isPalindrome("Z"));
    }
    
    // Simple palindromes (no punctuation / spaces)
    
    @Test
    public void testSimplePalindromes() {
        assertTrue(Palindrome.isPalindrome("aba"));
        assertTrue(Palindrome.isPalindrome("abba"));
        assertTrue(Palindrome.isPalindrome("racecar"));
        assertTrue(Palindrome.isPalindrome("level"));
        assertTrue(Palindrome.isPalindrome("noon"));
        assertTrue(Palindrome.isPalindrome("madam"));
    }
    
    // Simple non-palindromes (no punctuation / spaces)
    
    @Test
    public void testSimpleNonPalindromes() {
        assertFalse(Palindrome.isPalindrome("ab"));
        assertFalse(Palindrome.isPalindrome("abc"));
        assertFalse(Palindrome.isPalindrome("hello"));
        assertFalse(Palindrome.isPalindrome("world"));
        assertFalse(Palindrome.isPalindrome("java"));
    }
    
    // Case-insensitive checks
    
    @Test
    public void testMixedCasePalindromes() {
        assertTrue(Palindrome.isPalindrome("RaceCar"));
        assertTrue(Palindrome.isPalindrome("RaceCar1"));  
    }
    
    @Test
    public void testAllUpperCasePalindrome() {
        assertTrue(Palindrome.isPalindrome("RACECAR"));
    }
    
    // Strings with spaces and punctuation (letters only matter)
    
    @Test
    public void testSentenceWithPunctuationAndSpacesIsPalindrome() {
        assertTrue(Palindrome.isPalindrome("Bob wondered, 'Now, Bob?'"));
    }
 
    @Test
    public void testPhraseWithSpacesIsPalindromes() {
        assertTrue(Palindrome.isPalindrome("A man a plan a canal Panama"
                .replace(" ", " "))); 
        assertTrue(Palindrome.isPalindrome("A man a plan a canal Panama"));
    }
 
    @Test
    public void testLeadingAndTrailingPunctuationIsPalindrome() {
        assertTrue(Palindrome.isPalindrome("...aba..."));
    }
 
    @Test
    public void testLeadingAndTrailingPunctuationIsNotPalindrome() {
        assertFalse(Palindrome.isPalindrome("...abc..."));
    }
 
    @Test
    public void testLettersSurroundedByNumbersIsPalindrome() {
        assertTrue(Palindrome.isPalindrome("1a2b2a1"));
    }
 
    @Test
    public void testInterleavedNonLettersIsPalindrome() {
        assertTrue(Palindrome.isPalindrome("a!b  b?a"));
    }
 
    @Test
    public void testInterleavedNonLettersIsNotPalindrome() {
        assertFalse(Palindrome.isPalindrome("a!b  c?d"));
    }
    
    // Two-letter edge cases
    
    @Test
    public void testTwoSameLetters() {
        assertTrue(Palindrome.isPalindrome("aa"));
    }
 
    @Test
    public void twoSameLettersDifferentCase() {
        assertTrue(Palindrome.isPalindrome("Aa"));
    }
 
    @Test
    public void testTwoDifferentLetters() {
        assertFalse(Palindrome.isPalindrome("ab"));
    }
    
    // Whitespace-only string
    
    @Test
    public void testWhitespaceOnly() {
        assertFalse(Palindrome.isPalindrome("     "));
    }
}