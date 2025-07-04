/*
class Node
{
    public:
    int data;
    Node *left, *right;
    Node(int val)
    {
        data = val;
        left = right = NULL;
    }
};
*/

class Solution {
  public:
    Node *first = NULL, *second = NULL, *prev = NULL;
    void inorder(Node* root){
        if (!root) return;
        inorder(root->left);
        if (prev && root->data < prev->data) {
            if (!first) {
                first = prev;
            }
            second = root;
        }
        prev = root;
        inorder(root->right);
    }
    void correctBST(Node* root) {
        inorder(root);
        if (first && second) {
            swap(first->data, second->data);
        }
    }
};
