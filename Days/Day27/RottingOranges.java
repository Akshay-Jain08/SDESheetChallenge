/**
 * Problem: Rotting Oranges
 * Link: https://leetcode.com/problems/rotting-oranges/
 * 
 * Time Complexity: O(M * N) -> Linear scan pass followed by a multi-source level-order BFS traversal.
 * Space Complexity: O(M * N) -> Bounded by the visited grid allocation matrix and queue footprint.
 */

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {
    public int orangesRotting(int[][] grid) {
        
        // TC - O(M * N) & SC - O(M * N)
        // This approach uses Multi-point BFS traversal to propagate from
        // all the rotten oranges at the same time
        // Each BFS level represents one minute of rotting.
        int m = grid.length;
        int n = grid[0].length;

        // I am using the visited array, if you wish not to
        // You can as well make changes to grid directly by changing the value from 1 to 2
        // Only con is that you are making changes to the given data
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        int freshOranges = 0;

        // Store all the rotten oranges in the queue
        // Count the no of freshOranges
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[] {i, j});
                    visited[i][j] = true;
                }
                else if (grid[i][j] == 1) freshOranges++;
            }
        }

        // If there are no freshOranges, there is no need to check
        if (freshOranges == 0) return 0;

        // for propagating in all 4 directions
        int[] delRow = {1, 0, -1, 0};
        int[] delCol = {0, -1, 0, 1};

        int minutes = -1;

        // Standard BFS approach
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();

                for (int k = 0; k < 4; k++) {
                    int nRow = curr[0] + delRow[k];
                    int nCol = curr[1] + delCol[k];

                    if (nRow >= 0 && nRow < m && nCol >= 0 && nCol < n && grid[nRow][nCol] == 1 && !visited[nRow][nCol]) {
                        queue.offer(new int[] {nRow, nCol});
                        visited[nRow][nCol] = true;
                        freshOranges--;
                    }
                }
            }

            // every iteration, increment the minutes
            minutes++;
        }

        // If we were able to convert all the freshOranges, return the time taken.
        // Otherwise -1
        return freshOranges == 0 ? minutes : -1;
    }
}
