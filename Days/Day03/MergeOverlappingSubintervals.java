/**
 * Problem: Merge Overlapping Subintervals
 * Link: https://leetcode.com/problems/merge-intervals/
 * 
 * Time Complexity: O(N log N) -> Dominated by the standard Array sorting operation.
 * Space Complexity: O(N)      -> Needed to allocate the worst-case maximum size bounds for the output result.
 */

import java.util.Arrays;

public class MergeOverlappingSubintervals {
    public int[][] merge(int[][] intervals) {
        
        // This is just a refined version of the solution below it
        // with same time and space complexity, just cleaner

        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        int[][] result = new int[n][2];
        int idx = 0;

        // start and end variables instead of overwriting the original array
        int start = intervals[0][0];
        int end = intervals[0][1];

        // comparing with previous and current, instead of current and next in the previous approach
        for (int i = 1; i < n; i++) {
            // when previous end is greater than currentStart
            if (end >= intervals[i][0]) {
                // Update the end
                end = Math.max(end, intervals[i][1]);
            }
            else {
                // When the current interval is not overlapping with the previous one,
                // Update the result and assign current values to start and end
                result[idx][0] = start;
                result[idx][1] = end;

                start = intervals[i][0];
                end = intervals[i][1];

                idx++;
            }
        }

        // Final start and end clean up
        result[idx][0] = start;
        result[idx][1] = end;

        // Since, the number of non-overlapping intervals is unknown
        // we return result till idx only
        return Arrays.copyOf(result, idx + 1);




        /*
        // TC - O(nlogn)
        // SC - O(n)

        int n = intervals.length;

        // Sorting the intervals based on their start time so 
        // we can merge the all the intervals for a particular start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int[][] result = new int[n][2];
        int idx = 0;

        for (int i = 0; i < n - 1; i++) {
            // when currentEnd >= nextStart
            if (intervals[i][1] >= intervals[i + 1][0]) {
                // Update the next start and end
                intervals[i + 1][0] = intervals[i][0];
                intervals[i + 1][1] = Math.max(intervals[i + 1][1], intervals[i][1]);
            }
            else {
                // if the interval is not overlapping with next 
                // add it to the result
                result[idx][0] = intervals[i][0];
                result[idx][1] = intervals[i][1];
                idx++;
            }
        }
        // for the last interval
        result[idx][0] = intervals[n - 1][0];
        result[idx][1] = intervals[n - 1][1];

        // Since, the number of non-overlapping intervals is unknown
        // we return result till idx only
        return Arrays.copyOf(result, idx + 1);
        */
    }
}
