package two.pointers.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import two.pointers.TrappingRainWater;

public class TrappingRainWaterTest {
	
	// Null / Empty / Zero Trapped Water / Edge Inputs
	
	@Test(expected = IllegalArgumentException.class) 
	public void testInputIsNull() {
		TrappingRainWater.calculateTrappedWater(null);
	}
	
	@Test
	public void testInputIsEmpty() {
		assertEquals(0, TrappingRainWater.calculateTrappedWater(new int[]{}));
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void testInputContainsNegativeElement() {
		assertEquals(0, TrappingRainWater.calculateTrappedWater(new int[]{-1}));
		assertEquals(0, TrappingRainWater.calculateTrappedWater(new int[]{5,0,-1,5}));
	}
	
	@Test
	public void testInputIsSingleElement() {
		 assertEquals(0, TrappingRainWater.calculateTrappedWater(new int[]{5}));
	}
	
	@Test
	public void testInputIsTwoElements() {
		assertEquals(0, TrappingRainWater.calculateTrappedWater(new int[]{4, 2}));
	}
	
	@Test
	public void testInputIsMonotonicallyIncreasing() {
        assertEquals(0, TrappingRainWater.calculateTrappedWater(new int[]{1, 2, 3, 4, 5}));
	}
	
	@Test
	public void testInputIsMonotonicallyDecreasing() {
        assertEquals(0, TrappingRainWater.calculateTrappedWater(new int[]{5, 4, 3, 2, 1}));
	}
	
	@Test
	public void testInputIsAllElementsEqual() {
        assertEquals(0, TrappingRainWater.calculateTrappedWater(new int[]{3, 3, 3, 3}));
	}
	
	@Test
	public void testInputIsAllElementsZero() {
        assertEquals(0, TrappingRainWater.calculateTrappedWater(new int[]{0, 0, 0}));
        assertEquals(0, TrappingRainWater.calculateTrappedWater(new int[]{0, 0, 0, 0}));
	}
	
	@Test
	public void testOtherInputsWithNoTrappedWater() {
        assertEquals(0, TrappingRainWater.calculateTrappedWater(new int[]{1, 1, 5}));
        assertEquals(0, TrappingRainWater.calculateTrappedWater(new int[]{5, 1, 1}));
        assertEquals(0, TrappingRainWater.calculateTrappedWater(new int[]{1, 3, 2}));
        assertEquals(0, TrappingRainWater.calculateTrappedWater(new int[]{0, 5, 0}));
	}
	
	// Water is Trapped
	
	@Test
	public void testInputsWithTrappedWater() {
		assertEquals(1, TrappingRainWater.calculateTrappedWater(new int[]{1, 0, 5}));
		assertEquals(1, TrappingRainWater.calculateTrappedWater(new int[]{5, 0, 1}));
		
		assertEquals(2, TrappingRainWater.calculateTrappedWater(new int[]{10, 5, 7}));
		assertEquals(2, TrappingRainWater.calculateTrappedWater(new int[]{3, 1, 5}));
		
        assertEquals(5, TrappingRainWater.calculateTrappedWater(new int[]{5, 0, 5}));
        assertEquals(5, TrappingRainWater.calculateTrappedWater(new int[]{10, 5, 10}));
        assertEquals(5, TrappingRainWater.calculateTrappedWater(new int[]{10, 5, 10, 7}));
        
        assertEquals(7, TrappingRainWater.calculateTrappedWater(new int[]{3, 0, 2, 0, 4}));
        assertEquals(9, TrappingRainWater.calculateTrappedWater(new int[]{4, 2, 0, 3, 2, 5}));
        
        assertEquals(8, TrappingRainWater.calculateTrappedWater(new int[]{5, 1, 5, 1, 5}));
        assertEquals(8, TrappingRainWater.calculateTrappedWater(new int[]{5, 1, 5, 1, 5}));
        
        assertEquals(15, TrappingRainWater.calculateTrappedWater(new int[]{5, 0, 0, 0, 5}));
	}
	
	@Test
	public void testLargeValues() {
		assertEquals(999_999, TrappingRainWater.calculateTrappedWater(
                new int[]{1_000_000, 1, 1_000_000}));
	}
}