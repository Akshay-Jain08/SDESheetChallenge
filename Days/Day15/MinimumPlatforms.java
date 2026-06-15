/**
 * Problem: Minimum Platforms Required for a Railway Station
 * Link: https://www.geeksforgeeks.org/problems/minimum-platforms-1587115620/1
 * 
 * Time Complexity: O(N log N) -> Dominated by sorting arrival and departure times independently.
 * Space Complexity: O(1)       -> In-place sorting and scalar tracking indicators.
 */

import java.util.Arrays;

public class MinimumPlatforms {
    public int minPlatform(int arr[], int dep[]) {
        //  code here
        int n = arr.length;
        
        // Sorting both the arrays because we kind of analyzing the railway station
        // as the time goes through
        Arrays.sort(arr);
        Arrays.sort(dep);
        
        int i = 0;
        int j = 0;
        
        int maxPlatform = 0;
        int platform = 0;
        
        // checking for i only because arrival will always be before departure
        while (i < n) {
            // If the next arrival comes before next departure, we'll need another platform
            if (arr[i] <= dep[j]) {
                i++;
                platform++;
            }
            // If the next departure comes before next arrival, we'll be needing one lesser platform
            else {
                j++;
                platform--;
            }
            
            maxPlatform = Math.max(maxPlatform, platform);
        }
        
        return maxPlatform;
    }
}