/*
Node is as follows
class Node {
    int data;
    Node *left;
    Node *right;

    Node(int val) {
        data = val;
        left = right = NULL;
    }
};
*/

class Solution {
  public:
    bool findTarget(Node *root, int target) {
        unordered_set<int> seen;
        return dfs(root, target, seen);
    }
    bool dfs(Node* node, int target, unordered_set<int>& seen) {
        if (!node) return false;
        if (seen.count(target - node->data)){
            return true;
        }
        seen.insert(node->data);
        return dfs(node->left, target, seen) ||
               dfs(node->right, target, seen);
    }
};
