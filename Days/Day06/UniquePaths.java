/**
 * Problem: Grid Unique Paths
 * Link: https://leetcode.com/problems/unique-paths/
 * 
 * Time Complexity: O(M * N) -> Each matrix block state evaluated exactly once.
 * Space Complexity: O(M * N) -> Storage allocated for the 2D DP cache grid.
 */

import java.util.Arrays;

public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return solveMemo(m, n, 0, 0, dp);
    }

    public int solveMemo(int m, int n, int row, int col, int[][] dp) {
        if (row == m - 1 && col == n - 1) {
            return 1;
        }

        if (dp[row][col] != -1) return dp[row][col];

        int right = 0, down = 0;
        
        if (row < m - 1) {
            down = solveMemo(m, n, row + 1, col, dp);
        }
        if (col < n - 1) {
            right = solveMemo(m, n, row, col + 1, dp);
        }

        return dp[row][col] = right + down;
    }
}
