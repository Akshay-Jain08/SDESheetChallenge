/**
 * Problem: Next Smaller Element
 * Link: https://www.geeksforgeeks.org/problems/immediate-smaller-element1142/1
 * 
 * Time Complexity: O(N) -> Monotonic stack processing pass followed by a fast two-pointer reversal list pass.
 * Space Complexity: O(N) -> Linear workspace storage for unmatched elements inside the stack container.
 */

import java.util.ArrayDeque;
import java.util.ArrayList;

public class NextSmallerElement {
    static ArrayList<Integer> nextSmallerEle(int[] arr) {
        
        // TC - O(N) & SC - O(N)
        // This approach uses a stack to get the next Smaller element
        // traversing backward as we need to check all the later elements
        // to determine next Smaller
        int n = arr.length;
        ArrayList<Integer> result = new ArrayList<>();
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        
        for (int i = n - 1; i >= 0; i--) {
            
            // pop until stack top becomes strictly smaller than current
            while (!stack.isEmpty() && arr[i] <= stack.peek()) {
                stack.pop();
            }
            
            // add the result to the result list and push the current element
            // onto the stack
            result.add(stack.isEmpty() ? -1 : stack.peek());
            stack.push(arr[i]);
        }
        
        // reverse the result list as we traversed backward
        // so the result is also in reverse order
        int left = 0;
        int right = n - 1;
        
        while (left < right) {
            swap(result, left, right);
            left++;
            right--;
        }
        
        return result;
    }
    
    // swap function
    public static void swap(ArrayList<Integer> list, int f, int s) {
        int temp = list.get(f);
        list.set(f, list.get(s));
        list.set(s, temp);
    }
}
