/**
 * Problem: Index of First Occurrence of Pattern / Search Pattern (Z-Function)
 * Link: https://www.geeksforgeeks.org/problems/search-pattern0205/1
 * 
 * Time Complexity: O(N + M) -> Linear string processing pass via sliding boundary Z-box expansions.
 * Space Complexity: O(N + M) -> Linear workspace layout caching unified array segments.
 */

import java.util.ArrayList;

public class SearchPattern {
    ArrayList<Integer> search(String pat, String txt) {
        
        // TC - O(N + M) & SC - O(N + M)
        // We are using Z function pattern matching algorithm here
        // It uses a Z array which describes z[i] as the longest prefix of the string
        // that matches with the substring starts at i
        
        // Make the pat + random char + txt string
        String str = pat + "$" + txt;
        
        // Make the z array for this string
        int[] z = new int[str.length()];
        buildZArray(z, str);
        
        int m = pat.length();
        ArrayList<Integer> result = new ArrayList<>();
        
        // Now, every time a value in z array is equivalent to the pat.length()
        // we can say that a substring starting from that index is equal to the 
        // prefix of length pat.length()
        for (int i = m + 1; i < z.length; i++) {
            
            if (z[i] == m) {
                result.add(i - m - 1);
            }
        }
        
        return result;
    }
    
    public void buildZArray(int[] z, String str) {
        
        // using l and r as boundaries (inclusive)
        int l = 0;
        int r = 0;
        
        for (int i = 1; i < str.length(); i++) {
            
            // When the current index is inside the boundary, that means
            // we can use a pre-computed mirror value from the prefix
            if (i <= r) {
                
                // mirror index
                int k = i - l;
                
                // In case, the value surpass the current boundary
                // we cannot put a value more than the distance from the current
                // index to the boundary as the further elements have not been computed
                z[i] = Math.min(z[k], r - i + 1);
            }
            
            // Generally expansion is possible in two cases
            // i + z[i] - 1 == r & i + z[i] - 1 > r 
            // Try to extend the current match beyond the known Z-box.
            // If no further characters match, this loop exits immediately.
            while (i + z[i] < str.length() && str.charAt(z[i]) == str.charAt(i + z[i])) {
                z[i]++;
            }
            
            // Update the boundaries
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
    }
}
