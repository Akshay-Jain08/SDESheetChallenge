/*
Problem: Pascal's Triangle
Link: https://leetcode.com/problems/pascals-triangle/

Time Complexity: O(N^2)  -> Where N is numRows. Nested Loops visit each element
Space Complexity: O(N^2) -> To store the resulting 2D List
*/

import java.util.*;

public class PascalsTriangle1 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        // Edge Case
        if (numRows == 0) return result;

        // Adding numRows number of ArrayList
        for (int i = 0; i < numRows; i++) {
            result.add(new ArrayList<Integer>());
        }
        result.get(0).add(1);

        for (int i = 1; i < numRows; i++) {
            // prev list for the curr list computations
            int prev = i - 1;
            int prevSize = result.get(prev).size();

            int prevElement = -1;

            for (int j = 0; j < prevSize; j++) {
                int currElement = result.get(prev).get(j);

                if (j == 0) {
                    result.get(i).add(currElement);
                } 
                else {
                    result.get(i).add(prevElement + currElement);
                }

                if (j == prevSize - 1) {
                    result.get(i).add(currElement);
                }

                prevElement = currElement;
            }
        }
        return result;
    }
}
