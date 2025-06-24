/*
class Node {
public:
    int data;
    Node* left;
    Node* right;

    Node(int val) {
        data = val;
        left = NULL;
        right = NULL;
    }
};

Node* newNode(int val) {
    return new Node(val);
}
*/

class Solution {
  public:
    int maxDiameter = 0;
    int height(Node* node) {
        if (node == NULL) return 0;
        int leftHeight = height(node->left);
        int rightHeight = height(node->right);
        maxDiameter = std::max(maxDiameter, 
                               leftHeight + rightHeight);
        return 1 + std::max(leftHeight, rightHeight);
    }
    int diameter(Node* root) {
        //this might not be that hard
        //for each node,i compute the height of the left 
        //and right subtrees
        //the diameter at this node is leftheight + 
        //rightheight
        //then i keep track of the maximum diameter found
        //so far and return the h to parent recursive calls
        height(root);
        return maxDiameter;
    }
};
