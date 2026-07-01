/**
 * Problem: Longest Palindromic Substring
 * Link: https://leetcode.com/problems/longest-palindromic-substring/
 * 
 * Time Complexity: O(N^2) -> Expands outward across 2N - 1 logical center coordinates.
 * Space Complexity: O(1)    -> Pure in-place index tracking using instance state registers.
 */

public class LongestPalindromicSubstring {
    int start = 0;
    int maxLen = 1;
    public String longestPalindrome(String s) {
        

        // TC - O(N^2) & SC - O(1)
        // Instead of checking every generated substring individually for palindromicity
        // We expand the current boundary in both the directions to check
        // if the palindrome property sustains as we move forward

        // Expand around every possible center.
        // A palindrome can have:
        // 1. Odd center (i,i)
        // 2. Even center (i,i+1)

        // Point to each index and spread in both directions
        for (int i = 0; i < s.length(); i++) { 

            // expand for the odd center
            expand(i, i, s);

            // expand for the even center
            expand(i, i + 1, s);
        }

        return s.substring(start, start + maxLen);



        /*
        // TC - O(N^3) & SC - O(N)
        // This generates all the substrings and checks individually whether
        // the generated substring is palindrome or not

        String result = "";

        // Generate all the substrings
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {

                String sub = s.substring(i, j);

                // Check for palindrome
                if (isPalindrome(sub)) {
                    // If the current substring is longer than the one stores in result
                    // update the result
                    if (result.length() < sub.length()) {
                        result = sub;
                    }
                }
            }
        }

        return result;
        */
    }

    // Method to check palindrome
    public boolean isPalindrome(String s) {
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

    public void expand(int left, int right, String s) {

        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            if (right - left + 1 > maxLen) {
                start = left;
                maxLen = right - left + 1;
            }

            left--;
            right++;
        }
    }
}
