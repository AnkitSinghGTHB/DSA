/*
structure of linked list node:

struct Node
{
    int data;
    Node* next;

    Node(int val)
    {
        data = val;
        next = NULL;
    }
};

*/

class Solution {
  public:
    // Function to remove a loop in the linked list.
    void removeLoop(Node* head) {
        /*extra dev note:
        Think of the loop like a circular track. If one runner is faster, they will catch up with the slower one if they’re both running in the loop. 
        Once they meet, you use pointer repositioning to identify exactly where the loop begins.
        */
        //first i detect loop using tortoise hare strat
        //if loop exists then i will check if loop
        //starts from head and then i remove the loop
        if (!head || !head->next)
            return;
        Node* slow = head;
        Node* fast = head;
        while (fast && fast->next) {
            slow = slow->next;
            fast = fast->next->next;
            if (slow == fast)
                break;
        }
        if (slow == fast){
            slow = head;
            if (slow == fast){
                while (fast->next != slow){
                    fast = fast->next;
                }
            }
            else{
                while (slow->next != fast->next){
                    slow = slow->next;
                    fast = fast->next;
                }
            }
            fast->next = NULL;
        }
    }
};
