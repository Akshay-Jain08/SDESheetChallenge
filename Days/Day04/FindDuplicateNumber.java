/**
 * Problem: Find the Duplicate Number
 * Link: https://leetcode.com/problems/find-the-duplicate-number/
 * 
 * Time Complexity: O(N)  -> Linear time cycle detection and pointer synchronization.
 * Space Complexity: O(1) -> Pure pointer manipulation without extra memory.
 */

public class FindDuplicateNumber {
    public int findDuplicate(int[] nums) {

        // Floyd's cycle detection algorithm (applied in linked list)
        // How's it being used? Consider each index as i -> nums[i]
        // since there are [0, n] indices and [1, n] numbers
        // Example 1: [1, 3, 4, 2, 2]
        // 0 -> 1
        // 1 -> 3
        // 2 -> 4
        // 3 -> 2
        // 4 -> 2
        // Notice something 2 -> 4 -> 2 makes a cycle starting from the duplicate element
        int slow = 0;
        int fast = 0;

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        // the point where they meet is a random point in the cycle

        slow = 0;

        // it will run till they reach the starting point of the cycle
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;




        /*
        // Cyclic Sort Approach
        // TC - O(N) & SC - O(1)
        // Note: Good approach but not suitable for this problem because it modifies the given array.

        int i = 0;

        // If a number is not in its correct position and its correct position already contains the same number, that number must be the duplicate.
        while (i < nums.length) {
            // detected that the current number is not correct
            if (nums[i] != i + 1) {
                // correct Index of the current number
                int correctIndex = nums[i] - 1;

                // if the correctIndex contains some other value, then replace
                if (nums[i] != nums[correctIndex]) {
                    swap(nums, i, correctIndex);
                }   
                // but if it contains the same value, then it must be the duplicate one
                else {
                    return nums[i];
                }
            }
            else {
                i++;
            }
        }
        return -1;
        */
    }

    public void swap(int[] nums, int f, int s) {
        int temp = nums[f];
        nums[f] = nums[s];
        nums[s] = temp;
    }
}
