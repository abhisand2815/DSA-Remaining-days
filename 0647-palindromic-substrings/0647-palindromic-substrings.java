class Solution {
    public int countSubstrings(String s) {
        int n = s.length(), ans = 0;
        char[] t = new char[2 * n + 1];

        for (int i = 0; i < n; i++) {
            t[2 * i] = '#';
            t[2 * i + 1] = s.charAt(i);
        }
        t[2 * n] = '#';

        int[] p = new int[t.length];
        int center = 0, right = 0;

        for (int i = 0; i < t.length; i++) {
            int mirror = 2 * center - i;

            if (i < right)
                p[i] = Math.min(right - i, p[mirror]);

            while (i + p[i] + 1 < t.length &&
                   i - p[i] - 1 >= 0 &&
                   t[i + p[i] + 1] == t[i - p[i] - 1])
                p[i]++;

            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }

            ans += (p[i] + 1) / 2;
        }

        return ans;
    }
}