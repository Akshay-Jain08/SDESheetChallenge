/**
 * Problem: Vertical Order Traversal of a Binary Tree
 * Link: https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
 * 
 * Time Complexity: O(N * (log C + log R + log K)) -> Linear node insertion via sequential tree map lookups.
 * Space Complexity: O(N)                          -> Linear storage matching total tree size properties.
 */


import java.util.*;

public class VerticalOrderTraversalOfABinaryTree {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        
        // TC - O(N(logC + logR + logK)) & SC - O(N)
        // C = No. of columns
        // R = No. of rows
        // K = No. of elements at a particular index
        // We perform a BFS traversal and store every node grouped by column, 
        // then row, while a PriorityQueue keeps values sorted when multiple 
        // nodes occupy the same coordinates.

        //  Edge Case
        if (root == null) return new ArrayList<>();

        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();

        Queue<Node> queue = new LinkedList<>();
        // Add the initial value as root at 0th row and 0th col
        queue.offer(new Node(root, 0, 0));

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            TreeNode node = curr.node;
            int r = curr.row;
            int c = curr.col;

            // Keep adding the values to the map at each iteration
            map.computeIfAbsent(c, k -> new TreeMap<>())
            .computeIfAbsent(r, k -> new PriorityQueue<>())
            .offer(node.val);

            // For the left child, it'll be row + 1, and col - 1
            if (node.left != null) {
                queue.offer(new Node(node.left, r + 1, c - 1));
            }
            // For the right child, it'll be row + 1, and col + 1
            if (node.right != null) {
                queue.offer(new Node(node.right, r + 1, c + 1));
            }
        }

        List<List<Integer>> result = new ArrayList<>();

        // Iterate over the map to store the values in the result
        for (TreeMap<Integer, PriorityQueue<Integer>> col : map.values()) {

            // list to store all the elements at a particular col
            List<Integer> list = new ArrayList<>();

            for (PriorityQueue<Integer> values : col.values()) {

                while (!values.isEmpty()) {
                    list.add(values.poll());
                }
            }

            // add the current col list to the result and move to the next col
            result.add(list);
        }

        return result;





        /*
        // TC - O(N logN) & SC - O(N)
        // This approach uses standard DFS to store all the nodes(val) in an additional
        // list along with its coordinates
        // After performing the traversal, we can perform a global sorting on the list
        // firstly based on the horizontal distance(col), then based on the vertical distance(row),
        // and lastly based on the value
        // This ensures that we get the values sorted according the horizontal distances
        // and keep the order of the items in a particular col while handling the case
        // when two items collide at the same coordinates (row, col)

        // Edge Case
        if (root == null) return new ArrayList<>();

        // List to store all the elements
        List<Node> list = new ArrayList<>();

        dfs(root, list, 0, 0);

        // Sort the list
        Collections.sort(list, (a, b) -> {
            // First compare the col (horizontal dist)
            if (a.col != b.col) {
                return a.col - b.col;
            }
            // If comparing within the same col, then compare the row(vertical dist)
            if (a.row != b.row) {
                return a.row - b.row;
            }
            // If comparing on the same set of coordinates, then compare the value
            return a.val - b.val;
        });

        List<List<Integer>> result = new ArrayList<>();

        int prevCol = Integer.MIN_VALUE;
        List<Integer> curr = new ArrayList<>();

        // Traverse the list and keep storing all the elements of every col together
        for (Node t : list) {

            // If we reach a new col, then add the elements of the previous col
            if (t.col != prevCol) {
                if (!curr.isEmpty()) {
                    result.add(curr);
                }

                // Reset the curr list for the curr col
                curr = new ArrayList<>();
                // update the curr col
                prevCol = t.col;
            }

            // keep adding all the values of the curr col
            curr.add(t.val);
        }

        // add the last col to the result
        if (!curr.isEmpty()) {
            result.add(curr);
        }

        return result;
        */




        /*
        // TC - O(N + W * H + Sum(K logK)) & SC - O(N)
        // W = Maximum width of the tree
        // H = Height of the treee
        // K = no. of elements at a particular coordinate
        // Since, we have to do vertical order traversal, so we are taking vertical level or horizontal
        // distance from the root in consideration, and if two or more elements collide at the same 
        // coordinates, we must add them in a sorted manner, that's why along with the horizontal 
        // distance, we are also using depth from the root

        if (root == null) {
            return new ArrayList<>();
        }
        
        // Map to store elemenets of all the coordinates
        Map<List<Integer>, List<Integer>> map = new HashMap<>();

        // pointers that we will be using later to traverse over the map
        // in a sorted manner without having to use a TreeMap
        // which would cost us logN per operation
        int leftBoundary = 0;
        int rightBoundary = 0;
        int depth = 0;

        Queue<Node> queue = new LinkedList<>();
        // store the root node with horizontal distance from the root as 0, and depth as 0
        queue.offer(new Node(root, 0, 0));

        while (!queue.isEmpty()) {
            
            Node curr = queue.poll();

            // coordinates for the current node
            List<Integer> coor = List.of(curr.row, curr.col);

            // Update the map
            map.putIfAbsent(coor, new ArrayList<>());
            map.get(coor).add(curr.node.val);

            // Update the leftBoundary, rightBoundary and depth
            leftBoundary = Math.min(leftBoundary, curr.col);
            rightBoundary = Math.max(rightBoundary, curr.col);
            depth = Math.max(depth, curr.row);

            // left child will be, horizontal distance - 1 and vertical distance(depth) + 1;
            if (curr.node.left != null) {
                queue.offer(new Node(curr.node.left, curr.row + 1, curr.col - 1));
            }
            // left child will be, horizontal distance + 1 and vertical distance(depth) + 1;
            if (curr.node.right != null) {
                queue.offer(new Node(curr.node.right, curr.row + 1, curr.col + 1));
            }
        }

        List<List<Integer>> result = new ArrayList<>();

        // Traverse all the coordinates to store the elements in the result in a vertical manner
        for (int c = leftBoundary; c <= rightBoundary; c++) {
            
            // list to store all the elements of a particular horizontal distance
            List<Integer> list = new ArrayList<>();

            for (int r = 0; r <= depth; r++) {
                // sublist to store the elements at a particular depth of the curr horizontal distance
                List<Integer> subList = map.get(List.of(r, c));

                // Check null before adding to the list
                if (subList != null) {
                    // Sort the elements
                    Collections.sort(subList);
                    list.addAll(subList);
                }
            }

            result.add(list);
        }

        return result;
        */
    }

    public void dfs(TreeNode node, List<Node> list, int row, int col) {

        if (node == null) return;

        list.add(new Node(row, col, node.val));

        dfs(node.left, list, row + 1, col - 1);
        dfs(node.right, list, row + 1, col + 1);
    }

    // Node class that we will be using as a generic for the queue
    static class Node {
        TreeNode node;
        int row;
        int col;
        int val;

        // Constructor for BFS + HashMap approach
        Node(TreeNode node, int row, int col) {
            this.node = node;
            this.row = row;
            this.col = col;
        }

        // Constructor for DFS + global sort approach
        Node(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }

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
}
