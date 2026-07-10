/**
 * Problem: Same Tree (Check if Two Trees are Identical)
 * Link: https://leetcode.com/problems/same-tree/
 * 
 * Time Complexity: O(N) -> Linear simultaneous dual-tree recursive pass.
 * Space Complexity: O(H) -> System recursion stack memory bounded strictly by tree height H.
 */

public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        
        // TC - O(N) & SC - O(H)
        // H = Height of the tree
        // This approach recursively traverses both trees simultaneously.
        // At every node, we verify:
        // 1. Both nodes exist.
        // 2. Their values are equal.
        // 3. Their left subtrees are identical.
        // 4. Their right subtrees are identical.

        // If both nodes are null, it is identical
        if (p == null && q == null) {
            return true;
        }

        // If only one of them is null, they are not identical
        if (p == null || q == null) {
            return false;
        }

        // If the nodes' values are unequal, they are not identical
        if (p.val != q.val) {
            return false;
        }

        // Both left and right subtrees must also be identical.
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
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
