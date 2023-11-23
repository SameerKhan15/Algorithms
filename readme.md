# Algorithms & DataStructures
## Sliding Window
### Description
- Technique for reducing the complexity of algorithms  
- Main idea is to covert two nested loops into a single loop  
- This technique helps to reduce the complexity from O(n^2) to O(n)  
- Fixed-Size Sliding Window  
- Flexible-Size Sliding Window  

[Sliding Window Technique](https://www.youtube.com/watch?v=MK-NZ4hN7rs&t=760s)  
[Sliding Window Technique: A Comprehensive Guide](https://leetcode.com/discuss/interview-question/3722472/mastering-sliding-window-technique-a-comprehensive-guide)  

### Problem Types suitable for this pattern  
- Things we iterate over sequentially  
-- Contiguous sequence of elements  
-- Strings, Arrays, Linked List  

- Min, Max, Longest, Shortest, Contained  
-- Maybe we need to calculate and record some value  

- Running Average  
- Formulating Adjacent Pairs  
- Target Value Identification  
- Longest/Shortest/Most Optimal Sequence  

**The most important thing to remember is that the compute is going to be sequential and contiguous.**  

###Problems###   
**Problem1:**  
Given an integer array, find the max sum across contiguous elements  

Ex1: {0, 1, -4, 5, 6, -2, 2}  
Output: 11 {5, 6}  

Ex2: {-1, 4, 3, 6, 9, -9, 10, 11, -2}  
Output: 22 {4, 3, 6, 9}  

Ex3: {}
Output: throws "invalid input" exception  

**Solution Description**  
- Flexible-Size Sliding Window  
- Initialize both pointers to index[0] element  
- Initialize maxSum var to the value of index[0]  
- Initialize currSum var to the value of maxSum  
- In a while (true) loop  
-- Check if the right-pointer can be incremented 
--- If false, break the loop  
--- If true, increment the pointer    
---- If the increased window adds to OR keeps the currSum, add the new element to the currSum var  
----- Since the array can contain negative integers, check if shrinking the left-pointer increases the currSum  
------ If true, move the left-pointer to the right
----- If the (newly computed) currSum is > maxSum, set maxSum to currSum  
---- If the increased window reduces the currSum, terminate the current window by advancing the left-pointer to the position of right-pointer and setting the currSum to array[right-pointer]  
//Note that array[right-pointer] canNOT be > maxSum, because this value reduced the previous window currSum. Whenever the right-pointer is advanced, the previous currSum value has already gone through the comparison with maxSum var  

This continues until the loop termination condition  

Performance: O(n)  

**Problem2:**  
Given an string array, find the smallest substring that contains k distinct chars  

Ex1: {'p','a','p','c','d','f','g','a','p','a'}  
k=3  
Output: {1,3}  

Ex2: {'p','p','p','c','p','f','g','a','p','a'} 
k=3  
Output: {3,5}  

Ex3: {'p','p','p','c','p','f','g','a','p','a'} 
k=0  
Output: {0,-1}    

**Solution Description**  
- Flexible-Size Sliding Window  
- Initialize HashMap that contains chars and their associated counts for the running window    
- Initialize smallestWindowleftPtr to 0 and smallestWindowrightPtr to -1  
-- //smallestWindowrightPtr = -1 is a marker for the condition where k distinct chars were NOT found  
- Initialize left-pointer to 0 and right-pointer to -1  
-- //right-pointer is initialized to -1 so that in the while (true) loop, we are able to start from (0,0) window  
- In a while (true) loop  
-- Check if the right-pointer can be incremented  
-- If false, break the loop  
-- If true, increment the pointer  
-- Add right-pointer index element to HashMap 
-- Check if there are k distinct elements in the Map  
--- If true, trim the window from the left and if the window size is less than the current minWindow, replace minWindow with the current one    
---- Advance the left-pointer, and ensure to reflect this in the HashMap, by decrementing the counter (if > 1) or removing the char (if count is 1)  

Return of result with window (0,-1) indicates no match found  
Return of result with window (>=0, >=0) contains window pointers for the smallest window that contains the k distinct chars  

 
        


