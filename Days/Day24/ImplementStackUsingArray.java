/**
 * Problem: Implement Stack Using Array
 * Link: https://www.geeksforgeeks.org/problems/implement-stack-using-array/1
 * 
 * Time Complexity: O(1) for all core operational methods.
 * Space Complexity: O(N) -> Fixed primitive storage footprint initialized via array bounds.
 */

class myStack {
    
    // TC - O(1) & SC - O(N)
    
    int[] stack;
    int top;
    int size;

    public myStack(int n) {
        // Define Data Structures
        stack = new int[n];
        top = -1;
        size = n;
    }

    public boolean isEmpty() {
        // check if the stack is empty
        return top == -1;
        
    }

    public boolean isFull() {
        // check if the stack is full
        return top == size - 1;
    }

    public void push(int x) {
        // Inserts x at the top of the stack
        if (!isFull()) {
            stack[++top] = x;
        }
    }

    public void pop() {
        // Removes an element from the top of the stack
        if (!isEmpty()) {
            top--;
        }
    }

    public int peek() {
        // Returns the top element of the stack
        if (!isEmpty()) {
            return stack[top];
        }
        return -1;
    }
}