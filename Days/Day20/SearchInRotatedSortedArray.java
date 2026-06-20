/**
 * Problem: Search in Rotated Sorted Array
 * Link: https://leetcode.com/problems/search-in-rotated-sorted-array/
 * 
 * Time Complexity: O(log N) -> Sequential binary scans for pivot locating and value searching.
 * Space Complexity: O(1)    -> Constant pointer modifications in constant storage space.
 */

public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        
        // TC - O(logN) & SC - O(1)
        // This approach finds the rotation pivot (largest element)
        // which divides the array into two individually sorted halves
        int pivot = findPivot(nums);

        // If pivot is -1, that means the array is already sorted so simply 
        // implement binary search in the whole array
        if (pivot == -1) {
            return binarySearch(nums, target, 0, nums.length - 1);
        }

        if (nums[pivot] == target) {
            return pivot;
        }
        // If the target is bigger than the first element, it lies in the first sorted half
        // else lies in the second sorted half
        else if (target >= nums[0]) {
            return binarySearch(nums, target, 0, pivot - 1);
        }
        else {
            return binarySearch(nums, target, pivot + 1, nums.length - 1);
        }




        /*
        // TC - O(logN) & SC - O(1)
        
        int n = nums.length;
        int left = 0;
        int right = n - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            // If the element is found, return
            if (nums[mid] == target) {
                return mid;
            }

            // If the left half is sorted and the element lies within the bounds of left half
            // search in the left half, else search in the right half
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                }
                else {
                    left = mid + 1;
                }
            }
            // If the right half is sorted and the element lies within the bounds of right half
            // search in the right half, else search in the left half
            else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                }
                else {
                    right = mid - 1;
                }
            }
        }

        return -1;

        */
    }
    
    // Method to find pivot index
    public int findPivot(int[] nums) {

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // If the previous to mid element is greater
            // means the array was rotated till mid - 1
            if (mid > left && nums[mid] < nums[mid - 1]) {
                return mid - 1;
            }

            // If the mid element is greater than the next element
            // means the array was rotated till mid
            if (mid < right && nums[mid] > nums[mid + 1]) {
                return mid;
            }

            // if the left element is greater or equal to mid, 
            // the pivot is in the left
            if (nums[left] >= nums[mid]) {
                right = mid - 1;
            }
            // else the right
            else {
                left = mid + 1;
            }
        }

        return -1;
    }

    // Standard binary search method
    public int binarySearch(int[] nums, int target, int left, int right) {

        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (target == nums[mid]) {
                return mid;
            }
            else if (target < nums[mid]) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }

        return -1;
    }
}
