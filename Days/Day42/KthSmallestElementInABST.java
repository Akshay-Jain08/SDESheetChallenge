/**
 * Problem: Kth Smallest Element in a BST
 * Link: https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 * 
 * Time Complexity: O(H + K) -> Logarithmic descent to the left boundary followed by K sorted index ticks.
 * Space Complexity: O(H)     -> System recursion stack memory bounded strictly by tree height H.
 */

public class KthSmallestElementInABST {
    public int kthSmallest(TreeNode root, int k) {
        
        // TC - O(H + K) & SC - O(H)
        // This approach uses inorder traversal, and since inorder is always
        // sorted for a BST, we can pass a count variable to keep counting the 
        // number of nodes visited.
        // The kth node visited would be the kth smallest element

        int[] ans = {-1};

        inorder(root, k, ans, new int[] {0});

        return ans[0];
    }

    public boolean inorder(TreeNode root, int k, int[] ans, int[] count) {
        
        // Base Case
        if (root == null) return false;

        // Move to the left sub-tree
        if (inorder(root.left, k, ans, count)) return true;

        // Increment the counter
        count[0]++;
        // If the counter reaches k, return true
        if (count[0] == k) {
            ans[0] = root.val;
            return true;
        }

        // Move to the right sub-tree
        return inorder(root.right, k, ans, count);
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
