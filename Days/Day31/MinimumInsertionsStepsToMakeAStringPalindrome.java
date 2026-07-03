/**
 * Problem: Minimum Insertions to Make a String Palindrome
 * Link: https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
 * 
 * Time Complexity: O(N^2) -> Quadratic state space checking via memoized interval DP.
 * Space Complexity: O(N^2) -> Driven by the 2D matrix state tracking grid and system call stack.
 */

import java.util.Arrays;

public class MinimumInsertionsStepsToMakeAStringPalindrome {
    public int minInsertions(String s) {
        
        // TC - O(N^2) & SC - O(N^2)
        // We can simply found Longest Palindromic Subsequence for the given string
        // Characters that are not part of the LPS must be
        // inserted at suitable positions to form a palindrome.
        // Therefore, minimum insertions = n - LPS.
        int n = s.length();

        int[][] dp = new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        // Lps for current string
        int len = lps(0, n - 1, s, dp);

        // n - len will be the no. of insertions required
        return n - len;
    }

    public int lps(int i, int j, String s, int[][] dp) {

        // To find the lps, instead of going with the naive approach of generating 
        // all the subsequences, then verify the palindromic ones and return the longest
        // Find the Longest Palindromic Subsequence using interval DP.
        if (i > j) {
            return 0;
        }

        // When there is only one char left to compare
        if (i == j) {
            return 1;
        }

        // Cache the result
        if (dp[i][j] != -1) return dp[i][j];

        int len = 0;

        // If the current pair of characters are equal, they will contribute to the lps
        if (s.charAt(i) == s.charAt(j)) {
            len = 2 + lps(i + 1, j - 1, s, dp);
        }
        // Otherwise, we have to check for all other possible subsequences
        else {
            len = Math.max(len, lps(i + 1, j, s, dp));
            len = Math.max(len, lps(i, j - 1, s, dp));
        }

        // return and store the result
        return dp[i][j] = len;
    }
}
