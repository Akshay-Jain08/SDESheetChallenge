/**
 * Problem: Merge Two Sorted Lists
 * Link: https://leetcode.com/problems/merge-two-sorted-lists/description/
 * 
 * Time Complexity: O(N + M) -> Linear element comparisons across both lists.
 * Space Complexity: O(1)     -> In-place pointer link restructuring.
 */

public class MergeTwoSortedLists {

    // Definition for singly-linked list node provided by LeetCode
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        // Simply traversing both the list and inserting which every carries smallest value
        // Using dummy node because the very first node needs something to hold on
        ListNode dummy = new ListNode(0);
        // temp node to traverse through the list while dummy keeps the track of the very first node of result
        ListNode temp = dummy;

        while (list1 != null && list2 != null) {
            // add list2 when its value is smaller
            if (list1.val > list2.val) {
                temp.next = list2;
                list2 = list2.next;
            }
            // add list1 when its value is smaller
            else {
                temp.next = list1;
                list1 = list1.next;
            }
            temp = temp.next;
        }

        // final cleanup for whichever list is empty
        temp.next = (list1 != null) ? list1 : list2;

        return dummy.next;
    }
}
