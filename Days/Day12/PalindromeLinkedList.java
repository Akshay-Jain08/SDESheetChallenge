/**
 * Problem: Palindrome Linked List
 * Link: https://leetcode.com/problems/palindrome-linked-list/
 * 
 * Time Complexity: O(N)  -> Three linear passes to find mid, reverse, and compare.
 * Space Complexity: O(1) -> In-place pointer changes without auxiliary heap usage.
 */

import java.util.ArrayList;
import java.util.List;

public class PalindromeLinkedList {

    // Definition for singly-linked list node provided by LeetCode
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    
    public boolean isPalindrome(ListNode head) {

        // This approach uses linked list intuition to perform palindrome checking

        // slow and fast pointers to find the middle of the linked list
        ListNode slow = head;
        // fast = head.next for the middle node to be just before the second half starts
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // prev and curr poointers to reverse the second half of the linked list
        ListNode prev = null;
        ListNode curr = slow.next;

        while (curr != null) {
            ListNode next = curr.next;

            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        // traverse both halves of the lists to check if the list is palinddrome
        ListNode temp1 = head;
        ListNode temp2 = prev;

        while (temp2 != null) {
            if (temp1.val != temp2.val) return false;

            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        return true;





        /*
        // This approach uses an ArrayList to store all the elements of the linked list,
        // then perform palindrome checking on the ArrayList
        // TC - O(N) & SC - O(N)

        List<Integer> list = new ArrayList<>();

        // Add all the elements of the linked list to ArrayList
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        int left = 0;
        int right = list.size() - 1;

        // Palindrome checking on the ArrayList
        while (left < right) {
            if (list.get(left) != list.get(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;

        */
    }
}