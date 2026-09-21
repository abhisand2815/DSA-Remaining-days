class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode s = head, f = head;

        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
        }

        ListNode prev = null;
        while (s != null) {
            ListNode next = s.next;
            s.next = prev;
            prev = s;
            s = next;
        }

        while (prev != null) {
            if (head.val != prev.val) return false;
            head = head.next;
            prev = prev.next;
        }

        return true;
    }
}