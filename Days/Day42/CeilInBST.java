/**
 * Problem: Ceil in BST
 * Link: https://www.geeksforgeeks.org/problems/implementing-ceil-in-bst/1
 * 
 * Time Complexity: O(H) -> Logarithmic branch scanning pass bounded strictly by tree height H.
 * Space Complexity: O(1) -> Space-optimized constant auxiliary storage with zero stack usage.
 */

public class CeilInBST {
    int findCeil(Node root, int k) {
        
        // TC - O(H) & SC - O(1)
        // This process uses the BST property to find the ceil value
        // in just one traversal from root to leaf
        
        // Initialize the ceil with -1, in case if there is no possible ceil value
        int ceil = -1;
        
        // Traverse the tree until the end
        while (root != null) {
            
            // If the key is equal to the curr node's value, return the value
            if (k == root.data) {
                return root.data;
            }
            
            // If the key is smaller, move to the left sub-tree
            // Also, update the ceil value as the curr node's value
            // is the best ceil value that we have seen so far
            else if (k < root.data) {
                ceil = root.data;
                root = root.left;
            }
            
            // If the key is greater, move to the right sub-tree
            else {
                root = root.right;
            }
        }
        
        return ceil;
        
        
        
        
        /*
        // TC - O(N) & SC - O(H)
        // We do the inorder traversal and the property of BST that
        // the inorder traversal is always sorted
        // The first elements that is greater than or equal to the
        // key would be the ceil of the key
        
        return inorder(root, k);
        */
    }
    
    public int inorder(Node root, int k) {
        
        // Base Case
        if (root == null) return -1;
        
        // Move to the left sub-tree first
        int left = inorder(root.left, k);
        // If the ans from the left sub-tree is not -1, return the ans
        if (left != -1) return left;
        
        // If the curr node's value is greater or equal to key, it is the ceil
        if (root.data >= k) return root.data;
        
        // Move to the right sub-tree
        int right = inorder(root.right, k);
        // If the ans from the right sub-tree is not -1, return the ans
        if (right != -1) return right;
        
        // If after traversing the whole tree, we could not find any ceil
        // value, return -1
        return -1;
    }

    static class Node {
        int data;
        Node left;
        Node right;
        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
}
