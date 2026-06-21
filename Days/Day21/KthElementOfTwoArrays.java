/**
 * Problem: K-th Element of Two Sorted Arrays
 * Link: https://www.geeksforgeeks.org/problems/k-th-element-of-two-sorted-array1317/1
 * 
 * Time Complexity: O(log(min(M, N))) -> Binary search over the bounded smaller array partition bounds.
 * Space Complexity: O(1)              -> Pure in-place index arithmetic using scalar indicators.
 */

public class KthElementOfTwoArrays {
    public int kthElement(int a[], int b[], int k) {
        
        // TC - O(log(min(M, N))) & SC - O(1)
        // This question uses similar approach that we used in "Median in two sorted arrays"
        // That is, Partition binary search
        
        // apply BS on smaller array
        // More details on "Median in two Sorted arrays" file
        if (b.length < a.length) return kthElement(b, a, k);
        
        int n = a.length;
        int m = b.length;
        
        // Restrict search space so cut2 remains inside array bounds
        // 0 <= cut2 <= m
        // cut2 = k - cut1 => 0 <= k - cut1 <= m => (cut1 <= k & cut1 >= k - m)
        // => max(0, k-m) <= cut1 <= min(k,n)
        int low = Math.max(0, k - m);
        int high = Math.min(k, n);
        
        while (low <= high) {
            int cut1 = low + (high - low) / 2;
            int cut2 = k - cut1;
            
            int l1 = cut1 == 0 ? Integer.MIN_VALUE : a[cut1 - 1];
            int l2 = cut2 == 0 ? Integer.MIN_VALUE : b[cut2 - 1];
            int r1 = cut1 == n ? Integer.MAX_VALUE : a[cut1];
            int r2 = cut2 == m ? Integer.MAX_VALUE : b[cut2];
            
            if (l1 <= r2 && l2 <= r1) {
                return Math.max(l1, l2);
            }
            else if (l1 > r2) {
                high = cut1 - 1;
            }
            else {
                low = cut1 + 1;
            }
        }
        return 0;
    }
}
