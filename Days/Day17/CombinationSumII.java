/**
 * Problem: Combination Sum II (Unique combinations with limited item usage)
 * Link: https://leetcode.com/problems/combination-sum-ii/
 * 
 * Time Complexity: O(2^N + S * L) -> Exponential search branching with deep-copy solutions.
 * Space Complexity: O(N)          -> System recursion stack frame limit tracking.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        // TC - O(2^N + S * L) & SC - O(N) excluding Output
        // S = valid solutions
        // L = average solution length

        // Sorting the array for preventing duplicates
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();

        solve(0, candidates, target, new ArrayList<>(), result);

        return result;
    }

    public void solve(int idx, int[] candidates, int remain, List<Integer> list, List<List<Integer>> result) {
        
        // Base Case
        if (remain == 0) {
            result.add(new ArrayList<>(list));
            return;
        }

        // In the current call, if any of the same elements encountered, skip it
        for (int i = idx; i < candidates.length; i++) {
            if (i > idx && candidates[i] == candidates[i - 1]) {
                continue;
            }

            if (candidates[i] > remain) {
                break;
            }

            // Try every candidate as the next element of the combination.
            list.add(candidates[i]);
            solve(i + 1, candidates, remain - candidates[i], list, result);
            list.remove(list.size() - 1);
        }
    }
}
