/**
 * Problem: String to Integer (atoi)
 * Link: https://leetcode.com/problems/string-to-integer-atoi/
 * 
 * Time Complexity: O(N) -> Single linear scan pass with constant-time character validations.
 * Space Complexity: O(1) -> In-place mathematical evaluation in constant register memory.
 */

public class StringToInteger {
    public int myAtoi(String s) {
        
        // TC - O(N) & SC - O(1)
        // This is like a space optimized approach of the below solution
        // Instead of relying on trim() and substring() to remove
        // leading spaces and process the sign, we use a pointer.
        int n = s.length();
        int i = 0;
        long result = 0;
        int sign = 1;

        // Skip all the leading spaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // Handle the sign
        if (i < n && (s.charAt(i) == '-' || s.charAt(i) == '+')) {
            sign = s.charAt(i) == '-' ? -1 : 1;
            i++;
        }

        // Traverse the numeric part and build the integer.
        while (i < n) {
            char ch = s.charAt(i);
            
            // If the current char is not a digit, immediately break
            if (!Character.isDigit(ch)) break;

            // add the digit to the result
            int digit = ch - '0';
            result = result * 10 + digit;

            // If the result exceeds int_max and it is a positive number
            // the result will be int_max value
            if (sign == 1 && result > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }

            // If the result exceeds int_min and it is a negative number
            // the result will be int_min value
            if (sign == -1 && -result < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }

            i++;
        }

        return (int) (result * sign);




        /*
        // TC - O(N) & SC - O(N)
        // This approach simply traverses the string and build the result on the go
    
        // Trim to remove any leading or trailing spaces
        s = s.trim();

        // Edge Case
        if (s.length() == 0) {
            return 0;
        }

        long result = 0;
        int sign = 1;

        // If the first char represents negative number, 
        // advance to the next character
        if (s.charAt(0) == '-') {
            sign = -1;
            s = s.substring(1);
        }
        else if (s.charAt(0) == '+') {
            s = s.substring(1);
        }

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            
            // If the current char is not a digit, immediately break
            if (!Character.isDigit(ch)) break;

            // add the digit to the result
            result = result * 10 + (ch - '0');

            // If the result exceeds int_max and it is a positive number
            // the result will be int_max value
            if (sign == 1 && result > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }

            // If the result exceeds int_min and it is a negative number
            // the result will be int_min value
            if (sign == -1 && -result < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }

        return (int) (result * sign);
        */
    }
}
