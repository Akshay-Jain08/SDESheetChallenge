/**
 * Problem: Nth Root of an Integer
 * Link: https://www.geeksforgeeks.org/problems/find-nth-root-of-m5843/1
 * 
 * Time Complexity: O(log M * log N) -> Binary search range reduction multiplied by binary exponentiation scaling.
 * Space Complexity: O(1)             -> In-place constant register tracking.
 */

public class FindNthRootOfM {
    public int nthRoot(int n, int m) {
        
        // TC - O(logM * logN) & SC - O(1)
        // This approach uses binary search on the range [0, m] to find out
        // the nth root of m. Since, the root will always within the range
        int left = 0;
        int right = m;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            // findPow method to find the value of mid ^ n and 
            // check if it's equal to m
            int pow = findPow(mid, n);
            
            if (pow == m) {
                return mid;
            }
            // If the pow is lesser than m, that means the current mid is smaller
            // than the root
            else if (pow < m) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        
        return -1;
    }
    
    // Method to find power
    public int findPow(int base, int exp) {
        
        int res = 1;
        
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res *= base;
            }
            
            base *= base;
            exp >>= 1;
        }
        
        return res;
    }
}
