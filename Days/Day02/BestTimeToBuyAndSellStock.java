/**
 * Problem: Best Time to Buy and Sell Stock
 * Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * 
 * Time Complexity: O(N)  -> Optimal single-pass iteration.
 * Space Complexity: O(1) -> Uses constant space for pointer variables.
 */

public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        // This solution focuses on: 
        // For each selling day:
        // what is the cheapest buying day before it?
        // and not: 
        // For each buying day:
        // what is the most expensive selling day after it?
        // Optimal Solution
        int n = prices.length;
        if (n <= 1) return 0;

        int maxProfit = 0;

        // to track the minPrice till ith day
        int min = prices[0];

        for (int i = 1; i < n; i++) {
            min = Math.min(min, prices[i]);

            // max profit on day i with the best min from past = prices[i] - min
            maxProfit = Math.max(maxProfit, prices[i] - min);
        }
        return maxProfit;


        /*
        // Brute Force
        // TC - O(N)
        // SC - O(N)
        int n = prices.length;
        if (n <= 1) return 0;

        int maxProfit = 0;

        // temp array to store suffix max of prices
        int[] temp = new int[n];
        int max = prices[n - 1];

        for (int i = n - 1; i >= 0; i--) {
            // updating the suffix max
            // updating the array before updating the variable to get the value of 
            // max price from i + 1 to end
            temp[i] = max;
            if (max < prices[i]) max = prices[i];
        }

        for (int i = 0; i < n; i++) {
            // profit on day i = max from i + 1 to end - today's price
            maxProfit = Math.max(maxProfit, temp[i] - prices[i]);
        }
        return maxProfit;
        */


        /*
        // Very naive approach - would cause TLE
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
            }
        }

        return maxProfit;
        */
    }
}
