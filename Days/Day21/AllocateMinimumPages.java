/**
 * Problem: Allocate Books / Book Allocation Problem
 * Link: https://www.geeksforgeeks.org/problems/allocate-minimum-number-of-pages0937/1
 * 
 * Time Complexity: O(N * log(sum)) -> Value range binary search with linear allocation validation scans.
 * Space Complexity: O(1)           -> Pure in-place scalar tracking variables.
 */

public class AllocateMinimumPages {
    public int findPages(int[] arr, int k) {
        
        // TC - O(N * log(sum)) & SC - O(1)
        // This is typical binary search on answer
        
        // Edge case
        if (k > arr.length) return -1;
        
        // low is max no of pages in any book
        // high is sum of pages in all the books
        // answer will lie somewhere in between [low, high]
        int low = 0;
        int high = 0;
        
        for (int pages : arr) {
            low = Math.max(low, pages);
            high += pages;
        }
        
        while (low <= high) {
            
            // mid is the limit that we will be checking
            // if for current limit, no. of valid students will be compared with k
            int limit = low + (high - low) / 2;
            
            int students = 1;
            int allottedPages = 0;
            
            for (int pages : arr) {
                
                if (allottedPages + pages <= limit) {
                    allottedPages += pages;
                }
                else {
                    students++;
                    allottedPages = pages;
                }
            }
            
            // if students <= target, that means we have to further lower the limit 
            // to check if any smaller answer is possible
            if (students <= k) {
                high = limit - 1;
            }
            // else it means we have to increase the limit
            else {
                low = limit + 1;
            }
        }
        
        // We search for minimum feasible limit
        // After loop:
        // high → last impossible
        // low → first feasible
        return low;
    }
}
