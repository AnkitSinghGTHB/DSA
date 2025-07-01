class Solution {
  public:
    // Function to check whether a Binary Tree is BST or not.
    bool isBST(Node* root) {
        return isBSTUtil(root, INT_MIN, INT_MAX);
    }
    bool isBSTUtil(Node* node, int min, int max) {
        if (node == nullptr) return true;
        if (node->data <= min || node->data >= max)
            return false;
        return isBSTUtil(node->left, min, node->data) && 
               isBSTUtil(node->right, node->data, max);
    }

};
