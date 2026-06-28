/**
 * Problem: Largest Rectangle in Histogram
 * Link: https://leetcode.com/problems/largest-rectangle-in-histogram/
 * 
 * Time Complexity: O(N) -> Combined single-pass linear boundary check via index popping.
 * Space Complexity: O(N) -> Monotonic index stack storage footprint.
 */

import java.util.ArrayDeque;

public class LargestRectangeInHistogram {
    public int largestRectangleArea(int[] heights) {
        
        // TC - O(N) & SC - O(N)
        // This approach computes the nse and pse in the single pass
        // We maintain a monotonic strictly increasing stack, and every previous element 
        // is pse to the current element
        // Whenever we come across an element which doesn't follow the monotonic invariant,
        // we can say that it's nse to the current top of the stack and which ever follows the 
        // same condition below it
        int n = heights.length;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int ans = 0;

        for (int i = 0; i < n; i++) {
            
            // Monotonic invariant is broken, nse found
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                // current top, for which the current element is nse
                int idx = stack.pop();
                // current nse
                int nse = i;
                // current pse (-1 if stack is empty as none of the elements before it are smaller)
                int pse = stack.isEmpty() ? -1 : stack.peek();

                ans = Math.max(ans, (nse - pse - 1) * heights[idx]);
            }

            stack.push(i);
        }

        // clean up for remaining elements in the stack
        // nse for these is always n
        while (!stack.isEmpty()) {
            int idx = stack.pop();
            int nse = n;
            int pse = stack.isEmpty() ? -1 : stack.peek();

            ans = Math.max(ans, (nse - pse - 1) * heights[idx]);
        }

        return ans;


        /*
        // TC - O(N) & SC - O(N)
        The Below approach uses 2 passes for nse and pse
        and one pass for the area computation
        We can optimize it by computing pse while traversal and computing nse beforehand
        Though, we need to try to optimize it to single pass
        */




        /*
        // TC - O(N) & SC - O(N)
        // We pre-compute next smaller element and previous smaller element
        // a bar can contribute to the area within its nse and pse boundary
        // so, the formula for area comes down to (nse - pse - 1) * height
        int n = heights.length;
        int[] pse = new int[n];
        int[] nse = new int[n];

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        // Pre-computing pse
        for (int i = 0; i < n; i++) {

            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }

            pse[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        // clear the stack before computing nse
        stack.clear();

        // Pre-computing nse
        for (int i = n - 1; i >= 0; i--) {

            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }

            nse[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        int ans = 0;
        // for every index, compute and height and store the max
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, (nse[i] - pse[i] - 1) * heights[i]);
        }

        return ans;
        */
    }
}
