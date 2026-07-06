/**
 * Problem: Bottom View of Binary Tree
 * Link: https://www.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1
 * 
 * Time Complexity: O(N) -> Single linear multi-level BFS scan with O(1) map entries.
 * Space Complexity: O(W + M) -> Storage footprint for queue width and vertical axis maps.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BottomViewOfBinaryTree {
    
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

    public ArrayList<Integer> bottomView(Node root) {
        
        // TC - O(N) & SC - O(W + M) 
        // W = Maximum width of the tree
        // M = no. of elements to be included in the bottom view
        // This approach certainly focused on the same logic as the previous one
        // The main difference is that it is a single pass approach
        // where we keep storing the value of each data with respect to their vertical level (horizontal distance from the root)
        // in a HashMap (though we can use a TreeMap as well to get the elements sorted 
        // according to their level, but it would cost another logM complexity per element stored in Map)
        
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
            
            // Put the current value with key as its vertical level
            map.put(pos, node.data);
            
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
        
        
        
        
        
        /*
        // TC - O(N) & SC - O(W)
        // W = Maximum width of the tree (Worst Case O(N))
        // We perform the BFS to get the vertical level of each node
        // (starting from the root as level 0)
        // The last elemenet to appear at the every level will be included in the bottom view of tree
        
        // pointers for the leftmost and rightmost level (inclusive);
        int leftBoundary = 0;
        int rightBoundary = 0;
        
        Queue<Pair> queue = new LinkedList<>();
        
        queue.offer(new Pair(root, 0));
        
        while (!queue.isEmpty()) {
            
            Pair pair = queue.poll();
            Node node = pair.node;
            int pos = pair.pos;
            
            // Update the leftBoundary and rightBoundary in every iteration
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
        
        // Define a list according to the size and initialize it with 0
        int listSize = rightBoundary - leftBoundary + 1;
        ArrayList<Integer> result = new ArrayList<>(Collections.nCopies(listSize, 0));
        
        leftBoundary *= -1;
        
        queue.offer(new Pair(root, 0));
        
        // Second BFS pass to get the last item to be processed at every level
        while (!queue.isEmpty()) {
            
            Pair pair = queue.poll();
            Node node = pair.node;
            int pos = pair.pos;
            
            // Update the value for each level every iteration so that 
            // the last node to be processed will be included in the result
            result.set(pos + leftBoundary, node.data);
            
            if (node.left != null) {
                queue.offer(new Pair(node.left, pos - 1));
            }
            if (node.right != null) {
                queue.offer(new Pair(node.right, pos + 1));
            }
        }
        
        return result;
        */
    }
}
