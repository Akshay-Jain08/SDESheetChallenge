/**
 * Problem: Implement Queue using Stacks
 * Link: https://leetcode.com/problems/implement-queue-using-stacks/
 * 
 * Time Complexity: O(1) Push, Amortized O(1) Pop/Peek.
 * Space Complexity: O(N)     -> Element storage distributed across two stack arrays.
 */

import java.util.ArrayDeque;

class MyQueue {

    // TC - Amortized O(1) & SC - O(N)

    ArrayDeque<Integer> s1;
    ArrayDeque<Integer> s2;

    public MyQueue() {
        s1 = new ArrayDeque<>();
        s2 = new ArrayDeque<>();
    }
    
    public void push(int x) {
        s1.push(x);
    }
    
    public int pop() {
        if (!s2.isEmpty()) {
            return s2.pop();
        }
        else {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
            return s2.pop();
        }
    }
    
    public int peek() {
        if (!s2.isEmpty()) {
            return s2.peek();
        }
        else {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
            return s2.peek();
        }
    }
    
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}