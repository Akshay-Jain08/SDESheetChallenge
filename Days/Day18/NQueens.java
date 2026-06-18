/**
 * Problem: N-Queens
 * Link: https://leetcode.com/problems/n-queens/
 * 
 * Time Complexity: O(N! * N^2) -> N! placement branches paired with O(N) safety scans and O(N^2) base case string conversions.
 * Space Complexity: O(N^2)     -> Driven by the 2D grid allocation layout board.
 */

import java.util.ArrayList;
import java.util.List;

public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        
        // TC - O(N * N!) & SC - O(N^2)
        // This is the standard backtracking approach to solve the N Queens Problem
        // Used a result list for final output
        // and a board list for the current solution
        List<List<String>> result = new ArrayList<>();
        List<List<String>> board = new ArrayList<>();

        // Fill all places with "."
        for (int i = 0; i < n; i++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(".");
            }
            board.add(row);
        }

        solve(0, n, board, result);

        return result;
    }

    public void solve(int row, int n, List<List<String>> board, List<List<String>> result) {

        // Base Case
        // Convert the 2D list board to 1D list finishedBoard by converting each
        // list from the board into a String
        if (row == n) {
            List<String> finishedBoard = new ArrayList<>();
            for (List<String> r : board) {
                finishedBoard.add(String.join("", r));
            }
            result.add(finishedBoard);
            return;
        }

        // Check for all the col for the particular row
        for (int col = 0; col < n; col++) {
            
            // if that col is safe, then move ahead and call the recursive function and then backtrack
            if (isSafe(row, col, board)) {
                board.get(row).set(col, "Q");
                solve(row + 1, n, board, result);
                board.get(row).set(col, ".");
            }
        }
    }

    public boolean isSafe(int row, int col, List<List<String>> board) {

        // check the current col
        // no need to check the current row as it'll automatically move forward to next row
        // when we place a Queen
        for (int i = 0; i < row; i++) {
            if (board.get(i).get(col).equals("Q")) return false;
        }

        // Upper Left Diagonal
        int minLeft = Math.min(row, col);
        for (int i = 1; i <= minLeft; i++) {
            if (board.get(row - i).get(col - i).equals("Q")) return false;
        }

        // Upper Right Diagonal
        int minRight = Math.min(row, board.size() - col - 1);
        for (int i = 1; i <= minRight; i++) {
            if (board.get(row - i).get(col + i).equals("Q")) return false;
        }

        return true;
    }
}
