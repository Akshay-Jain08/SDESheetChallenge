/**
 * Problem: The Celebrity Problem
 * Link: https://www.geeksforgeeks.org/problems/the-celebrity-problem/1
 * 
 * Time Complexity: O(N) -> Two-pointer linear candidate elimination followed by row/col verification.
 * Space Complexity: O(1) -> In-place constant register tracking.
 */

import java.util.ArrayList;

public class TheCelebrityProblem {
    public int celebrity(int mat[][]) {
        
        // TC - O(N) & SC - O(1)
        // This approach works by eliminate impossible celebrity candidate
        // per comparison
        int n = mat.length;
        
        // top represents the first person and bottom represents the last person
        int top = 0;
        int bottom = n - 1;
        
        while (top < bottom) {
            // top knows bottom, means top can't be a celebrity
            if (mat[top][bottom] == 1) {
                top++;
            }
            // bottom knows top, means bottom can't be a celebrity
            else if (mat[bottom][top] == 1) {
                bottom--;
            }
            // If none of these knows each other, neither of them can be a celebrity
            else {
                top++;
                bottom--;
            }
        }
        
        // No potential candidate to be a celebrity
        if (top > bottom) return -1;
        
        // If top == bottom, then that candidate is a potential celebrity
        // compute knowMe and iKnow for that particular candidate 
        int knowMe = 0;
        int iKnow = 0;
        for (int i = 0; i < n; i++) {
            if (mat[top][i] == 1) iKnow++;
            if (mat[i][top] == 1) knowMe++;
        }
        
        // if that candidate knows only 1 person and is known by n person
        // It's a celebrity, otherwise return -1
        return iKnow == 1 && knowMe == n ? top : -1;
        
        
        
        
        /*
        // TC - O(N^2) & SC - O(N)
        // This approach is slightly degraded version of the below approach
        // but it feels more intuitive and easier to understand
        int n = mat.length;
        
        // Compute knowMe and iKnow arrays for every candidate
        int[] knowMe = new int[n];
        int[] iKnow = new int[n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                
                // ith person knows jth person
                if (mat[i][j] == 1) {
                    iKnow[i]++;
                    knowMe[j]++;
                }
            }
        }
        
        // If, among all the candidates, we have one with iKnow as 1 && knowMe as n
        // we have found the celebrity
        for (int i = 0; i < n; i++) {
            if (iKnow[i] == 1 && knowMe[i] == n) {
                return i;
            }
        }
        
        return -1;
        */
        
        
        
        
        /*
        // TC - O(N^2) & SC - O(1)
        // This approach go through every row (i.e. the no. of people a person know)
        // If the count (acquintances) comes out to be 1, we have a 
        // potential candidate for the celebrity
        int n = mat.length;
        
        for (int i = 0; i < n; i++) {
            
            // Count acquintances for every row
            int acquintances = 0;
            int col = -1;
            
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    acquintances++;
                    col = j;
                }
            }
            
            // If the no. of acquintances are 1, we have a potential candidate
            if (acquintances == 1) {
                
                // Check its popularity by traversing in the particular column
                int popularity = 0;
                
                for (int row = 0; row < n; row++) {
                    if (mat[row][col] == 1) popularity++;
                }
                
                // If the popularity comes out to be the no. of people,
                // we have found the celebrity
                if (popularity == n) return i;
            }
        }
        
        return -1;
        */
    }
}
