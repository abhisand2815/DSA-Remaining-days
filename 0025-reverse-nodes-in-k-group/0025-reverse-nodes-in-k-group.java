class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode cur = head;
        for (int i = 0; i < k; i++)
            if (cur == null) return head;
            else cur = cur.next;

        ListNode prev = null, next;
        cur = head;

        for (int i = 0; i < k; i++) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        head.next = reverseKGroup(cur, k);
        return prev;
    }
}