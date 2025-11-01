/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    ListNode* modifiedList(vector<int>& nums, ListNode* head) {
        unordered_set<int> removeSet(nums.begin(), nums.end());        
        //create a dummy node to handle head removal easily
        ListNode* dummy = new ListNode(0, head);
        ListNode* current = dummy;        
        while (current->next != nullptr) {
            if (removeSet.count(current->next->val)) {                
                current->next = current->next->next; //remove the next node
            } 
            else {                
                current = current->next; //move to next node
            }
        }        
        return dummy->next;
    }
};
