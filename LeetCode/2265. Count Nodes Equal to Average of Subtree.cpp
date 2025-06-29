/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    int averageOfSubtree(TreeNode* root) {
        //i think we can do this using a recursive apprch
        int count = 0;
        traverse(root, count);
        return count;
    }

private:
    std::pair<int, int> traverse(TreeNode* node, int& count) {
        if (!node) {
            return {0, 0};
        }
        auto left = traverse(node->left, count);
        auto right = traverse(node->right, count);
        int sum = node->val + left.first + right.first;
        int num_nodes = 1 + left.second + right.second;
        if (node->val == sum / num_nodes) {
            count++;
        }
        return {sum, num_nodes};
    }
};
