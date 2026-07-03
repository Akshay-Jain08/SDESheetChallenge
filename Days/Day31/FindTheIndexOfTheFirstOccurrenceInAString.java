/**
 * Problem: Find the Index of the First Occurrence in a String (KMP Algorithm)
 * Link: https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/
 * 
 * Time Complexity: O(N + M) -> Linear worst-case performance via deterministic KMP prefix matching scans.
 * Space Complexity: O(M)     -> Restricted auxiliary array memory allocation storing pattern boundaries.
 */

public class FindTheIndexOfTheFirstOccurrenceInAString {
    public int strStr(String text, String pattern) {
        
        // TC - O(N + M) & SC - O(M)
        // N = length of the text
        // M = length of the pattern 
        // This approach uses KMP pattern searching algorithm
        // KMP preprocesses the pattern to build the LPS array,
        // which stores the longest proper prefix that is also a suffix.
        // During mismatch, instead of restarting from the beginning,
        // it rolls back the pattern pointer using LPS, avoiding re-comparisons.
        
        // lps[i] = length of the longest proper prefix
        // which is also a suffix for pattern[0...i]
        int n = text.length();
        int m = pattern.length();

        // Edge Case
        if (m > n) return -1;

        int[] lps = buildLPS(pattern);

        int i = 0;
        int j = 0;

        // Run the loop till the end of the text string
        while (i < n) {

            // If the char matches in both text and pattern, move the pointers ahead
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }
            // If they don't, rollback the j pointer to the previous char's lps
            else {
                // as long as j isn't 0, we can move j pointer to its previous lps
                if (j != 0) {
                    j = lps[j - 1];
                }
                // else just move the i forward
                else {
                    i++;
                }
            }

            // Found the pattern in the text
            if (j == m) {
                return i - j;
            }
        }

        return -1;
    }

    public int[] buildLPS(String pattern) {
        
        int m = pattern.length();
        int[] lps = new int[m];

        // For single char, lps will always be 0
        lps[0] = 0;
        int i = 1;
        int len = 0;

        while (i < m) {

            // If the current character extends the previous prefix-suffix match
            // the len for the lps will be increased
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            }
            // If it doesn't match
            else {
                // Fall back to the previous longest prefix-suffix
                if (len != 0) {
                    len = lps[len - 1];
                }
                // If len is 0, means the there is no prefix for some suffix upto length i
                else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }
}
