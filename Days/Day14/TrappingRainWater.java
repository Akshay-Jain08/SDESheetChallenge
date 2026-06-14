/**
 * Problem: Trapping Rain Water
 * Link: https://leetcode.com/problems/trapping-rain-water/
 * 
 * Time Complexity: O(N)  -> Symmetric inward two-pointer linear scanning pass.
 * Space Complexity: O(1) -> Bound constant memory space tracking boundary heights.
 */

public class TrappingRainWater {
    public int trap(int[] height) {
        
        // TC - O(N) & SC - O(1)
        // This approach directly compare leftMax and rightMax at each stage
        // after computing leftMax and rightMax with respect to the current elevations
        int leftMax = 0;
        int rightMax = 0;

        int i = 0;
        int j = height.length - 1;
        int ans = 0;

        while (i < j) {

            leftMax = Math.max(leftMax, height[i]);
            rightMax = Math.max(rightMax, height[j]);

            // If the leftMax is smaller, means leftMax is surely gonna be the bottleneck
            // for the current left elevation
            if (leftMax <= rightMax) {
                ans += leftMax - height[i];
                i++;
            }
            // same goes for rightMax
            else {
                ans += rightMax - height[j];
                j--;
            }
        }

        return ans;





        /*
        // TC - O(N) & SC - O(1)
        // This approach completely eliminates the need of space by
        // introducing leftMax and rightMax pointers that are responsible
        // for bottlenecks of any elevation in the series
        // Every elevation will be dependent on the min of leftMax and rightMax
        
        int leftMax = 0;
        int rightMax = 0;

        int i = 0;
        int j = height.length - 1;

        int ans = 0;

        while (i < j) {
            // If the left elevation is smaller, process it
            if (height[i] <= height[j]) {

                // It provides surity that leftMax is surely gonna be the bottleneck
                // and not the rightMax because with the above condition we can assure
                // that the elevation on j is simply bigger and equal to the current elevation
                // we would not have processed the current elevation if there was any
                // bigger left candidate previously that contributed to the leftMax.
                // In that case, we would have been processing the right candidate

                if (height[i] < leftMax) {
                    ans += leftMax - height[i];
                }
                else {
                    leftMax = height[i];
                }
                i++;
            }
            // If the right elevation is smaller, process it
            else {

                // Same thing for rightMax as stated earlier for leftMax
                if (height[j] < rightMax) {
                    ans += rightMax - height[j];
                }
                else {
                    rightMax = height[j];
                }
                j--;
            }
        }

        return ans;

        */
        




        /*
        // TC - O(N) & SC - O(N)
        // This approach uses a prefixMax and a suffixMax array called maxLeft and maxRight respectively
        // They are responsible for providing the max elevation on left and right 
        // for a particular elevation. That given, we can calculate the water on that elevation

        int n = height.length;
        int[] maxLeft = new int[n];
        int[] maxRight = new int[n];

        // Updating the maxLeft
        int left = 0;
        for (int i = 0; i < n; i++) {
            maxLeft[i] = left;
            left = (left >= height[i]) ? left : height[i];
        }

        // Updating the maxRight
        int right = 0;
        for (int i = n - 1; i >= 0; i--) {
            maxRight[i] = right;
            right = (right >= height[i]) ? right : height[i];
        }

        // Calculating the water on top of every elevation and adding it to ans
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (maxLeft[i] > height[i] && maxRight[i] > height[i]) {
                ans += Math.min(maxLeft[i], maxRight[i]) - height[i];
            }
        }

        return ans;

        */
    }
}