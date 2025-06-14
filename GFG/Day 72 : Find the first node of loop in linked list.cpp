// User function Template for C++

/*struct Node
{
    int data;
    struct Node *next;
    Node(int x) {
        data = x;
        next = NULL;
    }

*/
class Solution {
  public:
    Node* findFirstNode(Node* head) {
        //that should be easy cuz we did it last day
        //i should do the tortoise hare strat again
        //first i detech the loop
        //then move slow to head adn keep fast at meeting 
        //point then i move both one step at a time
        //the point they meet is the start of the loop
        Node* slow = head;
        Node* fast = head;
        while (fast != NULL && fast->next != NULL) {
            slow = slow->next;
            fast = fast->next->next;

            if (slow == fast)
                break;
        }
        if (fast == NULL || fast->next == NULL)
            return NULL;

        slow = head;
        while (slow != fast) {
            slow = slow->next;
            fast = fast->next;
        }
        return slow;
    }
};
