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
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        //see i got confused after reading the question
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }
        
        int pos = 0;
        ListNode prev = head;
        ListNode curr = head.next;
        ListNode next = curr.next;
        
        int firstPos = -1;
        int lastPos = -1;
        int prevPos = -1;
        int minDist = Integer.MAX_VALUE;
        
        while (next != null) {
            // check if curr is a critical point
            if ((curr.val > prev.val && curr.val > next.val) ||
                (curr.val < prev.val && curr.val < next.val)) {
                // found a critical point at position pos (0-based)
                if (firstPos == -1) {
                    firstPos = pos;
                } else {
                    minDist = Math.min(minDist, pos - prevPos);
                }
                prevPos = pos;
                lastPos = pos;
            }
            // move forward
            prev = curr;
            curr = next;
            next = next.next;
            pos++;
        }
        
        if (firstPos == -1 || lastPos == firstPos) {
            return new int[]{-1, -1};
        }
        
        int maxDist = lastPos - firstPos;
        return new int[]{minDist, maxDist};
    }
}
