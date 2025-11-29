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
    vector<int> boundaryTraversal(Node *root) {
        //
        vector<int> result;
        if (root == NULL) return result;
        if (!isLeaf(root)) {
            result.push_back(root->data);
        }
        addLeftBoundary(root->left, result);
        addLeaves(root, result);
        addRightBoundary(root->right, result);
        return result;
    }

private:
    bool isLeaf(Node *node) {
        return node != NULL && node->left == NULL && node->right == NULL;
    }

    void addLeftBoundary(Node *node, vector<int> &result) {
        if (node == NULL) return;
        Node *current = node;
        while (current != NULL) {
            if (!isLeaf(current)) {
                result.push_back(current->data);
            }
            if (current->left != NULL) {
                current = current->left;
            }
            else {
                current = current->right;
            }
        }
    }
    void addLeaves(Node *node, vector<int> &result) {
        if (node == NULL) return;
        if (isLeaf(node)) {
            result.push_back(node->data);
            return;
        }
        addLeaves(node->left, result);
        addLeaves(node->right, result);
    }

    void addRightBoundary(Node *node, vector<int> &result) {
        if (node == NULL) return;
        stack<int> st;
        Node *current = node;
        while (current != NULL) {
            if (!isLeaf(current)) {
                st.push(current->data);
            }
            if (current->right != NULL) {
                current = current->right;
            }
            else {
                current = current->left;
            }
        }
        while (!st.empty()) {
            result.push_back(st.top());
            st.pop();
        }
    }
};
