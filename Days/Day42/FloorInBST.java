/**
 * Problem: Floor in BST
 * Link: https://www.geeksforgeeks.org/problems/closest-neighbor-in-bst/1
 * 
 * Time Complexity: O(H) -> Logarithmic branch scanning pass bounded strictly by tree height H.
 * Space Complexity: O(1) -> Space-optimized constant auxiliary storage with zero stack usage.
 */

public class FloorInBST {
    public int findMaxFork(Node root, int k) {
        
        // TC - O(H) & SC - O(1)
        // We use the property of BST to solve this problem
        // i.e. All the nodes in the left sub-tree will be smaller than the root
        // and all the nodes in the right sub-tree will be greaater than the root
        
        // Initialize the floor value with -1 (in case if we find no possible value for
        // floor in the tree)
        int floor = -1;
        
        // Traverse the tree until we reach the end
        while (root != null) {
            
            // If the curr node's value is equal to the key, return the node's value
            if (root.data == k) {
                return root.data;
            }
            
            // If key is smaller, move to the left sub-tree
            else if (k < root.data) {
                root = root.left;
            }
            
            // If the key is greater, move to the right sub-tree
            // Also, store the curr node's value to the floor (as the curr
            // node is a probable answer for the tree)
            else {
                floor = root.data;
                root = root.right;
            }
        }
        
        return floor;
        
        
        
        
        
        /*
        // TC - O(N) & SC - O(H)
        // This approach uses the inorder traversal property of BST
        // that the inorder traversal is always sorted
        // We pass a mutable array for the floor value in the recursive calls, 
        // the first value which is greater than the key, would be the point
        // when the current floor value would be the floor of the key
        
        int[] floor = {-1};
        
        inorder(root, k, floor);
        
        return floor[0];
        */
    }
    
    public boolean inorder(Node root, int k, int[] floor) {
        
        // Base Case
        if (root == null) return false;
        
        // Traverse left sub-tree first
        if (inorder(root.left, k, floor)) return true;
        
        // First node with value greater than k, return true
        if (root.data > k) return true;
        
        // If the above check fails, update the floor value and move on to the
        // right sub-tree
        floor[0] = root.data;
        
        if (inorder(root.right, k, floor)) return true;
        
        // Traversal completed without encountering any value greater than k.
        // Therefore, floor[0] already stores the largest value in the tree
        // that is <= k (or -1 if no such value exists).
        return false;
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
