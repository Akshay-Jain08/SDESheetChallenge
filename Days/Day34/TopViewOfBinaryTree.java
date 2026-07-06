/**
 * Problem: Top View of Binary Tree
 * Link: https://www.geeksforgeeks.org/problems/top-view-of-binary-tree/1
 * 
 * Time Complexity: O(N) -> Single linear BFS pass with O(1) conditional map lookups.
 * Space Complexity: O(W + M) -> Space overhead tracking the queue width and horizontal lines.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class TopViewOfBinaryTree {
    
    static class Pair {
        Node node;
        int pos;
        
        Pair(Node node, int pos) {
            this.node = node;
            this.pos = pos;
        }
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

    public ArrayList<Integer> topView(Node root) {
        
        // TC - O(N) & SC - O(W + M) 
        // W = Maximum width of the tree
        // M = number of elements in the top view
        // This approach uses BFS traversal and a HashMap to keep the track of the 
        // very first element to be processed at every vertical level (horizontal distance from the root)
        // Though, we can use a TreeMap as well to get the elements sorted 
        // according to their level, but it would cost another logM complexity per element stored in Map
        
        // Edge Case
        if (root == null) {
            return new ArrayList<>();
        }
        
        // Pointers for the leftMost and rightMost level (inclusive)
        int leftBoundary = 0;
        int rightBoundary = 0;
        
        Queue<Pair> queue = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        
        queue.offer(new Pair(root, 0));
        
        while (!queue.isEmpty()) {
            
            Pair pair = queue.poll();
            Node node = pair.node;
            int pos = pair.pos;
            
            // Put the current value with key as its vertical level (horizontal distance)
            // Only put the value if it map doesn't contain any value for that level
            // as we need only the first value to be processed for every key
            map.putIfAbsent(pos, node.data);
            
            // Update the leftBoundary and rightBoundary
            leftBoundary = Math.min(leftBoundary, pos);
            rightBoundary = Math.max(rightBoundary, pos);
            
            // left child will be at currLevel - 1
            if (node.left != null) {
                queue.offer(new Pair(node.left, pos - 1));
            }
            // right child will be at currLevel + 1
            if (node.right != null) {
                queue.offer(new Pair(node.right, pos + 1));
            }
        }
        
        ArrayList<Integer> result = new ArrayList<>();
        
        // Traverse from leftBoundary to rightBoundary and fetch the values from map to store in the result
        for (int i = leftBoundary; i <= rightBoundary; i++) {
            result.add(map.get(i));
        }
        
        return result;
    }
}
