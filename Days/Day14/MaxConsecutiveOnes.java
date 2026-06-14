/**
 * Problem: Max Consecutive Ones
 * Link: https://leetcode.com/problems/max-consecutive-ones/
 * 
 * Time Complexity: O(N)  -> Single linear pass through the array.
 * Space Complexity: O(1) -> Uses a fixed number of primitive tracking counters.
 */

public class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        
        // TC - O(N) & SC - O(1)
        // result for storing the max consecutive ones
        // ones for storing the no of ones in a particular segment of ones
        int result = 0;
        int ones = 0;

        for (int val : nums) {
            // Increment the ones if a 1 is encountered
            if (val == 1) {
                ones++;
            }
            // Otherwise, reset the ones if the previous segment has ended
            else {
                ones = 0;
            }
            result = Math.max(result, ones);
        }

        return result;
    }
}
