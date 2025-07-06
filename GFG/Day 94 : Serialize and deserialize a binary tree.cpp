/* A binary tree node has data, pointer to left child
   and a pointer to right child
struct Node
{
    int data;
    Node* left;
    Node* right;
}; */

class Solution {
  public:
    // Function to serialize a tree and return a list containing nodes of tree.
    vector<int> serialize(Node *root) {
        //see i can do this by just doing bfs
        //just visit and save
        vector<int> arr;
        queue<Node*> q;
        q.push(root);
        while (!q.empty()) {
            Node* curr = q.front();
            q.pop();
            if (curr == nullptr) {
                arr.push_back(-1);
                continue;
            }
            arr.push_back(curr->data);
            q.push(curr->left);
            q.push(curr->right);
        }
        return arr;
    }

    // Function to deserialize a list and construct the tree.
    Node *deSerialize(vector<int> &arr) {
        //this one i am confused how to do, just imagine
        //an array [1,2,3,4,5,6] then 1->2,3->4,5, 6,N
        //how do i do it, oh nvm, N is given in input
        if (arr[0]==-1) return nullptr;
        Node* root = new Node(arr[0]);
        queue<Node*> q;
        q.push(root);
        int i = 1;
        while (!q.empty()){
            Node* curr = q.front();
            q.pop();
            if (arr[i]!=-1) {
               Node* left = new Node(arr[i]);
               curr->left = left;
               q.push(left);
            }
            i++;
            if (arr[i]!=-1) {
                Node* right = new Node(arr[i]);
                curr->right = right;
                q.push(right);
            }
            i++;
        }
        return root;
    }
};
