/**
 * Problem: Permutation Sequence (Find the K-th Permutation)
 * Link: https://leetcode.com/problems/permutation-sequence/
 * 
 * Time Complexity: O(N^2) -> Loop runs N times paired with a linear list element removal shift.
 * Space Complexity: O(N)   -> Linear tracker storage keeping digits from 1 to N.
 */

import java.util.ArrayList;
import java.util.List;

public class PermutationSequence {
    public String getPermutation(int n, int k) {
        
        // TC - O(N^2) & SC - O(N)
        // This approach uses mathematical vision to identify the correct digit at each place
        // with the help of k as. Every position divides the remaining permutations
        // into equal-sized blocks of factorial size.
        int fact = 1;
        List<Integer> numbers = new ArrayList<>();

        // Factorial till n - 1 only as that'll be the per part size for the first place
        for (int i = 1; i < n; i++) {
            fact *= i;
            numbers.add(i);
        }
        numbers.add(n);

        // treating the permutations as 0-index
        k = k - 1;
        StringBuilder sb = new StringBuilder();

        while(true) {
            // k / fact determines which factorial block contains
            // the next digit.
            sb.append(numbers.get(k / fact));

            // remove the used digit 
            numbers.remove(k / fact);

            // When all numbers are exhausted, means the permutation is complete
            if (numbers.size() == 0) break;

            // reduce the k and fact further for next sub-parts
            k %= fact;
            fact /= numbers.size();
        }

        return sb.toString();
    }
}
