/**
 * Problem: Sliding Window Maximum
 * Link: https://leetcode.com/problems/sliding-window-maximum/
 * 
 * Time Complexity: O(N) -> Combined single-pass linear deque sorting sweep.
 * Space Complexity: O(K) -> Sliding window index constraint workspace memory.
 */

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        
        // TC - O(N) & SC - O(K)
        // This approach uses a Double ended queue
        // Deque stores indices in decreasing order of values.
        // Front always contains maximum of current window.
        // and access them from the another end
        // front → maximum of current window
        // back → weakest candidates

        // Base Case
        if (k == 1) return nums;


        int n = nums.length;
        Deque<Integer> dq = new ArrayDeque<>();

        // For the window size of k, the result would be of size n - k + 1
        int[] res = new int[n - k + 1];
        int idx = 0;

        for (int i = 0; i < n; i++) {
            
            // Keep removing all the values from the stack
            // which doesn't follow the monotonic invariant for the current element
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast();
            }

            // Insert the current index
            dq.offerLast(i);

            // Remove any element from the front side if it is out of bounds for 
            // the current window (idx is nothing but the current window start)
            while (!dq.isEmpty() && dq.peekFirst() < idx) {
                dq.pollFirst();
            }

            // Once we have explored the first window, keep adding to the 
            // result array onwards
            if (i >= k - 1) {
                res[idx++] = nums[dq.peekFirst()];
            }
        }

        return res;




        /*
        // TC - O(N logK) & SC - O(K)
        // This approach uses a priority queue to store index and its corresponding value
        // we can get the max element for any window in constant time

        // Base Case
        if (k == 1) return nums;

        int n = nums.length;
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));

        // For the window size k, the resulting array
        // would be of size n - k + 1
        int[] res = new int[n - k + 1];
        int idx = 0;

        for (int i = 0; i < n; i++) {

            // insert every index to the maxHeap
            maxHeap.offer(new int[] {i, nums[i]});

            // Once the first window size is completed in the maxHeap
            if (i >= k - 1) {
                // retrieve the max on the current window
                int[] temp = maxHeap.peek();

                // idx is nothing but current windowStart
                // so whichever elements in maxHeap are out of the boundary of the current window start
                // remove them right now so they won't be included in the next window
                while (idx >= maxHeap.peek()[0]) {
                    maxHeap.poll();
                }

                // Store the result
                res[idx++] = temp[1];
            }
        }

        return res;
        */
    }
}
