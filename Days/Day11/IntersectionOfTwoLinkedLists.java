/**
 * Problem: Intersection of Two Linked Lists
 * Link: https://leetcode.com
 * 
 * Time Complexity: O(N + M) -> Simultaneous traversal neutralizing length differences.
 * Space Complexity: O(1)     -> In-place reference matching with zero extra memory.
 */

import java.util.HashSet;

public class IntersectionOfTwoLinkedLists {

    // Definition for singly-linked list node provided by LeetCode
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        
        // This approach doesn't require explicit length calculation 
        // like the approach below
        if (headA == null || headB == null) return null;

        // Take two pointers at head of both the lists
        ListNode a = headA;
        ListNode b = headB;

        // Traverse both the pointers for the full length of both the lists combined
        // Smaller list pointer would finish first, to start propagating the bigger list later
        // and covering the diff offset implicitly while the other pointer finishes up traversing
        // Both pointers travel N + M, so they align automatically
        while (a != b) {
            a = (a == null) ? headB : a.next;
            b = (b == null) ? headA : b.next;
        }

        return a;




        /*
        // Standard length calculation and diff offset approach
        TC - O(N + M) & SC - O(1)
        int lenA = 0;
        int lenB = 0;
        ListNode tempA = headA;
        ListNode tempB = headB;

        // Calculate the length of both the lists
        while (tempA != null || tempB != null) {
            if (tempA != null) {
                lenA++;
                tempA = tempA.next;
            }
            if (tempB != null) {
                lenB++;
                tempB = tempB.next;
            }
        }

        tempA = headA;
        tempB = headB;
        int diff = Math.abs(lenA - lenB);

        // Traverse the diff in whichever list is bigger, 
        // essentially putting the pointers on the same position relative to the end
        for (int i = 0; i < diff; i++) {
            if (lenA > lenB) tempA = tempA.next;
            else tempB = tempB.next;
        }

        // Propagate both the pointers to check when they intersect
        while (tempA != null) {
            if (tempA == tempB) {
                return tempA;
            }
            tempA = tempA.next;
            tempB = tempB.next;
        }

        return null;
        */



        /*
        // This approach leverages a HashSet for storing all the nodes and check
        // whether the second list has any common node with the first list
        // The first common node is the intersection point
        // TC - O(N + M) & SC - O(N)
        HashSet<ListNode> set = new HashSet<>();

        ListNode tempA = headA;

        // Adding all the nodes of the first list in the HashSet
        while (tempA != null) {
            set.add(tempA);
            tempA = tempA.next;
        }

        ListNode tempB = headB;

        // Check if any of the node from List B is common with List A
        while (tempB != null) {
            if (!set.contains(tempB)) {
                set.add(tempB);
            }
            else {
                return tempB;
            }
            tempB = tempB.next;
        }

        return null;

        */
    }
}
