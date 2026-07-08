/**
 * Problem: Binary Tree Level Order Traversal
 * Link: https://leetcode.com/problems/binary-tree-level-order-traversal/
 * 
 * Time Complexity: O(N) -> Single linear level-by-level BFS scan passing through all tree nodes.
 * Space Complexity: O(W) -> Space requirements bounded strictly by the maximum tree layer width W.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        
        // TC - O(N) & SC - O(W) 
        // W = Maximum width of the tree
        
        // Perform a standard BFS traversal.
        // Each iteration processes exactly one level.

        List<List<Integer>> result = new ArrayList<>();

        // Edge Case
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            
            // Number of nodes present in the current level.
            int size = queue.size();
            // List to store the elements of the current level
            List<Integer> levelList = new ArrayList<>();

            for (int i = 0; i < size; i++) {

                TreeNode curr = queue.poll();

                // add the curr element in the levelList
                levelList.add(curr.val);

                // Store the left and right child in the queue if they exist
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }

            // Add the current levelList to the result list
            result.add(levelList);
        }

        return result;
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
