/**
 * Problem: Median of Two Sorted Arrays
 * Link: https://leetcode.com/problems/median-of-two-sorted-arrays/
 * 
 * Time Complexity: O(log(min(M, N))) -> Binary search over the smaller array indices partition space.
 * Space Complexity: O(1)              -> Pure constant reference matching with zero heap copy overhead.
 */

public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // TC - O(log(min(M, N))) & SC - O(1)
        // Partition Binary Search
        // This approach iteratively breaks the two arrays in two parts based on the required size
        // to find the median. As for the median, half of the element would be in the left half
        // and half would be in the right half
        // That is, we need (m + n + 1) / 2 elements in the left half and rest in the right half
        // So, we apply binary search to look for the partition position and check whether the 
        // partition is valid or not

        // apply BS on smaller array
        if (nums2.length < nums1.length) return findMedianSortedArrays(nums2, nums1);

        int m = nums1.length;
        int n = nums2.length;

        // Apply binary search on nums1 and calculate cut2 with the help of cut1
        int low = 0;
        int high = m;

        while (low <= high) {
            // cut1 will be the mid-point on the nums1
            int cut1 = low + (high - low) / 2;
            // cut2 will be the remaining from the required elements
            int cut2 = ((m + n + 1) / 2) - cut1;

            // calculate left and right for every partition
            int left1 = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int left2 = cut2 == 0 ? Integer.MIN_VALUE : nums2[cut2 - 1];
            int right1 = cut1 == m ? Integer.MAX_VALUE : nums1[cut1];
            int right2 = cut2 == n ? Integer.MAX_VALUE : nums2[cut2];

            // Check if the current partition is valid, then return the median accordingly
            if (left1 <= right2 && left2 <= right1) {
                if ((m + n) % 2 == 0) {
                    return ((double)Math.max(left1,left2) + Math.min(right1,right2)) / 2;
                }
                else {
                    return Math.max(left1, left2);
                }
            }
            // If the condition violates, apply binary search accordingly
            else if (left1 > right2) {
                high = cut1 - 1;
            }
            else {
                low = cut1 + 1;
            }
        }
        return 0.0;



        /*
        // TC -  O(M + N) & SC - O(M + N)
        int m = nums1.length; 
        int n = nums2.length;

        int[] arr = new int[m + n];

        int i = 0;
        int j = 0;

        int idx = 0;

        // Making of the merged array
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                arr[idx++] = nums1[i++];
            }
            else {
                arr[idx++] = nums2[j++];
            }
        }

        while (i < m) {
            arr[idx++] = nums1[i++];
        }

        while (j < n) {
            arr[idx++] = nums2[j++];
        }

        // return the median
        int k = arr.length;
        if (k % 2 == 1) {
            return arr[k / 2];
        }
        else {
            return (double) (arr[k / 2] + arr[(k - 1) / 2]) / 2;
        }
        */
    }
}