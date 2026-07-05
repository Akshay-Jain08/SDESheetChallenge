/**
 * Problem: Binary Tree Preorder Traversal
 * Link: https://leetcode.com/problems/binary-tree-preorder-traversal/
 * 
 * Time Complexity: O(N) -> Linear traversal over all N nodes via temporary edge threads.
 * Space Complexity: O(1) -> Space-optimized constant auxiliary storage with zero stack usage.
 */

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePreorderTraversal {

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

    public List<Integer> preorderTraversal(TreeNode root) {
        
        // TC - O(N) & SC - O(1)
        // This is Morris traversal which doesn't use any extra auxilliary space
        // It uses a concept of threaded binary tree
        // Morris traversal temporarily creates a thread from the inorder
        // predecessor to the current node, allowing traversal without
        // recursion or an explicit stack.
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
                // Find the inorder predecessor (rightmost node of left subtree)
                TreeNode temp = curr.left;

                // temp.right != curr check if for when we comeback again to the curr
                // after traversing the whole left sub-tree so to not repeat it, we add a check
                while (temp.right != null && temp.right != curr) {
                    temp = temp.right;
                }

                // If we already have a linkage to the curr, means thread already exists, 
                // so the left subtree has been processed. 
                // the current node, that means its left sub-tree has already been traversed
                if (temp.right == curr) {
                    // break the thread
                    temp.right = null;
                    curr = curr.right;
                }
                else {
                    // Set up the thread if we are visiting the curr node for the first time
                    // Visit the current node before traversing the left subtree,
                    // then create the thread and move left.
                    temp.right = curr;
                    result.add(curr.val);
                    curr = curr.left;
                }
            }
        }
        return result;



        /*
        // TC - O(N) & SC - O(N)
        // This is yet another iterative approach of traversal
        // But more intuitive than the previous one

        List<Integer> result = new ArrayList<>();

        // Edge Case
        if (root == null) return result;

        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        // For every node, add its value
        // and add right child first, then left child
        // as stack works on LIFO
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            result.add(node.val);
            
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return result;
        */



        /*
        // TC - O(N) & SC - O(H)
        // H = height of the tree (worst case O(N), balanced O(log N))
        // This is the iterative approach for preorder traversal
        // It used a stack that resembles the recursive stack

        List<Integer> result = new ArrayList<>();
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();

        while (true) {
            // Keep going until the leftmost node, and keep adding the current value as
            // preorder needs root -> left -> right
            if (root != null) {
                stack.push(root);
                result.add(root.val);
                root = root.left;
            }
            else {
                // When traversal is complete, break the loop
                if (stack.isEmpty()) {
                    break;
                }

                // When leftmost node is visited, start visiting the right of each node iteratively
                TreeNode node = stack.pop();

                root = node.right;
            }
        }
        return result;
        */



        /*
        // TC - O(N) & SC - O(H)
        // H = height of the tree (worst case O(N), balanced O(log N))
        // This is the recursive approach for the pre-order traversal

        List<Integer> res = new ArrayList<>();

        // helper method to execute the traversal
        helper(res, root);

        return res;
        */
    }

    public void helper(List<Integer> res, TreeNode root) {

        // Base Case
        if (root == null) return;

        // Add the curr node 
        res.add(root.val);
        // Go to the left sub-tree
        helper(res, root.left);
        // Go to the right sub-tree
        helper(res, root.right);
    }
}
