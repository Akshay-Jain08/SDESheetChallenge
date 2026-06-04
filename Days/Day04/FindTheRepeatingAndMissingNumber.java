/**
 * Problem: Find the Repeating and Missing Number
 * Link: https://www.geeksforgeeks.org/problems/find-missing-and-repeating2512/1
 * 
 * Time Complexity: O(N)  -> Single pass mathematical summation / Two-pass cyclic sort.
 * Space Complexity: O(1) -> All calculations performed with constant primitive variables.
 */

import java.util.ArrayList;
import java.util.Arrays;

public class FindTheRepeatingAndMissingNumber {
    public ArrayList<Integer> findTwoElement(int arr[]) {
        // code here
        
        // Used standard cyclic sort approach where sorted the arr
        // and whichever index didn't contain the expected element
        // the expected element is the duplicate, and index + 1 is the missing element
        // since array is 0-indexed
        ArrayList<Integer> result = new ArrayList<>();
            
        int i = 0;
        
        while (i < arr.length) {
            // correct index for the current element
            int correctIndex = arr[i] - 1;
            
            // if the current element is not at the correct Index, then swap
            if (arr[i] != arr[correctIndex]) {
                swap(arr, i, correctIndex);
            }
            else {
                i++;
            }
        }
        
        // another loop to detect the abnormality in the sorted array
        // ie which index doesn't contain the expected element
        for (int k = 0; k < arr.length; k++) {
            if (arr[k] != k + 1) {
                result.add(arr[k]);
                result.add(k + 1);
                break;
            }
        }
        
        return result;
        
        
        
        
        /*
        // Used Maths and algebra to sole the problem but it's easier to mess up
        // because of interger overflow and the mathematical relations
        // Useful when asked to not modify the given array and also not allowed to use extra space
        // TC - O(N) & SC - O(1)
        
        int n = arr.length;
        long sum = 0;
        long squareSum = 0;
        
        // calculating sum and squareSum
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            squareSum += (long) arr[i] * arr[i];
        }
        
        long sumN = (long) n * (n + 1) / 2;
        long squareSumN = (long) n * (n + 1) * (2L * n + 1) / 6;
        
        // Below is the representation of what we're going to use in this problem
        // x = repeated & y = missing
        // x - y = sum - sumN
        // x^2 - y^2 = squareSum - squareSumN
        // x + y = (squareSum - squareSumN) / (x - y)
        
        long xMinusy = sum - sumN;
        long xPlusy = (squareSum - squareSumN) / (xMinusy);
        
        long x = (xPlusy + xMinusy) / 2;
        long y = (xPlusy - xMinusy) / 2;
        
        return new ArrayList<>(Arrays.asList((int) x, (int) y));
        */
    }
    
    public void swap(int[] arr, int f, int s) {
        int temp = arr[f];
        arr[f] = arr[s];
        arr[s] = temp;
    }
}
