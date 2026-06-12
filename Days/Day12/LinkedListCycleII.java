/**
 * Problem: Linked List Cycle II (Find Entry Point)
 * Link: https://leetcode.com/problems/linked-list-cycle-ii/
 * 
 * Time Complexity: O(N)  -> Two linear phases for collision detection and pointer synchronization.
 * Space Complexity: O(1) -> Pure reference pointer checking in constant workspace memory.
 */

public class LinkedListCycleII {

    // Definition for singly-linked list node provided by LeetCode
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    
    public ListNode detectCycle(ListNode head) {
        
        // Using famous Floyd's hare & tortoise approach
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // the point when two pointers meet confirms the presence of cycle
            if (slow == fast) {
                // set one pointer to head
                slow = head;

                // now, traverse both pointers, they will meet at the beginning of the cycle
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }

                return slow;
            }
        }

        return null;
    }
}
