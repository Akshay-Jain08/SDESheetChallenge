/**
 * Problem: Implement Custom Max-Heap Structure
 * Link: https://www.geeksforgeeks.org/problems/max-heap-implementation/1
 * 
 * Time Complexity: O(log N) for Insertion & Deletion operations.
 * Space Complexity: O(N)     -> Linearly tracks the element capacity in dynamic array storage.
 */


import java.util.ArrayList;

class MaxHeapImplementation {
    
    // TC - O(logN) in case for insertion or deletion
    // SC - O(N)
    // This is the implementation of maxHeap with an array (0 - indexed)
    
    ArrayList<Integer> heap;
    
    // Constructor
    public MaxHeapImplementation() {
        // Initialize the heap array
        heap = new ArrayList<>();
    }

    public void push(int x) {
        // Insert x into the heap
        heap.add(x);
        
        // upheap the added element
        upHeap(heap.size() - 1);
    }

    public void pop() {
        // Remove the top (maximum) element
        int leaf = heap.remove(heap.size() - 1);
        
        // check in case there was only 1 element in the array
        if (heap.size() > 0) {
            heap.set(0, leaf);
            downHeap(0);
        }
    }

    public int peek() {
        // Return the top element or -1 if empty
        if (heap.size() == 0) return -1;
        
        return heap.get(0);
    }

    public int size() {
        // Return the number of elements in the heap
        return heap.size();
    }
    
    public int parent(int index) {
        // returns the index of the parent
        return (index - 1) / 2;
    }
    
    public int leftChild(int index) {
        // returns the index of the left child
        return 2 * index + 1;
    }
    
    public int rightChild(int index) {
        // returns the index of the right child
        return 2 * index + 2;
    }
    
    public void upHeap(int index) {
        // Base Case
        if (index == 0) {
            return;
        }
        
        int p = parent(index);
        
        // If the current element is greater than its parent, 
        // swap and uphead of parent
        if (heap.get(p) < heap.get(index)) {
            swap(p, index);
            upHeap(p);
        }
    }
    
    public void downHeap(int index) {
        // compute the max out of the current index, left child, and right child
        int max = index;
        int left = leftChild(index);
        int right = rightChild(index);
        
        // If the left child is greater
        if (left < heap.size() && heap.get(max) < heap.get(left)) {
            max = left;
        }
        
        // If the right child is greater
        if (right < heap.size() && heap.get(max) < heap.get(right)) {
            max = right;
        }
        
        // If a child becomes maximum,
        // swap and continue downHeap
        if (max != index) {
            swap(max, index);
            downHeap(max);
        }
    }
    
    // swap method
    public void swap(int f, int s) {
        int temp = heap.get(f);
        heap.set(f, heap.get(s));
        heap.set(s, temp);
    }
}