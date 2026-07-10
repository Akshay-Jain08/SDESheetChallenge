/**
 * Problem: Boundary Traversal of Binary Tree
 * Link: https://www.geeksforgeeks.org/problems/boundary-traversal-of-binary-tree/1
 * 
 * Time Complexity: O(N) -> Linear segmented traversal loops processing boundaries and leaf layers.
 * Space Complexity: O(N) -> Driven by the auxiliary right boundary stack and system recursion stack.
 */

import java.util.ArrayDeque;
import java.util.ArrayList;

public class TreeBoundaryTraversal {
    ArrayList<Integer> boundaryTraversal(Node root) {
        
        // TC - O(N) & SC - O(N)
        // For Boundary traversal, we can separately traverse the left
        // boundary, leaf nodes, and the right boundary
        // and the elements to the result list
        
        ArrayList<Integer> result = new ArrayList<>();
        
        // Edge Case
        if (root == null) return result;
        
        // If the root is not the only element in the tree
        // preventing the duplication of the root node (in the leaf node traversal)
        if (root.left != null || root.right != null) {
            result.add(root.data);
        }
        
        // Using a stack for the right boundary traversal
        // as the elements need to be stored in the reverse manner
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        
        // Do the leftBoundary traversal first
        addLeftBoundary(result, root.left);
        // then leaf nodes traversal
        addLeafNodes(result, root);
        // then rightBoundary traversal and store in a stack initially
        addRightBoundary(stack, root.right);
        
        // Transfer from stack to result so that elements get
        // stored in the reverse manner
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        
        return result;
    }
    
    public void addLeftBoundary(ArrayList<Integer> result, Node root) {
        
        // Base Case : do not include leaf nodes in leftBoundary traversal
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        
        // add the curr element to the result
        result.add(root.data);
        
        // for leftBoundary, prefer left child over the right child
        if (root.left != null) {
            addLeftBoundary(result, root.left);
        }
        // If the left child is null, then go to the right child
        else {
            addLeftBoundary(result, root.right);
        }
    }
    
    public void addRightBoundary(ArrayDeque<Integer> stack, Node root) {
        
        // Base Case : do not include leaf nodes in the rightBoundary traversal
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        
        // add the curr element to the stack
        stack.push(root.data);
        
        // for rightBoundary, prefer right child over the left child
        if (root.right != null) {
            addRightBoundary(stack, root.right);
        }
        // If the right child is null, then go to the left child
        else {
            addRightBoundary(stack, root.left);
        }
    }
    
    public void addLeafNodes(ArrayList<Integer> result, Node root) {
        
        // Traverse the whole tree and collect only leaf nodes.
        // The traversal order (preorder/inorder/postorder) does not matter,
        // because only leaf nodes are added.
        // Base Case
        if (root == null) return;
        
        // If the curr node is a leaf node, add it to the result
        if (root.left == null && root.right == null) {
            result.add(root.data);
            return;
        }
        
        // Go to the left sub-tree, then right sub-tree 
        addLeafNodes(result, root.left);
        addLeafNodes(result, root.right);
    }

    static class Node {
        int data;
        Node left, right;

        Node(int val) {
            data = val;
            left = right = null;
        }
    }
}
