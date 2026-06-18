/**
 * Problem: Sudoku Solver
 * Link: https://leetcode.com/problems/sudoku-solver/
 * 
 * Time Complexity: O(9^E) -> Where E is the count of empty cells. Up to 9 branches per cell.
 * Space Complexity: O(E)   -> Driven entirely by the recursion call stack depth over empty cells.
 */

public class SudokuSolver {
    public void solveSudoku(char[][] board) {
        
        // TC - O(9^E) & SC - O(E)
        // E - No. of Empty cells
        // This used Standard Recursion and Backtracking approach
        solve(0, 0, board);
    }

    public boolean solve(int row, int col, char[][] board) {

        // Base Case: The Sudoku is solved
        if (row == board.length) return true;

        // If col is exhausted, shift to next row
        if (col == board[0].length) {
            return solve(row + 1, 0, board);
        }

        // If the current cell already contains a number, skip it
        if (board[row][col] != '.') {
            return solve(row, col + 1, board);
        }

        // Check all the numbers in the current cell and keep moving forward
        // recursively attempt remaining cells;
        // if impossible, undo current placement
        for (int i = 1; i <= 9; i++) {

            if (isSafe(i, row, col, board)) {
                board[row][col] = (char)(i + '0');

                // If the current choice leads to the successful completion of sudoku, return true;
                if (solve(row, col + 1, board)) {
                    return true;
                }

                // Otherwise backtrack and let the next choice be at the current cell
                board[row][col] = '.';
            }
        }
        return false;
    }

    public boolean isSafe(int num, int row, int col, char[][] board) {
        
        // Current col
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == (char)(num + '0')) return false;
        }

        // Current row
        for (int i = 0; i < board[0].length; i++) {
            if (board[row][i] == (char)(num + '0')) return false;
        }

        // Every number is placed in 3 * 3 block and a block
        // suppose the current cell is (5, 2)
        // so the block number is (1, 0) and start coordinates are 3, 0
        int blockRowStart = (row / 3) * 3;
        int blockColStart = (col / 3) * 3;

        for (int i = blockRowStart; i <= blockRowStart + 2; i++) {
            for (int j = blockColStart; j <= blockColStart + 2; j++) {
                if (board[i][j] == (char)(num + '0')) return false;
            }
        }

        return true;
    }
}
