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
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> nodeMap = new HashMap<>();
        Set<Integer> children = new HashSet<>();
                
        for (int[] desc : descriptions) {
            int parentVal = desc[0];
            int childVal = desc[1];
            int isLeft = desc[2];
            //this is something new i learnt in java
            TreeNode parent = nodeMap.getOrDefault(parentVal, new TreeNode(parentVal));
            TreeNode child = nodeMap.getOrDefault(childVal, new TreeNode(childVal));
            
            if (isLeft == 1) {
                parent.left = child;
            } 
            else {
                parent.right = child;
            }
            
            nodeMap.put(parentVal, parent);
            nodeMap.put(childVal, child);
            children.add(childVal);
        }
        
        //finding root
        for (TreeNode node : nodeMap.values()) {
            if (!children.contains(node.val)) {
                return node;
            }
        }
        return null;
    }
}
