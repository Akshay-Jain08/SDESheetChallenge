/**
 * Problem: Next Greater Element I
 * Link: https://leetcode.com/problems/next-greater-element-i/
 * 
 * Time Complexity: O(N + M) -> Linear monotonic scanning pass followed by a fast map lookup loop.
 * Space Complexity: O(M)     -> Storage bounds for the map and internal stack references.
 */

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;

public class NextGreaterElementI {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        
        // TC - O(N + M) & SC - O(M)
        // This approach works in better TC because we map every value 
        // in nums2 with its respective next Greater element
        // So, the lookup is faster when traversing nums1
        // All the other details are mentioned in the below approach as both approaches are same
        int n1 = nums1.length;
        int n2 = nums2.length;

        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int i = n2 - 1; i >= 0; i--) {

            while (!stack.isEmpty() && nums2[i] >= stack.peek()) {
                stack.pop();
            }

            map.put(nums2[i], stack.isEmpty() ? -1 : stack.peek());

            stack.push(nums2[i]);
        }

        int[] res = new int[n1];

        for (int i = 0; i < n1; i++) {
            res[i] = map.get(nums1[i]);
        }

        return res;



        /*
        // TC - O(N * M) & SC - O(M)
        int n1 = nums1.length;
        int n2 = nums2.length;
        
        // nextGreater array for every element of nums2
        int[] nextGreater = new int[n2];

        // stack to help find the nextGreater for every element
        // traversing from the back
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int i = n2 - 1; i >= 0; i--) {

            // If the current element is larger than stack's top
            // then pop the element from stack, as it can't be the nextGreater 
            // for the current element
            while (!stack.isEmpty() && nums2[i] >= stack.peek()) {
                stack.pop();
            }

            // Store the nextGreater for the current element
            nextGreater[i] = (stack.isEmpty()) ? -1 : stack.peek();

            // push the current element to the stack 
            stack.push(nums2[i]);
        }

        int[] res = new int[n1];

        // Since all the elements are unique in nums1 and nums2
        // we can identify the index for an element in nums1 in nums2 array
        // by simply traversing over nums2
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                if (nums1[i] == nums2[j]) {
                    res[i] = nextGreater[j];
                    break;
                }
            }
        }

        return res;
        */
    }
}
