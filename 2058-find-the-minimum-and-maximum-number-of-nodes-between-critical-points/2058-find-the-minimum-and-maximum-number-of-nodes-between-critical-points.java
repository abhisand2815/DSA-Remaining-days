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
        int[] ans = {-1, -1};
        if (head == null || head.next == null || head.next.next == null) {
            return ans;
        }
        ListNode prev = head;
        ListNode curr = head.next;
        int index = 1;
        int first = -1;
        int last = -1;
        int minDistance = Integer.MAX_VALUE;
        while (curr.next != null) {
            int prevVal = prev.val;
            int currVal = curr.val;
            int nextVal = curr.next.val;
            boolean critical =
                    (currVal > prevVal && currVal > nextVal) ||
                    (currVal < prevVal && currVal < nextVal);
            if (critical) {
                if (first == -1) {
                    first = index;
                } else {
                    minDistance = Math.min(
                            minDistance,
                            index - last
                    );
                }
                last = index;
            }

            prev = curr;
            curr = curr.next;
            index++;
        }

        // Need at least two critical points
        if (first == -1 || first == last) {
            return ans;
        }

        int maxDistance = last - first;

        return new int[]{minDistance, maxDistance};
    }
}