/**
 * Problem: Subset Sum Problem (Minimum Flags Logic)
 * Link: https://www.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1
 * 
 * Time Complexity: O(N * Sum) -> Driven by checking target branches over N elements.
 * Space Complexity: O(Sum)     -> Optimized 1D boolean array state tracking workspace.
 */

public class SubsetSum {
    static Boolean isSubsetSum(int arr[], int sum) {
        // code here
        
        // TC - O(N * Sum) & SC - O(Sum)
        // Tabulation appraoch requires only 1D dp array of Sum + 1 size
        // dp[s] stores whether sum s
        // can be formed using processed elements
        
        boolean[] dp = new boolean[sum + 1];
        
        // Sum = 0 is true always
        dp[0] = true;
        
        for (int num : arr) {
            
            // For every num in arr, propage sum backward and check if
            // (s - num) is possible making num eligible for inclusion
            // dp[s] stores whether sum s
            // can be formed using processed elements
            for (int s = sum; s >= num; s--) {
                
                dp[s] = dp[s] || dp[s - num];
            }
        }
        
        return dp[sum];
        
        
        
        
        /*
        // TC - O(N * Sum) & SC - O(N * Sum)
        // This approach works on the take & notTake principle
        // Either we take or notTake current element into consideration
        
        int n = arr.length;
        
        // dp array to take if it is possible to get i value of sum with given array
        Boolean[][] dp = new Boolean[n][sum + 1];
        
        return memo(0, arr, sum, dp);
        
        */
        
    }
    
    public static boolean memo(int idx, int[] arr, int remainingSum, Boolean[][] dp) {
        
        // if remainingSum is 0, there exists a subset that sums up to it
        if (remainingSum == 0) return true;
        
        // Base Case
        if (idx >= arr.length) return false;
        
        // Memoization check
        if (dp[idx][remainingSum] != null) return dp[idx][remainingSum];
        
        // if we want to take that element, check if it is lesser or equal to 
        // remaining sum and call the recursive fn
        boolean take = remainingSum >= arr[idx] && 
                       memo(idx + 1, arr, remainingSum - arr[idx], dp);
                       
        // when we do not want to take the curr element
        boolean notTake = memo(idx + 1, arr, remainingSum, dp);
        
        // Caching
        return dp[idx][remainingSum] = take || notTake;
    }
}
