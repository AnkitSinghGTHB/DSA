// Class that contains the logic to build the binary tree
/*
Definition of the Node class
class Node {
public:
    int data;
    Node *left;
    Node *right;

    Node(int x) {
        data = x;
        left = NULL;
        right = NULL;
    }
};
*/
class Solution {
  public:
    int preIndex = 0;
    unordered_map<int, int> inMap;
    //lets make a helper fn to help me out T_T
    Node* buildTreeHelper(vector<int>& inorder, vector<int>& preorder,
                                    int inStart, int inEnd) {
        if (inStart > inEnd) return NULL;
        int rootVal = preorder[preIndex++];
        Node* root = new Node(rootVal);
        int inIndex = inMap[rootVal];
        root->left = buildTreeHelper(inorder, preorder, inStart,
                                     inIndex - 1);
        root->right = buildTreeHelper(inorder, preorder,
                                      inIndex + 1, inEnd);
        return root;
    }
    // Function to build the tree from given inorder and preorder traversals
    Node *buildTree(vector<int> &inorder, vector<int> &preorder) {
        //i have an exam tomorrow and my brain aint workin
        //T_T aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
        int n = inorder.size();
        for (int i = 0; i < n; i++) {
            inMap[inorder[i]] = i;
        }
        return buildTreeHelper(inorder, preorder, 0, n - 1);
    }
};
