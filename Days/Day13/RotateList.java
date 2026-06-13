/**
 * Problem: Rotate List
 * Link: https://leetcode.com/problems/rotate-list/
 * 
 * Time Complexity: O(N)  -> One full pass for length/tail, one partial pass for new head split.
 * Space Complexity: O(1) -> In-place pointer restructuring using zero auxiliary space.
 */

public class RotateList {

    // Definition for singly-linked list node provided by LeetCode
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode rotateRight(ListNode head, int k) {

        // This is the standard solution for rotating the list
        // It is a more refined version of the below approach and leverages the circular array

        // Edge Case
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // Finding the length and tail of the current list  
        ListNode tail = head;
        int len = 1;

        while (tail.next != null) {
            tail = tail.next;
            len++;
        }

        // When k > len
        k = k % len;

        // If the resulting list is equal to the current list
        if (k == 0) return head;

        // making the circular array    
        tail.next = head;

        // Finding the tail of the resulting list
        ListNode newTail = head;
        int steps = len - k;

        for (int i = 1; i < steps; i++) {
            newTail = newTail.next;
        }

        // assign head of the resulting list and cut off the circular list
        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;



        /*

        // TC - O(N) & SC - O(1)
        // Edge Case
        if (head == null || head.next == null || k == 0) return head;

        // Calculating length
        ListNode temp = head;
        int len = 0;

        while (temp != null) {
            temp = temp.next;
            len++;
        }

        // When k > len
        k = k % len;

        // If the resulting list is equal to the current list
        if (k == 0) return head;

        // Finding the tail of the new list
        temp = head;
        for (int i = 1; i < len - k; i++) {
            temp = temp.next;
        }

        // cutoff the tail and assign the head of the new list to temp2
        ListNode temp2 = temp.next;
        temp.next = null;
        temp = temp2;

        // traverse the temp2 pointer to the end and join it to the current head
        while (temp2 != null && temp2.next != null) {
            temp2 = temp2.next;
        }

        if (temp2 != null) temp2.next = head;

        return temp;

        */
    }
}
