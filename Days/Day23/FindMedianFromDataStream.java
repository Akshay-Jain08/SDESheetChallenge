/**
 * Problem: Find Median from Data Stream
 * Link: https://leetcode.com/problems/find-median-from-data-stream/
 * 
 * Time Complexity: O(log N) for addNum() and O(1) for findMedian().
 * Space Complexity: O(N)      -> Stores the streaming elements across two balancing heaps.
 */

import java.util.PriorityQueue;

class MedianFinder {

    // TC - O(logN) & SC - O(N)
    // This approach uses two heaps one for storing the left half of the data
    // other one for storing the right half of the data
    // left half uses maxHeap for accessing the end of the left half (left stores smaller half using maxHeap)
    // right half uses minHeap for accessing the start of the right half
    PriorityQueue<Integer> left;
    PriorityQueue<Integer> right;

    // Constructor to initialize the heaps
    public MedianFinder() {
        left = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        right = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        // If the num is greater than the max of left, add in the right half
        if (!left.isEmpty() && num > left.peek()) {
            right.offer(num);
        }
        // else add in left half
        else {
            left.offer(num);
        }

        // If the size difference gets more than 2 ever, resize them by tranferring
        // the elements from one to another
        if (left.size() > right.size() + 1) {
            right.offer(left.poll());
        }
        else if (right.size() > left.size() + 1) {
            left.offer(right.poll());
        }
    }
    
    public double findMedian() {
        // if the size is even, it's the mean of max from left and min from right
        if ((left.size() + right.size()) % 2 == 0) {
            return (left.peek() + right.peek()) / 2.0;
        }
        // otherwise, which ever is bigger, it'll provide the median
        else {
            if (left.size() > right.size()) {
                return left.peek();
            }
            else {
                return right.peek();
            }
        }
    }
}