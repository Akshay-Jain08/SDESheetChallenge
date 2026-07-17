/**
 * Problem: Two Sum IV - Input is a BST (Find a Pair with Given Sum in BST)
 * Link: https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
 * 
 * Time Complexity: O(N) -> Linear coordinate evaluation pass moving dual stacks in-place.
 * Space Complexity: O(H) -> Storage footprints bounded tightly by tree height stack structures.
 */

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

public class TwoSumInABST {
    public boolean findTarget(TreeNode root, int k) {
        
        // TC - O(N) & SC - O(H)
        // This approach uses the two pointer approach of sorted array
        // here on a BST.
        // start pointer does inorder traversal (from smallest to largest)
        // end pointer does reverse inorder(from largest to smallest)
        // We maintain their respective stacks to find the successor and predecessor
        // for start and end respectively in O(1)

        // Edge Case
        if (root == null || (root.left == null && root.right == null)) return false;

        TreeNode start = root;
        TreeNode end = root;

        ArrayDeque<TreeNode> stack1 = new ArrayDeque<>();
        ArrayDeque<TreeNode> stack2 = new ArrayDeque<>();

        // Take the start pointer to the smallest element
        while (start.left != null) {
            stack1.push(start);
            start = start.left;
        }

        // Take the end pointer to the largest element
        while (end.right != null) {
            stack2.push(end);
            end = end.right;
        }

        // Two-Pointer approach 
        while (start.val < end.val) {

            int sum = start.val + end.val;

            // If the sum is equal to target, return true
            if (sum == k) return true;

            // If the sum is greater than target, move the end pointer to its predecessor
            else if (sum > k) end = predecessor(stack2, end);

            // If the sum is lesser than target, move the start pointer to its successor
            else start = successor(stack1, start);
        }

        return false;



        /*
        // TC - O(N) & SC - O(N)
        // This approach uses a set to store all the processed nodes
        // At every node, we check if (k - node.val) is present in the set.
        // If it is, then we have found a pair with sum k

        Set<Integer> set = new HashSet<>();

        return preorder(root, k, set);
        */
    }

    public TreeNode successor(ArrayDeque<TreeNode> stack, TreeNode start) {
        
        // Successor of the current node would be the leftmost element
        // of its right child
        // In case it doesn't exist, we will move to its parent
        start = start.right;

        while (start != null) {
            stack.push(start);
            start = start.left;
        }

        return stack.pop();
    }

    public TreeNode predecessor(ArrayDeque<TreeNode> stack, TreeNode end) {

        // Predecessor of the current node would be the rightmost element
        // of its left child
        // In case, it doesn't exist, we'll move to its parent
        end = end.left;

        while (end != null) {
            stack.push(end);
            end = end.right;
        }

        return stack.pop();
    }

    public boolean preorder(TreeNode root, int k, Set<Integer> set) {

        // Base Case
        if (root == null) return false;

        // If the complement (k - node.val) has already been seen,
        // then we have found two nodes whose sum equals k.
        int remain = k - root.val;
        if (set.contains(remain)) return true;

        // Add the curr value to the set
        set.add(root.val);

        // Move to the left sub-tree
        if (preorder(root.left, k, set)) return true;

        // Move to the right sub-tree
        return preorder(root.right, k, set);
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
