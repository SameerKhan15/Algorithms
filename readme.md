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

**Problem3:**  
Given an string array, find the longest substring without repeating chars  

Ex1: {'p', 'a', 'p', 'c', 'd', 'f', 'g', 'a', 'p', 'a'}   
Output: {1,6}  

Ex2: {'p', 'p', 'p', 'd','l'}  
Output: {2,4}  

**Solution Description**  
- Flexible-Size Sliding Window  
- HashSet for detecting dupes  
- In while (true) loop  
-- Until (or unless) a dupe is detected, keep advancing the window, AND if the current window is > current-max-window, advance the current-max-window  
-- If a dupe is detected, the current window terminates.  If the terminated window is > current-max-window, reset the current-max-window accordingly    
--- In order to configure a new window, shrink the current window from the left pointer until the dupe char is eliminated.   
 
**Problem4:**  
Given an integer array where index location represents a tree and index value represents fruit type, find the max number of fruits in a contiguous set of trees, such that each of the k baskets do NOT hold more than single fruit type. For a given fruit type, the basket can hold as many fruits as possible.  

Ex1: {0,0,2,2,3,4,4,4,5}  
k=2    
Output: {0,3} //4 fruits  

Ex2: {0,1,2,2,3,4,4,4,5}  
k=2  
Output: {4,7} //4 fruits  

Ex3: {0,1,1,1,1,1,1,1,8}  
k=3  
Output: {0,8}  //8 fruits  

**Solution Description**  
- Flexible-Size Sliding Window  
- HashMap for fruit_type -> fruits count mapping  
- Initialize lPtr to 0 and rPtr to -1  
- Initialize maxWindowlPtr and maxWindowrPtr to 0  
- In while (true) loop  
-- IF the rPtr+1 exceeds the array length, terminate the loop  
-- IF the fruit-type already exists in the collection, increment the count. Compare the current running window with the max-window and if the former is greater, set the max-window to the current-window coordinates  
-- IF the fruit-type does NOT exists in the collection  
--- IF the collection already reached max fruit type limit, terminate the current window. Compare the current running window with the max-window and if the former is greater, set the max-window to the current-window coordinates. Additionally, shrink the current window from the left until the 'max fruit-type reached violation' is corrected.  Add the new fruit type to the collection.  
--- If the collection has NOT reached the limit, add the fruit to the collection.  Compare the current running window with the max-window and if the former is greater, set the max-window to the current-window coordinates  

 






