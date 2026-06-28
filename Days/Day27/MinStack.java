/**
 * Problem: Min Stack (Design Stack with Constant-Time Min Lookup)
 * Link: https://leetcode.com/problems/min-stack/
 * 
 * Time Complexity: O(1) For all core tracking operations.
 * Space Complexity: O(N) Space-optimized single stack data structure wrapper.
 */

import java.util.ArrayDeque;

class MinStack {

    // TC - O(1) & SC - O(N) 
    // Optimized MinStack using a single stack + encoding technique 
    // 
    // Instead of storing {value, minSoFar} pairs, 
    // we store only one value in stack. 
    // 
    // Idea: 
    // Whenever a new minimum appears: 
    // encoded = 2 * newMin - oldMin 
    // 
    // Push encoded instead of actual value and update min. 
    // 
    // Important invariant: 
    // encoded < currentMin 
    // 
    // This helps identify whether the top is: 
    // - a normal value 
    // - an encoded value

    ArrayDeque<Long> stack;
    long min;

    // Constructor to initialize the members
    public MinStack() {
        stack = new ArrayDeque<>();
        min = Long.MAX_VALUE;
    }
    
    public void push(int value) {
        long val = value;

        // First element: \
        // push directly and initialize min
        if (stack.isEmpty()) {
            stack.push(val);
            min = val;
        }
        else {
            // Normal value: min remains unchanged
            if (val < min) {
                stack.push(2L * val - min);
                min = val;
            }
            // New minimum: store encoded value and update current min
            else {
                stack.push(val);
            }
        }
    }
    
    public void pop() {
        long x = stack.pop();

        // If top < min: 
        // top is encoded 
        // encoded = 2 * newMin - oldMin 
        // => oldMin = 2 * newMin - encoded 
        // current min = newMin
        if (x < min) {
            min = 2 * min - x;
        }

        // Restore state after empty stack
        if (stack.isEmpty()) {
            min = Long.MAX_VALUE;
        }
    }
    
    public int top() {
        long x = stack.peek();

        // Encoded value: 
        // actual top is current min
        if (x < min) {
            return (int) min;
        }
        // Normal value
        return (int) x;
    }
    
    public int getMin() {
        return (int) min;
    }
}




/*
class MinStack {

    // TC - O(1) & SC- O(N)
    // This approach uses a standard stack to store the value and minSoFar 
    // We can use standard stack operations to retrieve value as well as min in O(1)

    ArrayDeque<int[]> stack;

    // Constructor to initialize the stack
    public MinStack() {
        stack = new ArrayDeque<>();
    }
    
    public void push(int value) {
        // If the stack is empty, then the current value is set to min
        // else we have to compare between the current value and prev min
        int min = stack.isEmpty() ? value : Math.min(value, getMin());

        // store the value and min to the stack
        stack.push(new int[] {value, min});
    }
    
    public void pop() {
        stack.pop();
    }
    
    public int top() {
        return stack.peek()[0];
    }
    
    public int getMin() {
        return stack.peek()[1];
    }
}
*/
