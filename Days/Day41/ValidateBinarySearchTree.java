/**
 * Problem: Validate Binary Search Tree
 * Link: https://leetcode.com/problems/validate-binary-search-tree/
 * 
 * Time Complexity: O(N) -> Single linear recursive pass evaluating all tree nodes against ancestor bounds.
 * Space Complexity: O(H) -> System recursion stack memory bounded strictly by tree height H.
 */

public class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        
        // TC - O(N) & SC - O(H)
        // H - Height of the tree
        // To validate a binary tree, we need to check if a node's value is within the
        // permitted range as directed by its ancestors
        // i.e. the whole left sub-tree will have value strictly lesser than the root
        // the whole right sub-tree will have value strictly greater than the root

        // We will use a helper function, that uses three parameters : root, low, high
        // low and high represent the valid range inherited from the node's ancestors.
        // For root, there is no limit, that's why we start with null
        
        return isValid(root, null, null);
        



        /*
        // TC - O(N) & SC - O(H)
        // H - Height of the tree
        // We can use the inorder traversal to verify the BST as it is guaranteed
        // that the inorder traversal of a BST is always strictly increasing.
        // Thus, we carry a prevVal as a parameter in the recursive calls
        // At every call, we check if the current node's value satisfy the
        // invariant for the inorder of the BST

        return inorder(root, new Integer[] {null});
        */
    }

    public boolean isValid(TreeNode root, Integer low, Integer high) {

        // Base Case
        if (root == null) return true;

        // Current node violates the permissible range.
        if ((low != null && root.val <= low) || (high != null && root.val >= high)) {
            return false;
        }

        // Verify recursively for the children
        // Update the high value for the left child (as it must possess a value lesser than the node)
        // Update the low value for the right child (as it must possess a value greater than the node)
        return isValid(root.left, low, root.val) && 
               isValid(root.right, root.val, high);
    }

    public boolean inorder(TreeNode root, Integer[] prevVal) {

        // Base Case
        if (root == null) return true;

        // Go to the left sub-tree first
        if (!inorder(root.left, prevVal)) return false;

        // If current node doesn't satisfy the invariant, return false
        if (prevVal[0] != null && root.val <= prevVal[0]) return false;

        // Update the prevVal
        prevVal[0] = root.val;

        // Go to the right sub-tree
        if (!inorder(root.right, prevVal)) return false;

        return true;
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
