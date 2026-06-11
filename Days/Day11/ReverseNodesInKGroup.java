/**
 * Problem: Reverse Nodes in k-Group
 * Link: https://leetcode.com/problems/reverse-nodes-in-k-group/
 * 
 * Time Complexity: O(N)  -> Dual scan per group for lookahead boundaries and sub-list reversal.
 * Space Complexity: O(1) -> In-place pointer link modification in constant storage.
 */

public class ReverseNodesInKGroup {

    // Definition for singly-linked list node provided by LeetCode
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    
    public ListNode reverseKGroup(ListNode head, int k) {
        
        // Edge Case
        if (head == null || head.next == null || k <= 1) return head;
        
        // prev & curr -> used for reversing current group
        // nextGroupStart & nextGroupEnd keeps the track of the next group
        ListNode prev = null;
        ListNode curr = head;
        ListNode nextGroupStart = head;

        // Determining nextGroupStart
        for (int i = 0; i < k; i++) {
            if (nextGroupStart == null) return head;

            // Updating head
            if (i == k - 1) head = nextGroupStart;

            nextGroupStart = nextGroupStart.next;
        }

        while (curr != null) {
            ListNode nextGroupEnd = nextGroupStart;
            
            // Traverse k - 1 nodes from nextGroupStart to reach nextGroupEnd
            for (int i = 0; i < k - 1 && nextGroupEnd != null; i++) {
                nextGroupEnd = nextGroupEnd.next;
            }

            // If nextGroupEnd is null, that means the next Group contains lesser than k elements
            // so prev would be nextGroupStart to reverse the current group
            prev = (nextGroupEnd != null) ? nextGroupEnd : nextGroupStart;

            // Reverse the current group until curr reaches nextGroupStart
            // that is essential the boundary for the current group
            while (curr != nextGroupStart) {
                ListNode next = curr.next;

                curr.next = prev;

                prev = curr;
                curr = next;
            }

            // If nextGroupEnd is null, that means there are lesser than k elements in
            // the next group, so no need to reverse them
            if (nextGroupEnd == null) break;

            // Otherwise update the nextGroupStart to reverse the next list
            else nextGroupStart = nextGroupEnd.next;
        }

        return head;
        



        /*
        // Another Iterative Solution
        if (head == null || k <= 1) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // This pointer is just before the current group of k nodes
        ListNode groupPrev = dummy;

        while (true) {

            // Find the Kth Node
            ListNode kth = groupPrev;

            for (int i = 0; i < k && kth != null; i++) {
                kth = kth.next;
            }

            if (kth == null) break;

            ListNode groupNext = kth.next;

            // Reverse Current Group
            ListNode prev = groupNext;
            ListNode curr = groupPrev.next;

            while (curr != groupNext) {
                ListNode next = curr.next;

                curr.next = prev;
                prev = curr;
                curr = next;
            }

            // Connect Previous Group
            ListNode temp = groupPrev.next;
            groupPrev.next = kth;
            groupPrev = temp;
        }

        return dummy.next;
        */
    }
}
