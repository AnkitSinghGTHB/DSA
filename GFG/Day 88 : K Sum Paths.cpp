/*
struct Node {
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
    int sumK(Node *root, int k) {
        std::unordered_map<int, int> prefixSum;
        prefixSum[0] = 1;
        int count = 0;
        dfs(root, 0, k, prefixSum, count);
        return count;
    }
    void dfs(Node* node, int currentSum, int k, std::unordered_map<int, int>& prefixSum, int& count) {
        if (!node) return;
        currentSum += node->data;
        count += prefixSum[currentSum - k];
        prefixSum[currentSum]++;
        dfs(node->left, currentSum, k, prefixSum, count);
        dfs(node->right, currentSum, k, prefixSum, count);
        prefixSum[currentSum]--;
    }
};
