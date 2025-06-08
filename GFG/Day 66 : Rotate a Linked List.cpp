/*

struct Node {
    int data;
    struct Node *next;
    Node(int x) {
        data = x;
        next = NULL;
    }
};

*/

class Solution {
  public:
    Node* rotate(Node* head, int k) {
        //i have a weird question, whats the diff b/w 
        //reverse and rotate the linked list?
        //ok so rotating means that we can shift by k
        //first we find the length of the linked list
        //if k >= len, rotate only k % len times
        //then traverse to the (k-1)th node
        //new head is at temp->next
        //we go to the end of the new list
        //finally connect end of new list to old head
        if (!head || !head->next || k == 0)
            return head;
        Node* temp = head;
        int len = 1;
        while (temp->next) {
            temp = temp->next;
            len++;
        }
        k = k % len;
        if (k == 0) return head;
        temp = head;
        for (int i = 1; i < k; i++) {
            temp = temp->next;
        }
        Node* newHead = temp->next;
        temp->next = nullptr;
        Node* tail = newHead;
        while (tail->next) {
            tail = tail->next;
        }
        tail->next = head;
        return newHead;
    }
};
