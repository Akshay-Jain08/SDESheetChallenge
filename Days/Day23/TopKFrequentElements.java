/**
 * Problem: Top K Frequent Elements
 * Link: https://leetcode.com/problems/top-k-frequent-elements/
 * 
 * Time Complexity: O(N) -> Linear frequency gathering and non-sorting bucket-sort extraction.
 * Space Complexity: O(N) -> Map storage and structural bucket list collections.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        
        // TC - O(N) & SC - O(N)
        // This approach uses Bucket Sort technique to store the elements in 
        // the bucket of their respective frequency
        int n = nums.length;

        // Map to store frequency
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // bucket[i] stores all elements appearing i times
        List<Integer>[] bucket = new ArrayList[n + 1];

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey();
            int freq = entry.getValue();

            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }

            bucket[freq].add(num);
        }

        // Making the res array by traversing the bucket from end
        int[] res = new int[k];
        int idx = 0;

        for (int i = bucket.length - 1; i >= 0; i--) {
            
            // If there is no element for that particular frequency
            if (bucket[i] != null) {
                for (int num : bucket[i]) {
                    res[idx++] = num;

                    if (idx == k) return res;
                }
            }
        }

        return res;




        /*
        // TC - O(N + U logK) & SC - O(N)
        // U - No. of unique elements
        // This approach uses a frequency map, and store unique elements with frequencies
        // to the priority queue which sorts the entries based on the frequencies
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        // Iterate over the map to store the entries to priority queue
        map.forEach((key, value) -> {
            minHeap.offer(new int[] {key, value});

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        });

        int[] res = new int[k];

        // Extract k most frequent elements
        for (int i = 0; i < k; i++) {
            res[i] = minHeap.poll()[0];
        }

        return res;
        */
    }
}
