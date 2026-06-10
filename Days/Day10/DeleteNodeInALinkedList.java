/**
 * Problem: Delete Node in a Linked List
 * Link: https://leetcode.com/problems/delete-node-in-a-linked-list/
 * 
 * Time Complexity: O(1)  -> Constant runtime executing instantaneous value overwrite.
 * Space Complexity: O(1) -> Bounded constant structural pointer modification.
 */

public class DeleteNodeInALinkedList {

    // Definition for singly-linked list node provided by LeetCode
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    
    public void deleteNode(ListNode node) {
        // Since we are not given any head of the linked list,
        // so we can't traverse from the head to identify the target node and delete it

        // What we are doing here is simply copying the next node in the current node
        // and delete the next node effectively doing the same thing
        node.val = node.next.val;
        node.next = node.next.next;



        /*
        // This approach is similar to the optimized approach above
        // instead of deleting the very next node, it copies the node
        // upto the very end and delete the last node
        // TC - O(N) & SC - O(1)

        // Traversing to the second last node and copying the next value to current node while traversing
        while (node.next.next != null) {
            node.val = node.next.val;
            node = node.next;
        }
        
        // Copy the value of the last node to second last node and delete the last node
        node.val = node.next.val;
        node.next = null;
        */
    }
}
