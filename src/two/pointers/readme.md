# Two-Pointers Algorithm  
## Trapping Rain Water - Algorithm Description    
### Overview  
The calculateTrappedWater method solves the classic Trapping Rain Water problem: given an array of non-negative integers representing wall heights in an elevation map, compute how much rainwater can be trapped between the walls after it rains.  

Class: TrappingRainWater  
Method: public static long calculateTrappedWater(int[] wallsHeight)  
Time Complexity: O(n)  
Space Complexity: O(1)  

### Problem Intuition  
Imagine a histogram of walls viewed from the side. Water settles in the valleys between taller walls on either side. For any given position i, the water level above it is determined by:  

water at i = min(maxWallToTheLeft, maxWallToTheRight) - wallsHeight[i]  

If this value is negative (i.e. the wall is taller than the water level), no water sits on top of it.  

Example — {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1} → 6 units trapped.  

### Algorithm: Two-Pointer Approach  
Rather than precomputing left-max and right-max arrays (which would require O(n) space), this implementation uses two inward-moving pointers and tracks running maximums. The key insight is:  

If wallsHeight[lPtr] <= wallsHeight[rPtr], the water level at lPtr is fully determined by maxLeftVal — because we already know the right side has at least one wall as tall as the current left wall, so the right side will never be the limiting factor for the left pointer's water level. The symmetric argument applies when the right wall is shorter.  

#### Pointer Movement Rules  
##### Condition, Action, Reason  
wallsHeight[lPtr] <= wallsHeight[rPtr], Process lPtr and then advance it right, Right boundary is guaranteed to be ≥ current left; left max is the bottleneck  

wallsHeight[lPtr] > wallsHeight[rPtr], Process rPtr and then advance it left, Left boundary is guaranteed to be > current right; right max is the bottleneck  

lPtr >= rPtr, Stop, Pointers have met; all positions processed  

#### Boundary Wall Exclusion  
The two boundary walls (index 0 and index wallsHeight.length - 1) never contribute water — they are the outermost enclosing walls and water cannot be retained beyond them. This is enforced by the lPtr != 0 and rPtr != 0 guards before accumulating water.  


       
