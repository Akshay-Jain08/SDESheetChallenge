/*
Problem: Set Matrix Zeroes
Link: https://leetcode.com/problems/set-matrix-zeroes/

Time Complexity: O(M * N) -> Where M is rows and N is columns
Space Complexity: O(1)    -> Optimized in-place solution using first row/col as markers
*/

import java.util.*;

public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {

        // Optimized Solution
        // TC - O(M * N)
        // SC - O(1)
        // For tracking the first row and column
        boolean firstRowZero = false;
        boolean firstColZero = false;


        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                // Assiging the first elements of the zero rows and columns as 0
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;

                    if (i == 0) {
                        firstRowZero = true;
                    }
                    if (j == 0) {
                        firstColZero = true;
                    }
                }
            }
        }

        // Updating all the rows except the first row as 0 based on their first element
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Updating all the cols except the first col as 0 based on their first element
        for (int j = 1; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Handling first col
        if (firstColZero) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }

        // Handling first row
        if (firstRowZero) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }


        // Semi-Optimized Solution
        // TC - O(M * N)
        // SC - O(M + N)
        // int m = matrix.length;
        // int n = matrix[0].length;

        // // For storing the indices of those rows and cols which needs to be updated to 0
        // int[] row = new int[m];
        // int[] col = new int[n];

        // for (int i = 0; i < m; i++) {
        //     for (int j = 0; j < n; j++) {
        //         // Updating the indices 
        //         if (matrix[i][j] == 0) {
        //             row[i] = 1;
        //             col[j] = 1;
        //         }
        //     }
        // }

        // // Updating the given matrix in-place
        // for (int i = 0; i < m; i++) {
        //     for (int j = 0; j < n; j++) {
        //         if (row[i] == 1 || col[j] == 1) {
        //             matrix[i][j] = 0;
        //         }
        //     }
        // }



        // Brute Force
        // TC - O(M * N * (M + N))
        // SC - O(M * N)

        // // A list for storing coordinates (rows and cols) which are 0 in the given matrix
        // ArrayList<int[]> zerosCoordinates = new ArrayList<>();

        // for (int i = 0; i < matrix.length; i++) {
        //     for (int j = 0; j < matrix[0].length; j++) {
        //         // Adding the coordinates
        //         if (matrix[i][j] == 0) {
        //             zerosCoordinates.add(new int[] {i, j});
        //         }
        //     }
        // }

        // // Updating the matrix in-place
        // for (int[] coordinates : zerosCoordinates) {
        //     int row = coordinates[0];
        //     int col = coordinates[1];

        //     for (int i = 0; i < matrix.length; i++) {
        //         matrix[i][col] = 0;
        //     }

        //     for (int j = 0; j < matrix[0].length; j++) {
        //         matrix[row][j] = 0;
        //     }
        // }
    }
}