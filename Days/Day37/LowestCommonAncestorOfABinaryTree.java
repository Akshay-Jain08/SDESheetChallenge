/**
 * Problem: Lowest Common Ancestor of a Binary Tree
 * Link: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * 
 * Time Complexity: O(N) -> Single linear recursive DFS pass evaluating all tree nodes.
 * Space Complexity: O(H) -> System recursion stack memory bounded strictly by tree height H.
 */

public class LowestCommonAncestorOfABinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
        // TC - O(N) & SC - O(H)
        // H = Height of the tree
        // This is the similar approach as the below one 
        // but implementing it might feel a little simpler than it

        // Base Case : If the current node is null or any among p or q, return it
        if (root == null || root == p || root == q) {
            return root;
        }

        // Go to left and right sub-trees to check if the node p or node q exists in the 
        // respective sub-trees
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // If any one of them is null, return the other one
        // If only one subtree returns a non-null node, it means 
        // both targets lie in that subtree (or this is one of the targets), 
        // so propagate that node upward.
        if (left == null) {
            return right;
        }
        else if (right == null) {
            return left;
        }
        // If both left and right are not null, then the current node is the lca
        else {
            return root;
        }




        /*
        // TC - O(N) & SC - O(H)
        // H = Height of the tree
        // This approach uses a dfs traversal to look out for the node p and q
        // in the binary tree. 
        
        // Whenever we counter any of the two elements in the  tree, 
        // we can be assured that it maybe the result, so return the root
        if (root == p || root == q) {
            return root;
        }

        // Go to left and right sub-trees to check if the node p or node q exists in the 
        // respective sub-trees
        TreeNode left = null;
        TreeNode right = null;

        if (root.left != null) {
            left = lowestCommonAncestor(root.left, p, q);
        }

        if (root.right != null) {
            right = lowestCommonAncestor(root.right, p, q);
        }

        // If at any node, both left and right are either node p or node q
        // then the current node is the lca
        if ((left == p && right == q) || (left == q && right == p)) {
            return root;
        }

        // If any of the sub-tree is null, return the other one
        return left != null ? left : right;
        */



        /*
        // TC - O(N) & SC - O(H)
        // Another approach would be to store root-to-node paths for both the nodes
        // Compare both the paths until they differ at a particular position, 
        // the node which was common just before they differ is the lca
        // It requires extra space to store the paths
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
