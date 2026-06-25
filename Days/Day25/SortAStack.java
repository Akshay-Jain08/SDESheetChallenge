/**
 * Problem: Sort a Stack
 * Link: https://www.geeksforgeeks.org/problems/sort-a-stack/1
 * 
 * Time Complexity: O(N^2) -> Nested implicit recursive execution tracks across N items.
 * Space Complexity: O(N)   -> Driven entirely by the deep system call stack frames.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class SortAStack {
    public void sortStack(Stack<Integer> st) {
        // code here
        // TC - O(N^2) & SC - O(N)
        // If asked to solve this question, without using extra DS
        // then we must use this recursive approach
        
        // Base Case as we're going further deep into the stack
        if (st.isEmpty()) return;
        
        // Remove and store the current top
        int top = st.pop();
        
        // Sort the remaining stack essentially dividing the
        // problem into smaller sub-problem
        sortStack(st);
        
        // Then, push the current top at its correct place in the 
        // stack after sorting
        insertSorted(top, st);
        
        
        
        
        /*
        // TC - O(N logN) & SC - O(N)
        // We store all the elements in a list
        ArrayList<Integer> list = new ArrayList<>();
        
        while (!st.isEmpty()) {
            list.add(st.pop());
        }
        
        // Sort the list and store the elements back to the stack
        Collections.sort(list);
        
        for (int val : list) {
            st.push(val);
        }
        */
    }
    
    public void insertSorted(int val, Stack<Integer> st) {
        
        // If stack is empty or it holds the condition
        // push the val
        if (st.isEmpty() || st.peek() <= val) {
            st.push(val);
            return;
        }
        // Otherwise store the current top, go deep in the stack
        // to push the current val at correct place
        int top = st.pop();
            
        insertSorted(val, st);
        
        st.push(top);
    }
}
