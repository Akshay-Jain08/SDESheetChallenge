/**
 * Problem: Count Inversions in an Array
 * Link: https://www.geeksforgeeks.org/problems/inversion-of-array-1587115620/1
 * 
 * Time Complexity: O(N log N) -> Standard divide and conquer merge split tree bounds.
 * Space Complexity: O(N)       -> Single auxiliary globally-allocated reference array to reduce recursion stack overhead.
 */

public class CountInversions {
    static int inversionCount(int arr[]) {
        // Code Here
        // This approach uses the standard merge sort approach to count the inversions
        // Nested loops would fail because of TLE
        // Merge sort actually compares almost all pairs without failing in TLE
        // making it ideal to use for such questions
        
        // Edge case for singular element array
        if (arr.length == 1) {
            return 0;
        }
        
        // Using a temp array so we don't have to make the temporary arrays inside
        // recursive calls reducing space overhead
        int[] temp = new int[arr.length];
        
        return mergeSort(arr, temp, 0, arr.length - 1);
    }
    
    public static int mergeSort(int[] arr, int[] temp, int start, int end) {
        // Case when only the approach partitioned the array upto a single element
        if (start >= end) {
            return 0;
        }
        
        int mid = start + (end - start) / 2;
        
        // solve left part and return the inversions
        int left = mergeSort(arr, temp, start, mid);
        
        // solve right part and return the inversions
        int right = mergeSort(arr, temp, mid + 1, end);
        
        // solve the current part for the total inversions in that
        int total = merge(arr, temp, start, mid, end);
        
        return left + right + total;
    }
    
    public static int merge(int[] arr, int[] temp, int start, int mid, int end) {
        // Pointers to keep track within the specified range
        int i = start;
        int j = mid + 1;
        int k = start;
        
        int inversionCount = 0;
        
        // Standard merge sort approach
        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            }
            else {
                temp[k++] = arr[j++];
                // inversionCount is solved on the basis of:
                // left side is already sorted so if at any point any left number
                // is greater than any number on the right side, then it's guaranteed
                // that all the remaining elements including the current on the left side
                // are greater than the current right element
                // that's why remaining left elements = mid - i + 1
                inversionCount += mid - i + 1;
            }
        }
        
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        
        while (j <= end) {
            temp[k++] = arr[j++];
        }
        
        // copying the temp to arr for later recursive calls
        System.arraycopy(temp, start, arr, start, end - start + 1);
        
        return inversionCount;
    }
}
