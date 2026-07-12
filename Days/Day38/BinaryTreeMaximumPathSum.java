/**
 * Problem: Binary Tree Maximum Path Sum
 * Link: https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * 
 * Time Complexity: O(N) -> Single linear recursive DFS pass evaluating all tree nodes.
 * Space Complexity: O(H) -> System recursion stack memory bounded strictly by tree height H.
 */

public class BinaryTreeMaximumPathSum {
    public int maxPathSum(TreeNode root) {

        // TC - O(N) & SC - O(H)
        // H = Height of the tree
        // We are doing standard dfs traversal on the tree
        // to go down to the leaf node. As we go up, we add the pathSum of 
        // the left and right sub-tree to the current val (if they are positive)
        // That gives us the maximum path sum for that node

        // Edge Case
        if (root == null) return 0;

        // Not using any global variable, instead using a mutable array
        // that could be passed onto the recursive calls
        int[] maxSum = new int[1];
        maxSum[0] = Integer.MIN_VALUE;

        findSum(root, maxSum);

        return maxSum[0];
    }

    public int findSum(TreeNode root, int[] maxSum) {

        // Base Case
        if (root == null) return 0;

        // Maximum downward path starting from the left/right child.
        int left = findSum(root.left, maxSum);
        int right = findSum(root.right, maxSum);

        int currentSum = root.val;

        // Only add if they are positive 
        if (left > 0) {
            currentSum += left;
        }
        if (right > 0) {
            currentSum += right;
        }

        // Update the maxSum
        maxSum[0] = Math.max(maxSum[0], currentSum);

        // Since we can take only one of the path upwards to the parent node
        // remove the smaller one among left and right if both are positive
        // Since both would have been added to the currentSum
        if (left > 0 && right > 0) {
            currentSum -= Math.min(left, right);
        }

        return currentSum;
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
