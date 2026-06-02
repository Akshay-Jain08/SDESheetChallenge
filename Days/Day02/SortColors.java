/**
 * Problem: Sort Colors
 * Link: https://leetcode.com/problems/sort-colors/
 * 
 * Time Complexity: O(N)  -> One-Pass algorithm iterating through the array once. (Dutch National Flag Algorithm)
 * Space Complexity: O(1) -> In-place modification using only 3 integer tracking variables.
 */

import java.util.*;

public class SortColors {
    public void sortColors(int[] nums) {
        int n = nums.length;

        int low = 0; // to maintain 0's
        int mid = 0; // to iterate through the array
        int high = n - 1; // to maintain 2's

        // mid <= high because only mid is moving, so the point when mid reaches high,
        // the array is sorted
        while (mid <= high) {
            // when mid reaches at a 0 value, swap with low and move both forward
            // because the unknown element from low is most likely 1
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            }
            // when mid reaches at a 2 value, swap with high and move only high
            // because the unknown element from high can be anything
            else if (nums[mid] == 2) {
                swap(nums, mid, high);
                high--;
            }
            // skip 1s
            else {
                mid++;
            }
        }



        // Counting Sort Approach
        // TC - O(N)
        // SC - O(1)
        /*
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
        */
    }

    public void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }
}
