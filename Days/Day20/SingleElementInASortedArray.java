/**
 * Problem: Single Element in a Sorted Array
 * Link: https://leetcode.com/problems/single-element-in-a-sorted-array/
 * 
 * Time Complexity: O(log N) -> Logarithmic binary search scale cuts.
 * Space Complexity: O(1)    -> Constant scalar workspace allocation.
 */

import java.util.Arrays;

public class SingleElementInASortedArray {
    public int singleNonDuplicate(int[] nums) {
        
        // TC - O(logN) & SC - O(1)
        // This is just a cleaner implementation of the below approach
        // that force the mid to be at even index always rather than checking all
        // conditions explicitly
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            int mid = left + (right - left) / 2;

            // If the mid is odd, force it to be even
            if ((mid & 1) == 1) {
                mid--;
            }

            // If nums[mid] == nums[mid + 1],
            // then all elements till mid+1 follow normal pairing,
            // therefore the single element must lie in the right half
            if (nums[mid] == nums[mid + 1]) {
                left = mid + 2;
            }
            // Pair structure breaks here,
            // therefore the single element lies at or before mid
            else {
                right = mid;
            }
        }
        // condition breaks when left == right which is the spot for the required candidate
        return nums[left];





        /*
        // TC -  O(logN) & SC - O(1)
        // This approach uses the position of pairs to check whether the single element
        // is on the left half of right half
        // Before single element, the first element of the pair lie at the even index
        // After single element, the first element of the pair lie at the odd index

        int n = nums.length;
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Checks if the pair indices are correct, that means the single element is yet to come
            // move to right half
            if ((mid < n - 1 && nums[mid] == nums[mid + 1] && mid % 2 == 0) || 
                (mid > 0 && nums[mid] == nums[mid - 1] && mid % 2 == 1)) {

                left = mid + 1;
            }

            // If the pair indices are not correct, move to left half
            else if ((mid < n - 1 && nums[mid] == nums[mid + 1] && mid % 2 == 1) || 
                    (mid > 0 && nums[mid] == nums[mid - 1] && mid % 2 == 0)) {

                right = mid - 1;
            }
            
            // return the value if the correct value is encountered
            else {
                return nums[mid];
            }
        }

        return -1;
        */




        /*
        // TC - O(N) & SC - O(1)
        // This approach leverages a property of XOR operator
        // XOR operator cancels out pairs, so we are left with single element at last
        int ans = 0;

        for (int num : nums) {
            ans ^= num;
        }

        return ans;

        */
    }
}