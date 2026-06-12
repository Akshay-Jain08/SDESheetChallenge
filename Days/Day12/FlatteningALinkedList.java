/**
 * Problem: Flattening a Linked List
 * Link: https://www.geeksforgeeks.org/problems/flattening-a-linked-list/1
 * 
 * Time Complexity: O(N^2 * M) -> Sequential pairwise merging across N lists of length M.
 * Space Complexity: O(1)      -> Pure in-place re-linking without auxiliary structures.
 */

import java.util.PriorityQueue;

public class FlatteningALinkedList {

    // Definition for custom multi-directional structural Node
    public static class Node {
        int data;
        Node next;
        Node bottom;
        Node(int d) {
            data = d;
            next = null;
            bottom = null;
        }
    }

    public Node flatten(Node root) {
        // code here
        
        // This approach uses the subsequent merging of the lists
        
        // Edge case
        if (root == null || root.next == null) return root;
        
        Node curr = root;
        
        // Loop till all the lists merge into one
        while (curr.next != null) {
            // Storing the next list to be merge after the current two
            Node nextSegment = curr.next.next;
            
            // merge the current two lists
            curr = mergeLists(curr, curr.next);
            
            curr.next = nextSegment;
        }
        
        return curr;
        
        
        
        
        
        /*
        // This approach leverages a Priority Queue for holding all the nodes in the horizontal path
        // It helps to return min node of all the nodes
        // TC - O(N * M * logN) & SC - O(N)
        
        PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.data, b.data));
        
        // Adding all the nodes in the horizontal path in the minHeap
        while (root != null) {
            minHeap.offer(root);
            root = root.next;
        }
        
        // dummy node for forming the flat linked list
        Node dummy = new Node(0);
        Node temp = dummy;
        
        while (!minHeap.isEmpty()) {
            Node node = minHeap.poll();
            
            // this is to ensure that each node doesn't have any horizontal
            // noise in between so that the resulting list is purely vertical   
            node.next = null;
            
            // Updating the result vertical list
            temp.bottom = node;
            temp = temp.bottom;
            
            // Adding the current node's bottomg child for the next iteration
            Node nextNode = node.bottom;
            if (nextNode != null) minHeap.offer(nextNode);
        }
        
        return dummy.bottom;
        
        */
    }
    
    public Node mergeLists(Node L1, Node L2) {
        // dummy node for the result list to be constructed
        Node dummy = new Node(0);
        Node temp = dummy;
        
        // Travese both the lists and keep adding the smaller node every iteration
        while (L1 != null && L2 != null) {
            if (L1.data <= L2.data) {
                temp.bottom = L1;
                L1 = L1.bottom;
            }
            else {
                temp.bottom = L2;
                L2 = L2.bottom;
            }
            
            temp = temp.bottom;
            
            // remove horizontal noise from the current node
            temp.next = null;
        }
        
        // List cleanup after one of the list has been iterated completely
        if (L1 != null) {
            temp.bottom = L1;
        }
        else {
            temp.bottom = L2;
        }
        
        return dummy.bottom;
    }
}
