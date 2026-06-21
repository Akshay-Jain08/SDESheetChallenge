/**
 * Problem: Aggressive Cows
 * Link: https://www.geeksforgeeks.org/problems/aggressive-cows/1
 * 
 * Time Complexity: O(N log N + N log(max)) -> Array sorting followed by range binary search with linear checks.
 * Space Complexity: O(1)                    -> Pure in-place index pointer manipulation.
 */

import java.util.Arrays;

public class AggressiveCows {
    public int aggressiveCows(int[] stalls, int k) {
        
        // TC - O(N logN + N log(max)) & SC - O(1)
        // This uses binary search on answer
        
        // Sort the array because we don't have to maintain the order
        // instead we need the adjacent distance
        Arrays.sort(stalls);
        
        // the range is the distance from min distance = 0 to max distance
        int low = 0;
        int high = stalls[stalls.length - 1] - stalls[0];
        
        while (low <= high) {
            
            // mid as current dist
            int dist = low + (high - low) / 2;
            
            int cows = 1;
            int idx = 0;
            
            // check how many cows can be assigned with at least current distance
            for (int i = 1; i < stalls.length; i++) {
                
                if (stalls[i] - stalls[idx] >= dist) {
                    cows++;
                    idx = i;
                }
            }
            
            // If more cows are allotted than the target
            // that means we can further increase the distance to reduce the cows
            if (cows >= k) {
                low = dist + 1;
            }
            // else reduce the distance
            else {
                high = dist - 1;
            }
        }
        
        // We search for maximum feasible distance
        // After loop:
        // high → last feasible
        // low → first infeasible
        return high;
    }
}
