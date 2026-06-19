/**
 * Problem: Word Break
 * Link: https://leetcode.com/problems/word-break/
 * 
 * Time Complexity: O(N^3) -> N states * N inner loops * O(N) string slicing operations.
 * Space Complexity: O(N)   -> Linear DP state tracking array and recursion call stack frames.
 */

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        
        // TC - O(N^3) & SC - O(N)
        // Converting the wordDict list to a HashSet for faster Lookups
        Set<String> dict = new HashSet<>(wordDict);

        // dp array for memoization
        Boolean[] dp = new Boolean[s.length()];
        return solve(0, s, dict, dp);
    }

    public boolean solve(int idx, String s, Set<String> dict, Boolean[] dp) {

        // Base Case
        if (idx == s.length()) {
            return true;
        }

        // Cache Lookup
        if (dp[idx] != null) return dp[idx];

        // From the current index to the last index, check for every substring possible
        // Whichever substring is present in dict, look for further substring cutting from that position
        for (int i = idx; i < s.length(); i++) {
            if (dict.contains(s.substring(idx, i + 1))) {
                if (solve(i + 1, s, dict, dp)) {
                    return dp[idx] = true;
                }
            }
        }

        // Store the answer and return
        return dp[idx] = false;
    }
}
