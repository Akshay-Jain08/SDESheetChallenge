/**
 * Problem: Remove Nth Node From End of List
 * Link: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 * 
 * Time Complexity: O(N)  -> Single-pass linear node traversal.
 * Space Complexity: O(1) -> Bounded constant reference pointer manipulation.
 */

public class RemoveNthNodeFromEndOfList {

    // Definition for singly-linked list node provided by LeetCode
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // dummy pointer for edge case handling when the required node to be removed is the head itself
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        while (fast != null) {
            // Move the fast pointer first to maintain a gap of n nodes
            if (n-- >= 0) {
                fast = fast.next;
                continue;
            }

            // then move both pointers together, until we reach the end
            fast = fast.next;
            slow = slow.next;
        }

        // slow pointer would be at the node previous to the required node to be removed
        slow.next = slow.next.next;

        return dummy.next;



        /* 
        Alternate Approach would be to calculate length first
        Then, move the pointer length - n and remmove the required node
        It would require two passes, though the complexity would remain same
        TC - O(N) & SC - O(1)
        */
    }
}