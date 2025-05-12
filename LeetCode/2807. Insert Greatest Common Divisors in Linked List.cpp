class Solution {
public:
    ListNode* insertGreatestCommonDivisors(ListNode* head) {
        //this shouldnt be hard, i just need to fetch the values
        //then calc gcd and insert the values
        //i forgot to put the 1 val cond lol
        if (!head || !head->next) return head;
        ListNode* curr = head;
        while (curr && curr->next) {
            // fetch and calc
            int a=curr->val;
            int b=curr->next->val;
            int g=gcd(a,b); 
            // insert
            ListNode* newNode =new ListNode(g);
            newNode->next = curr->next;
            curr->next = newNode;
            //two steps ahead :)
            curr = newNode->next;
        }
        return head;
    }
};
