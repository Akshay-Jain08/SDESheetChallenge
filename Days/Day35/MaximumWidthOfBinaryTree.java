/**
 * Problem: Maximum Width of Binary Tree
 * Link: https://leetcode.com/problems/maximum-width-of-binary-tree/
 * 
 * Time Complexity: O(N) -> Single linear level-order BFS scan over all tree nodes.
 * Space Complexity: O(W) -> Storage footprint bounded by the maximum horizontal queue width W.
 */

import java.util.LinkedList;
import java.util.Queue;

public class MaximumWidthOfBinaryTree {
    public int widthOfBinaryTree(TreeNode root) {
        
        // TC - O(N) & SC - O(W) 
        // W = Maximum no. of elements at a horizontal level in the tree
        // We are performing standard BFS traversal keeping the track of the
        // leftmost and rightmost index at every horizontal level

        // Edge Case
        if (root == null) return 0;

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 1));
        int maxWidth = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();
            // Leftmost and rightmost indices of the current level
            int left = queue.peek().pos; // calculate the left idx beforehand (to normalize the idx for the next level)
            int right = 0;

            for (int i = 0; i < size; i++) {

                Pair pair = queue.poll();
                TreeNode node = pair.node;

                // Keep updating the right pointer till the very end of the curr level
                right = pair.pos;

                // Normalize the current index to keep child indices small
                // and prevent overflow in deep trees.
                int curr = pair.pos - left;

                // Left child will be at 2 * currPos
                if (node.left != null) queue.offer(new Pair(node.left, 2 * curr));

                // Right child will be at 2 * currPos + 1
                if (node.right != null) queue.offer(new Pair(node.right, 2 * curr + 1));
            }

            // Update the maxWidth
            maxWidth = Math.max(maxWidth, right - left + 1);
        }

        return maxWidth;
    }

    static class Pair {
        TreeNode node;
        int pos;

        Pair(TreeNode n, int p) {
            node = n;
            pos = p;
        }
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
