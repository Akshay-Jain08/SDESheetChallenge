/**
 * Problem: Median in a Row-wise Sorted Matrix
 * Link: https://www.geeksforgeeks.org/problems/median-in-a-row-wise-sorted-matrix1527/1
 * 
 * Time Complexity: O(M * log N * log(max - min)) -> Value range binary search with row-by-row upper-bound scans.
 * Space Complexity: O(1)                        -> Pure in-place index arithmetic.
 */

import java.util.ArrayList;
import java.util.Collections;

public class MedianInARowWiseSortedMatrix {
    public int median(int[][] mat) {
        
        // TC - O(N * logM * log(max - min)) & SC - O(1)
        // This approach works by applying binary search on the value range of the 
        // given matrix, since we know that median will always lie within the range
        
        // So, for every mid, we check how many elements in the matrix are
        // lesser or equal to the mid.
        
        // If count(elements <= mid) > medianIndex,
        // then enough elements already exist before or at mid,
        // therefore median <= mid
        int m = mat.length;
        int n = mat[0].length;
        
        int low = mat[0][0];
        int high = mat[0][0];
        
        int medianPos = (m * n) / 2;
        
        for (int i = 0; i < mat.length; i++) {
            low = Math.min(low, mat[i][0]);
            high = Math.max(high, mat[i][mat[0].length - 1]);
        }
        
        while (low <= high) {
            
            int mid = low + (high - low) / 2;
            
            int lessOrEqual = 0;
            
            // Counting numbers that are lesser or equal to mid
            // with the help of upper bound (binary search) on every row
            for (int[] row : mat) {
                lessOrEqual += upperBound(row, mid);
            }
            
            // If the no of elements is less than or equal to the mid Index
            // then the median will surely lie in the right half (0 - based indexing)
            if (lessOrEqual <= medianPos) {
                low = mid + 1;
            }
            // If the no of elements are greater than mid Index,
            // then the median <= mid
            else {
                high = mid - 1;
            }
        }
        
        return low;
        
        
        
        
        
        /*
        
        // TC - O(N * M * log(N * M)) & SC - O(N * M)
        // This approach works by flattening the given matrix and
        // sorting the flattened array 
        ArrayList<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                list.add(mat[i][j]);
            }
        }
        
        Collections.sort(list);
        
        // Median will be the middle index
        return list.get(list.size() / 2);
        
        */
    }
    
    // Method to calculate upper Bound which is indirectly
    // no of elements lesser or equal to target
    public int upperBound(int[] nums, int target) {
        
        int left = 0;
        int right = nums.length - 1;
        
        int ans = nums.length;
        
        while (left <= right) {
            
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > target) {
                ans = mid;
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        
        return ans;
    }
}
