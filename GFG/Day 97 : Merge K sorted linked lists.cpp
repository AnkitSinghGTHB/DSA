/*Linked list Node structure

struct Node
{
    int data;
    Node* next;

    Node(int x){
        data = x;
        next = NULL;
    }

};
*/
struct compare {
    bool operator()(Node* a, Node* b) {
        return a->data > b->data;//min-heap
    }
};
class Solution {
  public:
    Node* mergeKLists(vector<Node*>& arr) {
        //uh wut
        priority_queue<Node*, vector<Node*>, compare> minHeap;
        for (Node* listHead : arr) {
            if (listHead)
                minHeap.push(listHead);
        }
        Node* dummy = new Node(0);
        Node* tail = dummy;
        while (!minHeap.empty()) {
            Node* smallest = minHeap.top();
            minHeap.pop();
            tail->next = smallest;
            tail = tail->next;
            if (smallest->next) {
                minHeap.push(smallest->next);
            }
        }
        return dummy->next;
    }
};
