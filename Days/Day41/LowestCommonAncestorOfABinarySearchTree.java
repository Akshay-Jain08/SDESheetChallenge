/**
 * Problem: Lowest Common Ancestor of a Binary Search Tree
 * Link: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * 
 * Time Complexity: O(H) -> Logarithmic branch splitting pass bounded strictly by tree height H.
 * Space Complexity: O(1) -> Space-optimized constant auxiliary storage with zero stack usage.
 */

public class LowestCommonAncestorOfABinarySearchTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
        // TC - O(H) & SC - O(1)
        // H - Height of the tree
        // This is the iterative version where we are implying the same conditions
        // as we were doing in the recursive solution. 

        // Edge Case 
        if (root == null) return root;

        // Run the loop until we move out of the tree
        while (root != null) {
            
            // If both values are greater than the current node
            // move to the right sub-tree
            if (p.val > root.val && q.val > root.val) root = root.right;

            // If both values are lesser than the current node
            // move to the left sub-tree
            else if (p.val < root.val && q.val < root.val) root = root.left;

            // If the values split at the current node, it is lca
            else return root;
        }

        return null;




        /*
        // TC - O(H) & SC - O(H)
        // We solve this problem recursively, each time verifying if both p and q
        // are lesser than the root, then move to the left sub-tree and move to the 
        // right sub-tree if both p and q are greater than the root
        // If either of them is greater and the other one is lesser than the root
        // Then, the current node is the lca

        // Edge Case
        if (root == null) return root;

        // Current node is the split point (or equals one of the targets)
        if ((p.val >= root.val && root.val >= q.val) ||
            (q.val >= root.val && root.val >= p.val)) return root;

        // Since we have already checked for the Base Case, if either of them is greater
        // that means both are greater so move to the right sub-tree
        else if (p.val > root.val) return lowestCommonAncestor(root.right, p, q);

        // Otherwise, move to the left sub-tree
        return lowestCommonAncestor(root.left, p, q);
        */
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
