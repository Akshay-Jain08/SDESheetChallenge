/**
 * Problem: Tree Traversals (Inorder, Preorder and Postorder in One Pass)
 * Link: https://www.naukri.com/code360/problems/tree-traversal_981269?leftPanelTabValue=PROBLEM&count=25&page=2&search=&sort_entity=order&sort_order=ASC
 * 
 * Time Complexity: O(N) -> Single linear unified traversal mapping nodes via 3 distinct lifecycle states.
 * Space Complexity: O(H) -> Auxilary stack storage memory bounded tightly by maximum tree height H.
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class PrePostInorderInOneTraversal {
    public static List<List<Integer>> getTreeTraversal(TreeNode root) {

        // TC - O(N) & SC - O(H)
        // H = height of the tree
        // This approach uses a value(state) with every node to determine
        // whether to store the particular value in preorder, inorder, or postorder list
        // This might not be very intuitive but it would made sense by doing dry run on paper
        List<List<Integer>> result = new ArrayList<>();

        // Add 3 lists, one for each type of traversal
        for (int i = 0; i < 3; i++) {
            result.add(new ArrayList<>());
        }

        // Edge Case
        if (root == null) return result;

        ArrayDeque<Pair> stack = new ArrayDeque<>();
        stack.push(new Pair(root, 1));

        while (!stack.isEmpty()) { 

            Pair pair = stack.pop();

            // If the state is 1, add the value to preorder list,
            // update the value to 2, push to the stack
            // and go to the left subtree
            if (pair.state == 1) {
                result.get(1).add(pair.node.data);
                pair.state++;
                stack.push(pair);

                if (pair.node.left != null) {
                    stack.push(new Pair(pair.node.left, 1));
                }
            }

            // If the state is 2, add the value to inorder list,
            // update the value to 3, push to the stack
            // and go to the right subtree
            else if (pair.state == 2) {
                result.get(0).add(pair.node.data);
                pair.state++;
                stack.push(pair);

                if (pair.node.right != null) {
                    stack.push(new Pair(pair.node.right, 1));
                }
            }

            // If the state is 3, add the value of postorder list
            // do not need to add or visit the tree further
            else {
                result.get(2).add(pair.node.data);
            }
        }

        return result;
    }

    // Pair class to be used as a generic with Stack
    static class Pair {
        TreeNode node;
        int state;

        Pair(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int data) { this.data = data; }
        TreeNode(int data, TreeNode left, TreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
}
