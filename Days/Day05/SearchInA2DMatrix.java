/**
 * Problem: Search a 2D Matrix
 * Link: https://leetcode.com/problems/search-a-2d-matrix/
 * 
 * Time Complexity: O(log(M * N)) -> Dual binary search runs in logarithmic bounds.
 * Space Complexity: O(1)        -> Pure index search pointer variables.
 */

public class SearchInA2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        
        // TC - O(logM + logN) = O(log(M * N))
        // lowerBound essentially does the same thing what we did in the below approach
        // just in better time complexity
        int row = lowerBound(matrix, target);

        // Binary search on the identified row
        return binarySearchRow(row, matrix, target);



        /*
        // TC - O(M log N)
        int m = matrix.length;

        int row = 0;

        // Iterating over the first col to check which row the target is likely to be in
        // Iterating from back to get the first row where target could possibly be
        // because all other rows will hold the condition, but the target would not be present in that row
        for (int i = m - 1; i >= 0; i--) {
            if (target >= matrix[i][0]) {
                row = i;
                break;
            }
        }

        // Binary search on the identified row
        return binarySearchRow(row, matrix, target);
        */


        /*
        TC - O(M x N)
        int m = matrix.length;
        int n = matrix[0].length;

        // Simply iterating over matrix to check for the target
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == target) {
                    return true;
                }
            }
        }

        return false;

        */
    }

    public int lowerBound(int[][] matrix, int target) {
        int start = 0;
        int end = matrix.length - 1;

        int ans = 0;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (target >= matrix[mid][0]) {
                ans = mid;
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }

        return ans;
    }

    public boolean binarySearchRow(int row, int[][] matrix, int target) {
        int start = 0;
        int end = matrix[0].length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (target > matrix[row][mid]) {
                start = mid + 1;
            }
            else if (target < matrix[row][mid]) {
                end = mid - 1;
            }
            else {
                return true;
            }
        }

        return false;
    }
}
