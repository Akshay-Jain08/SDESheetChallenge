/**
 * Problem: Symmetric Tree
 * Link: https://leetcode.com/problems/symmetric-tree/
 * 
 * Time Complexity: O(N) -> Single linear scan pass checking symmetric mirror pairs.
 * Space Complexity: O(W) -> Queue allocations bounded by the maximum tree layer width W.
 */

import java.util.LinkedList;
import java.util.Queue;

public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        
        // TC - O(N) & SC - O(W) 
        // W = Maximum width of the tree
        // This is the iterative version of the below recursive approach
        // For intuition explanation, refer below approach

        // We use a queue to iteratively  add the mirrored nodes
        // and perform the check if they are equal or not

        // Edge Case
        if (root == null) return true;

        // Enqueue the left and right child, as we are only dealing with pairs
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();

            // If both are null, the tree may be symmetric
            if (left == null && right == null) continue;

            // If either one of them is null, the tree cannot be symmetric
            if (left == null || right == null) return false;

            // If they differ in values, the tree cannot be symmetric
            if (left.val != right.val) return false;

            // Enqueue the further mirrored children
            // so that they will be processed in pairs in further iterations
            queue.offer(left.left);
            queue.offer(right.right);

            queue.offer(left.right);
            queue.offer(right.left);
        }

        return true;



        /*
        // TC - O(N) & SC - O(H)
        // H = Height of the tree
        // For a tree to be symmetric, left and right sub-trees should be
        // mirrored image of each other. That means:
        // left child of the left sub-tree should be equal to the right child of the right sub-tree
        // right child of the left sub-tree should be equal to the left child of the right sub-tree

        // Edge Case
        if (root == null) return true;

        // The left and right subtrees should be mirror images of each other.
        return isMirror(root.left, root.right);
        */
    }

    public boolean isMirror(TreeNode left, TreeNode right) {
        
        // If both nodes are null, then they are symmetric
        if (left == null && right == null) return true;

        // If either one of them is null, then they are not symmetric
        if (left == null || right == null) return false;

        // If values are different, they are asymmetric
        if (left.val != right.val) return false;

        // Recursively verify the mirrored children.
        return isMirror(left.left, right.right) && isMirror(left.right, right.left);
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