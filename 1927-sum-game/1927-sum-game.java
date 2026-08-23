class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int diff = 0;
        int leftQ = 0;
        int rightQ = 0;

        for (int i = 0; i < n / 2; i++) {
            char c = num.charAt(i);
            if (c == '?') {
                leftQ++;
            } else {
                diff += c - '0';
            }
        }

        for (int i = n / 2; i < n; i++) {
            char c = num.charAt(i);
            if (c == '?') {
                rightQ++;
            } else {
                diff -= c - '0';
            }
        }

        if ((leftQ + rightQ) % 2 != 0) {
            return true;
        }

        return diff * 2 != (rightQ - leftQ) * 9;
    }
}