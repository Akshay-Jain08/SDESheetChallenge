/**
 * Problem: Roman to Integer
 * Link: https://leetcode.com/problems/roman-to-integer/
 * 
 * Time Complexity: O(N) -> Single linear scan pass with constant-time value lookups.
 * Space Complexity: O(1) -> In-place mathematical evaluation in constant register memory.
 */

public class RomanToInteger {
    public int romanToInt(String s) {
        
        // TC - O(N) & SC - O(1)
        // This approach compares the current value to the next value
        // Normally symbols contribute positively.
        // If a smaller value appears before a larger value,
        // it acts as subtraction instead of addition.
        
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            int curr = getValue(s.charAt(i));
            int next = i < s.length() - 1 ? getValue(s.charAt(i + 1)) : 0;

            if (curr < next) {
                result -= curr;
            }
            else {
                result += curr;
            }
        }
        return result;




        /*
        // TC - O(N) & SC - O(1)
        // Just traverse the string and do according to the current symbol
        int n = s.length();
        int result = 0;

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            // simply add the value in case of M, D, L, V
            // when dealing with C, X, I
            // check if the next character is their counterpart which they decrease
            // or we can say, If current symbol appears before a larger valid symbol,
            // subtract its value instead of adding
            if (ch == 'M') result += 1000;

            else if (ch == 'D') result += 500;

            else if (ch == 'C') {
                if (i < n - 1 && (s.charAt(i + 1) == 'M' || s.charAt(i + 1) == 'D')) {
                    result -= 100;
                }
                else {
                    result += 100;
                }
            }

            else if (ch == 'L') result += 50;

            else if (ch == 'X') {
                if (i < n - 1 && (s.charAt(i + 1) == 'L' || s.charAt(i + 1) == 'C')) {
                    result -= 10;
                }
                else {
                    result += 10;
                }
            }

            else if (ch == 'V') result += 5;

            else if (ch == 'I') {
                if (i < n - 1 && (s.charAt(i + 1) == 'V' || s.charAt(i + 1) == 'X')) {
                    result -= 1;
                }
                else {
                    result += 1;
                }
            }
        }

        return result;
        */
    }

    // method to get the value for current roman numeral
    public int getValue(char ch) {
        switch (ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            default: return 1000;
        }
    }
}
