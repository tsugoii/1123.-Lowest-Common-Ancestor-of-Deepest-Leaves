/**
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // Given the root of a binary tree, return the LCA of deepest level
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        // Recursively call our helper to find LCA and depth
        Result result = findLCA(root);
        // Return LCA node
        return findLCA(root).lcaNode;
    }
    // Recursive helper to find deepest leaves and LCA
    private Result findLCA(TreeNode node) {
        // Base case for null
        if (node == null) {
            return new Result(-1, null);
        }
        
        // Firnd left subtree
        Result leftResult = findLCA(node.left);
        // Find depth and LCA from left
        int leftDepth = leftResult.depth;
        TreeNode leftLCA = leftResult.lcaNode;

        // Find right subtree
        Result rightResult = findLCA(node.right);
        // Find depth and LCA from right
        int rightDepth = rightResult.depth;
        TreeNode rightLCA = rightResult.lcaNode;

        // If left and right depths are same
        if (leftDepth == rightDepth) {
            // current node is LCA, current node + 1 is depth
            return new Result(leftDepth + 1, node);
        }
        // If left is deeper than right
        else if (leftDepth > rightDepth) {
            // LCA is in left, depth is left + 1
            return new Result(leftDepth + 1, leftLCA);
        }
        // If right is deeper than left
        else {
            // LCA is right, depth is right + 1
            return new Result(rightDepth + 1, rightLCA);
        }
    }
    // Helper to store results of depth and LCA
    private static class Result {
        int depth;
        TreeNode lcaNode;

        // Constructor
        Result(int depth, TreeNode lcaNode) {
            this.depth = depth;
            this.lcaNode = lcaNode;
        }

    }
}
