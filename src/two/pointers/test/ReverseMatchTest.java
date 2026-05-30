package two.pointers.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import two.pointers.ReverseMatch;

public class ReverseMatchTest {
	
	// --- Null / edge inputs  ---
	
	@Test
    public void testNullInput() {
        assertThrows(IllegalArgumentException.class, () -> ReverseMatch.match(null));
    }
	
	@Test
	public void testEmptyStr() {
		assertFalse(ReverseMatch.match(""));
	}
	
	@Test
	public void testSingleChar() {
		assertFalse(ReverseMatch.match("a"));
        assertFalse(ReverseMatch.match("A"));
        assertFalse(ReverseMatch.match("1"));
	}
	
	@Test
	public void testNoLettersPresent() {
		assertFalse(ReverseMatch.match(",,,"));
        assertFalse(ReverseMatch.match("123"));
        assertFalse(ReverseMatch.match("   "));
	}
	
	// --- Only one side has letters ---
	
	@Test
	public void testOnlyOneSideLetters() {
		assertFalse(ReverseMatch.match("abc"));
		assertFalse(ReverseMatch.match("ABC"));
	}
	
	// --- Symmetric / valid matches  ---
	
	@Test
	public void testValidMatches() {
		assertTrue(ReverseMatch.match("aA"));
		assertTrue(ReverseMatch.match("a,A"));
		assertTrue(ReverseMatch.match("haDrRAHd"));
		assertTrue(ReverseMatch.match("abcCBA"));
	}
	
	// --- Mismatches  ---
	
	@Test
	public void testMismatches() {
		assertFalse(ReverseMatch.match("haHrARDd"));
		assertFalse(ReverseMatch.match("abCA"));
		assertFalse(ReverseMatch.match("abcBA"));
	}
	
	// --- Interleaved / complex layouts ---
	
	@Test
	public void testComplexLayouts() {
		assertTrue(ReverseMatch.match("a1b2B3A"));
		assertFalse(ReverseMatch.match("aBcA"));
		assertTrue(ReverseMatch.match("111a222A333"));
	}
}