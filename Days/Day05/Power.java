/**
 * Problem: Pow(x, n)
 * Link: https://leetcode.com/problems/powx-n/
 * 
 * Time Complexity: O(log N) -> Iterative Binary Exponentiation.
 * Space Complexity: O(1)     -> Optimal memory usage with primitive variables.
 */

public class Power {
    public double myPow(double x, int n) {
        // We are using Binary Exponentiation method to avoid TLE
        // x^a*b = (x^a)^b so rather than O(a * b), it shifted to O(a + b)
        // like 2^16 = (2^8)^2

        // So, for 2^13, 13 = 1101,
        // 2^(8 + 4 + 1) = 2^8 * 2^4 * 2^1
        // No need to compute 13 times

        // converting into long because we will be negating the negative power to make it positive
        // and Integer.MIN_VALUE can cause Integer overflow
        long power = n;

        // Negative power
        if (power < 0) {
            // It's like saying 2^-2 = 0.5^2 both are same
            x = 1 / x;
            power = -power;
        }

        double result = 1;

        while (power > 0) {
            // When LSB is set, so current result needs to be updated
            if ((power & 1) == 1) {
                result *= x;
            }

            // Updating the value for next bit, as every bit simply doubles the value
            x *= x;
            // right shift power, technically making it half
            power >>= 1;
        }

        return result;



        /*
        // This will fail because of veru high power causing TLE
        double result = 1;

        while (n > 0) {
            result *= x;
            n--;
        }

        if (n < 0) {
            while (n < 0) {
                result /= x;
                n++;
            }
        }

        return result;
        */
    }
}
