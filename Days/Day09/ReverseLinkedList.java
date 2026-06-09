/**
 * Problem: Reverse Linked List
 * Link: https://leetcode.com/problems/reverse-linked-list/
 * 
 * Time Complexity: O(N)  -> Single pass iterative list traversal.
 * Space Complexity: O(1) -> Pure pointer manipulation in constant memory bounds.
 */

public class ReverseLinkedList {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode reverseList(ListNode head) {

        // We are using 3 pointers to carry out the reversal of the LL
        ListNode prev = null;
        ListNode curr = head;

        // Iterate the whole list until curr surpasses the last node 
        while (curr != null) {
            // Temporary next pointer to hold the next to curr node to move the pointers later
            ListNode next = curr.next;

            // reverse the direction of curr pointer
            curr.next = prev;

            // Move the pointers ahead
            prev = curr;
            curr = next;
        }

        return prev;
    }
}

