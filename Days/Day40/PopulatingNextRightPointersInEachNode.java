/**
 * Problem: Populating Next Right Pointers in Each Node
 * Link: https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 * 
 * Time Complexity: O(N) -> Single linear traversal pass visiting all tree nodes.
 * Space Complexity: O(1) -> Space-optimized constant auxiliary storage with zero stack usage.
 */

public class PopulatingNextRightPointersInEachNode {
    public Node connect(Node root) {
        
        // TC - O(N) & SC - O(1)
        // This approach solved the problem in constant space only as directed in the follow-up
        // Since, we are given a perfect binary tree, that means every node will have
        // two children and there is no gap (or null) in between a level
        // So we can connect the children of a node with node.left.next = node.right
        // and for the further connection, we can do node.right.next = node.next.left

        // The next pointers themselves become our traversal mechanism.
        // Once one level is connected, we can walk across that level
        // without any auxiliary data structure.

        // Edge Case
        if (root == null) return root;
        
        // we start with the leftMost node at each level
        Node leftMost = root;

        // This check prevents us to go to leaf node
        // as the last level will be connected by the second last level
        while (leftMost.left != null) {
            
            Node curr = leftMost;

            while (curr != null) {

                // Connect the two children of a node
                curr.left.next = curr.right;

                // If the curr node has a next pointer to some node
                // that means there is still some connection need to be done
                // before we move on to the next level
                if (curr.next != null) {
                    // Set up the further connection
                    curr.right.next = curr.next.left;
                }

                // move the curr node ahead in its level
                curr = curr.next;                
            }

            // Go to the next level
            leftMost = leftMost.left;
        }

        return root;
        



        /*
        // TC - O(N) & SC - O(H)
        // This is a recursive solution where we pass two nodes and 
        // connect the two nodes at each recursive call

        // Edge Case
        if (root == null) return null;

        // Passed the left and right node as first parameters
        connectNeighbours(root.left, root.right);

        return root;
        */



        /*
        // TC - O(N) & SC - O(W)
        // W - Maximum width of the tree
        // Perform a standard BFS traversal and at each level, connect
        // every node's next pointer to the next node in the queue

        // Edge Case    
        if (root == null) return null;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            
            // Size of the current level
            int size = queue.size();

            while (size-- > 0) {

                // Current Node at a particular level
                Node curr = queue.poll();

                // Set the next pointer to the next node in the queue
                // Set it to null when it's the last node in the queue
                curr.next = size == 0 ? null : queue.peek();

                // Enqueue the children
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
        }

        return root;
        */
    }

    public void connectNeighbours(Node left, Node right) {
        
        // Base Case : If either of them is null, return
        if (left == null) return;

        // Connect the two nodes
        left.next = right;

        // Follow with 3 recursive calls, 
        // for each pair of their children
        connectNeighbours(left.left, left.right);
        connectNeighbours(left.right, right.left);
        connectNeighbours(right.left, right.right);
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;
        public Node() {}
        public Node(int _val) { val = _val; }
        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
