/* Link list Node
struct Node {
    int data;
    Node *next;
    Node *random;

    Node(int x) {
        data = x;
        next = NULL;
        random = NULL;
    }
};*/

class Solution {
  public:
    Node *cloneLinkedList(Node *head) {
        //waht, oh so how about if i can interleave cloned
        //nodes with original ones and then assign 
        //random pointers for the copied nodes and 
        //separate the copied list from the original list
        if (!head) return NULL;
        Node* curr = head;
        while (curr) {
            Node* copy = new Node(curr->data);
            copy->next = curr->next;
            curr->next = copy;
            curr = copy->next;
        }
        curr = head;
        while (curr) {
            if (curr->random)
                curr->next->random = curr->random->next;
            curr = curr->next->next;
        }
        Node* dummy = new Node(0);
        Node* copyCurr = dummy;
        curr = head;
        while (curr) {
            copyCurr->next = curr->next;
            curr->next = curr->next->next;

            curr = curr->next;
            copyCurr = copyCurr->next;
        }

        return dummy->next;
    }
};
