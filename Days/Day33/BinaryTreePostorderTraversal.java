/**
 * Problem: Binary Tree Postorder Traversal
 * Link: https://leetcode.com/problems/binary-tree-postorder-traversal/
 * 
 * Time Complexity: O(N) -> Linear traversal sweep followed by a fast linear in-place array reversal.
 * Space Complexity: O(H) -> Storage footprints bounded tightly by the active tree height stack.
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryTreePostorderTraversal {

    class TreeNode {
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

    public List<Integer> postorderTraversal(TreeNode root) {
        
        // TC - O(N) & SC - O(N)
        // This is an optimized version of the two-stack approach.
        // Instead of storing nodes in a second stack,
        // we store them directly in the result list and reverse it once.
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        ArrayDeque<TreeNode> stack = new ArrayDeque<>();

        // start with the root
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            // adding the curr val first as we will reverse the list later
            result.add(node.val);

            // Push left child first.
            // Since stack is LIFO, right child will be processed before left.
            if (node.left != null) stack.push(node.left);
            // Go to the right sub-tree
            if (node.right != null) stack.push(node.right);
        }

        Collections.reverse(result);
        return result;




        /*
        // TC - O(N) & SC - O(H)
        // H = height of the tree (worst case O(N), balanced O(log N))
        // This version uses only a single stack but it's kind of less intuitive if looking at the code
        // Dry run is necessary to understand it

        List<Integer> result = new ArrayList<>();

        // Edge Case
        if (root == null) return result;

        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {

            // Keep going to the leftmost child
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            else {
                // After reaching the leftmost child, store its right child to temp
                TreeNode temp = stack.peek().right;

                // If right child is null (means the child is alone or leaf node)
                if (temp == null) {
                
                    // remove it from the stack and store its value
                    temp = stack.pop();
                    result.add(temp.val);

                    // At this point we are at a rightmost child of a (node)
                    // so keep moving up and keep adding its value in the result
                    // temp == stack.peek().right checks that we are moving up in that right
                    // line only and haven't moved yet to the parent of the (node)
                    while (!stack.isEmpty() && temp == stack.peek().right) {
                        temp = stack.pop();
                        result.add(temp.val);
                    }
                }
                // If the right child is not null, assign it to curr
                // as we have to look upon its left sub-tree first
                else {
                    curr = temp;
                }
            }
        }
        return result;
        */



        /*
        // TC - O(N) & SC - O(N) 
        // This is the iterative approach for postorder traversal
        // which uses 2 stacks - one for traversing and one for storing the result, then 
        // be able to store in the resulting list in the reverse order

        // Note: We can skip the second stack, and directly store in the list
        // reverse it later
        List<Integer> result = new ArrayList<>();

        // Edge Case
        if (root == null) return result;

        ArrayDeque<TreeNode> st1 = new ArrayDeque<>();
        ArrayDeque<TreeNode> st2 = new ArrayDeque<>();
        TreeNode node = root;
        
        // st1 stores the nodes which need to be traverse further down
        st1.push(node);

        while (!st1.isEmpty()) {

            // current node
            node = st1.pop();

            // Add the left child if not null
            if (node.left != null) st1.push(node.left);
            // Add the right child if not null
            if (node.right != null) st1.push(node.right);

            // Store the node in reverse postorder.
            // Popping from st2 later gives Left -> Right -> Root.
            st2.push(node);
        }

        // store in the result list (essentially reversing the order)
        while (!st2.isEmpty()) {
            result.add(st2.pop().val);
        }

        return result;
        */



        /*
        // TC - O(N) & SC - O(H)
        // H = height of the tree (worst case O(N), balanced O(log N))
        // This is the recursive approach for the postorder traversal
        List<Integer> res = new ArrayList<>();

        // helper method to execute the traversal
        helper(res, root);

        return res;
        */
    }

    public void helper(List<Integer> res, TreeNode root) {

        // Base Case
        if (root == null) return;

        // Go to the left sub-tree
        helper(res, root.left);
        // Go to the right sub-tree
        helper(res, root.right);
        // Add the current value
        res.add(root.val);
    }
}
