/**
 * Problem: Coin Change / Find Minimum Number of Coins
 * Link: https://leetcode.com/problems/coin-change/
 * 
 * Time Complexity: O(Coins * Amount) -> Multi-loop checking over all coins for every sub-amount level.
 * Space Complexity: O(Amount)        -> Bounded by the linear DP status tracking workspace.
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CoinChange {
    public int coinChange(int[] coins, int amount) {

        // TC - O(Coins * Amount) & SC - O(Amount)

        // Edge Case
        if (amount == 0) return 0;
        int INF = amount + 1;

        // Fill the dp array with INF because it is an unreachable answer
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, INF);

        dp[0] = 0;

        // Iterating for all the amounts and filling the dp array
        for (int curr = 1; curr <= amount; curr++) {

            for (int coin : coins) {
                
                // the min value for curr amount can be computed by doing all possible
                // combinations with the coins within the curr range and checking for dp[curr - coin]
                // essentially giving us a take to select that particular coin
                if (coin <= curr) {
                    dp[curr] = Math.min(dp[curr], 1 + dp[curr - coin]);
                }
            }
        }

        return dp[amount] == INF ? -1 : dp[amount];






        /*
        // TC - O(Coins * Amount) & SC - O(Amount) including memo + recursion stack
        // This approach uses memoization with the recursive solution 

        // Edge Case
        if (amount == 0) return 0;

        // using INF = amount + 1 because it is an unreachable answer as the max 
        // number coins for an amount can be the amount itself
        int INF = amount + 1;

        // dp array to memoize
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);

        int ans = memo(coins, amount, dp, INF);

        return ans == INF ? -1 : ans;

        */




        /*
        // TC - O(Coins * Amount) & SC - O(Amount)
        // This approach uses the BFS technique with the visited array to try out 
        // all the possible combinations of coins that could sum up to the amount

        if (amount == 0) return 0;

        // Queue Stores the remaining amount and all the valid coins for that remaining 
        // amount gets to participate in the amount
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[amount + 1];

        // Initially the remaining amount is equal to the given amount
        queue.offer(amount);
        visited[amount] = true;

        // signifies the no of coins used
        int level = 0;

        while (!queue.isEmpty()) {
            
            // size for that particular level (no of coins)
            int size = queue.size();

            while (size-- > 0) {

                int remainingAmount = queue.poll();

                // if the remainingAmount is zero, return the level (no of coins used)
                if (remainingAmount == 0) return level;

                for (int coin : coins) {
                    int nextAmount = remainingAmount - coin;

                    if (nextAmount >= 0 && !visited[nextAmount]) {
                        queue.offer(nextAmount);
                        visited[nextAmount] = true;
                    }
                }
            }

            level++;
        }

        return -1;
        */




        
        /*
        // TC - O(N logN) & SC - O(logN)
        // This is a greedy approach and use the maximum possible coin for making the amount
        Arrays.sort(coins);
        int minCoins = 0;

        for (int i = coins.length - 1; i >= 0; i--) {
            // if amount is less than the current coin, skip that coin
            if (amount < coins[i]) {
                continue;
            }

            // take the maximum number of denomination of coin
            minCoins += amount / coins[i];
            amount %= coins[i];
        }

        // If amount is not 0, then it's not possible to make that amount with possible denominations
        return amount == 0 ? minCoins : -1;
        */
    }

    public int memo(int[] coins, int amount, int[] dp, int INF) {
        
        // Edge case: if amount is 0, then no more coins required
        // if amount is less than 0, then the impossible state so return INF
        // to invalidate that response  
        if (amount == 0) return 0;
        if (amount < 0) return INF;

        // return the cached answer
        if (dp[amount] != -1) return dp[amount];

        // By default, INF (unreachable answer)
        int ans = INF;

        for (int coin : coins) {
            // use all the available coins and reduce the remaining amount
            ans = Math.min(ans, 1 + memo(coins, amount - coin, dp, INF));
        }

        // Cache the answer
        return dp[amount] = ans;
    }
}
