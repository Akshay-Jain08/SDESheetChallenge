/**
 * Problem: Remove Duplicates from Sorted Array
 * Link: https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 * 
 * Time Complexity: O(N)  -> Single linear pass to scan elements.
 * Space Complexity: O(1) -> Modifies the array in-place with no extra memory.
 */

public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        
        // TC - O(N) & SC - O(1)
        // Two-Pointer approach - One for keep the track of current space to filled in the array
        // Other to traverse the array

        // Edge Case - Though not necessary in this question
        if (nums.length == 0) return 0;

        int i = 1;

        for (int j = 1; j < nums.length; j++) {
            // When the current element is repeated
            if (nums[j] == nums[j - 1]) continue;
            
            nums[i++] = nums[j];
        }

        return i;
    }
}
