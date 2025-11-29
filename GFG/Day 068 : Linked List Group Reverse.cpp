/*
  Node is defined as
    struct node
    {
        int data;
        struct Node* next;

        Node(int x){
            data = x;
            next = NULL;
        }

    }*head;
*/

class Solution {
  public:
    Node *reverseKGroup(Node *head, int k) {
        //ok we rev till k, then rev from k to n
        //so first i will count k nodes
        //if we have k nodes then will reverse them
        //and then recursively call the same fn
        //head will be the last node in the grp, connected
        //to the result of the next reversed grp
        //then we reverse the remaining
        Node* curr = head;
        int count=0;
        while(curr!=NULL &&count<k){
            curr=curr->next;
            count++;
        }
        if(count==k){
            curr=head;
            Node* prev=NULL;
            Node* next=NULL;
            int i=0;
            while(i<k &&curr!=NULL){
                next=curr->next;
                curr->next=prev;
                prev=curr;
                curr=next;
                i++;
            }
            if (next!=NULL){
                head->next=reverseKGroup(next,k);
            }
            return prev;
        }
        if (count>0){
            Node* prev=NULL;
            Node* next=NULL;
            while(head!=NULL){
                next=head->next;
                head->next=prev;
                prev=head;
                head=next;
            }
            return prev;
        }
        return head;
    }
};
