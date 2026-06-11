/**
 * Problem: Linked List Cycle
 * Link: https://leetcode.com/problems/linked-list-cycle/
 * 
 * Time Complexity: O(N)  -> Linear cycle search pass.
 * Space Complexity: O(1) -> Bounded constant scalar memory layout.
 */


public class LinkedListCycle {

    // Definition for singly-linked list node provided by LeetCode
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public boolean hasCycle(ListNode head) {
        // This solution leverages Floyd's cycle detection algorithm
        ListNode slow = head;
        ListNode fast = head;

        // slow pointer moves one step at a time, while
        // fast pointer moves two steps at a time
        // resulting in slow and fast pointer collision if the cycle exists
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) return true;
        }

        return false;
    }
}
