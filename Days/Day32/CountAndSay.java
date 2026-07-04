/**
 * Problem: Count and Say
 * Link: https://leetcode.com/problems/count-and-say/
 * 
 * Time Complexity: O(N * M) -> Exponential generation pass tracking run-length character sequences.
 * Space Complexity: O(M)     -> Dynamic builder memory scaling with the maximum sequence character length.
 */

public class CountAndSay {
    public String countAndSay(int n) {

        // TC - O(N * M) & SC - O(M)
        // M = Average length of the encoded value

        // This approach is same as the below approach, the only diff is that
        // it is an iterative approach while the other one if recursive approach
        
        // Base Case
        String s = "1";

        // Since we already know the value for 1, run the loop from 2
        for (int p = 2; p <= n; p++) {

            // StringBuilder to store the run length encoded value
            StringBuilder sb = new StringBuilder();

            // i scans the string,
            // cnt counts consecutive identical digits.
            int i = 1;
            int cnt = 1;
            while (i <= s.length()) {
                
                // count all the consecutive identical characters
                while (i < s.length() && s.charAt(i) == s.charAt(i - 1)) {
                    i++;
                    cnt++;
                }

                // append the count and the respective character
                sb.append(cnt);
                sb.append(s.charAt(i - 1));

                // reset the counter
                cnt = 1;
                i++;
            }

            s = sb.toString();
        }

        return s;




        /*
        // TC - O(N * M) & SC - O(M)
        // M = Average length of the encoded value

        // Base Case
        if (n == 1) return "1";

        // We have to encode the coundAndSay value of n - 1 to get the countAndSay value of n
        String s = countAndSay(n - 1);

        // StringBuilder to store the run length encoded value
        StringBuilder sb = new StringBuilder();
        
        // i scans the string,
        // cnt counts consecutive identical digits.
        int i = 1;
        int cnt = 1;
        while (i <= s.length()) {
            
            // count all the consecutive identical characters
            while (i < s.length() && s.charAt(i) == s.charAt(i - 1)) {
                i++;
                cnt++;
            }

            // append the count and the respective character
            sb.append(cnt);
            sb.append(s.charAt(i - 1));

            // reset the counter
            cnt = 1;
            i++;
        }

        return sb.toString();
        */
    }
}
