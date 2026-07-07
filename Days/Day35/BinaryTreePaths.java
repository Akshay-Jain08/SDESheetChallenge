/**
 * Problem: Binary Tree Paths (Root to Leaf Paths)
 * Link: https://leetcode.com/problems/binary-tree-paths/
 * 
 * Time Complexity: O(N + L * H) -> Linear tree node processing pass with a quick path string generation step at each leaf node.
 * Space Complexity: O(H)        -> System recursion call stack and builder memory bounded strictly by tree height H.
 */
import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        
        // TC - O(N + L * H) & SC - O(H)
        // L = No. of Paths
        // H = Average / Max Height of the tree
        // We simply perform DFS to generate every root-to-leaf path.
        // Instead of using a List<String> to store each path like we are doing
        // in the below approach, we instead use a StringBuilder to store the path
        // This avoids repeated String.join()
        // operations and reduces temporary object creation.

        List<String> result = new ArrayList<>();

        // Edge Case
        if (root == null) return result;

        StringBuilder sb = new StringBuilder();

        // Store the root (as it will be a part of all the paths)
        sb.append(root.val);

        solve(root, result, sb);

        return result;




        /*
        // TC - O(N + L * H) & SC - O(H)
        // L = No. of Paths
        // H = Average / Max Height of the tree
        // We simply do a dfs traversal over the tree, to store all the paths
        // from root to tree

        List<String> result = new ArrayList<>();

        // Edge Case
        if (root == null) return result;

        // List to store each path
        List<String> pathList = new ArrayList<>();
        pathList.add(String.valueOf(root.val));

        solve(root, result, pathList);

        return result;
        */
    }

    public void solve(TreeNode node, List<String> result, StringBuilder sb) {

        // Base Case : We are at the leaf node
        if (node.left == null && node.right == null) {
            // Store the current path in the result
            result.add(sb.toString());
            return;
        }

        // Go to the left child
        if (node.left != null) {
            // compute len for backtracking later
            int len = sb.length();

            // Extend the current path with the left child.
            sb.append("->");
            sb.append(node.left.val);

            solve(node.left, result, sb);

            // Backtrack
            sb.setLength(len);
        }

        if (node.right != null) {
            int len = sb.length();

            // Extend the current path with the right child.
            sb.append("->");
            sb.append(node.right.val);

            solve(node.right, result, sb);

            // Backtrack
            sb.setLength(len);
        }
    }

    public void solve(TreeNode node, List<String> result, List<String> pathList) {
        
        // Base Case
        if (node.left == null && node.right == null) {
            // Convert the pathList to a String with the delimitter ->
            result.add(String.join("->", pathList));
            return;
        }

        // Go the left child
        if (node.left != null) {
            // add the value of the left child in the pathList
            pathList.add(String.valueOf(node.left.val));

            solve(node.left, result, pathList);

            // Backtrack (remove the value from the path)
            pathList.remove(pathList.size() - 1);
        }
        
        // Go to the right child
        if (node.right != null) {
            // add the value of the right child in the pathList
            pathList.add(String.valueOf(node.right.val));

            solve(node.right, result, pathList);

            // Backtrack (remove the value from the path)
            pathList.remove(pathList.size() - 1);
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
