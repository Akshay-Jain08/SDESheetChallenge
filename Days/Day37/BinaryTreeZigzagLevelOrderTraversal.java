/**
 * Problem: Binary Tree Zigzag Level Order Traversal
 * Link: https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 * 
 * Time Complexity: O(N) -> Single linear traversal pass manipulating double-ended pointers in O(1).
 * Space Complexity: O(W) -> Space requirements bounded strictly by the maximum tree layer width W.
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        
        // TC - O(N) & SC - O(W)
        // W = Maximum width of the tree
        // Unlike the standard solution, this approach changes
        // the traversal direction itself using a deque.
        // The deque is used both as the BFS queue and to
        // maintain the correct visiting order for each level.
        // Though, the below approach can feel somewhat better because of its simpler implementation

        List<List<Integer>> result = new ArrayList<>();

        // Edge Case
        if (root == null) return result;

        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);

        // Set the flag to true initially
        boolean leftToRight = true;

        while (!deque.isEmpty()) {

            // No. of elements at the current level
            int size = deque.size();
            List<Integer> levelList = new ArrayList<>();

            while (size-- > 0) {

                TreeNode curr;

                // If the flag is true
                if (leftToRight) {
                    
                    // retrieve the curr element as usual (pollFirst)
                    curr = deque.pollFirst();

                    // Enqueue the children as usual (offerLast)
                    if (curr.left != null) {
                        deque.offerLast(curr.left);
                    }
                    if (curr.right != null) {
                        deque.offerLast(curr.right);
                    }
                }
                // If the flag is false
                else {
                    
                    // retrieve the curr element opposite to usual (pollLast)
                    // Process the current level from right to left.
                    curr = deque.pollLast();

                    // Enqueue the children opposite to usual (offerFirst)
                    // Insert children at the front in reverse order so that
                    // the next level is processed from left to right.
                    if (curr.right != null) {
                        deque.offerFirst(curr.right);
                    }
                    if (curr.left != null) {
                        deque.offerFirst(curr.left);
                    }
                }

                levelList.add(curr.val);
            }

            result.add(levelList);
            // Reverse the flag for the next iteration
            leftToRight = !leftToRight;
        }

        return result;




        /*
        // TC - O(N) & SC - O(W)
        // W = Maximum width of the tree
        // This approach uses standard BFS traversal and a leftToRight flag
        // When the flag is true, we add to the levelList in the normal fashion
        // When the flag is false, we can add the values at the start of the levelList
        // causing all the elements to shift further but reversing the list for the curr level

        List<List<Integer>> result = new ArrayList<>();

        // Edge Case
        if (root == null) return result;

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        // Flag 
        boolean leftToRight = true;

        while (!queue.isEmpty()) {

            // No. of elements at the current level
            int size = queue.size();
            Deque<Integer> levelList = new ArrayDeque<>();

            while (size-- > 0) {

                TreeNode curr = queue.poll();

                // If the flag is true, add the elements normally
                if (leftToRight) {
                    levelList.offerLast(curr.val);
                }
                // If it is false, add the elements at the start (0th index)
                else {
                    levelList.offerFirst(curr.val);
                }

                // Enqueue the children to the queue
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }

            // Reverse the flag for the next iteration
            leftToRight = !leftToRight;

            // add the curr level list to the result
            result.add(new ArrayList<>(levelList));
        }

        return result;
        */
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
