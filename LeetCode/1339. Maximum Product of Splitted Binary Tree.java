/**
 * Definition for a binary tree node.
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
/**
 * Definition for a binary tree node.
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
    private long maxProd = 0;
    private long total = 0;
    private final int MOD = 1000000007;

    public int maxProduct(TreeNode root) {
        total = computeTotalSum(root);
        computeSubtreeSums(root);
        return (int)(maxProd % MOD);
    }

    private long computeTotalSum(TreeNode node) {
        if (node == null) return 0;
        return node.val + computeTotalSum(node.left) + computeTotalSum(node.right);
    }

    private long computeSubtreeSums(TreeNode node) {
        if (node == null) return 0;
        long leftSum = computeSubtreeSums(node.left);
        long rightSum = computeSubtreeSums(node.right);
        long subtreeSum = node.val + leftSum + rightSum;        
        if (node.left != null) {//consider cutting the edge to the left child
            long product = leftSum * (total - leftSum);
            if (product > maxProd) {
                maxProd = product;
            }
        }        
        if (node.right != null) {//consider cutting the edge to the right child
            long product = rightSum * (total - rightSum);
            if (product > maxProd) {
                maxProd = product;
            }
        }
        return subtreeSum;
    }
}
