/**
 * Problem: Kth Largest Element in an Array
 * Link: https://leetcode.com/problems/kth-largest-element-in-an-array/
 * 
 * Time Complexity: O(N log K) -> Linear element traversal with a logarithmic heap insertion ceiling.
 * Space Complexity: O(K)       -> Memory storage bound restricted to a maximum size of K items.
 */

import java.util.Arrays;
import java.util.PriorityQueue;

public class KthLargestElementInAnArray {
    public int findKthLargest(int[] nums, int k) {
        
        // TC - O(N logK) & SC - O(K)
        // Uses a minHeap of size K to maintain K largest elements
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {
            minHeap.offer(num);

            // If the size exceeds k, then remove the minimum element
            if (minHeap.size() > k) minHeap.poll();
        }

        // first element would be the kth largest in the array
        // as we are maintaining the minHeap
        return minHeap.peek();



        /*
        // TC - O(N logN) & SC - O(logN)
        // Sort the array (non-decreasing order)
        Arrays.sort(nums);

        // return the nums.length - kth element
        return nums[nums.length - k];
        */
    }
}
