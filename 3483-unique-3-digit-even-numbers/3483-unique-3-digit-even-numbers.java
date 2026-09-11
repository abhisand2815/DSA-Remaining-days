class Solution {
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];

        for (int d : digits)
            freq[d]++;

        int ans = 0;

        for (int num = 100; num <= 998; num += 2) {
            int x = num;
            int a = x / 100;
            int b = (x / 10) % 10;
            int c = x % 10;

            if (a != b && a != c && b != c) {
                if (freq[a] > 0 && freq[b] > 0 && freq[c] > 0)
                    ans++;
            } else if (a == b && b == c) {
                if (freq[a] >= 3)
                    ans++;
            } else if (a == b) {
                if (freq[a] >= 2 && freq[c] >= 1)
                    ans++;
            } else if (a == c) {
                if (freq[a] >= 2 && freq[b] >= 1)
                    ans++;
            } else {
                if (freq[b] >= 2 && freq[a] >= 1)
                    ans++;
            }
        }

        return ans;
    }
}