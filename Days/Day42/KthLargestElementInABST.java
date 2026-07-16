/**
 * Problem: Kth Largest Element in a BST
 * Link: https://www.geeksforgeeks.org/problems/kth-largest-element-in-bst/1
 * 
 * Time Complexity: O(H + K) -> Logarithmic descent to the right boundary followed by K sorted index ticks.
 * Space Complexity: O(H)     -> System recursion stack memory bounded strictly by tree height H.
 */

import java.util.ArrayDeque;

public class KthLargestElementInABST {
    public int kthLargest(Node root, int k) {
        
        // TC - O(H + K) & SC - O(H)
        // This approach uses the reverse inorder approach with the mutable
        // ans and count variables. We traverse through the tree until the 
        // count reaches k or traversal is complete
        
        int[] ans = {-1};
        int[] count = {0};
        
        inorderReverse(root, k, ans, count);
        
        return ans[0];
        
        
        
        
        /*
        // TC - O(N) & SC - O(H + K)
        // We use a Double ended queue to store the elements in inorder
        // manner. We keep removing elements from the deque if the size gets 
        // more than k. Since, the inorder is always sorted, then the first 
        // element of the deque after the traversal would be the kth largest
        
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        
        inorder(root, k, deque);
        
        return deque.peekFirst();
        */
    }
    
    public boolean inorderReverse(Node root, int k, int[] ans, int[] count) {
        
        // Base Case
        if (root == null) return false;
        
        // Move to the right sub-tree first
        if (inorderReverse(root.right, k, ans, count)) return true;
        
        // If the count reaches k, store the curr node's value to the ans
        // and return true
        if (++count[0] == k) {
            ans[0] = root.data;
            return true;
        }
        
        // Move to the left sub-tree
        return inorderReverse(root.left, k, ans, count);
    }
    
    public void inorder(Node root, int k, ArrayDeque<Integer> deque) {
        
        // Base Case
        if (root == null) return;
        
        // Move to the left sub-tree
        inorder(root.left, k, deque);
        
        // Add the current node's value to the deque
        deque.offerLast(root.data);
        
        // If the size is greater than k, then remove the element from front
        if (deque.size() > k) {
            deque.pollFirst();
        }
        
        // Move to the right sub-tree
        inorder(root.right, k, deque);
    }

    static class Node {
        int data;
        Node left;
        Node right;
        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
}
