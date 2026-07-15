/**
 * Problem: Construct Binary Search Tree from Preorder Traversal
 * Link: https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
 * 
 * Time Complexity: O(N) -> Single linear recursive pass mapping tree elements via global index increments.
 * Space Complexity: O(H) -> System recursion stack memory bounded strictly by tree height H.
 */

public class ConstructBinarySearchTreeFromPreorderTraversal {
    public TreeNode bstFromPreorder(int[] preorder) {
        
        // TC - O(N) & SC - O(H)
        // We can use an upperBound value in the recursive call
        // (Don't be confused with the upperBound used in the below approach)
        // This upperBound represents a value (permissible limit) for a particular sub-tree
        // For the current sub-tree, if the value exceeds upperBound, then
        // it's not valid

        // We traverse preorder exactly once. The upper bound determines whether 
        // the current value belongs to the current subtree or should be 
        // handled by an ancestor's recursive call.
        // We are using a mutable array as a parameter for the index because we 
        // want to be able to access the correct index for the right sub-tree 
        // after the left sub-tree has been formed

        return buildTree(preorder, new int[] {0}, Integer.MAX_VALUE);




        /*
        // TC - O(N) & SC - O(N)
        // Unlike the below approach where we were traversing the whole range 
        // for each sub-tree to find the rightStart value, we can precompute
        // the value using the monotonic stack approach and access the 
        // rightStart for any index in O(1) making the total TC O(N)

        int n = preorder.length;

        // Define stack and upperBound array
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int[] upperBound = new int[n];

        // Fill the upperBound with n in case if there is no greater value in the right
        // for a particular index
        Arrays.fill(upperBound, n);

        for (int i = 0; i < n; i++) {
            
            // Update the upperBound whenever we come across a greater value
            // than the one stored in the stack
            while (!stack.isEmpty() && preorder[i] > preorder[stack.peek()]) {
                upperBound[stack.pop()] = i;
            }

            // Push the curr value to the stack
            stack.push(i);
        }

        return buildTree(0, n - 1, preorder, upperBound);
        */




        /*
        // TC - O(N^2) & SC - O(H)
        // H - Height of the tree
        // The first element of the preorder array will be the root
        // We can recursively solve for subsequent sub-trees by dividing 
        // the preorder based on the start and end indices of the left and 
        // right sub-tree
        return buildTree(0, preorder.length - 1, preorder);
        */
    }
    
    public TreeNode buildTree(int[] preorder, int[] idx, int upperBound) {

        // Base Case : If we have reached the end of the preorder array
        // or, the curr value is greater than the upperBound
        if (idx[0] == preorder.length || preorder[idx[0]] > upperBound) {
            return null;
        }

        // Curr sub-tree's root
        TreeNode root = new TreeNode(preorder[idx[0]++]);

        // For the left sub-tree, we must update the upperBound to the root's value
        // as the next node must possess a lesser value than the current node's value
        root.left = buildTree(preorder, idx, root.val);

        // For the right sub-tree, we must keep the same upperBound value as
        // we generally visit the right sub-tree when we are bound to return
        // from the left sub-tree and so we must use the ancestor's upperBound
        // for the right sub-tree
        root.right = buildTree(preorder, idx, upperBound);

        return root;
    }

    public TreeNode buildTree(int start, int end, int[] preorder, int[] upperBound) {

        // Base Case
        if (start > end) return null;

        // Current sub-tree's root
        TreeNode root = new TreeNode(preorder[start]);
        // Start index of the right sub-tree
        int rightStart = upperBound[start];

        // Build the left and right sub-tree recursively
        root.left = buildTree(start + 1, rightStart - 1, preorder, upperBound);
        root.right = buildTree(rightStart, end, preorder, upperBound);

        // Return the current root
        return root;
    }

    public TreeNode buildTree(int start, int end, int[] preorder) {

        // Base Case
        if (start > end) return null;

        // value of the root of the current sub-tree
        int rootVal = preorder[start];
        // Current sub-tree's root
        TreeNode root = new TreeNode(rootVal);

        // Finding the start indices of the right sub-tree
        // So, the preorder sequence is partitioned into left and 
        // right subtree ranges using the BST property.
        int rightStart = end + 1;

        for (int i = start + 1; i <= end; i++) {
            // The first greater element than the root is the start of 
            // right sub-tree as the problem confirms it's a BST
            if (preorder[i] > rootVal) {
                rightStart = i;
                break;
            }
        }

        // Build the left and right sub-tree recursively
        root.left = buildTree(start + 1, rightStart - 1, preorder);
        root.right = buildTree(rightStart, end, preorder);

        // Return the current root
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
