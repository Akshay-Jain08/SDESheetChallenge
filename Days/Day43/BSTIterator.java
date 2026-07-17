/**
 * Problem: Binary Search Tree Iterator
 * Link: https://leetcode.com/problems/binary-search-tree-iterator/
 * 
 * Time Complexity: Constructor: O(H) | next(): O(1) Amortised | hasNext(): O(1).
 * Space Complexity: O(H) -> Storage footprint bounded strictly by active tree height stack.
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class BSTIterator {

    // TC - O(N) & SC - O(H)
    // This approach uses a stack to mimic the recursive stack to keep
    // the track of the processed elements to perfectly execute inorder traversal
    
    ArrayDeque<TreeNode> stack;

    // Construct to initialize the members
    public BSTIterator(TreeNode root) {
        
        stack = new ArrayDeque<>();

        // Push the entire left spine of the subtree.
        // After this, the top of the stack is the next
        // inorder node to be visited.
        pushLeft(root);
    }

    private void pushLeft(TreeNode root) {

        // Go to the leftmost child and keep storing nodes in the stack
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }
    
    // TC - O(H) Worse
    // O(1) Amortized
    public int next() {
        
        // Get the next node from the stack
        TreeNode node = stack.pop();

        // Process the right subtree.
        // Push its left spine so that the smallest node
        // in that subtree becomes the next node to visit.
        if (node.right != null) {
            
            pushLeft(node.right);
        }

        return node.val;
    }

    // returns if the traversal is complete or not
    public boolean hasNext() {
        
        return !stack.isEmpty();
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}



/*
public class BSTIterator {

    // TC - O(N) & SC - O(N)
    // This approach uses a list to store the inorder traversal
    // of the tree and the pointer points to the curr element's index

    // Members
    TreeNode root;
    int pointer;
    List<Integer> inorder;

    // Constructor to initialize the members
    public BSTIterator(TreeNode root) {
        this.root = root;
        pointer = -1;
        inorder = new ArrayList<>();

        // Do the traversal and preprocess the inorder list
        buildInorderList(this.root, inorder);
    }

    // Standard inorder traversal to make the list
    public void buildInorderList(TreeNode root, List<Integer> inorder) {

        if (root == null) return;

        buildInorderList(root.left, inorder);

        inorder.add(root.val);

        buildInorderList(root.right, inorder);
    }
    
    // returns the next node's value in the inorder list
    public int next() {
        return inorder.get(++pointer);
    }
    
    // returns the availability of a node ahead of curr node
    public boolean hasNext() {
        return pointer < inorder.size() - 1;
    }
}
*/
