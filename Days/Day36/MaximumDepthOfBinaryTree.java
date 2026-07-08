/**
 * Problem: Maximum Depth of Binary Tree
 * Link: https://leetcode.com/problems/maximum-depth-of-binary-tree/
 * 
 * Time Complexity: O(N) -> Single linear recursive DFS pass visiting all tree nodes.
 * Space Complexity: O(H) -> System recursion stack memory bounded strictly by tree height H.
 */

import java.util.LinkedList;
import java.util.Queue;

public class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        
        // TC - O(N) & SC - O(H)
        // H = Height of the tree
        // This approach uses the dfs to find the depth
        // The depth of the current node is
        // 1 + the maximum depth of its left and right subtrees.

        // Edge Case
        if (root == null) return 0;

        int depth = 1 + Math.max(maxDepth(root.left), maxDepth(root.right));

        return depth;



        /*
        // TC - O(N) & SC - O(W)
        // W = Maximum width of the tree
        // We do the standard bfs traversal and each level is one depth more than the last level
        
        // Edge Case
        if (root == null) return 0;

        Queue<Node> queue = new LinkedList<>();
        // Add the root node with depth 1
        queue.offer(new Node(root, 1));
        int depth = 0;

        while (!queue.isEmpty()) {

            Node curr = queue.poll();

            // Update the depth
            depth = Math.max(depth, curr.depth);

            // Store the left and right child in the queue if they exist
            // Increase the depth by 1 for all the children
            if (curr.node.left != null) {
                queue.offer(new Node(curr.node.left, curr.depth + 1));
            }
            if (curr.node.right != null) {
                queue.offer(new Node(curr.node.right, curr.depth + 1));
            }
        }

        return depth;
        */
    }

    static class Node {
        TreeNode node;
        int depth;

        Node(TreeNode n, int d) {
            node = n;
            depth = d;
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
