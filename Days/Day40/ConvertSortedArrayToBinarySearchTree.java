/**
 * Problem: Convert Sorted Array to Binary Search Tree
 * Link: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 * 
 * Time Complexity: O(N)      -> Linear tree construction pass visiting each node exactly once.
 * Space Complexity: O(log N) -> System recursion stack memory bounded strictly by balanced tree height log(N).
 */

public class ConvertSortedArrayToBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        
        // TC - O(N) & SC - O(log N)
        // Since, we have to follow the height-balancing property for the tree
        // So everytime, we go to a sub-tree, we can take mid value out of all
        // possible values as the root and divide the two halves into further 
        // sub-trees

        int n = nums.length;

        return buildTree(0, n - 1, nums);
    }

    public TreeNode buildTree(int start, int end, int[] nums) {
        
        // Base Case
        if (start > end) return null;

        // Choose the middle element as the root of the current subtree.
        int mid = start + (end - start) / 2;

        // node for the current sub-tree
        TreeNode root = new TreeNode(nums[mid]);

        // Recursively build the left and right balanced subtrees.
        root.left = buildTree(start, mid - 1, nums);
        root.right = buildTree(mid + 1, end, nums);

        return root;
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
