package two.pointers.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import two.pointers.ThreeWayMergeWithoutDupes;

public class ThreeWayMergeWithoutDupesTest {
	// Happy path tests
	 @Test   
	 public void testBasicMergeNoDuplicates() {   
		 int[] arr1 = {1, 4, 7};	      
		 int[] arr2 = {2, 5, 8};
		 int[] arr3 = {3, 6, 9};
	     
		 assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 
				 ThreeWayMergeWithoutDupes.merge(arr1, arr2, arr3));   
	 }
	 
	 @Test   
	 public void testDuplicatesWithinSingleArray() {   
		 int[] arr1 = {1, 1, 2};
		 int[] arr2 = {3};
		 int[] arr3 = {4};
	     
		 assertArrayEquals(new int[]{1, 2, 3, 4},
	                ThreeWayMergeWithoutDupes.merge(arr1, arr2, arr3));   
	 }
	 
	 @Test
	 public void testDuplicatesAcrossArrays() {  
		 int[] arr1 = {2, 3, 3, 4, 5, 7};
		 int[] arr2 = {3, 3, 9};
		 int[] arr3 = {3, 3, 9};
	     
		 assertArrayEquals(new int[]{2, 3, 4, 5, 7, 9},
	                ThreeWayMergeWithoutDupes.merge(arr1, arr2, arr3));
	 }
	 
	 @Test
	 public void testAllArraysSameValue() {       
		 int[] arr1 = {5};
		 int[] arr2 = {5};
		 int[] arr3 = {5};
	     
		 assertArrayEquals(new int[]{5},
	                ThreeWayMergeWithoutDupes.merge(arr1, arr2, arr3));
	 }
	 
	 @Test
	 public void testLargeDuplicateRunsAcrossAllArrays() {  
		 int[] arr1 = {1, 1, 1};	     
		 int[] arr2 = {1, 1, 1};
		 int[] arr3 = {1, 1, 1};
	     
		 assertArrayEquals(new int[]{1},
	                ThreeWayMergeWithoutDupes.merge(arr1, arr2, arr3));   
	 }
	 
	 @Test
	 public void testDeduplicationOutsideIntegerCache() {        
		 int[] arr1 = {200, 200};	     
		 int[] arr2 = {200};	     
		 int[] arr3 = {201};
	     
		 assertArrayEquals(new int[]{200, 201},
	                ThreeWayMergeWithoutDupes.merge(arr1, arr2, arr3));   
	 }
	 
	 @Test 
	 public void testNegativeNumbers() {       
		 int[] arr1 = {-5, -3, -1};	     
		 int[] arr2 = {-4, -3, 0};	     
		 int[] arr3 = {-3, 2};
	     
		 assertArrayEquals(new int[]{-5, -4, -3, -1, 0, 2},
	                ThreeWayMergeWithoutDupes.merge(arr1, arr2, arr3));	    
	 }
	 
	 @Test
	 public void testArraysOfDifferentLengths() {	 
		 int[] arr1 = {1};	     
		 int[] arr2 = {2, 3, 4, 5};	     
		 int[] arr3 = {6, 7};
	     
		 assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7},
	                ThreeWayMergeWithoutDupes.merge(arr1, arr2, arr3));
	 }
	    
	 @Test	    
	 public void testSingleElementArraysAllIdentical() {	 
		 int[] arr1 = {42};		     	    
		 int[] arr2 = {42};		    	    
		 int[] arr3 = {42};
	   
		 assertArrayEquals(new int[]{42},
		                ThreeWayMergeWithoutDupes.merge(arr1, arr2, arr3));   
	 }
	    
	 @Test
	 public void testSingleElementArraysAllDistinct() {	 
		 int[] arr1 = {3};	     
		 int[] arr2 = {1};	     
		 int[] arr3 = {2};
	     
		 assertArrayEquals(new int[]{1, 2, 3},
	                ThreeWayMergeWithoutDupes.merge(arr1, arr2, arr3));
	    
	 }
	 
	 @Test
	 public void testOnlyOneArrayNonEmpty() {	 
		 int[] arr1 = {1, 2, 3};	     
		 int[] arr2 = {};	     
		 int[] arr3 = {};
	     
		 assertArrayEquals(new int[]{1, 2, 3},
	                ThreeWayMergeWithoutDupes.merge(arr1, arr2, arr3));
	 }
	 
	 // Edge / boundary cases
	 @Test	    
	 public void testAllEmptyArrays() {	 
		 int[] arr1 = {};	     
		 int[] arr2 = {};	     
		 int[] arr3 = {};
	     
		 assertArrayEquals(new int[]{},
	                ThreeWayMergeWithoutDupes.merge(arr1, arr2, arr3));
	    
	 }
	    
	 @Test
	 public void testHeavyOverlapProducesUniqueElements() {	 
		 int[] arr1 = {1, 2, 3, 4, 5};	     
		 int[] arr2 = {1, 2, 3, 4, 5};	     
		 int[] arr3 = {1, 2, 3, 4, 5};
	     
		 assertArrayEquals(new int[]{1, 2, 3, 4, 5},
	                ThreeWayMergeWithoutDupes.merge(arr1, arr2, arr3));
	    
	 }
	    
	 @Test
	 public void testIntegerBoundaryValues() {	      
		 int[] arr1 = {Integer.MIN_VALUE, 0};	     
		 int[] arr2 = {Integer.MIN_VALUE, Integer.MAX_VALUE};	     
		 int[] arr3 = {0, Integer.MAX_VALUE};
	     
		 assertArrayEquals(new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE},
	                ThreeWayMergeWithoutDupes.merge(arr1, arr2, arr3));	    
	 }
	 
	 // Null / illegal-argument cases
	 @Test
	 public void testNonSortedFirstArray() {	 
		 int[] arr1 = {3, 4, 5, 1};	     
		 int[] arr2 = {1, 2, 3, 4, 5};	     
		 int[] arr3 = {1, 2, 3, 4, 5};
	     
		 assertThrows(IllegalArgumentException.class,
	                () -> ThreeWayMergeWithoutDupes.merge(arr1, arr2, arr3));	
	    
	 }
	 
	 @Test
	 public void testNonSortedSecondArray() {	 
		 int[] arr1 = {1, 2, 3, 4, 5};	     
		 int[] arr2 = {3, 4, 5, 1};	     
		 int[] arr3 = {1, 2, 3, 4, 5};
	     
		 assertThrows(IllegalArgumentException.class,
	                () -> ThreeWayMergeWithoutDupes.merge(arr1, arr2, arr3));	
	    
	 }
	 
	 @Test
	 public void testNonSortedThirdArray() {	 
		 int[] arr1 = {1, 2, 3, 4, 5};	     
		 int[] arr2 = {1, 2, 3, 4, 5};	     
		 int[] arr3 = {3, 4, 5, 1};
	     
		 assertThrows(IllegalArgumentException.class,
	                () -> ThreeWayMergeWithoutDupes.merge(arr1, arr2, arr3));	
	    
	 }
	 
	 @Test
	 public void testNonSortedArray() {	 
		 int[] arr1 = {1, 2, 3, 4, 5};	     
		 int[] arr2 = {1, 2, 3, 4, 5};	     
		 int[] arr3 = {3, 4, 2, 5};
	     
		 assertThrows(IllegalArgumentException.class,
	                () -> ThreeWayMergeWithoutDupes.merge(arr1, arr2, arr3));	
	    
	 }
	 
	 @Test
	 public void testNullFirstArrayThrows() {
		 assertThrows(IllegalArgumentException.class,
	                () -> ThreeWayMergeWithoutDupes.merge(null, new int[]{1}, new int[]{2}));	    
	 }
	 
	    
	 @Test
	 public void testNullSecondArrayThrows() {	 
		 assertThrows(IllegalArgumentException.class,
	                () -> ThreeWayMergeWithoutDupes.merge(new int[]{1}, null, new int[]{2}));	    
	 }
	 
	    
	 @Test
	 public void testNullThirdArrayThrows() {	 
		 assertThrows(IllegalArgumentException.class,
	                () -> ThreeWayMergeWithoutDupes.merge(new int[]{1}, new int[]{2}, null));	    
	 }
	 
	    
	 @Test
	 public void testAllNullArgumentsThrow() {
		 assertThrows(IllegalArgumentException.class,
	                () -> ThreeWayMergeWithoutDupes.merge(null, null, null));	    
	 }
}