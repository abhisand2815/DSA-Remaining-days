class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        int LOG = 17;

        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        int[] next = new int[n];
        int[] stack = new int[n];
        int top = -1;

        for (int i = n - 1; i >= 0; i--) {
            while (top >= 0 && nums[stack[top]] <= nums[i]) {
                top--;
            }

            next[i] = top == -1 ? n : stack[top];
            stack[++top] = i;
        }

        int[][] up = new int[LOG][n];
        long[][] cost = new long[LOG][n];

        for (int i = 0; i < n; i++) {
            up[0][i] = next[i];
            cost[0][i] = (long) nums[i] * (next[i] - i)
                       - (prefix[next[i]] - prefix[i]);
        }

        for (int p = 1; p < LOG; p++) {
            for (int i = 0; i < n; i++) {
                int mid = up[p - 1][i];

                if (mid < n) {
                    up[p][i] = up[p - 1][mid];
                    cost[p][i] = cost[p - 1][i] + cost[p - 1][mid];
                } else {
                    up[p][i] = n;
                    cost[p][i] = cost[p - 1][i];
                }
            }
        }

        long ans = 0;

        for (int r = 0; r < n; r++) {
            int low = 0, high = r;

            while (low < high) {
                int mid = low + (high - low) / 2;

                if (getCost(mid, r, nums, prefix, up, cost) <= k) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }

            ans += r - low + 1;
        }

        return ans;
    }

    private long getCost(int l, int r, int[] nums, long[] prefix,
                         int[][] up, long[][] cost) {
        long result = 0;
        int cur = l;

        for (int p = up.length - 1; p >= 0; p--) {
            if (up[p][cur] <= r) {
                result += cost[p][cur];
                cur = up[p][cur];
            }
        }

        result += (long) nums[cur] * (r - cur + 1)
                - (prefix[r + 1] - prefix[cur]);

        return result;
    }
}