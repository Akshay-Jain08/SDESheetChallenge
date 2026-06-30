/**
 * Problem: Online Stock Span
 * Link: https://www.geeksforgeeks.org/problems/stock-span-problem-1587115621/1
 * 
 * Time Complexity: O(N) -> Single linear scan with constant-time amortised index popping.
 * Space Complexity: O(N) -> Monotonic stack storage footprint tracking previous greater locations.
 */

import java.util.ArrayDeque;
import java.util.ArrayList;

public class StockSpanProblem {
    public ArrayList<Integer> calculateSpan(int[] arr) {
        
        // TC - O(N) & SC - O(N)
        // This approach uses a monotonic stack to find the 
        // previous greater element for every price in arr
        // As no. of elements from the current price till the pge (exclusive)
        // we will have the stock span for the current price
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        ArrayList<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < arr.length; i++) {
            
            // Remove all previous prices
            // which cannot become PGE
            // for current price
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            
            // previous greater element
            int pge = stack.isEmpty() ? -1 : stack.peek();
            
            // distance between current index and nearest previous greater
            // gives the span for the current element
            result.add(i - pge);
            
            stack.push(i);
        }
        
        return result;
        
        
        
        
        /*
        TC - O(N^2) & SC - O(1) excluding result
        Brute-Force approach would be to go backwards for every elements
        to find out how many elements prior to the current element
        are lesser or equal to the current element
        */
    }
}
