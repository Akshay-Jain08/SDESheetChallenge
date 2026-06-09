/**
 * Problem: Middle of the Linked List
 * Link: https://leetcode.com/problems/middle-of-the-linked-list/description/
 * 
 * Time Complexity: O(N)  -> Linear traversal visiting N/2 nodes.
 * Space Complexity: O(1) -> Bounded by two structural tracking pointers.
 */

public class MiddleOfTheLinkedList {

    // Definition for singly-linked list node provided by LeetCode
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode middleNode(ListNode head) {
        
        // Using special tortoise & hare (slow & fast pointer) approach for this
        // slow moves one step while fast moves 2 steps resulting
        // in slow reaching only half way though while the fast finishes the traversal
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // slow is at middle node when the fast reaches the end
        return slow;
    }
}
