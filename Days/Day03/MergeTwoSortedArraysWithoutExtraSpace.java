/**
 * Problem: Merge Sorted Array (LeetCode Variant)
 * Link: https://leetcode.com/problems/merge-sorted-array/
 * 
 * Time Complexity: O(M + N) -> Single pass filling the array from behind.
 * Space Complexity: O(1)     -> In-place elements merging using trailing zero space.
 */

public class MergeTwoSortedArraysWithoutExtraSpace {
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        // Propagating backwards to avoid overwriting values that have not been computed
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        // not using i >= 0 inside while loop to avoid extra while loop clean-up for j
        // because once j is exhausted, we can terminate the loop as the nums1 would have been already in place
        // as per requirement
        while (j >= 0) {
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            }
            // else condition runs when either i is exhaused to nums2[j] is bigger
            else {
                nums1[k--] = nums2[j--];
            }
        }




        /*
        // Two-Pointer approach
        // TC - O(m + n)
        // SC - O(m + n)
        int i = 0;
        int j = 0;

        // Additional array to keep the elements and copy to nums1 at last
        int[] temp = new int[m + n];
        int idx = 0;

        while (i < m && j < n) {
            // when nums2[j] is equal or larger, so add it to temp
            if (nums1[i] <= nums2[j]) {
                temp[idx++] = nums1[i++];
            }
            // when nums1[i] is larger, so add it to temp
            else {
                temp[idx++] = nums2[j++];
            }
        }
        // clean for nums1 elements
        while (i < m) {
            temp[idx++] = nums1[i++];
        }

        clean up for nums2 elements
        while (j < n) {
            temp[idx++] = nums2[j++];
        }

        System.arraycopy(temp, 0, nums1, 0, temp.length);
        */


        // Brute Force approach would be to add all the elements from nums2 to nums1
        // from index m to m + n - 1
        // Sort it afterwards taking us to 
        // TC - O((m + n) log(m + n)) and SC - O(1)
    }
}
