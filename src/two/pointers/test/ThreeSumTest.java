package two.pointers.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.junit.Test;
import two.pointers.ThreeSum;

public class ThreeSumTest {
	
	// 1. Exception Cases
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullInput() {
		ThreeSum.getTriplets(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEmptyArray() {
		ThreeSum.getTriplets(new int[]{});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testOneElement() {
		ThreeSum.getTriplets(new int[]{1});
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTwoElements() {
		ThreeSum.getTriplets(new int[]{1, 2});
	}
	
	@Test
	public void testDuplicateSkip() {
		List<String> result = ThreeSum.getTriplets(new int[]{-2, -1, 0, 1, 2});
		assertTrue(result.contains("-1,0,1"));
		assertTrue(result.contains("-2,0,2"));
	}
	
	// 2. Empty Result Cases
	
	@Test
	public void testAllPositive() {
		assertEquals(Collections.emptyList(), ThreeSum.getTriplets(new int[]{1, 2, 3, 4}));
	}
	
	@Test
	public void testAllNegative_noTriplets() {
	    assertEquals(Collections.emptyList(), ThreeSum.getTriplets(new int[]{-4, -3, -2, -1}));
	}

	@Test
	public void testAllSameNonZero_noTriplets() {
	    assertEquals(Collections.emptyList(), ThreeSum.getTriplets(new int[]{2, 2, 2}));
	}

	@Test
	public void testNoValidCombination() {
	    assertEquals(Collections.emptyList(), ThreeSum.getTriplets(new int[]{-1, 2, 5}));
	}
	
	// 3. Single Triplet — Minimal Inputs
	
	@Test
	public void testExactlyThreeElements() {
		assertEquals(List.of("-1,0,1"), ThreeSum.getTriplets(new int[]{-1, 0, 1}));
	}
	
	@Test
	public void testAllZeros_threeElements() {
	    assertEquals(List.of("0,0,0"), ThreeSum.getTriplets(new int[]{0, 0, 0}));
	}

	@Test
	public void testAllZeros_fourElements() {
	    assertEquals(List.of("0,0,0"), ThreeSum.getTriplets(new int[]{0, 0, 0, 0}));
	}

	@Test
	public void testNegativeSumming() {
	    assertEquals(List.of("-3,1,2"), ThreeSum.getTriplets(new int[]{-3, 1, 2}));
	}
	
	// 4. Multiple Triplets
	
	@Test
	public void testTwoTriplets() {
		List<String> result = ThreeSum.getTriplets(new int[]{-2, -1, 0, 0, 1, 2});
	    assertEquals(2, result.size());
	    assertTrue(result.contains("-2,0,2"));
	    assertTrue(result.contains("-1,0,1"));
	    
	    result = ThreeSum.getTriplets(new int[]{-2, -2, -1, -1, 0, 1, 3});
	    assertTrue(result.contains("-2,-1,3"));
	    assertTrue(result.contains("-1,0,1"));
	    assertEquals(2, result.size());
	}
	
	@Test
	public void testThreeTriplets() {
		List<String> result = ThreeSum.getTriplets(new int[]{-4, -2, -1, 0, 1, 2, 3});
	    assertTrue(result.contains("-4,1,3"));
	    assertTrue(result.contains("-2,-1,3"));
	    assertTrue(result.contains("-1,0,1") || result.contains("-2,0,2"));
	}
	
	// 5. Duplicate Handling — No Repeated Triplets
	
	@Test
	public void testDuplicateBase() {
		List<String> result = ThreeSum.getTriplets(new int[]{-1, -1, 0, 1});
	    assertEquals(1, result.size());
	    assertEquals("-1,0,1", result.get(0));
	}
	
	@Test
	public void testDuplicateLeft() {
	    // two 0s in the middle
	    List<String> result = ThreeSum.getTriplets(new int[]{-1, 0, 0, 1});
	    assertEquals(1, result.size());
	    assertEquals("-1,0,1", result.get(0));
	}
	
	@Test
	public void testDuplicateRight() {
		List<String> result = ThreeSum.getTriplets(new int[]{-2, 0, 1, 1});
	    assertEquals(1, result.size());
	    assertEquals("-2,1,1", result.get(0));
	}
	
	@Test
	public void testHeavyDuplicates_noRepeat() {
	    List<String> result = ThreeSum.getTriplets(new int[]{-1, -1, -1, 0, 0, 1, 1, 1});
	    assertEquals(1, result.size());
	    assertEquals("-1,0,1", result.get(0));
	}
	
	@Test
	public void testDuplicateSkip_doesNotMissTriplets() {
	    // proved working in prior review — no duplicates adjacent, both triplets found
	    List<String> result = ThreeSum.getTriplets(new int[]{-2, -1, 0, 1, 2});
	    assertEquals(2, result.size());
	    assertTrue(result.contains("-2,0,2"));
	    assertTrue(result.contains("-1,0,1"));
	}
	
	// 6. Unsorted Input
	
	@Test
	public void testUnsortedInput() {
		List<String> result = ThreeSum.getTriplets(new int[]{3, 1, 0, -1, -2});
	    assertTrue(result.contains("-2,0,2") || result.contains("-1,0,1"));
	}
	
	@Test
	public void testUnsortedInput_randomOrder() {
	    List<String> sorted   = ThreeSum.getTriplets(new int[]{-1, 0, 1, 2, -2});
	    List<String> unsorted = ThreeSum.getTriplets(new int[]{2, -1, 0, 1, -2});
	    assertEquals(new HashSet<>(sorted), new HashSet<>(unsorted));
	}
	
	// 7. Boundary / Special Values
	
	@Test
	public void testAllSameZero() {
		List<String> result = ThreeSum.getTriplets(new int[]{0, 0, 0, 0, 0});
	    assertEquals(1, result.size());
	    assertEquals("0,0,0", result.get(0));
	}
	
	@Test
	public void testLargeArray_noTriplets() {
	    int[] arr = new int[1000];
	    Arrays.fill(arr, 1);
	    assertEquals(Collections.emptyList(), ThreeSum.getTriplets(arr));
	}
	
	@Test
	public void testLargeArray_withTriplets() {
	    int[] arr = new int[999];
	    Arrays.fill(arr, 1);
	    arr[0] = -2;  // -2 + 1 + 1 = 0
	    List<String> result = ThreeSum.getTriplets(arr);
	    assertEquals(1, result.size());
	    assertEquals("-2,1,1", result.get(0));
	}
	
	@Test(timeout = 2000)
	public void testPerformance_noHang() {
	    // ensures no infinite loop on large input
	    int[] arr = new int[10000];
	    for (int i = 0; i < arr.length; i++) arr[i] = i - 5000;
	    assertNotNull(ThreeSum.getTriplets(arr));
	}
	
	// 8. Output Format
	
	@Test
	public void testOutputFormat() {
		List<String> result = ThreeSum.getTriplets(new int[]{-1, 0, 1});
	    assertTrue(result.get(0).matches("-?\\d+,-?\\d+,-?\\d+"));
	}
	
	@Test
	public void testOutputFormat_negativeNumbers() {
	    List<String> result = ThreeSum.getTriplets(new int[]{-3, 1, 2});
	    assertEquals("-3,1,2", result.get(0));
	}
}