/**
 * Problem: Maximum of Minimum for Every Window
 * Link: https://www.geeksforgeeks.org/problems/maximum-of-minimum-for-every-window-size3453/1
 * 
 * Time Complexity: O(N) -> Linear boundary calculation scans combined with backward result propagation.
 * Space Complexity: O(N) -> Bounded by the explicit index stack tracking and array storage layers.
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public class MaxOfMinForEveryWindowSize {
    public ArrayList<Integer> maxOfMins(int[] arr) {
        
        // TC - O(N) & SC - O(N)
        // This approach uses a monotonic stack to find out the 
        // prevSmaller and nextSmaller values for every value in the arr
        // As for an element, previous and next smaller define
        // the maximum window where current element remains minimum
        int n = arr.length;
        
        int[] prevSmaller = new int[n];
        int[] nextSmaller = new int[n];
        
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        
        // Making the prevSmaller array
        for (int i = 0; i < n; i++) {
            
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            
            prevSmaller[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        
        // Clear the stack before moving on to making of nextSmaller array
        stack.clear();
        
        // Making the nextSmaller array
        for (int i = n - 1; i >= 0; i--) {
            
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            
            nextSmaller[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        
        // Initialize the result list with 0
        ArrayList<Integer> result = new ArrayList<>(Collections.nCopies(n, 0));
        
        // For every value in the arr, find the windowSize (the current value would
        // be minimum within that window)
        for (int i = 0; i < n; i++) {
            int windowSize = nextSmaller[i] - prevSmaller[i] - 1;
            
            result.set(windowSize - 1, Math.max(result.get(windowSize - 1), arr[i]));
        }
        
        // not every window size receivesa direct answer, so propagate
        // larger-window answers backward
        // As the window size increases, the max of min would decrease
        // so, we must compare the values in backwards direction to be able
        // to maintain the result list in a non-increasing manner
        for (int i = n - 2; i >= 0; i--) {
            result.set(i, Math.max(result.get(i), result.get(i + 1)));
        }
        
        return result;
        
        
        
        /*
        TC - O(N^3) or O(N^2) & SC - O(N)
        
        Brute-Force:
        For every possible window size from 1 to N:
            - Compute the minimum of every sliding window of that size
            - Among all those minimums, pick the maximum
            - Store it in the result
        
        Complexities:
        - O(N^3):
            If every window minimum is computed independently by scanning
            all elements inside the window.
        
        - O(N^2):
            If sliding window minimum itself is optimized to O(N)
            (using deque / preprocessing), repeated for all N window sizes.
        */
    }
}
