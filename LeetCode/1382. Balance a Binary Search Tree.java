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

 // I remember doing this in class :)
class Solution {
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        // Step 1: Perform in-order traversal to get sorted list
        inOrder(root, inorder);
        
        // Step 2: Build balanced BST from sorted list
        return buildBalancedBST(inorder, 0, inorder.size() - 1);
    }
    
    private void inOrder(TreeNode node, List<Integer> list) {
        if (node == null) return;
        inOrder(node.left, list);
        list.add(node.val);
        inOrder(node.right, list);
    }
    
    private TreeNode buildBalancedBST(List<Integer> list, int left, int right) {
        if (left > right) return null;
        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(list.get(mid));
        node.left = buildBalancedBST(list, left, mid - 1);
        node.right = buildBalancedBST(list, mid + 1, right);
        return node;
    }
}
