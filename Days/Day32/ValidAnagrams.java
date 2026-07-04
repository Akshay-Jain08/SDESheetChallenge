/**
 * Problem: Valid Anagram
 * Link: https://leetcode.com/problems/valid-anagram/
 * 
 * Time Complexity: O(N) -> Single linear scan pass with constant-time balance verification.
 * Space Complexity: O(1) -> Fixed 26-element character tracking bucket in constant memory.
 */

public class ValidAnagrams {
    public boolean isAnagram(String s, String t) {
        
        // TC - O(N) & SC - O(1)
        // We use the freq array to keep the track of all the characters in both the strings
        int n1 = s.length();
        int n2 = t.length();

        // Edge case : If lengths are unequal, they can never an anagrams
        if (n1 != n2) return false;

        // freq array of size 26 as the there are only 26 lowercase alphabets
        int[] freq = new int[26];

        for (int i = 0; i < n1; i++) {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);

            // increment the freq for the char in s
            freq[ch1 - 'a']++;
            // decrement the freq for the char in t
            freq[ch2 - 'a']--;
        }

        // all the freq should be 0 if the given strings are anagrams
        for (int val : freq) {
            if (val != 0) return false;
        }
        return true;



        /*
        TC - O(N logN) & SC - O(N) 
        We can also use sorting to check the char at every position
        If there seems to be any difference in chars at any position, 
        we can say that they are not anagrams
        */
    }
}
