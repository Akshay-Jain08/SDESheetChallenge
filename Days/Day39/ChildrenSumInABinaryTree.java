/**
 * Problem: Children Sum Property
 * Link: https://www.geeksforgeeks.org/problems/children-sum-parent/1
 * 
 * Time Complexity: O(N) -> Single linear recursive pass verifying child sum constraints.
 * Space Complexity: O(H) -> System recursion stack memory bounded strictly by tree height H.
 */

public class ChildrenSumInABinaryTree {
    public boolean isSumProperty(Node root) {
        
        // TC - O(N) & SC - O(H)
        // Traverse every node and verify that each 
        // non-leaf node equals the sum of its existing children.
        
        // Edge Case
        if (root == null) return true;
        
        int childSum = 0;
        
        // If both the children are null, then it's a valid node
        if (root.left == null && root.right == null) return true;
        
        // Add the non-null children's value to the childSum
        if (root.left != null) childSum += root.left.data;
        if (root.right != null) childSum += root.right.data;
        
        // Perform the check : If not equal, return false
        if (root.data != childSum) return false;
        
        // Recursively verify the property for both subtrees.
        return isSumProperty(root.left) && isSumProperty(root.right);
    }

    static class Node{
        int data;
        Node left, right;
        Node(int key)
        {
            data = key;
            left = right = null;
        }
    }
}
