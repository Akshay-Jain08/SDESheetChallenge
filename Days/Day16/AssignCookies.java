/**
 * Problem: Assign Cookies / Find Content Children
 * Link: https://leetcode.com/problems/assign-cookies/
 * 
 * Time Complexity: O(N log N + M log M) -> Driven by sorting children greed and cookie size arrays.
 * Space Complexity: O(log N + O(log M)     -> System stack allocation for primitive array sorting.
 */

import java.util.Arrays;

public class AssignCookies {
    public int findContentChildren(int[] g, int[] s) {
        
        // TC - O(N logN + M logM) & SC - O(logN + logM)
        // This approach uses the greedy method where we sort the greedyness and the size 
        // of the cookies in non-decreasing order
        Arrays.sort(g);
        Arrays.sort(s);

        int child = 0;
        int cookie = 0;

        int count = 0;

        // Iterate till either all the child gets the cookies or all the cookies are exhausted
        while (child < g.length && cookie < s.length) {
            // assigning the smallest possible cookie to every child
            if (g[child] <= s[cookie]) {
                count++;
                child++;
                cookie++;
            }
            // current cookie cannot satisfy current child,
            // try larger cookie
            else {
                cookie++;
            }
        }

        return count;
    }
}
