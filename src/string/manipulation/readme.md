#Author: Sameer Khan

# Rolling Hash Algorithms (used in SubstringMatch problem)
## Overview  
We want to find occurrences of a pattern (keyword) in a string using efficient techniques.

## Technique #1: Brute Force (O(m × k))
- Slide a window of size k across the string of length m  
- Compare each substring with the target string  
- Time Complexity: O(m × k)  

## Technique #2: Rolling Hash (O(m + k))  
A more efficient approach using polynomial rolling hash.  

Core Idea: Maintain a hash of the current window and update it efficiently when sliding.  

#Hash Construction:  
Naïve Approach (Not Recommended)  
- Sum ASCII values of characters  
- Problem: High collision rate  
   Example: hash("abc") == hash("cab")  

Polynomial Hash (Recommended)  
Assign positional weights using a base:  
hash(abc)= 97 x B2 + 98 x B1 + 99 x B0  

#Choosing the Base (B)  
- Choose B > max ASCII value  
- Typically: B = 257  

Why?  
Prevents positional ambiguity (carry-over collisions)  

Bad Example (Base = 10)  
  hash(ab)=97 x 10 + 98=1068  

But: 98 = 9 x 10 + 8  

hash(ab) can be written as:  (97 x 10) + (9 x 10 + 8) = (97 + 9) x 10 + 8 = (106) x 10 + 8 = hash(106,8)  

So hash(97,98) = hash(106,8) // Collision occurs  

#Why Modulo (M)?  
We use modulo to:  
1. Prevent overflow  
2. Keep hash values bounded  

Typical choice: M=1,000,000,007  

Integer Limits  
- Signed 64-bit max: 2^63 − 1 ≈ 9.22 × 10^18  
- Unsigned 64-bit max: 2^64 − 1 ≈ 1.84 × 10^19  

#Overflow Analysis (Base = 257)  
Unsigned Limit: 257^x ≈ 1.84 × 10^19, x = log_base_257 (1.84 × 10^19) = 8  
Signed Limit: 257^x ≈ 9.22 × 10^18, x = log_base_257 (9.22 × 10^18) = 7.87  

Window size ≥ 9 risks overflow without modulo  

#Modular Arithmetic Rules  
 (a + b) mod M = ((a mod M) + (b mod M)) mod M  
 (a x b) mod M = ((a mod M) x (b mod M)) mod M  

#Multiplication Forms  
Fully Reduced  
(a x b x c) mod M=((a mod M) x (b mod M) x (c mod M)) mod M  

Stepwise (Preferred)  
((((a mod M) x b) mod M) x c) mod M  
Prevents overflow  

Addition Form  
(a + b + c) mod M = (((a mod M) + b) mod M + c) mod M  

##Worked Example  
Input  
- String: "abcd"  
- Window size: 3  
- Base: 257  
- Mod: 10^9 + 7  
- ASCII: a=97, b=98, c=99, d=100  

Hash("abc")   
(97 x 257^2 + 98 x 257 + 99) mod M  

val(a):  
(97 x 257 x 257) % M = (((97 % 1,000,000,007) x 257) % 1,000,000,007 x 257) % 1,000,000,007) = 6,406,753  

val(b):  
...  

val(c):  
...  

Contributions  
- a → 6,406,753  
- b → 25,186  
- c → 99  

hash("abc")=6,432,038  

#abc => bcd  
#Direct Computation: "bcd"  
- b → 6,472,802  
- c → 25,443  
- d → 100  

hash("bcd")=6,498,345  

#Rolling Hash Computation  
Step 1: Remove outgoing  
6,432,038 − 6,406,753 = 25,285  

If negative:  
(x + M) mod M  

Step 2: Multiply by base  
(25,285 x 257) mod M = 6,498,245  

Step 3: Add incoming  
(6,498,245 + 100) mod M = 6,498,345  

Final Formula  
H_next ​= ((H − outgoing + M) x B + incoming) mod M  

#Key Takeaways  
- Polynomial hashing avoids collisions better than simple sums  
- Base must exceed character range  
- Modulo is essential for:  
   > overflow safety  
   > bounded computation  
- Stepwise modular arithmetic is critical for correctness  
- Rolling hash reduces time from O(mk) → O(m)  

 