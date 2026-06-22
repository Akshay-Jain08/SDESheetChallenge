/**
 * Problem: Maximum Sum Combinations / Top K Sum Pairs
 * Link: https://www.geeksforgeeks.org/problems/maximum-sum-combination/1
 * 
 * Time Complexity: O(N log N + K log K) -> Driven by initial array sorting and K heap extraction operations.
 * Space Complexity: O(K)                -> Storage bounds for the tracking hash map and priority queue.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class MaximumSumCombination {
    public ArrayList<Integer> topKSumPairs(int[] a, int[] b, int k) {
        
        // TC - O(N logN + K logK) & SC - O(K)
        // Sort the both the arrays
        Arrays.sort(a);
        Arrays.sort(b);
        int n = a.length;
        
        // Priority Queue that maintains sum, index 1, index 2
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((u, v) -> Integer.compare(v[0], u[0]));
        
        // HashSet to prevent the duplicate pairs
        Set<String> visited = new HashSet<>();
        
        // Start with the maximum sum i.e., the largest elements in both the arrays
        maxHeap.offer(new int[] {a[n - 1] + b[n - 1], n - 1, n - 1});
        visited.add((n - 1) + ", " + (n - 1));
        
        ArrayList<Integer> res = new ArrayList<>();
        
        while (k-- > 0 && !maxHeap.isEmpty()) {
            int[] curr = maxHeap.poll();
            int sum = curr[0], i = curr[1], j = curr[2];
            
            // add curr sum to the res
            res.add(sum);
            
            // Check for the i - 1 and j pair
            if (i - 1 >= 0) {
                String key1 = (i - 1) + ", " + j;
                
                if (visited.add(key1)) {
                    maxHeap.offer(new int[] {a[i - 1] + b[j], i - 1, j});
                }
            }
            
            // Check for i and j - 1 pair
            if (j - 1 >= 0) {
                String key2 = i + ", " + (j - 1);
                
                if (visited.add(key2)) {
                    maxHeap.offer(new int[] {a[i] + b[j - 1], i, j - 1});
                }
            }
        }
        
        return res;
    }
}
