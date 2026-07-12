/**
 * Problem: Flatten Binary Tree to Linked List
 * Link: https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 * 
 * Time Complexity: O(N) -> Linear in-place modification traversing edge paths.
 * Space Complexity: O(1) -> Absolute constant storage footprint with zero auxiliary structures.
 */

import java.util.ArrayDeque;

public class FlattenBinaryTreeToLinkedList {
    
    // Global variable
    TreeNode prev = null;

    public void flatten(TreeNode root) {
        
        // TC - O(N) & SC - O(1)
        // H = Height of the tree
        // Unlike the below approach that requires an additional queue to store the nodes
        // Also requires a second pass to make the linked list
        // We, instead use this approach to store to flatten the binary tree in a single pass
        // The rightmost child of the left sub-tree will be the one pointing to the right sub-tree
        // of the current node

        TreeNode curr = root;
        while (curr != null) {
            
            // If left sub-tree exists
            if (curr.left != null) {
                // Go to the rightmost child of the left sub-tree
                TreeNode predecessor = curr.left;

                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }

                // The rightmost child will point to the right sub-tree of the current node
                predecessor.right = curr.right;
                // Move the left sub-tree to the right pointer of the current node
                curr.right = curr.left;
            }

            // Set the left pointer to null
            curr.left = null;
            // Move further
            curr = curr.right;
        }
        



        /*
        // TC - O(N) & SC - O(H)
        // We are using a reversePreorder approach here, as we know
        // that we have to flatten the binary tree in preorder manner
        // So, we always have to take care about who comes next after the current node
        // in the preorder traversal because that's what curr node's right pointer 
        // will point to (but we don't know the next until we traverse to it)
        // We can traverse in reversePreorder (right -> left -> root)
        // so that we always know who comes before the current node and that is prev

        // Reset the global variable
        prev = null;
        reversePreorder(root);
        */




        /*
        // TC - O(N) & SC - O(N)
        // We Store all the nodes in a queue in preorder manner
        // Then we traverse the queue to make the linked list

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();

        // Fill the queue in preorder manner
        preorder(queue, root);

        // dummy node to handle the root / head
        TreeNode dummy = queue.poll();

        // traverse the queue and keep pointing the curr node
        // to the prev node's left
        while (!queue.isEmpty()) {
            dummy.right = queue.poll();

            // Set the left pointer to null
            dummy.left = null;
            dummy = dummy.right;
        }

        dummy.left = null;
        */
    }

    public void reversePreorder(TreeNode root) {
        
        // Base Case
        if (root == null) return;

        // ReversePreorder (right -> left -> root)
        reversePreorder(root.right);
        reversePreorder(root.left);

        root.right = prev;
        root.left = null;

        // Update the prev to the curr node
        prev = root;
    }

    public void preorder(ArrayDeque<TreeNode> queue, TreeNode root) {

        if (root == null) return;

        queue.offer(root);

        preorder(queue, root.left);
        preorder(queue, root.right);
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
