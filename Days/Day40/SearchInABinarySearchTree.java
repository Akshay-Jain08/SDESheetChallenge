/**
 * Problem: Search in a Binary Search Tree
 * Link: https://leetcode.com/problems/search-in-a-binary-search-tree/
 * 
 * Time Complexity: O(H) -> Logarithmic branch scanning pass bounded strictly by tree height H.
 * Space Complexity: O(1) -> Space-optimized constant auxiliary storage with zero stack usage.
 */

public class SearchInABinarySearchTree {
    public TreeNode searchBST(TreeNode root, int val) {
        
        // TC - O(H) & SC - O(1)
        // H - Height of the tree
        // Iterative way

        // Run the loop until we reach the end or we find our target
        while (root != null && root.val != val) {
            
            // If the target is greater, search right sub-tree
            if (val > root.val) root = root.right;

            // If the target is smaller, search left sub-tree
            else root = root.left;
        }

        return root;



        /*
        // TC - O(H) & SC - O(H) 
        // H - Height of the tree
        
        // Edge Case
        if (root == null) return null;

        // If the curr value equals to the target, return the node
        if (val == root.val) return root;

        // If the target is greater, then go to the right sub-tree
        else if (val > root.val) return searchBST(root.right, val);

        // If the target is smaller, then go to the left sub-tree
        else return searchBST(root.left, val);
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
