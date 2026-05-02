package string.manipulation.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Test;
import string.manipulation.SubstringMatch;

/*
 * Unit tests for SubstringMatch.findIndex()
 * 
 * Test categories:
 * 	1. Invalid / edge inputs: null, empty, keyword longer than string
 *  2. Single char cases: trivial but important boundary 
 *  3. Normal matches: keyword at start / middle / end
 *  4. No-match cases: keyword not present
 *  5. Whole-string match: keyword == string
 *  6. Repeated-character strings: stress-tests hash collision handling
 *  7. Rolling-hash correctness: multi-window traversal
 *  8. Case sensitivity
 *  9. Numeric characters
 *  10. Spaces and punctuation
 *  11. Hash collision stress tests
 *  12. Overflow boundary
 *  13. High ASCII values (near BASE=257 boundary)
 *  14. Rolling hash negative delta guard
 */
public class SubstringMatchTest {
	
	// 1. Invalid / edge inputs
	
	@Test
    public void nullStrThrows() {
		assertThrows(IllegalArgumentException.class, () -> SubstringMatch.findIndex(null, "abc"));
	}
	
	@Test
	public void emptyStr() {
		assertEquals(-1, SubstringMatch.findIndex("", "abc"));
	}
	
	@Test
	public void nullKeywordThrows() {
		assertThrows(IllegalArgumentException.class, () -> SubstringMatch.findIndex("abc", null));
	}
	
	@Test
	public void emptyKeyword() {
		assertEquals(-1, SubstringMatch.findIndex("jdj", ""));
	}
	
	@Test
	public void keywordLongerThanString() {
		assertEquals(-1, SubstringMatch.findIndex("ab", "abc"));
	}
	
	// 2. Single char cases
	
	@Test
	public void singleCharMatch() {
		assertEquals(0, SubstringMatch.findIndex("a", "a"));
	}
	
	@Test
	public void singleCharMatchInside() {
		assertEquals(2, SubstringMatch.findIndex("abcde", "c"));
	}
	
	@Test
	public void singleCharNoMatch() {
		assertEquals(-1, SubstringMatch.findIndex("abcde", "z"));
	}
	
	// 3. Normal matches
	
	@Test
	public void normalMatches() {
		assertEquals(0, SubstringMatch.findIndex("abcd", "ab"));
		assertEquals(1, SubstringMatch.findIndex("abcd", "bc"));
		assertEquals(2, SubstringMatch.findIndex("abcd", "cd"));
		assertEquals(1, SubstringMatch.findIndex("abcde", "bcd"));
		assertEquals(1, SubstringMatch.findIndex("abcde", "bcde"));
		assertEquals(0, SubstringMatch.findIndex("abcde", "abcd"));
		assertEquals(6, SubstringMatch.findIndex("hello world", "world"));
		assertEquals(1, SubstringMatch.findIndex("mississippi", "issi"));
	}
	
	// 4. No-match cases
	
	@Test
	public void noMatchCases() {
		assertEquals(-1, SubstringMatch.findIndex("abcd", "xyz"));
		assertEquals(-1, SubstringMatch.findIndex("abcd", "ac"));
		assertEquals(-1, SubstringMatch.findIndex("abcde", "abcdf"));
		assertEquals(-1, SubstringMatch.findIndex("hello", "world"));
		assertEquals(-1, SubstringMatch.findIndex("aaaa", "aaaaa"));
	}
	
	// 5. Whole-string match
	
	@Test
	public void wholeStringMatch() {
		assertEquals(0, SubstringMatch.findIndex("abcde", "abcde"));
	}
	
	@Test
	public void singleCharWholeMatch() {
		assertEquals(0, SubstringMatch.findIndex("x", "x"));
	}
	
	// 6. Repeated-character strings
	
	@Test
	public void allSameCharsStart() {
		assertEquals(0, SubstringMatch.findIndex("aaaa", "aaa"));
		assertEquals(0, SubstringMatch.findIndex("aaaaa", "aaa"));
	}
	
	@Test
	public void almostMatchRepeated() {
		assertEquals(-1, SubstringMatch.findIndex("aaaa", "aab"));
	}
	
	@Test
	public void repeatedWithOneDiff() {
		assertEquals(2, SubstringMatch.findIndex("aaabaa", "ab"));
	}
	
	// 7. Rolling-hash correctness 
	
	@Test
	public void longStringKeywordAtEnd() {
		String str = "abcdefghijklmnopqrstuvwxyz";
        assertEquals(23, SubstringMatch.findIndex(str, "xyz"));
	}
	
	@Test
	public void longStringNoMatch() {
		String str = "abcdefghijklmnopqrstuvwxyz";
        assertEquals(-1, SubstringMatch.findIndex(str, "az"));
	}
	
	@Test
	public void keywordAtOverflowBoundary() {
		String str     = "abcdefghij";
        String keyword = "bcdefghij";   // 9 chars, starts at index 1
        assertEquals(1, SubstringMatch.findIndex(str, keyword));
	}
	
	@Test
	public void keywordAboveOverflowBoundary() {
		String str     = "abcdefghijk";
        String keyword = "bcdefghijk";   // 10 chars
        assertEquals(1, SubstringMatch.findIndex(str, keyword));
	}
	
	// 8. Case sensitivity
	
	@Test
	public void caseSensitiveNoMatch() {
		assertEquals(-1, SubstringMatch.findIndex("abcde", "BCD"));
	}
	
	@Test
	public void caseSensitiveMatch() {
		assertEquals(1, SubstringMatch.findIndex("aBCDe", "BCD"));
	}
	
	// 9. Numeric characters
	
	@Test
	public void numericCharsMatch() {
		assertEquals(2, SubstringMatch.findIndex("ab123cd", "123"));
	}
	
	@Test
	public void numericCharsNoMatch() {
		assertEquals(-1, SubstringMatch.findIndex("ab123cd", "124"));
	}
	
	// 10. Spaces and punctuation
	
	@Test
	public void spaceInKeyword() {
		assertEquals(4, SubstringMatch.findIndex("foo bar", "bar"));
	}
	
	@Test
	public void punctuationMatch() {
		assertEquals(7, SubstringMatch.findIndex("hello, world!", "world"));
	}
	
	// 11. Hash collision stress tests
	@Test
	public void anagramNoMatch() {
		assertEquals(-1, SubstringMatch.findIndex("abcdef", "bca"));
	}
	
	@Test
	public void sameSumDifferentArrangement() {
		assertEquals(-1, SubstringMatch.findIndex("acddd", "bb"));
	}
	
	@Test
	public void windowSizeOneLastChar() {
	    assertEquals(25, SubstringMatch.findIndex("abcdefghijklmnopqrstuvwxyz", "z"));
	}
	
	@Test
	public void windowOneCharShortMatch() {
		assertEquals(0, SubstringMatch.findIndex("abcde", "abcd"));
	}
	
	@Test
	public void windowOneCharShortNoMatch() {
		assertEquals(-1, SubstringMatch.findIndex("abcde", "abce"));
	}
	
	@Test
	public void windowEqualsStringLength() {
		assertEquals(0, SubstringMatch.findIndex("abcde", "abcde"));
	}
	
	@Test
	public void windowEqualsStringLengthNoMatch() {
		assertEquals(-1, SubstringMatch.findIndex("abcde", "abcdf"));
	}
	
	// 12. Overflow boundary
	
	@Test
	public void windowSize8() {
		String str = "xabcdefghx";
	    assertEquals(1, SubstringMatch.findIndex(str, "abcdefgh")); // 8 chars
	}
	
	@Test
	public void windowSize9AtBoundary() {
		String str = "xabcdefghix";
	    assertEquals(1, SubstringMatch.findIndex(str, "abcdefghi")); // 9 chars
	}
	
	@Test
	public void windowSize9NoMatch() {
		assertEquals(-1, SubstringMatch.findIndex("abcdefghi", "abcdefghx"));
	}
	
	@Test
	public void windowSize20() {
		String str     = "xxxxabcdefghijklmnopqrstx";
	    String keyword = "abcdefghijklmnopqrst"; // 20 chars
	    assertEquals(4, SubstringMatch.findIndex(str, keyword));
	}
	
	// 13. High ASCII values (near BASE=257 boundary)
	
	@Test
	public void highAsciiChars() {
		// Extended ASCII: é=233, ü=252, ý=253 — all below 257
	    String str     = "abcéüýdef";
	    String keyword = "éüý";
	    assertEquals(3, SubstringMatch.findIndex(str, keyword));
	}
	
	@Test
	public void mixedAsciiRange() {
		String str     = "az" + (char)254 + (char)255; // near-max ASCII
	    String keyword = "" + (char)254 + (char)255;
	    assertEquals(2, SubstringMatch.findIndex(str, keyword));
	}
	
	// 14. Rolling hash negative delta guard
	
	@Test
	public void negativeModGuard() {
		// 'z'(122) outgoing, 'a'(97) incoming — subtraction goes negative before mod
	    // This directly exercises the (delta + MOD) % MOD branch in computeRollingHash
	    assertEquals(1, SubstringMatch.findIndex("zabc", "abc"));
	}
	
	@Test
	public void maxOutgoingMinIncoming() {
		assertEquals(1, SubstringMatch.findIndex((char)255 + "aaa", "aaa"));
	}
}