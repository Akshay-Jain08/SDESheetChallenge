/**
 * Problem: Balanced Binary Tree
 * Link: https://leetcode.com/problems/balanced-binary-tree/
 * 
 * Time Complexity: O(N) -> Single linear recursive pass co-computing subtree heights and balance invariants.
 * Space Complexity: O(H) -> System recursion stack memory bounded strictly by tree height H.
 */

public class BalancedBinaryTree {

    // Global variable
    boolean balanced = true;

    public boolean isBalanced(TreeNode root) {
        
        // TC - O(N) & SC - O(H)
        // A single DFS computes the height of every subtree.
        // While returning from recursion, we check whether
        // the current node is height-balanced.
        // Update the balanced variable as we go up 

        // Edge Case
        if (root == null) return true;

        // Good practice to reset the global variable inside the method
        balanced = true;

        findHeight(root);

        return balanced;
    }

    public int findHeight(TreeNode root) {

        // Base Case
        if (root == null) return 0;

        // Height of the left child
        int left = findHeight(root.left);
        // Height of the right child
        int right = findHeight(root.right);

        // Balance factor of the current node would be the difference of the 
        // height of the left child and right
        // For it to be a balanced node, the difference should be less than or equal to 1
        if (Math.abs(left - right) > 1) {
            balanced = false;
        }

        // return the height of the curr node to wherever the method is called
        return 1 + Math.max(left, right);
    }

    static class TreeNode {
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
}
