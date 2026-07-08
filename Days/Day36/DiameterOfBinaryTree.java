/**
 * Problem: Diameter of Binary Tree
 * Link: https://leetcode.com/problems/diameter-of-binary-tree/
 * 
 * Time Complexity: O(N) -> Single linear recursive pass co-computing subtree heights and diameters.
 * Space Complexity: O(H) -> System recursion stack memory bounded strictly by tree height H.
 */

public class DiameterOfBinaryTree {

    // Global variable for diameter
    int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        
        // TC - O(N) & SC - O(H)
        // H = Height of the tree
        // Instead of finding the depth of the children for every tree
        // making the overall complexity N^2
        // During a single DFS, compute the depth of every subtree.
        // While returning from recursion, compute the diameter passing
        // through the current node and update the global answer.

        // Edge Case
        if (root == null) return 0;

        // Good practice to reset the global variables inside the method
        diameter = 0;

        // A single DFS from the root computes depths of all subtrees
        // while simultaneously updating the diameter.
        findDepth(root);

        return diameter;




        /*
        // TC - O(N^2) & SC - O(H)
        // H = Height of the tree
        // The diameter of a node is just the depth of left child + right child
        // So for every node, we can find the depth of its child and find its diameter
        // Diameter of a tree would be the max of the diameters of a node, and its children
        
        // Edge Case
        if (root == null) return 0;

        // Diameter of the current node
        int currDia = getDepth(root.left) + getDepth(root.right);

        // Diameter of the left child
        int leftDia = diameterOfBinaryTree(root.left);

        // Diameter of the right child
        int rightDia = diameterOfBinaryTree(root.right);

        // return the max of all
        return Math.max(currDia, Math.max(leftDia, rightDia));
        */
    }

    // Method to find the depth and updating the diameter of a node
    public int findDepth(TreeNode node) {

        // Base Case
        if (node == null) return 0;

        // Depth of the left child
        int left = findDepth(node.left);
        // Depth of the right child 
        int right = findDepth(node.right);

        // Update the diameter with respect to the diameter of the current node
        // and return its depth to wherever the method was called
        diameter = Math.max(diameter, left + right);
        return 1 + Math.max(left, right);
    }

    // Method to find the depth of a node
    public int getDepth(TreeNode node) {

        // Base Case
        if (node == null) return 0;

        int depth = 1 + Math.max(getDepth(node.left), getDepth(node.right));

        return depth;
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
