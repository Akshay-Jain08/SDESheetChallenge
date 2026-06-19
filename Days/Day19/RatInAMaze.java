/**
 * Problem: Rat in a Maze
 * Link: https://www.geeksforgeeks.org/problems/rat-in-a-maze-problem/1
 * 
 * Time Complexity: O(4^(N * N)) -> Exponential path search branching factor tree limits.
 * Space Complexity: O(N^2)       -> Driven by the visited tracking matrix state grid.
 */

import java.util.ArrayList;

public class RatInAMaze {
    public ArrayList<String> ratInMaze(int[][] maze) {
        
        // TC - O(4^(N * N)) & SC - O(N^2)
        // This approach uses standard recursion and backtracking approach to keep 
        // approach the last cell following the lexicographical order 
        // fixed by dr and dc arrays
        ArrayList<String> result = new ArrayList<>();
        
        // If the first cell is blocked, the rat cannot move further
        if (maze[0][0] == 0) return result;
        
        int n = maze.length;
        boolean[][] visited = new boolean[n][n];
        
        int[] dr = {1, 0, 0, -1};
        int[] dc = {0, -1, 1, 0};
        char[] dir = {'D', 'L', 'R', 'U'};
        
        solve(0, 0, result, new StringBuilder(), n, maze, visited, dr, dc, dir);
        
        return result;
    }
    
    public void solve(int row, int col, ArrayList<String> result, StringBuilder sb, int n, int[][] maze, boolean[][] visited, int[] dr, int[] dc, char[] dir) {
        
        // Base Case
        if (row == n - 1 && col == n - 1) {
            result.add(sb.toString());
            return;
        }
        
        // Mark the current cell
        visited[row][col] = true;
        
        // To traverse in all 4 directions
        for (int k = 0; k < 4; k++) {
            int nRow = row + dr[k];
            int nCol = col + dc[k];
            
            // If the new row and new col is within the bounds and not blocked and not visited,
            // move further into recursive calls
            if (nRow >= 0 && nCol >= 0 && nRow < maze.length && nCol < maze.length && maze[nRow][nCol] == 1 && !visited[nRow][nCol]) {
                
                // append the current direction
                sb.append(dir[k]);
                
                solve(nRow, nCol, result, sb, n, maze, visited, dr, dc, dir);
                
                // Backtrack the movement
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        
        // Backtrack the current marking on the cell
        visited[row][col] = false;
    }
}
