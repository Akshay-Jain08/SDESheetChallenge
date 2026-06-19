/**
 * Problem: M-Coloring / Graph Coloring Problem
 * Link: https://www.geeksforgeeks.org/problems/m-coloring-problem-1587115620/1
 * 
 * Time Complexity: O(M^V * V) -> M^V maximum states with a linear neighbor safety check at each node.
 * Space Complexity: O(V + E)   -> Adjacency graph allocation combined with linear coloring arrays.
 */

import java.util.ArrayList;
import java.util.List;

public class MColoringProblem {
    boolean graphColoring(int v, int[][] edges, int m) {
        
        // TC - O(V * M ^ V) M^V = total states & V = Color Safety Check
        // SC - O(V + E)
        // Making an adjacent graph from the edges array
        List<List<Integer>> graph = new ArrayList<>();
        
        for (int i = 0; i < v; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        
        // Colors array to keep track of colors on all the vertices
        int[] colors = new int[v];
        
        return solve(0, v, m, graph, colors);
    }
    
    public boolean solve(int node, int v, int m, List<List<Integer>> graph, int[] colors) {
        
        // Base Case
        if (node == v) {
            return true;
        }
        
        // Try to fill the current node with all the available colors
        // If any recursive branch succeeds, return true immediately
        for (int i = 1; i <= m; i++) {
            
            // Check if the current color is possible for current node
            if (isSafe(i, node, graph, colors)) {
                colors[node] = i;
                
                if (solve(node + 1, v, m, graph, colors)) return true;
                
                // Backtrack if the current color didn't work out in future calls
                colors[node] = 0;
            }
        }
        
        // No valid color exists for current node
        return false;
    }
    
    public boolean isSafe(int color, int node, List<List<Integer>> graph, int[] colors) {
        
        // Check all the neighbours to see if they possess the same color 
        for (int neighbour : graph.get(node)) {
            if (colors[neighbour] == color) {
                return false;
            }
        }
        
        return true;
    }
}
