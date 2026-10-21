class Solution {
    public int minOperations(String s) {
        int n = s.length();
        boolean sorted = true;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) < s.charAt(i - 1)) { sorted = false; break; }
        }
        if (sorted) return 0;
        if (n == 2) return -1;

        char min = 'z', max = 'a';
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c < min) min = c;
            if (c > max) max = c;
        }

        // Sort s[1..n-1] or s[0..n-2]
        if (s.charAt(0) == min || s.charAt(n - 1) == max) return 1;

        // A min exists in s[0..n-2], or a max exists in s[1..n-1]
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == min) return 2;
        }
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == max) return 2;
        }

        // min only at the last index, max only at the first index
        return 3;
    }
}