// User Fuction template for C++
/*
// Tree Node
class Node {
public:
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
    // Function to return maximum path sum from any node in a tree.
    int findMaxSum(Node *root) {
        //oh this, i might have done it in CSA2001
        int maxSum = INT_MIN;
        maxPathSum(root, maxSum);
        return maxSum;
    }

    int maxPathSum(Node* node, int &maxSum) {
        if (node == NULL) return 0;
        int leftSum = max(0, maxPathSum(node->left, maxSum));
        int rightSum = max(0, maxPathSum(node->right, maxSum));
        int currentMax = node->data + leftSum + rightSum;
        maxSum = max(maxSum, currentMax);
        return node->data +max(leftSum, rightSum);
    }
};
