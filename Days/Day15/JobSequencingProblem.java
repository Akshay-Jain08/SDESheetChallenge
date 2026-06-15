/**
 * Problem: Job Sequencing Problem
 * Link: https://www.geeksforgeeks.org/problems/job-sequencing-problem-1587115620/1
 * 
 * Time Complexity: O(N log N) -> Driven by sorting deadlines and processing elements via min-heap.
 * Space Complexity: O(N)       -> Required for the 2D jobs array and the heap footprint.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class JobSequencingProblem {
    public ArrayList<Integer> jobSequencing(int[] deadline, int[] profit) {
        // code here
        
        // TC - O(N logN) & SC - O(N)
        
        // This approach uses a priority queue to store the profits of all the chosen jobs
        // Whenever the size of minHeap exceeds the currJobDeadline means
        // we have selected more jobs than available slots, remove it
        int n = deadline.length;
        int[][] jobs = new int[n][2];
        
        // Making the jobs array
        for (int i = 0; i < n; i++) {
            jobs[i][0] = deadline[i];
            jobs[i][1] = profit[i];
        }
        
        // Sort the jobs array based on deadline
        Arrays.sort(jobs, (a, b) -> Integer.compare(a[0], b[0]));
        
        int maxProfit = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        // Iterate through the jobs array to select the best possible jobs
        for (int i = 0; i < n; i++) {
            int currJobDeadline = jobs[i][0];
            int currJobProfit = jobs[i][1];
            
            minHeap.offer(currJobProfit);
            maxProfit += currJobProfit;
            
            // if we have selected more jobs than available slots, 
            // remove the smallest profit
            if (minHeap.size() > currJobDeadline) {
                maxProfit -= minHeap.poll();
            }
        }
        
        
        return new ArrayList<>(Arrays.asList(minHeap.size(), maxProfit));
        
        
        
        
        /*
        
        Naive Greedy Approach
        TC - O(N^2) & SC - O(N)
        
        Sort the jobs array based on the profit in non-increasing order
        Now, iterate through the jobs array to get the maximum possible
        profit out of all available jobs
        Whatever the deadline is, check from deadline index all the way to 
        starting index to fill the slot with that job only (greedy for profit)
        
        Although, for current constraint that appraoch would have failed due to TLE
        
        
        */
    }
}
