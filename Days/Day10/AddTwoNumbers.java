/**
 * Problem: Add Two Numbers
 * Link: https://leetcode.com/problems/add-two-numbers/
 * 
 * Time Complexity: O(max(M, N)) -> Single-pass simultaneous list traversal.
 * Space Complexity: O(max(M, N)) -> Linear memory allocation for the result nodes.
 */

public class AddTwoNumbers {

    // Definition for singly-linked list node provided by LeetCode
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // This is just a refined version of the below approach
        // It leverages only one single loop and reduce the redundant code
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;

        // when the digit sum exceeds 10
        int carry = 0;

        // loop until both are null or carry is not 0 because 2 n-digit numbers may sum up to n + 1-digit number
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;

            // store the value to sum
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            // make a new node with only the modulo value because the remaining portion 
            // would be taken to the next digits as carry
            temp.next = new ListNode(sum % 10);
            temp = temp.next;

            // overwrite the carry with current carry
            carry = sum / 10;
        }

        return dummy.next;




        /*
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;

        int carry = 0;

        while (l1 != null && l2 != null) {
            int val = l1.val + l2.val + carry;

            temp.next = new ListNode(0);
            temp = temp.next;
            temp.val += val % 10;

            carry = val / 10;
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int val = l1.val + carry;

            temp.next = new ListNode(0);
            temp = temp.next;
            temp.val += val % 10;

            carry = val / 10;
            l1 = l1.next;
        }

        while (l2 != null) {
            int val = l2.val + carry;

            temp.next = new ListNode(0);
            temp = temp.next;
            temp.val += val % 10;

            carry = val / 10;
            l2 = l2.next;
        }

        if (carry > 0) temp.next = new ListNode(carry);

        return dummy.next;

        */
    }
}
