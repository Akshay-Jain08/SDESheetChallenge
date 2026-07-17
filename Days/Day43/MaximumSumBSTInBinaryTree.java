/**
 * Problem: Maximum Sum BST in Binary Tree
 * Link: https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/
 * 
 * Time Complexity: O(N) -> Single linear post-order Tree DP pass evaluating all tree nodes.
 * Space Complexity: O(H) -> System recursion stack memory bounded strictly by tree height H.
 */

public class MaximumSumBSTInBinaryTree {
    public int maxSumBST(TreeNode root) {
        
        // TC - O(N) & SC - O(H)
        // We use a custom data structure State to maintain the following 
        // four properties of a sub-tree: BST validity, sum, min, max
        // Update the maxSum for those sub-tree which are valid
        // This is a bottom-up tree DP.
        // A parent can determine its BST validity only after knowing whether
        // its left and right subtrees are BSTs, along with their minimum,
        // maximum and sum.

        // Edge Case
        if (root == null) return 0;
        
        int[] maxSum = {0};
        
        // helper method that traverses the tree and update the maxSum
        helper(root, maxSum);

        return maxSum[0];




        /*
        // TC - O(N^2) & SC - O(H)
        // This approach validate the BST property for every single node
        // If the particular sub-tree is a valid BST, then only consider its sum
        // Though, this might be the first intuition that you get for this problem
        // This solution is correct but inefficient. It recomputes the BST validation 
        // and subtree sum for every node, leading to O(N²) time.

        // Edge Case
        if (root == null) return 0;

        // value of sub-tree sum of left and right sub-tree
        int left = maxSumBST(root.left);
        int right = maxSumBST(root.right);

        int[] sum = new int[]{0};
        int curr = 0;

        // If the curr sub-tree is a valid BST, then only update the curr sum
        // Otherwise, let it be zero
        if (isValidBST(root, sum, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
            curr = sum[0];
        }

        return Math.max(0, Math.max(curr, Math.max(left,right)));
        */
    }

    public State helper(TreeNode root, int[] maxSum) {

        // Base Case
        // An empty tree is a valid BST.
        // min = +∞ and max = -∞ ensure that
        // parent comparisons always succeed.
        if (root == null)
            return new State(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);

        // Get the state for left and right sub-trees       
        State left = helper(root.left, maxSum);
        State right = helper(root.right, maxSum);

        // Determine the BST validity for the current sub-tree
        // Both the child sub-trees must be valid
        // The curr node must follow the BST invariant
        // i.e. it should be greater than left max and lesser than right min
        boolean isBST = left.isBST && right.isBST && root.val > left.max && root.val < right.min;

        // curr sub-tree sum
        int currSum = left.sum + right.sum + root.val;
        // min element in curr sub-tree
        int min = Math.min(root.val, left.min);
        // max element in curr sub-tree
        int max = Math.max(root.val, right.max);

        // If the curr sub-tree is a valid BST, then only update the maxSum
        if (isBST) {
            maxSum[0] = Math.max(maxSum[0], currSum);
        }

        // return the curr sub-tree's state to its parent
        return new State(isBST, currSum, min, max);
    }

    // This method checks the BST validity and update the sum as we go
    public boolean isValidBST(TreeNode root, int[] sum, int low, int high) {

        if (root == null) return true;

        if (root.val <= low || root.val >= high) return false;
        sum[0] += root.val;

        return isValidBST(root.left, sum, low, root.val) &&
               isValidBST(root.right, sum, root.val, high);
    }

    // Custom Class for maintaining the state of a sub-tree
    static class State {
        boolean isBST;
        int sum;
        int min;
        int max;

        State(boolean isBST, int sum, int min, int max) {
            this.isBST = isBST;
            this.sum = sum;
            this.min = min;
            this.max = max;
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
