/**
 * Problem: Longest Consecutive Sequence
 * Link: https://leetcode.com/problems/longest-consecutive-sequence/
 * 
 * Time Complexity: O(N)  -> Elements are inserted and verified inside the HashSet in linear bounds.
 * Space Complexity: O(N) -> Memory allocated for the unique index HashSet container.
 */

import java.util.Arrays;
import java.util.HashSet;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        
        // Edge Case
        if (nums.length <= 0) return 0;

        // Set for adding all the unique elements
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int longestSequence = 1;

        for (int num : set) {
            // Whenever any element doesn't have a predecessor, that means
            // it is a potential starting point for the longest sequence
            if (!set.contains(num - 1)) {

                int sequence = 1;
                int start = num;

                // checking the length of the current sequence
                while (set.contains(start + 1)) {
                    sequence++;
                    start = start + 1;
                }
                longestSequence = Math.max(longestSequence, sequence);
                sequence = 1;
            }
        }

        return longestSequence;





        /*
        // This is a sorting approach and later counting the longest sequence
        // TC - O(N logN) & SC - O(1)

        int n = nums.length;
        // Edge Case 
        if (n <= 0) return 0;

        // Sorting the array
        Arrays.sort(nums);

        int longestSequence = 1;
        int sequence = 1;

        for (int i = 0; i < n - 1; i++) {
            // If the current element follows the previous sequence
            if (nums[i + 1] == nums[i] + 1) {
                sequence++;
            }

            // If the current element is equal to the last element, means 
            // it neither contributes to the previous sequence nor break it
            else if (nums[i + 1] == nums[i]) {
                continue;
            }

            // If the current element breaks the sequence
            else {
                sequence = 1;
            }
            longestSequence = Math.max(longestSequence, sequence);
        }
        return longestSequence;

        */
    }
}
