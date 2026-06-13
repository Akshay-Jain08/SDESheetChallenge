/**
 * Problem: 3Sum (Find all unique triplets that sum to zero)
 * Link: https://leetcode.com/problems/3sum/
 * 
 * Time Complexity: O(N^2) -> Quadratic traversal combining an outer loop with two-pointer scans.
 * Space Complexity: O(1)   -> In-place tracking ignoring system sorting stack frames.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashSet;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        
        // TC - O(N^2) & SC - O(1)
        // This approach uses Sorting + Two Pointer
        // For a fixed i, find the j and k pointers that satisfy the triplet condition
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            
            // If i is positive, then j and k would be positive too
            // Target can't be achieved with 3 positive elements    
            if (nums[i] > 0) break;

            // Skip the current element at i if it is equal to the previous element to prevent duplicates
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // Two pointer approach for every i
            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                
                // reduce k if sum is greater than target
                if (sum > 0) {
                    k--;
                }
                // increase j if sum is lesser than target
                else if (sum < 0) {
                    j++;
                }
                // if it is equal to target, add the current triplet
                else {
                    result.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k])));
                    j++;
                    k--;

                    // traverse j and k to the next non-similar element
                    while (j < k && nums[j] == nums[j - 1]) j++;
                    while (j < k && nums[k] == nums[k + 1]) k--;
                }
            }
        }

        return result;



        
        /*
        // TC - O(N^2) & SC - O(N)
        // This approach leverages outer HashSet for preventing duplicate triplets
        // Another HashSet inside the loop for the other two pointers j and k so that
        // we can computer target = 0 - (nums[i] + nums[j])

        // Sort the array first for preventing duplicates
        Arrays.sort(nums);
        int n = nums.length;

        List<List<Integer>> result = new ArrayList<>();
        HashSet<List<Integer>> trips = new HashSet<>();

        for (int i = 0; i < n; i++) {

            //  Stores the elements between i and j to check if the target has been computed previously
            HashSet<Integer> set = new HashSet<>();

            for (int j = i + 1; j < n; j++) {
                int target = 0 - (nums[i] + nums[j]);

                // Check if the set contains target
                // If it does, add the current triplet
                if (set.contains(target)) {
                    trips.add(new ArrayList<>(Arrays.asList(nums[i], target, nums[j])));
                }

                set.add(nums[j]);
            }
        }

        // HashSet trips prevented duplicate triplets
        result.addAll(trips);
        return result;

        */
    }
}
