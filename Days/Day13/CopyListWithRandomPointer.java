/**
 * Problem: Copy List with Random Pointer
 * Link: https://leetcode.com/problems/copy-list-with-random-pointer/
 * 
 * Time Complexity: O(N)  -> Three isolated sequential linear loops.
 * Space Complexity: O(1) -> In-place interweaving structure avoids dictionary allocation.
 */

import java.util.HashMap;

public class CopyListWithRandomPointer {

    // Definition for custom Node provided by LeetCode
    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {

        // TC - O(N) & SC - O(1)
        // Interweaving approach
        // This approach directly places the copies to the next of the respective nodes itself
        // because of which we solve the problem in constant space without the use of HashMap

        // Adding the copies next to the respective nodes
        Node curr = head;
        while (curr != null) {
            Node copy = new Node(curr.val);

            copy.next = curr.next;
            curr.next = copy;
            curr = copy.next;
        }

        // Adding random pointers
        curr = head;
        while (curr != null) {
            // this is the key
            // curr.next.random is current node's copy's random pointer
            // curr.random.next is current node's random pointer's copy
            // so we are assigning to node's random copy to node's copy's random pointer

            // A -> A' -> B -> B' -> C -> C'
            // suppose A.random = C
            // so we have to do A' -> C' (random pointer)
            // A.next.random -> A.random.next
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }

        // Separating the original list and copied list
        Node dummy = new Node(0);
        Node temp = dummy;
        curr = head;
        while (curr != null) {
            temp.next = curr.next;

            // Original list restored
            curr.next = curr.next.next;
            curr = curr.next;

            temp = temp.next;
        }

        return dummy.next;



        
        /*
        // TC - O(N) & SC - O(N)
        // Map to keep track of the respective copies of the current nodes
        HashMap<Node, Node> map = new HashMap<>();

        // Create the copies and update in map
        Node temp = head;
        while (temp != null) {
            Node node = new Node(temp.val);
            map.put(temp, node);

            temp = temp.next;
        }

        // Link the copies with respective to the current list
        temp = head;
        while (temp != null) {
            Node node = map.get(temp);

            node.next = map.get(temp.next);
            node.random = map.get(temp.random);

            temp = temp.next;
        }

        return map.get(head);
        */
    }
}
