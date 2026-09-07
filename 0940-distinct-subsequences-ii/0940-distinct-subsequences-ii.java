class Solution {
    public int distinctSubseqII(String s) {
        long[] end = new long[26];
        long total = 0;
        long MOD = 1_000_000_007;

        for (char ch : s.toCharArray()) {
            int i = ch - 'a';

            long add = (total + 1) % MOD;
            total = (total + add - end[i] + MOD) % MOD;
            end[i] = add;
        }

        return (int) total;
    }
}