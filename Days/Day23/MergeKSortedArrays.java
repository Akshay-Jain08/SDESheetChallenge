/**
 * Problem: Merge K Sorted Arrays
 * Link: https://www.geeksforgeeks.org/problems/merge-k-sorted-arrays/1
 * 
 * Time Complexity: O(N * M * log N) -> Bounded by N-sized heap operations across all N * M items.
 * Space Complexity: O(N)             -> Restrictive workspace allocation keeping N coordinate items.
 */

import java.util.ArrayList;
import java.util.PriorityQueue;

public class MergeKSortedArrays {
    public ArrayList<Integer> mergeArrays(int[][] mat) {
        
        // TC - O(N * M * logN) & SC - O(N) excluding output
        // This approach uses a minHeap to store a single element from 
        // all the rows of the matrix
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        // Insert first element of every row
        for (int i = 0; i < mat.length; i++) {
            minHeap.offer(new int[] {mat[i][0], i, 0});
        }
        
        ArrayList<Integer> res = new ArrayList<>();
        
        // minHeap gives the smallest out of the all the rows
        // we, further, insert next element from same row
        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int val = curr[0];
            int row = curr[1];
            int col = curr[2];
            
            res.add(val);
            
            int newCol = col + 1;
            
            // if the newCol is within the bounds, then add it to the minHeap
            if (newCol < mat[0].length) {
                minHeap.offer(new int[] {mat[row][newCol], row, newCol});
            }
        }
        
        return res;
        
        
        
        /*
        Divide and Conquer Merge approach
        TC - O(N * M * logN) & SC - O(N * M)
        Suppose if there are 4 rows, R1, R2, R3, R4
        merge(R1, R2), merge(R3, R4)
        merge(result, result)
        */
        
        
        
        
        /*
        Brute Force
        TC - O(N * M * log(N * M)) & SC - O(N * M)
        Simply flatten the array and sort it afterwards
        */
    }
}
