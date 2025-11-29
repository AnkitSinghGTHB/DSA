/*
// Tree Node
class Node {
  public:
    int data;
    Node* left;
    Node* right;

    // Constructor to initialize a new node
    Node(int val) {
        data = val;
        left = NULL;
        right = NULL;
    }
};
*/

class Solution {
  public:
    //simple helper function
    void inorderRecursive(Node* root, vector<int>& result) {
    if (root == NULL) return;
    inorderRecursive(root->left, result);
    result.push_back(root->data);
    inorderRecursive(root->right, result);
    }
    // Function to return a list containing the inorder traversal of the tree.
    vector<int> inOrder(Node* root) {
        //uh recursive approach, but one issue, O(n) space
        vector<int> result;
        inorderRecursive(root, result);
        return result;
    }
};
