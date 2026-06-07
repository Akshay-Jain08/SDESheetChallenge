/**
 * Problem: Two Sum
 * Link: https://leetcode.com/problems/two-sum/
 * 
 * Time Complexity: O(N)  -> Optimal single-pass map lookup.
 * Space Complexity: O(N) -> HashMap allocations to track traversed pairs.
 */

import java.util.HashMap;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            // Check if the companion for the current element has been traversed already
            Integer idx = map.get(target - nums[i]);

            // Return when companion found
            if (idx != null) {
                return new int[] {idx, i};
            }

            map.put(nums[i], i);
        }

        return new int[] {-1, -1};
    }

    // Semi-Optimized Approach
    // TC - O(N logN) & SC - O(N)
    // Sorting + Two-Pointer from both the ends
    // Move left Pointer when sum is below target
    // Move right Pointer when sum is above target


    // Brute Force Approach
    // TC - O(N^2) & SC - O(1)
    // For every i, check every j
}
