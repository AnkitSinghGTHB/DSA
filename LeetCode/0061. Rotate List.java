/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;
        int len = 1; //first we compute length
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            len++;
        }
        k = k % len; //then check effective rotations
        if (k == 0) return head;        
        //now find new tail: (len - k-1)th node
        ListNode newTail = head;
        for (int i = 0; i < len - k - 1; i++) {
            newTail = newTail.next;
        }        
        ListNode newHead = newTail.next; //new head is newTail.next
        //at last break the list and make it circular correctly
        newTail.next = null;
        tail.next = head;        
        return newHead;
    }
}
