/**
 * Problem: Construct Binary Tree from Inorder and Postorder Traversal
 * Link: https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * 
 * Time Complexity: O(N) -> Linear tree reconstitution via O(1) hash map index partitioning.
 * Space Complexity: O(N) -> Memory footprint caching element indices and processing sub-tree ranges.
 */

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        
        // TC - O(N) & SC - O(N)
        // We will be breaking the problem down at each recursive level
        // Since, we are given inorder and postorder traversal of the tree
        // So, for the current sub-tree, the root will be the last element of the postorder
        // and its subsequent left and right sub-tree can be determined with
        // the help of the inorder (left elements to the root would be placed in the 
        // left sub-tree while the right elements to the root would be placed in the 
        // right sub-tree)

        int n = inorder.length;

        // Using a map for fast retrieval of the index of the values
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }

        return buildTree(inorder, 0, n - 1, postorder, 0, n - 1, map);
        




        /*
        // TC - O(N) & SC - O(N)
        // This is the iterative way of solving this problem
        // We will maintain a stack that will maintain the order of
        // all the ancestors of the current node

        int n = inorder.length;

        // Using map for the fast retrieval of indices from the inorder
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }

        // root of the tree would be the last value of the postorder
        TreeNode root = new TreeNode(postorder[n - 1]);
        TreeNode parent = root;

        // Define a stack and push the root node
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(parent);

        // Traverse the postorder in backward manner as the next element at every iteration
        // would be the root of the subsequent sub-tree
        // The stack maintains all the nodes in order whose right child have been
        // processed and the left child is yet to be processed
        // The stack stores the path from the root to the current node.
        // Nodes remain on the stack until their left subtree has been attached.
        for (int i = n - 2; i >= 0; i--) {

            int currVal = postorder[i];

            // If the child is at right side of the root in the inorder
            // That means it would be placed at the right sub-tree
            if (map.get(currVal) > map.get(stack.peek().val)) {
                parent.right = new TreeNode(currVal);
                parent = parent.right;

                // push the right child to the stack after adding it to the tree
                stack.push(parent);
            }
            // If the child is at the left side of the root, so
            // to correctly determine the position of the child
            // we backtrack to the ancestors until it follows the condition for being a left child
            else {
                while (!stack.isEmpty() && map.get(currVal) < map.get(stack.peek().val)) {
                    parent = stack.pop();
                }

                parent.left = new TreeNode(currVal);
                parent = parent.left;

                // push the left child to the stack after adding it to the tree
                stack.push(parent);
            }
        }

        return root;
        */
    }

    public TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd, Map<Integer, Integer> map) {

        // Base Case
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }

        // current node
        TreeNode root = new TreeNode(postorder[postEnd]);
        
        // index of the current node in the inorder
        int rootIdx = map.get(root.val);
        // no. of elements at left of the root (those would be placed at the left sub-tree)
        int leftNodes = rootIdx - inStart;

        // build left sub-tree
        root.left = buildTree(inorder, inStart, rootIdx - 1, postorder, postStart, postStart + leftNodes - 1, map);

        // build right sub-tree
        root.right = buildTree(inorder, rootIdx + 1, inEnd, postorder, postStart + leftNodes, postEnd - 1, map);

        // return the current node to its parent
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
