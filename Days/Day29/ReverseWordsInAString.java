/**
 * Problem: Reverse Words in a String
 * Link: https://leetcode.com/problems/reverse-words-in-a-string/
 * 
 * Time Complexity: O(N) -> Linear tokenization scan combined with dual-pointer array swaps.
 * Space Complexity: O(N) -> Bounded by the split string array capacity footprints.
 */

import java.util.ArrayDeque;
import java.util.Arrays;

public class ReverseWordsInAString {
    public String reverseWords(String s) {
        
        // TC - O(N) & SC - O(N)
        // This approach uses a few regex methods

        // trim() removes leading & trailing spaces
        // split("\\s+") splits by one or more spaces and extracts words
        String[] arr = s.trim().split("\\s+");

        // reverse the array
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            String temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }

        // Join the array with delimiter as a single space
        return String.join(" ", arr);




        /*
        // TC - O(N) & SC - O(N)
        // We can fix the word shifting issue in the below approach
        // with this backward pass approach

        // remove leading & trailing spaces
        s = s.trim();

        StringBuilder sb = new StringBuilder();

        int right = s.length() - 1;

        while (right >= 0) {

            // Propagate right until the end of the next word
            while (right >= 0 && s.charAt(right) == ' ') {
                right--;
            }

            int left = right;

            // Propage left until the starting boundary of the current word
            while (left >= 0 && s.charAt(left) != ' ') {
                left--;
            }

            // Append the current word and if it's not the last word append a space
            sb.append(s.substring(left + 1, right + 1));
            if (left > 0) sb.append(' ');

            // point the right outside of the current word
            right = left;
        }

        return sb.toString();
        */




        /*
        // TC - O(N^2) & SC - O(N)
        // This is forward pass approach, generally not preferred because of shifting
        // of words in the stringbuilder

        // Using trim to remove leading and trailing spaces
        s = s.trim();
        StringBuilder sb = new StringBuilder();

        // left and right pointers to locate the current word
        int left = 0;
        int right = 0;

        while (left < s.length()) {
            // Propage left and right to the starting of the next word
            while (left < s.length() && s.charAt(left) == ' ') {
                left++;
                right++;
            }

            // Propage right to the boundary of the current word
            while (right < s.length() && s.charAt(right) != ' ') {
                right++;
            }

            // Insert the current word at 0th index so that the result is reversed 
            sb.insert(0, s.substring(left, right) + " ");

            // point the left outside of the current word
            left = right;
        }

        // Remove the last space from the string
        sb.setLength(sb.length() - 1);
        return sb.toString();
        */
    }
}
