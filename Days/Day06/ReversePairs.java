/**
 * Problem: Reverse Pairs
 * Link: https://leetcode.com/problems/reverse-pairs/
 * 
 * Time Complexity: O(N log N) -> Logarithmic divide-and-conquer tree bounds with linear comparisons.
 * Space Complexity: O(N)       -> Constant auxiliary temp array structure allocation.
 */


public class ReversePairs {
    public int reversePairs(int[] nums) {
        // Using the merge sort approach, kinda same what could be used in Inversion count
        // At every level of merge sort, the left array and right array are sorted
        // we can leverage this to compare left and right array to count the reverse pairs
        int n = nums.length;

        // using temp array to avoid space overhead at each recursive calls
        int[] temp = new int[n];

        return mergeSort(nums, temp, 0, n - 1);
    }

    public int mergeSort(int[] nums, int[] temp, int left, int right) {
        // Base Case
        if (left >= right) {
            return 0;
        }

        int mid = left + (right - left) / 2;

        // left array further solved
        int leftPairs = mergeSort(nums, temp, left, mid);
        // right array further solved
        int rightPairs = mergeSort(nums, temp, mid + 1, right);
        // current array pair solved using countPairs method
        int currentPairs = countPairs(nums, left, mid, right);

        merge(nums, temp, left, mid, right);

        return leftPairs + rightPairs + currentPairs;
    }

    public int countPairs(int[] nums, int left, int mid, int right) {
        int ans = 0;
        int i = left;

        for (int j = mid + 1; j <= right; j++) {
            // Fix j (right array) and iterate over i (left array) to find the first candidate that 
            // holds the reverse pair condition
            // all the elements from i to mid must hold the condition as well
            // also cast the comparing condition to long as 2 * nums[j] might cause overflow
            while (i <= mid && (long) nums[i] <= 2L * nums[j]) {
                i++;
            }
            ans += mid - i + 1;
        }

        return ans;
    }

    /*
    // This countPairs works same as the upper method, just the implementation is a bit different
    // It iterates over the left array (or fix the i) and maximizes in the right array (j)
    // up till the condition holds valid, then all the right array elements upto j are to be counted in the answer
    public int countPairs(int[] nums, int left, int mid, int right) {
        int ans = 0;
        int j = mid + 1;

        for (int i = left; i <= mid; i++) {
            while (j <= right && (long) nums[i] > 2L * nums[j]) {
                j++;
            }
            ans += j - (mid + 1);
        }
        return ans;
    }
    */

    // typical merge function
    public void merge(int[] nums, int[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            }
            else {
                temp[k++] = nums[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= right) {
            temp[k++] = nums[j++];
        }

        System.arraycopy(temp, left, nums, left, right - left + 1);
    }
}
