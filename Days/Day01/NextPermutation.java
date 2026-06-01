/*
Problem: Next Permutation
Link: https://leetcode.com/problems/next-permutation/description/

Time Complexity: O(N)  -> Single Pass for pivot tracking, swapping, and reversing
Space Complexity: O(1) -> In-place Modification
*/

import java.util.*;

public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        // Edge Case
        if (n == 1) return;

        int pivot = -1;

        for (int i = n - 2; i >= 0; i--) {
            // Pivot detection
            if (nums[i] < nums[i + 1]) {
                pivot = i;
                break;
            }
        }

        // When the array is in non-increasing order
        if (pivot == -1) {
            reverse(nums, 0, n - 1);
            return;
        }

        // Swapping the pivot with next smallest larger element than pivot
        for (int j = n - 1; j > pivot; j--) {
            if (nums[j] > nums[pivot]) {
                swap(nums, j, pivot);
                break;
            }
        }

        // reverse the array after pivot
        reverse(nums, pivot + 1, n - 1);
    }

    public void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}
