/**
 * Problem: Compare Version Numbers
 * Link: https://leetcode.com/problems/compare-version-numbers/
 * 
 * Time Complexity: O(N + M) -> Single linear parallel scan over characters.
 * Space Complexity: O(1)     -> In-place constant register evaluation.
 */

public class CompareVersionNumbers {
    public int compareVersion(String version1, String version2) {
        
        // TC - O(N + M) & SC - O(1)
        // Parse one revision at a time from both version strings.
        // Missing revisions are treated as 0.

        // Default revisions
        int revision1 = 0;
        int revision2 = 0;

        // Two pointers for both the versions
        int i = 0;
        int j = 0;

        // Keep running the loop if either of the version has revisions left
        // since we can keep the revision as 0 if either of the version doesn't have any respective revision
        while (i < version1.length() || j < version2.length()) {
            
            // Compute the current revision of version1
            while (i < version1.length() && version1.charAt(i) != '.') {
                char ch = version1.charAt(i);

                revision1 = revision1 * 10 + (ch - '0');
                i++;
            }

            // Compute the current revision of version2
            while (j < version2.length() && version2.charAt(j) != '.') {
                char ch = version2.charAt(j);

                revision2 = revision2 * 10 + (ch - '0');
                j++;
            }

            // Check the revisions at every iteration 
            if (revision1 > revision2) {
                return 1;
            }
            else if (revision1 < revision2) {
                return -1;
            }

            // If both the revisions are equal, check further revisions
            else {
                revision1 = 0;
                revision2 = 0;
                i++;
                j++;
            }
        }

        // return 0 if all the revisions were equal
        return 0;



        /*
        TC - O(N + M) & SC - O(N + M)
        Another approach is to split both version strings using:

            String[] a = version1.split("\\.");

        This separates each version into its individual revisions, which can then
        be compared one by one.

        However, split() creates additional String objects and arrays, resulting
        in extra memory usage and object creation overhead compared to the
        two-pointer approach.
        */
    }
}
