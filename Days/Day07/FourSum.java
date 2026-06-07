/**
 * Problem: 4Sum
 * Link: https://leetcode.com/problems/4sum/
 * 
 * Time Complexity: O(N^3) -> Dual loop initialization with a linear two-pointer scanning sequence.
 * Space Complexity: O(1)   -> In-place modifications discarding auxiliary heap references.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        
        // This is the optimized method for finding the quadruplets
        // This uses a sorting + two pointer approach

        // Sorting the array beforehand for duplicate quadruplets handling
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        
        // i and j are fixed till the two pointer approach with k and l pointers run till k < l
        for (int i = 0; i < nums.length; i++) {
            // Moving i to next different element so no same quadruplets are added to result
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < nums.length; j++) {
                // Moving j to next different element 
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                int k = j + 1;
                int l = nums.length - 1;

                // two pointer approach for finding the quadruplet
                while (k < l) {
                    long sum = (long) nums[i] + nums[j] + nums[k] + nums[l];

                    if (sum < target) {
                        k++;
                    }
                    else if (sum > target) {
                        l--;
                    }
                    else {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                        k++;
                        l--;

                        // Moving k and l to next different element
                        while (k < l && (nums[k] == nums[k - 1])) k++;
                        while (k < l && (nums[l] == nums[l + 1])) l--;
                    }
                }
            }
        }

        return result;
        


        
        /*
        // Semi-Optimized Approach
        // TC - O(N^3 + N logN)
        // SC - O(N)

        // Basically this approach uses 3 nested for loops and leverages a HashSet
        // to keep the track of the elements between the last two pointers in hope of
        // finding the fourth element

        int n = nums.length;
        // Sorting the array beforehand for better duplicate handling
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();

        // Used for preventing the duplicate quadruplets in the result list
        HashSet<List<Integer>> quads = new HashSet<>();

        // i is fixed till all j and k combinations are done
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                
                // Set to keep track of all elements between j and k
                HashSet<Integer> set = new HashSet<>();

                for (int k = j + 1; k < n; k++) {
                    
                    long sum = (long) nums[i] + nums[j] + nums[k];
                    long fourth = target - sum;

                    if (fourth < Integer.MIN_VALUE || fourth > Integer.MAX_VALUE) continue;

                    // if the set contains the fourth element, insert the quadruplet in the quads set
                    // to prevent the duplicate quadruplets
                    if (set.contains((int) fourth)) {
                        List<Integer> list = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k], (int) fourth));
                        quads.add(list);
                    }

                    // adding all the elements between j and k
                    set.add(nums[k]);
                }
            }
        }

        result.addAll(quads);

        return result;
        */
        



        /*
        TC - O(N^4) & SC - O(No. of quadruplets)
        Brute-Force Approach would be to use 4 nested loops to find the quadruplets
        */
    }
}
