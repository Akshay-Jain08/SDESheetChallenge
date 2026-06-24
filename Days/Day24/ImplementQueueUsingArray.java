/**
 * Problem: Implement Queue Using Array
 * Link: https://www.geeksforgeeks.org/problems/implement-queue-using-array/1
 * 
 * Time Complexity: O(1) for all core queue operations.
 * Space Complexity: O(N) -> Fixed array storage space initialized via size constraints.
 */

class myQueue {
    
    // TC - O(1) & SC - O(N)
    
    int[] queue;
    int front;
    int rear;
    int size;

    // Constructor
    public myQueue(int n) {
        // Define Data Structures
        queue = new int[n];
        front = -1;
        rear = -1;
        size = n;
    }

    public boolean isEmpty() {
        // Check if queue is empty
        return front == -1;
    }

    public boolean isFull() {
        // Check if queue is full
        return rear == size;
    }

    public void enqueue(int x) {
        // Enqueue
        if (!isFull()) {
            if (rear == -1) {
                rear = 0;
                front = 0;
            }
            queue[rear++] = x;
        }
    }

    public void dequeue() {
        // Dequeue
        if (!isEmpty()) {
            front++;
            
            if (front == rear) {
                front = -1;
                rear = -1;
            }
        }
    }

    public int getFront() {
        // Get front element
        if (!isEmpty()) {
            return queue[front];
        }
        else return -1;
    }

    public int getRear() {
        // Get last element
        if (!isEmpty()) {
            return queue[rear - 1];
        }
        else return -1;
    }
}

