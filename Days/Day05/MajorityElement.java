/**
 * Problem: Majority Element (appears > N/2 times)
 * Link: https://leetcode.com/problems/majority-element/
 * 
 * Time Complexity: O(N)  -> Optimal single-pass Boyer-Moore Voting sequence.
 * Space Complexity: O(1) -> Achieved using constant scalar variables.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class MajorityElement {
    public int majorityElement(int[] nums) {

        // This is Boyer-Moore Vote Algorithm
        // We are doing in one pass only because a majority element with occurence > n / 2 is expected
        // If it was not guaranteed that a majority element exists always, then we would have to use 
        // multiple passes

        // Candidate is for storing current winning candidate
        int candidate = 0;
        int count = 0;

        for (int val : nums) {
            // if the overall difference is 0, then the previous candidate is no more useful, so update
            if (count == 0) {
                candidate = val;
            }

            if (candidate == val) {
             count++;
            }
            else {
             count--;
            }
        }

        return candidate;
        




        /*
        // Using a HashMap to store the frequency of each number
        // TC - O(N) & SC - O(N)
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int val : nums) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }

        // Iterating over the HashMap to identify the majority element
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > n / 2) {
                return entry.getKey();
            }
        }

        return -1;
        */


        /*
        // Sort the array and whichever element is at the middle will be majority element
        // TC - O(N logN) & SC - O(logN)
        Arrays.sort(nums);
        return nums[nums.length / 2];
        */
    }
}
