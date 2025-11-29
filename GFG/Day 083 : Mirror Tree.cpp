/*
class Node {
public:
    int data;
    Node *left;
    Node *right;

    Node(int x) {
        data = x;
        left = right = NULL;
    }
};
*/

class Solution {
  public:
    // Function to convert a binary tree into its mirror tree.
    void mirror(Node* node) {
        //erm how do i do this?
        //let me recursively call mirror()
        //on left and right subtrees
        //and then swap the left and right children
        if (node == NULL) return;
        mirror(node->left);
        mirror(node->right);
        Node* temp = node->left;
        node->left = node->right;
        node->right = temp;
    }
};
