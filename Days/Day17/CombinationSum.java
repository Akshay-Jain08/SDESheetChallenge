/**
 * Problem: Combination Sum
 * Link: https://leetcode.com/problems/combination-sum/
 * 
 * Time Complexity: O(K + S * L) -> Branching calls tree + solution population deep copies.
 * Space Complexity: O(D)       -> Maximum system recursion stack depth frame footprint.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        
        // TC - O(K + S * L)
        // SC - O(D) excluding output
        // K = Total recursive calls (search cost)
        // S = Number of valid solutions
        // L = Average length of each solution
        // D = Maximum recursion depth
        // This approach works on take or not take approach

        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();

        solve(0, candidates, target, new ArrayList<>(), result);

        return result;
    }

    public void solve(int idx, int[] candidates, int remain, List<Integer> list, List<List<Integer>> result) {
        
        // Base Case: remain == 0, add the current list to the result
        if (remain == 0) {
            result.add(new ArrayList<>(list));
            return;
        }

        // Iterate through all the possible candidates for the current call
        for (int i = idx; i < candidates.length; i++) {
            
            // If any element is greater than remain, break the loop
            // as further elements would be greater too
            if (candidates[i] > remain) break;

            list.add(candidates[i]);
            solve(i, candidates, remain - candidates[i], list, result);
            list.remove(list.size() - 1);
        }
    }
}
