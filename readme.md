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

**Problem5:**  
Find anagrams in a given string.  

Ex1: s = {'c','b','a','e','b','a','b','a','c','d'}  //target string
	 a = {a,b,c} //anagram chars    
Output: [0,2], [6,8] //all index boundaries of the anagrams in string s 

Ex2: s = {'c','b','a','b','c','e','b','z','b','a','c','d'}  
	 a = {a,c,b}  
Output: [0,2], [2,4], [8,10]  

**Solution Description**  
- Flexible-Size Sliding Window  
- A HashSet for storing anagram characters
- A HashSet for storing anagram characters found in the running window  
- A List containing boundaries of all the anagrams found  
- Initialize lPtr to 0 and rPtr to -1  
- In while (true) loop  
-- IF the rPtr+1 exceeds the array length, terminate the loop  
-- IF chars[rPtr] has a match in the anagram set  
--- IF the running window set does NOT contain the char, add it to the set   
--- IF the running window set does contain the char, we have a dupe. shrink the window from the left side (along with removing from the current window HashSet) until the dupe is resolved.  
--- IF the current window set is equal in size to anagram set, we have a match and therefore record it. Increment the left-pointer (and remove the char from the current window set)  
-- IF the chars[rPtr] does NOT have a match, terminate the current window. Initialize a new current window HashSet, and advance the lPtr to rPtr+1 (if not out of bound)  
--- //we are starting a brand new window here  

**Problem6:**  
Minimum Window Substring  
Given two strings s and t, return min window substring of s such that every char in t (including duplicates) are present in s. If no substring match, return ""  

Ex1: s = {ADOBECODEBANC}  
     t = {ABC}  
Output: {BANC}  

**Solution Description**  
 
Variables  
- minWindowLPtr, minWindowRPtr  
- currWindowLPtr, currWindowRPtr  
- reqdCharsCount // sum of char instances across string t  
- currWindowCharsCount  
- Map<Character, Integer> for s and t  
- In while (true) loop  
-- IF currWindowRPtr == s.length, break the loop  
-- incr currWindowRPtr  
-- Update s map for s[currWindowRPtr] char, if exists in map t  
-- Update currWindowCharsCount if s[currWindowRPtr] exists in map t AND the current count (in map s) is < the required count (in map t)  
-- Perform window match check, by comparing equality between currWindowRPtr and reqdCharsCount  
-- IF match occurs, perform the following logic  
--- If the current (matched) window is < minWindow, replace minWindow coodrinates with the current window values  
--- We are done with the current window. Next step is to setup the left-pointer to start a new window. This is a two-step process  
--- Initial shrink of the window (from the left) by char 1  
--- If the s[currWindowlPtr] count (in map s) is <= the required count (in map t), decrement the currWindowCharsCount because after shifting the left-pointer, the count will fall below the required threshold  
--- Decr the counter associated with s[currWindowlPtr] by 1, in map s  
--- Increment currWindowlPtr  
--- Since this shrink window can result in a match, perform the window match check (exactly as described above)  
--- Perform subsequent shrink of the window, to remove any "bloat"  
--- Example1: {.DOBEC} is the string after initial shrink, we can shrink the window by skipping D and O, since we do not have these chars in the set t  
--- Example2: {.AABEC} is the string after initial shrink, we can shrink the window by skipping the 2nd A too, because we only need single A  
--- Subsequent shrinking logic  
--- If s[currWindowlPtr] exists in map t and its count is <= required count, we canNOT shrink the window. Break the loop  
--- If s[currWindowlPtr] exists in map t and its count is > required count, decr the count in map s and incr currWindowlPtr  
--- If s[currWindowlPtr] does NOT exists in map t, incr currWindowlPtr  
--- Check for the match  
--- Examples  
--- {A[0]DOBEC[5] 0DEBANC} // current window 0 - 5 after an expansion    
--- We have a match#1. Record this as min match (if applicable)  
--- Shrink the left-pointer by 1, as a prep to setup the left-pointer for a new (sliding) window  
--- {AD[1]OBEC[5] ODEBANC} // current window 1 - 5 after the initial shrink  
...  
[Further details of the algorithm](https://www.evernote.com/shard/s80/sh/2d5ed7a9-1538-4f22-9e65-ae90fddac546/Y4GynS0HvJwO3EnsuGjkf331jnKWr3Mq-dnfYabk9WxQc8kEwpm236kuoA)   







