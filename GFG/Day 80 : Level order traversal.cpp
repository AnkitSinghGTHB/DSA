/* A binary tree Node
class Node {
  public:
    int data;
    Node* left;
    Node* right;

    // Constructor
    Node(int val) {
        data = val;
        left = nullptr;
        right = nullptr;
    }
};
*/

class Solution {
  public:
    vector<vector<int>> levelOrder(Node *root) {
        //im new to tree, but let me learn
        //there is some issue with outputing
        //nvm it was me, human error lol
        if (root==nullptr){
            return {};
        }
        queue<Node *>q;
        vector<vector<int>> res;
        q.push(root);
        
        while(!q.empty()){
            int len=q.size();
            vector<int> level;
            for(int i=0;i<len;i++){
                Node *node =q.front();
                q.pop();
                level.push_back(node->data);
                if (node->left !=nullptr){
                    q.push(node->left);
                }
                if (node->right !=nullptr){
                    q.push(node->right);
                }
            }
            res.push_back(level);
        }
        return res;
    }
};
