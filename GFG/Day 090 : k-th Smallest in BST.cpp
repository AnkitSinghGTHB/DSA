/*Complete the function below

struct Node {
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
    // Return the Kth smallest element in the given BST
    int kthSmallest(Node *root, int k) {
        stack<Node*> stack;
        Node* current = root;
        int count = 0;
        while (current != NULL || !stack.empty()) {
            while (current != NULL) {
                stack.push(current);
                current = current->left;
            }
            current = stack.top();
            stack.pop();
            count++;
            if (count == k) {
                return current->data;
            }
            current = current->right;
        }
        return -1;
    }
};
