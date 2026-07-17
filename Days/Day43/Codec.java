/**
 * Problem: Serialize and Deserialize Binary Tree
 * Link: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * 
 * Time Complexity: O(N) -> Single linear recursive data streaming passes for both core operations.
 * Space Complexity: O(H) -> System recursion stack memory bounded strictly by tree height H.
 */

public class Codec {

    // TC - O(N) & SC - O(H)
    // This approach uses standard encoding approach of a tree
    // Represent the null nodes with "#" so the tree structure can be reconstructed uniquely.
    // keep adding the nodes to the string in the preorder fashion

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        
        StringBuilder sb = new StringBuilder();

        preorder(root, sb);

        return sb.toString();
    }

    private void preorder(TreeNode root, StringBuilder sb) {

        // Base Case
        if (root == null) {
            sb.append("#").append(",");
            return;
        }

        // Add the current node
        sb.append(root.val).append(",");
        // Move to the left sub-tree
        preorder(root.left, sb);
        // Move to the right sub-tree
        preorder(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        
        // Split the data into an array
        String[] tree = data.split(",");

        // call the buildTree method to build the binary tree from the given data
        return buildTree(tree);
    }

    int idx = 0;

    private TreeNode buildTree(String[] tree) {

        // When the current node is null, just move the index ahead
        // and return null
        if (tree[idx].equals("#")) {
            idx++;
            return null;
        }   

        // Initialize the curr node
        int currVal = Integer.parseInt(tree[idx++]);
        TreeNode currNode = new TreeNode(currVal);

        // Build the left sub-tree recursively
        currNode.left = buildTree(tree);

        // Build the right sub-tree recursively
        currNode.right = buildTree(tree);

        return currNode;
    }

    static class TreeNode {
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


/*
public class Codec {
    
    // TC - O(N) & SC - O(H)
    // The encoding technique that i have used here is simply a preorder 
    // traversal and each node is separated by a number of characters
    // representing the next node's position in the tree

    // It consists of 'L' before some nodes representing that the next node
    // to be visited would be placed at the left sub-tree of the curr node
    // Some nodes might be followed by a number 'R' which represents which 
    // ancestor's right sub-tree to attach the next node to
    // For a single R, it would simply attach the next node to curr node's right sub-tree
    // For multiple Rs, it would go up that many number of times to find the correct
    // position of the next node 

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        
        StringBuilder sb = new StringBuilder();

        // Encoding the tree
        preorder(sb, root);

        // reduce the length by 1, because the string would include an R at last
        // which would have no meaning in itself
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }

    public void preorder(StringBuilder sb, TreeNode root) {
        
        // Base Case
        if (root == null) {
            return;
        }

        // Add the curr node
        sb.append(root.val).append(",");

        // If left sub-tree exists, then only make a call for left sub-tree
        // This is done to prevent the duplicate L being added in case of 
        // leaf node
        if (root.left != null) {
            sb.append("L").append(",");
            preorder(sb, root.left);
        }
        
        // Append R before moving to the right sub-tree
        sb.append("R").append(",");
        preorder(sb, root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        // Edge Case
        if (data.length() == 0) return null;

        // Split the steps into an array
        String[] steps = data.split(",");

        // Initialize the root
        int rootVal = Integer.parseInt(steps[0]);
        TreeNode root = new TreeNode(rootVal);

        // We will be using a stack to mimic the recursion stack
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        // Run the loop and the increment would be +2 everytime because an extra
        // would be there just to represent the position of the next node to come
        for (int i = 1; i < steps.length; i += 2) {
            
            // If the direction says L, simply add the next node to be added
            // to the left of the last processed node
            if (steps[i].equals("L")) {

                TreeNode currNode = stack.peek();
                TreeNode newNode = new TreeNode(Integer.parseInt(steps[i + 1]));

                currNode.left = newNode;
                // Add the processed node to stack
                stack.push(currNode.left);
            }

            // If the direction says R, so we cannot simply add the next node 
            // to be added to the right of the last processed node
            // We must confirm the position of the node by checking the frequency of R
            else {
                
                // pop the last processed node, because both its left and right sub-tree
                // would be processed by the end
                TreeNode currNode = stack.pop();

                // While there are more Rs in the steps, keep going back to the ancestors
                // incrementing i at the same time
                while (steps[i + 1].equals("R")) {

                    currNode = stack.pop();
                    i++;
                }

                // The node to be processed
                TreeNode newNode = new TreeNode(Integer.parseInt(steps[i + 1]));

                // Attach the node to the right of curr node
                currNode.right = newNode;

                // Push the the processed node to the stack
                stack.push(currNode.right);
            }
        }

        return root;
    }
}
*/
