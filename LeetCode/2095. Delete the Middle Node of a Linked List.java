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
    public ListNode deleteMiddle(ListNode head) {
        //if the list has only 1 node, deleting the middle leaves it empty
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        //give fast a head start so slow stops exactly ONE node before the middle
        ListNode fast = head.next.next;

        // Traverse the list
        while (fast != null && fast.next != null) {
            slow = slow.next;        //moves 1 step
            fast = fast.next.next;   //moves 2 steps
        }

        //'slow' is now at the node just before the middle.
        //route 'slow.next' to point to the node after the middle one.
        slow.next = slow.next.next;

        return head;
    }
}
