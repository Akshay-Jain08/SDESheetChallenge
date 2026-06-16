/**
 * Problem: Subsets II (Subsets with Duplicates)
 * Link: https://leetcode.com/problems/subsets-ii/
 * 
 * Time Complexity: O(N * 2^N) -> Bounded by list deep copy actions over 2^N subset variations.
 * Space Complexity: O(N)       -> Constant auxiliary footprint tracking stack depth.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        
        // TC - O(N * 2^N) & SC - O(N) excluding output
        // Sort the array for duplicate prevention
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        subset2(0, nums, new ArrayList<>(), result);
        return result;



        /*
        // TC - O(N * 2^N) & SC - O(N * 2^N) 

        // Sort the array first to prevent duplicates
        Arrays.sort(nums);

        // Using the HashSet so that no duplicate subsets are included in the result
        Set<List<Integer>> result = new HashSet<>();

        subset(0, nums, new ArrayList<>(), result);

        return new ArrayList<>(result);

        */
    }

    // This method uses HashSet for duplicates
    public void subset(int idx, int[] nums, List<Integer> list, Set<List<Integer>> result) {
        // Base Case
        if (idx == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }

        // When we don't add current element
        subset(idx + 1, nums, list, result);

        // add current element for the all possible subsets
        list.add(nums[idx]);
        subset(idx + 1, nums, list, result);

        // remove the current element for later iterations without this element
        list.remove(list.size() - 1);
    }


    // This method doesn't need any HashSet
    public void subset2(int idx, int[] nums, List<Integer> list, List<List<Integer>> result) {

        // Directly add current list to result from the previous recursive call
        result.add(new ArrayList<>(list));

        // For that particular call, check all the valid (distinct) elements 
        // to increase the current list size by 1
        for (int i = idx; i < nums.length; i++) {
            
            // if same element occurs in the same call, skip it
            if (i > idx && nums[i] == nums[i - 1]) {
                continue;
            }

            // include all possible elements to increase the list size by 1
            list.add(nums[i]);
            subset2(i + 1, nums, list, result);
            list.remove(list.size() - 1);
        }
    }
}
