class Solution {
    int count = 0;

    public int totalNQueens(int n) {
        boolean[] col = new boolean[n];
        boolean[] d1 = new boolean[2 * n - 1];
        boolean[] d2 = new boolean[2 * n - 1];

        solve(0, n, col, d1, d2);
        return count;
    }

    void solve(int r, int n, boolean[] col, boolean[] d1, boolean[] d2) {
        if (r == n) {
            count++;
            return;
        }

        for (int c = 0; c < n; c++) {
            int x = r - c + n - 1;
            int y = r + c;

            if (col[c] || d1[x] || d2[y]) continue;

            col[c] = d1[x] = d2[y] = true;
            solve(r + 1, n, col, d1, d2);
            col[c] = d1[x] = d2[y] = false;
        }
    }
}