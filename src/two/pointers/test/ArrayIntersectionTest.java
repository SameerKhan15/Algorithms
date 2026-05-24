package two.pointers.test;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import two.pointers.ArrayIntersection;

public class ArrayIntersectionTest {
	
	// Null / empty guard tests
	
	@Test(expected = IllegalArgumentException.class) 
	public void testNullArr1() {
		ArrayIntersection.computeIntersection(null, new int[]{1, 2, 3});
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void testNullArr2() {
		ArrayIntersection.computeIntersection(new int[]{1, 2, 3}, null);
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void testBothNull() {
		ArrayIntersection.computeIntersection(null, null);
	}
	
	@Test
    public void testEmptyArr1() {
        int[] result = ArrayIntersection.computeIntersection(new int[]{}, new int[]{1, 2, 3});
        assertArrayEquals(new int[]{}, result);
    }
	
	@Test
    public void testEmptyArr2() {
        int[] result = ArrayIntersection.computeIntersection(new int[]{1, 2, 3}, new int[]{});
        assertArrayEquals(new int[]{}, result);
    }
	
	@Test
    public void testBothEmpty() {
        int[] result = ArrayIntersection.computeIntersection(new int[]{}, new int[]{});
        assertArrayEquals(new int[]{}, result);
    }
	
	// No-intersection cases
	
	@Test
	public void testNoIntersection() {
		int[] result = ArrayIntersection.computeIntersection(
                new int[]{1, 3, 5},
                new int[]{2, 4, 6});
        assertArrayEquals(new int[]{}, result);
	}
	
	@Test
    public void testArr1AllLessThanArr2() {
        int[] result = ArrayIntersection.computeIntersection(
                new int[]{1, 2, 3},
                new int[]{4, 5, 6});
        assertArrayEquals(new int[]{}, result);
    }
	
	@Test
    public void testArr1AllGreaterThanArr2() {
        int[] result = ArrayIntersection.computeIntersection(
                new int[]{4, 5, 6},
                new int[]{1, 2, 3});
        assertArrayEquals(new int[]{}, result);
    }
	
	// Basic intersection cases
	
	@Test
    public void testPartialIntersection() {
        int[] result = ArrayIntersection.computeIntersection(
                new int[]{1, 2, 3},
                new int[]{1, 3, 5});
        assertArrayEquals(new int[]{1, 3}, result);
    }
	
	@Test
    public void testIdenticalArrays() {
        int[] result = ArrayIntersection.computeIntersection(
                new int[]{1, 2, 3},
                new int[]{1, 2, 3});
        assertArrayEquals(new int[]{1, 2, 3}, result);
    }
	
	@Test
    public void testSingleElementIntersection() {
        int[] result = ArrayIntersection.computeIntersection(
                new int[]{1, 2, 3},
                new int[]{3, 4, 5});
        assertArrayEquals(new int[]{3}, result);
    }
	
	@Test
    public void testSingleElementArraysMatch() {
        int[] result = ArrayIntersection.computeIntersection(
                new int[]{7},
                new int[]{7});
        assertArrayEquals(new int[]{7}, result);
    }
	
	@Test
    public void testSingleElementArraysNoMatch() {
        int[] result = ArrayIntersection.computeIntersection(
                new int[]{1},
                new int[]{2});
        assertArrayEquals(new int[]{}, result);
    }
	
	// Duplicate handling
	
	@Test
    public void testDuplicatesInBothArrays() {
        int[] result = ArrayIntersection.computeIntersection(
                new int[]{1, 1, 1},
                new int[]{1, 1});
        assertArrayEquals(new int[]{1, 1}, result);
    }
	
	@Test
    public void testDuplicatesOnlyInArr1() {
        int[] result = ArrayIntersection.computeIntersection(
                new int[]{2, 2, 3},
                new int[]{2, 3, 4});
        assertArrayEquals(new int[]{2, 3}, result);
    }
	
	@Test
    public void testDuplicatesOnlyInArr2() {
        int[] result = ArrayIntersection.computeIntersection(
                new int[]{2, 3, 4},
                new int[]{2, 2, 3});
        assertArrayEquals(new int[]{2, 3}, result);
    }
	
	// Arrays of different lengths
	
	@Test
    public void testArr1LongerThanArr2() {
        int[] result = ArrayIntersection.computeIntersection(
                new int[]{1, 2, 3, 4, 5, 6, 7, 8},
                new int[]{3, 6});
        assertArrayEquals(new int[]{3, 6}, result);
    }
 
    @Test
    public void testArr2LongerThanArr1() {
        int[] result = ArrayIntersection.computeIntersection(
                new int[]{3, 6},
                new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        assertArrayEquals(new int[]{3, 6}, result);
    }
    
    // Edge values
    
    @Test
    public void testNegativeNumbers() {
        int[] result = ArrayIntersection.computeIntersection(
                new int[]{-5, -3, -1, 0},
                new int[]{-3, 0, 2});
        assertArrayEquals(new int[]{-3, 0}, result);
    }
    
    @Test
    public void testMixedNegativeAndPositive() {
        int[] result = ArrayIntersection.computeIntersection(
                new int[]{-2, 0, 2, 4},
                new int[]{-2, 1, 2, 3});
        assertArrayEquals(new int[]{-2, 2}, result);
    }
    
    @Test
    public void testLargeValues() {
        int max = Integer.MAX_VALUE;
        int[] result = ArrayIntersection.computeIntersection(
                new int[]{max - 1, max},
                new int[]{max});
        assertArrayEquals(new int[]{max}, result);
    }
}