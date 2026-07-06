/**
 * Problem: Binary Tree Right Side View
 * Link: https://leetcode.com/problems/binary-tree-right-side-view/
 * 
 * Time Complexity: O(N) -> Linear sweep passing through all N nodes across the tree structure.
 * Space Complexity: O(W) -> Space requirements bounded by the maximum horizontal layer width W of the queue.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView {

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

    public List<Integer> rightSideView(TreeNode root) {
        
        // TC - O(N) & SC - O(H)
        // H = height of the tree
        // This is the recursive approach to solve this problem using DFS
        // We traverse like root -> right -> left
        // Every time, we reach a new depth, we add the node's value to the result
        // Since we visit the right subtree before the left subtree,
        // the first node encountered at every depth is the rightmost visible node.
        List<Integer> result = new ArrayList<>();

        // Edge Case (not necessarily required as the recursive method handles it well)
        if (root == null) return result;

        // Recursive method
        dfs(root, result, 0);

        return result;



        /*
        // TC - O(N) & SC - O(W)
        // W = Maximum width of the tree (Worse Case O(N))
        // We are doing standard BFS on this tree, 
        // The last node processed at every level is the rightmost visible node.

        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        // Edge Case
        if (root == null) return result;

        queue.offer(root);

        while (!queue.isEmpty()) {
            
            // Size of the current level
            int size = queue.size();
            TreeNode node;

            for (int i = 0; i < size; i++) {
                node = queue.poll();

                // Only add the last element from the every level
                if (i == size - 1) {
                    result.add(node.val);
                }

                // Add the children for the next level.
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }

        return result;
        */
    }

    public void dfs(TreeNode node, List<Integer> result, int depth) {

        // Base Case
        if (node == null) return;

        // If this depth is visited for the first time,
        // add the current node to the result.
        if (depth == result.size()) {
            result.add(node.val);
        }

        // Traverse right sub-tree first, then left sub-tree
        dfs(node.right, result, depth + 1);
        dfs(node.left, result, depth + 1);
    }
}
