/*
Problem: Maximum Subarray
Link: https://leetcode.com/problems/maximum-subarray/

Time Complexity: O(N)  -> Single pass algorithm traversing the array once.
Space Complexity: O(1) -> IUses only two variables for keeping track of the sum.
*/

import java.util.*;

class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int maxSoFar = 0;

        for (int i = 0; i < nums.length; i++) {
            // add the current element to maxSoFar, so we can later check if the 
            // current subarray is producing negative sum
            maxSoFar += nums[i];

            // updating the maxSum
            maxSum = Math.max(maxSum, maxSoFar);

            // check if the current subarray sums up to negative sum as
            // it'll only affect negatively to later subarrays 
            if (maxSoFar < 0) {
                maxSoFar = 0;
            }
        }

        return maxSum;
    }
}