/**
 * Problem: Valid Parentheses
 * Link: https://leetcode.com/problems/valid-parentheses/
 * 
 * Time Complexity: O(N) -> Single linear scan with constant-time stack updates.
 * Space Complexity: O(N) -> Linear workspace storage for unmatched nested characters.
 */

import java.util.ArrayDeque;

public class ValidParantheses {
    public boolean isValid(String s) {

        // TC - O(N) & SC - O(N)
        // This approach uses a stack to store the open brackets and 
        // closing brackets to normalize (pop) the open brackets
        ArrayDeque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // If the current char is any closing bracket, the stack must have 
            // its respective open bracket to be valid,
            // Otherwise, it's not valid 
            if (ch == ')') {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                }
                else {
                    return false;
                }
            }
            else if (ch == '}') {
                if (!stack.isEmpty() && stack.peek() == '{') {
                    stack.pop();
                }
                else {
                    return false;
                }
            }
            else if (ch == ']') {
                if (!stack.isEmpty() && stack.peek() == '[') {
                    stack.pop();
                }
                else {
                    return false;
                }
            }
            // Push any opening bracket to the stack
            else {
                stack.push(ch);
            }
        }

        // The given string is valid only if all the open brackets
        // have been normalized by their respective closing brackets
        // i.e. stack must be empty 
        return stack.isEmpty();
    }
}
