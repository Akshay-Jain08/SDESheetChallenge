/**
 * Problem: Implement Stack using Queues
 * Link: https://leetcode.com/problems/implement-stack-using-queues/
 * 
 * Time Complexity: O(1) Push, O(N) Pop/Top (or vice versa based on rotation layout).
 * Space Complexity: O(N)     -> Standard linear storage keeping elements in dynamic memory.
 */

import java.util.LinkedList;
import java.util.Queue;

class MyStack {

    // TC - O(N) & SC - O(N)
    // Using one queue only

    Queue<Integer> q1;

    public MyStack() {
        q1 = new LinkedList<>();
    }
    
    public void push(int x) {
        q1.offer(x);
    }
    
    public int pop() {
        for (int i = 0; i < q1.size() - 1; i++) {
            q1.offer(q1.poll());
        }
        return q1.poll();
    }
    
    public int top() {
        for (int i = 0; i < q1.size() - 1; i++) {
            q1.offer(q1.poll());
        }
        int data = q1.poll();
        q1.offer(data);
        return data;
    }
    
    public boolean empty() {
        return q1.size() == 0;
    }



    /*
    // TC - O(N) & SC - O(N)
    // Using two queues

    Queue<Integer> q1;
    Queue<Integer> q2;

    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }
    
    public void push(int x) {
        if (q2.size() > 0) {
            q2.offer(x);
        }
        else {
            q1.offer(x);
        }
    }
    
    public int pop() {
        int data;

        if (q2.size() > 0) {
            while (q2.size() > 1) {
                q1.offer(q2.poll());
            }
            data = q2.poll();
        }
        else {
            while (q1.size() > 1) {
                q2.offer(q1.poll());
            }
            data = q1.poll();
        }

        return data;
    }
    
    public int top() {
        int data;

        if (q2.size() > 0) {
            while (q2.size() > 1) {
                q1.offer(q2.poll());
            }
            data = q2.poll();
            q1.offer(data);
        }
        else {
            while (q1.size() > 1) {
                q2.offer(q1.poll());
            }
            data = q1.poll();
            q2.offer(data);
        }

        return data;
    }
    
    public boolean empty() {
        return (q1.size() == 0 && q2.size() == 0);
    }
    */
}