/**
 * Problem: Activity Selection / N Meetings in One Room
 * Link: https://www.geeksforgeeks.org/problems/activity-selection-1587115620/1
 * 
 * Time Complexity: O(N log N) -> Driven by sorting the activities by finish time.
 * Space Complexity: O(N)       -> Required for the 2D array tracking merged pairs.
 */

import java.util.Arrays;

public class ActivitySelection {
    public int activitySelection(int[] start, int[] finish) {
        // code here
        
        // TC - O(N logN) & SC - O(N)
        // This approach sorts the activity array based on the finish time
        // because earlier completion maximizes remaining scheduling opportunities
        
        int n = start.length;
        int[][] activity = new int[n][2];
        
        // Making the activity array
        for (int i = 0; i < n; i++) {
            activity[i][0] = start[i];
            activity[i][1] = finish[i];
        }
        
        // Sorting activity array based on finish time in non-decreasing order
        Arrays.sort(activity, (a, b) -> Integer.compare(a[1], b[1]));
        
        int result = 0;
        int currentFinish = -1;
        
        for (int i = 0; i < n; i++) {
            // if current activity is starting after previous activity finished
            // select that activity and update currentFinish
            if (activity[i][0] > currentFinish) {
                currentFinish = activity[i][1];
                result++;
            }
        }
        
        return result;
    }
}