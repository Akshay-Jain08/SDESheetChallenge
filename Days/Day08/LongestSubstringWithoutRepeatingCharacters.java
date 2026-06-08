/**
 * Problem: Longest Substring Without Repeating Characters
 * Link: https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * 
 * Time Complexity: O(N) -> Single-pass linear sliding window with hash map lookups.
 * Space Complexity: O(1) -> Bound by the unique character alphabet set size footprint.
 */

import java.util.HashMap;

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        // Sliding Window + HashMap to store the last occurrence of every character processed
        // So we can essentially remain in the window on unique characters
        int res = 0;
        int left = 0;

        // Map stores the last occurrence of the characters
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // Checking if it is a repeating character
            Integer idx = map.get(ch);
            if (idx != null) {
                // make sure to use max(left, idx + 1) because the last occurrence of a particular character
                // might lie outside the current window, and we don't want the window to grow backwards
                left = Math.max(left, idx + 1);
            }

            // Update the map
            map.put(ch, i);
            res = Math.max(res, i - left + 1);
        }
        return res;


        /*
        Brute-Force approach would be to use 2 nested loops and going the each and every subarray
        making the TC - O(N^3) & SC - O(1)
        */
    }
}
