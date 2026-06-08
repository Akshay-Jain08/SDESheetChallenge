/**
 * Problem: Longest Subarray with Sum K
 * Link: https://www.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1
 * 
 * Time Complexity: O(N)  -> Single-pass prefix sum map tracking.
 * Space Complexity: O(N) -> HashMap allocation for tracking original boundary indices.
 */

import java.util.HashMap;

public class LongestSubarrayWithSumK {
    public int longestSubarray(int[] arr, int k) {
        // Prefix Sum + HashMap Approach works on
        //    sum(l..r) = prefixSum(r) - prefixSum(l - 1)
        // => prefixSum(l - 1) = prefixSum(r) - sum(l..r)
        // So, if we subtract the sum fron currect prefixSum
        // we can lookup in the HashMap to check if the value has been
        // already processed as a previous prefixSum
        
        // Map to store the prefixSum -> index
        HashMap<Integer, Integer> map = new HashMap<>();
        
        int longestSequence = 0;
        int prefixSum = 0;
        
        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];
            
            // if the prefix sum is equal to the target value,
            // then, prefer it over any other subarray
            if (prefixSum == k) {
                longestSequence = i + 1;
            }
            else {
                // look up for left prefixSum 
                // if it exists, then update the longestSequence
                Integer idx = map.get(prefixSum - k);
                if (idx != null) {
                    longestSequence = Math.max(longestSequence, i - idx);
                }
            }
            
            // Only keep the first occurrence of a prefix sum
            // to maximize the length of the subarray
            if (!map.containsKey(prefixSum)) {
                map.put(prefixSum, i);
            }
        }
        
        return longestSequence;
        
        
        /*
        Brute-Force Approach would be to use 2 nested loops
        which goes through all the possible subarrays and return 
        the longest one's length
        
        TC - O(N^2) & SC - O(1)
        */
    }
}