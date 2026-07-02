/**
 * Problem: Longest Common Prefix (Optimal Character-by-Character Sweep)
 * Link: https://leetcode.com/problems/longest-common-prefix/
 * 
 * Time Complexity: O(N * M) -> Where M is the shortest string length. Halts at the first mismatch.
 * Space Complexity: O(M)    -> Character builder allocation tracking matching prefix sequences.
 */


import java.util.Arrays;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        
        // TC - O(N logN * M) & SC - O(N)
        // where M = average length of the strings involved in comparison
        // This approach sorts the given array lexicographically 
        // Though this approach is more simpler but it is slightly worse than the previous one
        // We can just compare the first and the last string to get the longest common prefix
        Arrays.sort(strs);

        int n = strs.length;
        int pos = 0;
        // The common prefix cannot exceed the length of the shorter string.
        int len = Math.min(strs[0].length(), strs[n - 1].length());

        // Compare the first and last string position by position
        while (pos < len) {
            if (strs[0].charAt(pos) != strs[n - 1].charAt(pos)) {
                break;
            }
            pos++;
        }

        return strs[0].substring(0, pos);




        /*
        // TC - O(N * M) & SC - O(1) excluding output
        // where M = length of the shortest string
        // This approach compare every character position across all strings. (Vertical Character Scanning)
        // The first mismatch marks the end of the common prefix.

        // Finding minLen, since the longest common prefix can be at max equivalent to minLen
        int minLen = Integer.MAX_VALUE;
        for (String str : strs) {
            minLen = Math.min(minLen, str.length());
        }

        StringBuilder sb = new StringBuilder();
        int idx = 0;

        while (idx < minLen) {

            for (int i = 1; i < strs.length; i++) {
                // If any two adjacent strings doesn't have the common prefix 
                // then we can return the pre-computed prefix for previous indices
                if (strs[i].charAt(idx) != strs[i - 1].charAt(idx)) {
                    return sb.toString();
                }
            }
            // If all the strings have the current char as the part of common prefix
            // add it to the string
            sb.append(strs[0].charAt(idx));
            idx++;
        }

        return sb.toString();
        */
    }
}
