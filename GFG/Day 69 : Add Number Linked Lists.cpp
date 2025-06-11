/* node for linked list:

struct Node {
    int data;
    struct Node* next;
    Node(int x) {
        data = x;
        next = NULL;
    }
};

*/

class Solution {
  public:
    Node* reverse(Node* head) {
        Node* prev = nullptr;
        while (head) {
            Node* next = head->next;
            head->next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
    Node* trimLeadingZeros(Node* head) {
        while (head && head->data == 0 && head->next != nullptr) {
            head = head->next;
        }
        return head;
    }
    Node* addTwoLists(Node* num1, Node* num2) {
        //how to do dis, i remember doing something similar
        //with subtraction
        //instead of adding 0s to the front, i can just
        //reverse them ig (added reverse fn)
        //ok i faced another issue with leading zeros
        num1=reverse(num1);
        num2=reverse(num2);
        Node* dummy =new Node(0);
        Node* current =dummy;
        int carry= 0;
        while (num1 || num2 || carry){
            int sum=carry;
            if (num1){
                sum+=num1->data;
                num1=num1->next;
            }
            if (num2){
                sum+=num2->data;
                num2=num2->next;
            }
            carry=sum/10;
            current->next=new Node(sum%10);
            current=current->next;
        }
        Node* result = reverse(dummy->next);
        return trimLeadingZeros(result);
    }
};
