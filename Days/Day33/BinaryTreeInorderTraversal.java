/**
 * Problem: Binary Tree Inorder Traversal
 * Link: https://leetcode.com/problems/binary-tree-inorder-traversal/
 * 
 * Time Complexity: O(N) -> Linear traversal over all N nodes via temporary edge threads.
 * Space Complexity: O(1) -> Space-optimized constant auxiliary storage with zero stack usage.
 */

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeInorderTraversal {

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

    public List<Integer> inorderTraversal(TreeNode root) {
        
        // TC - O(N) & SC - O(1)
        // This is Morris traversal which doesn't use any extra auxilliary space
        // It uses a concept of threaded binary tree
        // Basically everytime, we go to the left,
        // we make a thread (linkage) from the rightmost node of the left-subtree to the current 
        // node so we can comeback to the current node after traversing the left sub-tree

        // Morris traversal temporarily modifies the tree,
        // but restores every thread before finishing.

        List<Integer> result = new ArrayList<>();
        TreeNode curr = root;

        while (curr != null) {
             
            // If there is no left sub-tree, just add the current value
            // and go to the right sub-tree
            if (curr.left == null) {
                result.add(curr.val);
                curr = curr.right;
            }
            else {
                // Find the inorder predecessor (rightmost node in the left subtree)
                TreeNode temp = curr.left;

                // temp.right == curr means the thread already exists,
                // so the left subtree has already been processed.
                while (temp.right != null && temp.right != curr) {
                    temp = temp.right;
                }

                // If we already have a linkage to the curr, means we've already been through 
                // the current node, that means its left sub-tree has already been traversed
                if (temp.right == curr) {
                    // break the thread
                    temp.right = null;
                    // add current val (since left part is done)
                    result.add(curr.val);
                    // move to the right subtree
                    curr = curr.right;
                }
                else {
                    // Set up the thread if we are visiting the curr node for the first time
                    // and move to the left subtree
                    temp.right = curr;
                    curr = curr.left;
                }
            }
        }
        return result;




        /*
        // TC - O(N) & SC - O(H)
        // H = height of the tree (worst case O(N), balanced O(log N))
        // This is the iterative solution for this problem
        // We use a single stack that resembles the recursive stack space

        List<Integer> result = new ArrayList<>();
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();

        while (true) {

            // Keep going to the left node, until you reach to very end
            if (root != null) {
                stack.push(root);
                root = root.left;
            }
            else {
                // Case when the traversal is complete
                if(stack.isEmpty()) {
                    break;
                }

                // After reaching the leftest node, start adding the nodes to the list
                TreeNode node = stack.pop();

                // add the current value to the result, and go to the right sub-tree
                result.add(node.val);
                root = node.right;
            }
        }

        return result;
        */



        /*
        // TC - O(N) & SC - O(H)
        // H = height of the tree (worst case O(N), balanced O(log N))
        // This is the recursive solution for the Inorder traversal

        List<Integer> result = new ArrayList<>();

        // helper function to execute traversal
        helper(result, root);

        return result;
        */
    }

    public void helper(List<Integer> result, TreeNode root) {

        // Base Case
        if (root == null) {
            return;
        }

        // Go for left sub-tree first
        helper(result, root.left);

        // Add current node
        result.add(root.val);

        // Go for right sub-tree
        helper(result, root.right);
    }
}
