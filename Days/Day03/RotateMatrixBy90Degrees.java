/**
 * Problem: Rotate Image (Matrix by 90° Clockwise)
 * Link: https://leetcode.com/problems/rotate-image/
 * 
 * Time Complexity: O(N^2) -> Double pass for Transpose and Row Reversal.
 * Space Complexity: O(1)  -> In-place elements mutation.
 */

public class RotateMatrixBy90Degrees {
    public void rotate(int[][] matrix) {
        // Rotate by 90 degrees is equal to
        // Transpose + reverse every row

        // Transpose the matrix
        transpose(matrix);
        
        // Reverse Every row
        for (int i = 0; i < matrix.length; i++) {
            reverseRow(matrix, i);
        }
    }

    public void transpose(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            // j = i + 1 because the lower triangle of the matrix
            // will already be swapped with upper triangle elements
            for (int j = i + 1; j < n; j++) {
                swap(matrix, i, j, j, i);
            }
        }
    }

    public void reverseRow(int[][] matrix, int row) {
        int n = matrix.length;

        int left = 0; 
        int right = n - 1;

        // Standard reversing approach
        while (left < right) {
            swap(matrix, row, left, row, right);

            left++;
            right--;
        }
    }

    // Swap function
    public void swap(int[][] matrix, int r1, int c1, int r2, int c2) {
        int temp = matrix[r1][c1];
        matrix[r1][c1] = matrix[r2][c2];
        matrix[r2][c2] = temp;
    }
}
