/**
 * Problem: Repeated String Match
 * Link: https://leetcode.com/problems/repeated-string-match/
 * 
 * Time Complexity: O(N + M) -> Pre-allocating repeated sequence bounds in linear time, 
 *                              followed by optimal KMP linear lookup validation.
 * Space Complexity: O(N)    -> Proportional to the length of the built target repeated string block.
 */

public class RepeatedStringMatch {
    public int repeatedStringMatch(String a, String b) {
        
        // TC - Average - O(N + M), Worst - O(N * M)
        // SC - O(N)
        // N = length of the repeated string
        // M = length of pattern b
        // This approach is very similar to the below approach, the only difference is that
        // we are using Rabin-Karp algorithm to compute the index of the pattern b in the repeated
        // string of a instead of indexOf() method
        int n1 = a.length();
        int n2 = b.length();

        // We only need to check up to (b.length() / a.length()) + 2 repetitions.
        // The extra repetitions handle cases where b overlaps two copies of a.
        int repeat = (n2 / n1) + 2;

        StringBuilder sb = new StringBuilder();

        // Repeat the string a set no. of times
        for (int i = 1; i <= repeat; i++) {
            sb.append(a);
        }

        // Starting index of the first occurrence of b.
        int idx = rabinKarpSearch(sb.toString(), b);

        if (idx != -1) {
            // Ending index of that occurrence.
            int lastIndexOfOccurrence = idx + n2 - 1;

            // Now if we divide the last index with the length of a, 
            // we will get the block at which the pattern is found
            // since, it'll be 0-indexed, so we added 1 to it 
            return (lastIndexOfOccurrence / n1) + 1;
        }

        return -1;





        /*
        // TC - O(N + M) & SC - O(N)
        // N = length of the repeated string
        // M = length of pattern b
        // This approach is very similar to the below approach, the only difference is that
        // we are using KMP algorithm to compute the index of the pattern b in the repeated
        // string of a instead of indexOf() method
        // We first build the repeated string in O(N),
        // then use KMP to search the pattern in O(N + M).
        int n1 = a.length();
        int n2 = b.length();

        // We only need to check up to (b.length() / a.length()) + 2 repetitions.
        // The extra repetitions handle cases where b overlaps two copies of a.
        int repeat = (n2 / n1) + 2;

        StringBuilder sb = new StringBuilder();

        // Repeat the string a set no. of times
        for (int i = 1; i <= repeat; i++) {
            sb.append(a);
        }

        // Starting index of the first occurrence of b.
        int idx = kmpSearch(sb.toString(), b);

        if (idx != -1) {
            // Ending index of that occurrence.
            int lastIndexOfOccurrence = idx + n2 - 1;

            // Now if we divide the last index with the length of a, 
            // we will get the block at which the pattern is found
            // since, it'll be 0-indexed, so we added 1 to it 
            return (lastIndexOfOccurrence / n1) + 1;
        }

        return -1;
        */




        /*
        // TC - O(N) & SC - O(N)
        // Here, N refers to the length of the final repeated string.
        // Appending is O(N) in total, but indexOf() is called after every append,
        // making the overall time quadratic.

        Although Java's indexOf() often performs very well in practice,
        // its exact implementation is JVM-dependent and its worst-case
        // complexity is not guaranteed by the Java specification.
        // KMP, however, guarantees O(N + M) time regardless of the input.

        int n1 = a.length();
        int n2 = b.length();

        // We only need to check up to (b.length() / a.length()) + 2 repetitions.
        // The extra repetitions handle cases where b overlaps two copies of a.
        int repeat = (n2 / n1) + 2;

        StringBuilder sb = new StringBuilder();

        // Repeat the string a set no. of times
        for (int i = 1; i <= repeat; i++) {
            sb.append(a);
        }

        // Starting index of the first occurrence of b.
        int idx = sb.indexOf(b);

        if (idx != -1) {
            // Ending index of that occurrence.
            int lastIndexOfOccurrence = idx + n2 - 1;

            // Now if we divide the last index with the length of a, 
            // we will get the block at which the pattern is found
            // since, it'll be 0-indexed, so we added 1 to it 
            return (lastIndexOfOccurrence / n1) + 1;
        }

        return -1;
        */



        /*
        // TC - O(N^2) & SC - O(N)
        // Here, N refers to the length of the final repeated string.
        // Appending is O(N) in total, but indexOf() is called after every append,
        // making the overall time quadratic.
        int n1 = a.length();
        int n2 = b.length();

        // We only need to check up to (b.length() / a.length()) + 2 repetitions.
        // The extra repetitions handle cases where b overlaps two copies of a.
        int repeat = (n2 / n1) + 2;

        StringBuilder sb = new StringBuilder();

        // Keep repeating the string a until it gets to a point that it has 
        // either exhausted its no of repeats allowed or it starts to contain b in it
        for (int i = 1; i <= repeat; i++) {
            sb.append(a);

            if (sb.indexOf(b) != -1) {
                return i;
            }
        }

        return -1;
        */
    }

    public int kmpSearch(String text, String pattern) {

        // KMP preprocesses the pattern to build the LPS array,
        // which stores the longest proper prefix that is also a suffix.
        // During mismatch, instead of restarting from the beginning,
        // it rolls back the pattern pointer using LPS, avoiding re-comparisons.
        
        // lps[i] = length of the longest proper prefix
        // which is also a suffix for pattern[0...i]
        int[] lps = new int[pattern.length()];

        buildLPS(lps, pattern);

        int i = 0;
        int j = 0;

        // Run the loop till the end of the text string
        while (i < text.length()) {
            
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
            if (j == pattern.length()) {
                return i - j;
            }
        }

        return -1;
    }

    public void buildLPS(int[] lps, String pattern) {
        
        // For single char, lps will always be 0
        lps[0] = 0;
        int i = 1;
        int len = 0;

        while (i < pattern.length()) {
            
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
    }

    public int rabinKarpSearch(String text, String pattern) {
        
        // Rabin-Karp compares hash values instead of comparing every character.
        // A rolling hash updates the current window in O(1), and
        // character-by-character comparison is done only when hashes match.

        int n = text.length();
        int m = pattern.length();

        int base = 131;
        int MOD = 1000000007;

        long power = 1;

        // Pre-compute the multiplier for the most significant value in a string
        for (int i = 0; i < m - 1; i++) {
            power = (power * base) % MOD;
        }

        long windowHash = 0;
        long patternHash = 0;

        // Pre-compute windowHash & patternHash
        for (int i = 0; i < m; i++) {
            windowHash = (windowHash * base + text.charAt(i)) % MOD;
            patternHash = (patternHash * base + pattern.charAt(i)) % MOD;
        }

        for (int i = 0; i <= n - m; i++) {
            
            // If windowHash and patternHash are equal,
            // there may be a chance that the two strings are equal, so check them char by char
            if (windowHash == patternHash) {
                int j = 0;
                while (j < m) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        break;
                    }
                    j++;
                }

                // If j reaches m, that means both strings are equal
                if (j == m) {
                    return i;
                }
            }

            // Rolling Hash
            // Removing the starting char and adding the next character for the window
            if (i < n - m) {
                windowHash = (windowHash - text.charAt(i) * power) % MOD;
                windowHash = (windowHash + MOD) % MOD; // this is to prevent negative value of windowHash
                windowHash = (windowHash * base + text.charAt(i + m)) % MOD;
            }
        }

        return -1;
    }
}
