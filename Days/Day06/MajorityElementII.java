/**
 * Problem: Majority Element II (elements appearing > N/3 times)
 * Link: https://leetcode.com/problems/majority-element-ii/
 * 
 * Time Complexity: O(N)  -> Optimal two-pass extended Boyer-Moore Voting sequence.
 * Space Complexity: O(1) -> Pure constant scalar variables memory layout.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MajorityElementII {
    public List<Integer> majorityElement(int[] nums) {

        // It's kind of an extended Boyer Moore Algorithm for 2 candidates
        // Individual candidate and count variable for both the candidates
        // Why only 2? because for the given condition (> n / 3), there are only max 2 possible answers
        int candidate1 = 0, candidate2 = 0;
        int count1 = 0, count2 = 0;

        for (int num : nums) {
            // Update the counter of first candidate
            if (num == candidate1) {
                count1++;
            }
            // Update the counter of second candidate if it's not first candidate
            else if (num == candidate2) {
                count2++;
            }
            // Logic for updating candidates after updating count to prevent the missing out on
            // potential 2 candidates
            else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            }
            else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            }
            else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;

        // Iterate over again to check if the resulting candidates satisfy the condition
        for (int num : nums) {
            if (num == candidate1) count1++;
            else if (num == candidate2) count2++;
        }

        List<Integer> result = new ArrayList<>();

        if (count1 > nums.length / 3) result.add(candidate1);
        if (count2 > nums.length / 3) result.add(candidate2);

        return result;



        /*
        // TC - O(N) & SC - O(N) 
        // Count the freq of elements
        ArrayList<Integer> result = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Iterate over the map to check which elements satisfy the condition
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > nums.length / 3) {
                result.add(entry.getKey());
            }
        }

        return result;
        */
    }
}
