/**
 * Problem: Predecessor and Successor
 * Link: https://www.geeksforgeeks.org/problems/predecessor-and-successor/1
 * 
 * Time Complexity: O(H) -> Logarithmic lookup pass bounded strictly by tree height H.
 * Space Complexity: O(1) -> Space-optimized constant auxiliary storage with zero stack usage.
 */

import java.util.ArrayList;

public class PredecessorAndSuccessor {
    public ArrayList<Node> findPreSuc(Node root, int key) {
        
        // TC - O(H) & SC - O(1)
        // For finding the predecessor and successor of the key,
        // (the key may or may not be present in the tree)
        // We will be using two variables for predecessor and successor 
        // Everytime, we go to the left sub-tree, update the successor
        // (because the curr node will be just greater element out of all visited for the next node)
        // Everytime, we go to the right sub-tree, update the predecessor
        // (because the curr node will be the just lesser element out of all visited ones for the next node)
        
        // Result list initialization
        ArrayList<Node> result = new ArrayList<>();
        
        // Predecessor and Successor variables initialization
        Node predecessor = null, successor = null;
        
        // We run the loop until we move out of the tree
        while (root != null) {
            
            // If the curr node possess the value same as key
            // The two required values would be rightmost element in the left sub-tree
            // and the leftmost element in the right sub-tree
            if (root.data == key) {
                
                Node left = root.left;
                Node right = root.right;
                
                while (left != null && left.right != null) {
                    left = left.right;
                }
                
                while (right != null && right.left != null) {
                    right = right.left;
                }
                
                // In case, if there is no left or right sub-tree for the curr node
                // we can look for the values already possessed from the ancestors
                result.add(left == null ? predecessor : left);
                result.add(right == null ? successor : right);
                return result;
            }
            
            // If the key is smaller, move to the left and update the successor
            else if (root.data > key) {
                
                successor = root;
                root = root.left;
            }
            
            // If the key is greater, move to the right and update the predecessor
            else if (root.data < key) {
                
                predecessor = root;
                root = root.right;
            }
        }
        
        // Add the predecessor and successor to the result and return
        result.add(predecessor);
        result.add(successor);
        return result;
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
