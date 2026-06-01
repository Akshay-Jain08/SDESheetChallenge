/**
 * Problem: Sort Colors
 * Link: https://leetcode.com/problems/sort-colors/
 * 
 * Time Complexity: O(N)  -> Two-pass algorithm (One pass to count, one pass to overwrite).
 * Space Complexity: O(1) -> In-place modification using only 3 integer tracking variables.
 */

import java.util.*;

public class SortColors {
    public void sortColors(int[] nums) {
        // Using a counting sort approach to replace the given array in-place
        int zeros = 0;
        int ones = 0;
        int twos = 0;

        // counting all the zeros, ones, and twos
        for (int val : nums) {
            if (val == 0) zeros++;
            else if (val == 1) ones++;
            else twos++;
        }

        // Updating the array in place with the help of counters
        for (int i = 0; i < nums.length; i++) {
            if (zeros > 0) {
                nums[i] = 0;
                zeros--;
            }
            else if (ones > 0) {
                nums[i] = 1;
                ones--;
            }
            else {
                nums[i] = 2;
            }
        }
    }
}
