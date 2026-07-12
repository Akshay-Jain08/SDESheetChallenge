/**
 * Problem: Construct Binary Tree from Preorder and Inorder Traversal
 * Link: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * 
 * Time Complexity: O(N) -> Linear tree reconstitution via O(1) hash map index partitioning.
 * Space Complexity: O(N) -> Memory footprint caching element indices and processing sub-tree ranges.
 */

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromInorderAndPreorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        
        
        // TC - O(N) & SC - O(N)
        // We will be breaking the problem down at each recursive level
        // Since, we are given inorder and preorder traversal of the tree
        // So, for the current sub-tree, the root will be the first element of the preorder
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

        return buildTree(inorder, 0, n - 1, preorder, 0, n - 1, map);




        /*
        // TC - O(N) & SC - O(N)
        // This is the iterative way of solving this problem
        // We will maintain a stack that will maintain the order of
        // all the ancestors of the current node

        int n = preorder.length;

        // Using map for the fast retrieval of indices from the inorder
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }

        // root of the tree would be the first value of the preorder
        TreeNode root = new TreeNode(preorder[0]);
        TreeNode parent = root;

        // Define a stack and push the root node
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(parent);

        // Traverse the preorder as every next element of this traversal
        // would be the root of the subsequent sub-tree
        // The stack maintains all the nodes in order whose left child have been
        // processed and the right child is yet to be processed
        // The stack stores the path from the root to the current node. 
        // Nodes leftNodes on the stack until their right subtree has been attached.
        for (int i = 1; i < n; i++) {
            
            int currVal = preorder[i];

            // If the child is at left side of the parent in the inorder
            // That means it would be placed at the left sub-tree
            if (map.get(currVal) < map.get(stack.peek().val)) {
                parent.left = new TreeNode(currVal);
                parent = parent.left;

                // push the left child to the stack after adding it to the tree
                stack.push(parent);
            }
            // If the child is at the right side of the parent, so
            // to correctly determine the position of the child
            // we backtrack to the ancestors until it follows the condition for being a right child
            else {
                while (!stack.isEmpty() && map.get(currVal) > map.get(stack.peek().val)) {
                    parent = stack.pop();
                }

                parent.right = new TreeNode(currVal);
                parent = parent.right;

                // push the right child to the stack after adding it to the tree
                stack.push(parent);
            }
        }

        return root;
        */
    }

    public TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] preorder, int preStart, int preEnd, Map<Integer, Integer> map) {

        // Base Case
        if (inStart > inEnd || preStart > preEnd) return null;

        // current node 
        TreeNode root = new TreeNode(preorder[preStart]);

        // index of the current node in the inorder
        int rootIdx = map.get(root.val);
        // no. of elements at left of the root (those would be placed at the left sub-tree)
        int leftNodes = rootIdx - inStart;

        // build left sub-tree
        root.left = buildTree(inorder, inStart, rootIdx - 1, preorder, preStart + 1, preStart + leftNodes, map);

        // build right sub-tree
        root.right = buildTree(inorder, rootIdx + 1, inEnd, preorder, preStart + leftNodes + 1, preEnd, map);

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
