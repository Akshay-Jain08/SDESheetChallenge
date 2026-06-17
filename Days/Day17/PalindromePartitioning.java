/**
 * Problem: Palindrome Partitioning
 * Link: https://leetcode.com/problems/palindrome-partitioning/
 * 
 * Time Complexity: O(N^2 * 2^N) -> Generating 2^N partition points with substring and check loops.
 * Space Complexity: O(N)       -> Bound by the maximum recursion call stack matching string length.
 */

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        
        // TC - O(N^2 * 2^N) & SC - O(N) excluding output
        List<List<String>> result = new ArrayList<>();

        solve(0, s, new ArrayList<>(), result);

        return result;
    }

    public void solve(int idx, String s, List<String> list, List<List<String>> result) {
        
        // Base
        if (idx == s.length()) {
            result.add(new ArrayList<>(list));
            return;
        }

        // Add the strings off all the sizes from current index
        for (int i = idx; i < s.length(); i++) {
            
            String str = s.substring(idx, i + 1);

            // if the selected string is a palindrome, then only move forward
            // otherwise skip the current iteration
            if (isPalindrome(str)) {

                list.add(str);
                solve(i + 1, s, list, result);
                list.removeLast();
            }
        }
    }

    // Palindrome check
    public boolean isPalindrome(String s) {

        if (s.length() <= 1) return true;

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
