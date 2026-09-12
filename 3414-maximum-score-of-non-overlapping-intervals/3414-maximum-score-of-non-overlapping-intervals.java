import java.util.*;

class Solution {
    static class S {
        long w;
        int[] id;

        S(long w, int[] id) {
            this.w = w;
            this.id = id;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] a = new int[n][4];

        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0);
            a[i][1] = intervals.get(i).get(1);
            a[i][2] = intervals.get(i).get(2);
            a[i][3] = i;
        }

        Arrays.sort(a, (x, y) -> x[0] != y[0]
                ? Integer.compare(x[0], y[0])
                : Integer.compare(x[1], y[1]));

        int[] start = new int[n];
        for (int i = 0; i < n; i++) start[i] = a[i][0];

        S[][] dp = new S[n + 1][5];

        for (int k = 0; k <= 4; k++)
            dp[n][k] = new S(0, new int[0]);

        for (int i = n - 1; i >= 0; i--) {
            dp[i][0] = new S(0, new int[0]);

            int next = upper(start, a[i][1]);

            for (int k = 1; k <= 4; k++) {
                S skip = dp[i + 1][k];
                S p = dp[next][k - 1];

                int[] ids = Arrays.copyOf(p.id, p.id.length + 1);
                ids[ids.length - 1] = a[i][3];
                Arrays.sort(ids);

                S take = new S(a[i][2] + p.w, ids);
                dp[i][k] = better(take, skip);
            }
        }

        return dp[0][4].id;
    }

    private int upper(int[] a, int x) {
        int l = 0, r = a.length;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (a[m] <= x) l = m + 1;
            else r = m;
        }
        return l;
    }

    private S better(S a, S b) {
        if (a.w != b.w) return a.w > b.w ? a : b;

        for (int i = 0; i < a.id.length; i++) {
            if (a.id[i] != b.id[i])
                return a.id[i] < b.id[i] ? a : b;
        }
        return a.id.length <= b.id.length ? a : b;
    }
}