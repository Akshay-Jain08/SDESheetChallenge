/**
 * Problem: Count Subarrays with Given XOR K
 * Link: https://www.geeksforgeeks.org/problems/count-subarray-with-given-xor/1
 * 
 * Time Complexity: O(N)  -> Single-pass linear scan with prefix XOR frequency tracking.
 * Space Complexity: O(N) -> HashMap allocation for storing prefix XOR frequency counts.
 */

import java.util.HashMap;

public class CountSubarraysWithGivenXOR {
    public long subarrayXor(int arr[], int k) {
        // This is a HashMap + prefixSum approach
        // PrefixSum because it works exactly like a prefixSum
        // but with a prefixXor approach
        // Just like sum(l..r) = prefixSum[r] - prefixSum[l - 1] 
        // is equal to prefixSum[l - 1] = prefixSum[r] - sum(l..r)
        
        // It works like xor(l..r) == prefixXor[r] ^ prefixXor(l - 1)
        // which is equivalent to prefixXor(l - 1) = prefixXor(r) ^ xor(l..r)
        // so at every point we can look for a prefixXor(l - 1) for a potential subarray
        long res = 0;
        
        // Map to store prefixXor -> frequency because we need to return count
        HashMap<Integer, Integer> map = new HashMap<>();
        
        // This will be used to combine two steps into one as described below
        // map.put(0, 1);
        
        int prefixXor = 0;
        
        for (int i = 0; i < arr.length; i++) {
            prefixXor ^= arr[i];
            
            // when prefixXor is equal to k, increment res
            if (prefixXor == k) {
                res++;
            }
            
            // Find the no. of occurrence of prefixXor(l - 1) and add it to the res
            Integer occurrence = map.get(prefixXor ^ k);
            if (occurrence != null) {
                res += occurrence;
            }
            
            // Above two steps can be combined into this one step
            // res += map.getOrDefault(prefixXor ^ k, 0);
            
            map.put(prefixXor, map.getOrDefault(prefixXor, 0) + 1);
        }
        return res;
        
        
        
        /*
        Brute-Force Approach would be to use 2 nested loops
        which goes through all the possible subarrays and 
        add 1 to the answer for every required subarray
        
        TC - O(N^2) & SC - O(1)
        */
    }
}
