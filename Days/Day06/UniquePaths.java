/**
 * Problem: Grid Unique Paths
 * Link: https://leetcode.com/problems/unique-paths/
 * 
 * Time Complexity: O(min(M, N)) -> Extremely optimized linear combinatorics loop.
 * Space Complexity: O(1)          -> constant scalar storage calculations.
 */

import java.util.Arrays;

public class UniquePaths {
    public int uniquePaths(int m, int n) {
        // We can also use another method called combinatorics
        // for a grid of size m * n, we have to go right n - 1 times and down m - 1 times
        // so total no of moves = m + n - 2;
        // Now, out of those total moves, if we can select either right or down moves, (by nCr)
        // then the ans would be the total no of paths to reach the end

        int totalMoves = m + n - 2;
        int downMoves = m - 1;
        int rightMoves = n - 1;
        int k = Math.min(downMoves, rightMoves);

        // taking long ans to prevent integer overflow during the nCr calculation
        long ans = 1;
        // Standard iterative way to calculate nCr
        for (int i = 1; i <= k; i++) {
            ans *= (totalMoves - i + 1);
            ans /= i;
        }

        return (int) ans;



        /*
        TC - O(M x N) & SC - O(M x N)
        // This is the standard DP approach to solve the problem, we can also solve it with space optimization

        int[][] dp = new int[m][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return solveMemo(m, n, 0, 0, dp);
        */
    }

    public int solveMemo(int m, int n, int row, int col, int[][] dp) {
        // Base case: Reached the destination
        if (row == m - 1 && col == n - 1) {
            return 1;
        }

        // Return cached result if already evaluated
        if (dp[row][col] != -1) return dp[row][col];

        int right = 0, down = 0;
        
        if (row < m - 1) {
            down = solveMemo(m, n, row + 1, col, dp);
        }
        if (col < n - 1) {
            right = solveMemo(m, n, row, col + 1, dp);
        }

        // Cache and return total paths
        return dp[row][col] = right + down;
    }
}
