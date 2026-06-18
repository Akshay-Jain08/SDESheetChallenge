/**
 * Problem: Permutations
 * Link: https://leetcode.com/problems/permutations/
 * 
 * Time Complexity: O(N! * N) -> N! leaves with a linear list conversion pass at each base case.
 * Space Complexity: O(N)       -> Constant auxiliary storage bounded by recursion stack depth.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        
        // TC - O(N! * N) & SC - O(N) excluding output
        // This approach focuses on in-place mutation of the array to generate
        // all the possible permutations
        // This version reduces the overhead of adding and removing elements from arraylist
        // like we are doing in the below approach
        List<List<Integer>> result = new ArrayList<>();

        solve2(0, nums, result);

        return result;



        /*
        // TC - O(N! * N) & SC - O(N) excluding output
        List<List<Integer>> result = new ArrayList<>();

        // Storing the current array nums into an arraylist numbers
        // cuz it stores remaining candidates explicitly
        List<Integer> numbers = new ArrayList<>(Arrays.stream(nums).boxed().toList());

        solve(numbers, new ArrayList<>(), result);

        return result;
        */
    }

    public void solve2(int idx, int[] nums, List<List<Integer>> result) {

        // Base Case
        if (idx == nums.length) {
            List<Integer> list = new ArrayList<>();

            for (int num : nums) {
                list.add(num);
            }

            result.add(list);
            return;
        }

        // All the numbers before idx are fixed
        // Now at idx, try all the numbers >= idx and call the function further for next idx
        for (int i = idx; i < nums.length; i++) {
            
            swap(nums, idx, i);

            solve2(idx + 1, nums, result);

            swap(nums, idx, i);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void solve(List<Integer> numbers, List<Integer> list, List<List<Integer>> result) {

        // Base Case
        if (numbers.size() == 0) {
            result.add(new ArrayList<>(list));
            return;
        }

        // Try every remaining number at current position
        for (int i = 0; i < numbers.size(); i++) {
            int val = numbers.get(i);

            // add val to list and remove from numbers for further recursive calls
            list.add(val);
            numbers.remove(i);

            solve(numbers, list, result);

            // backtrack for the next iteration of the current loop
            list.removeLast();
            numbers.add(i, val);
        }
    }
}
