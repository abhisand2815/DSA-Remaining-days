import java.util.*;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int m = 0;
        for (int i = 0; i < n; i++) {
            if (m == 0 || sorted[i] != sorted[m - 1]) {
                sorted[m++] = sorted[i];
            }
        }

        Fenwick count = new Fenwick(m);
        Fenwick sum = new Fenwick(m);

        long[] cost = new long[n - x + 1];

        for (int i = 0; i < x; i++) {
            int idx = Arrays.binarySearch(sorted, 0, m, nums[i]);
            count.add(idx, 1);
            sum.add(idx, nums[i]);
        }

        for (int i = 0; i <= n - x; i++) {
            int medianIndex = count.kth((x + 1) / 2);
            long median = sorted[medianIndex];

            long leftCount = count.query(medianIndex);
            long leftSum = sum.query(medianIndex);

            long rightCount = x - leftCount;
            long rightSum = sum.query(m - 1) - leftSum;

            cost[i] = median * leftCount - leftSum
                    + rightSum - median * rightCount;

            if (i < n - x) {
                int remove = Arrays.binarySearch(sorted, 0, m, nums[i]);
                int add = Arrays.binarySearch(sorted, 0, m, nums[i + x]);

                count.add(remove, -1);
                sum.add(remove, -nums[i]);

                count.add(add, 1);
                sum.add(add, nums[i + x]);
            }
        }

        long INF = Long.MAX_VALUE / 4;
        long[][] dp = new long[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }

        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = dp[i - 1][j];

                if (j > 0 && i >= x && dp[i - x][j - 1] != INF) {
                    dp[i][j] = Math.min(
                        dp[i][j],
                        dp[i - x][j - 1] + cost[i - x]
                    );
                }
            }
        }

        return dp[n][k];
    }

    static class Fenwick {
        long[] tree;

        Fenwick(int n) {
            tree = new long[n + 1];
        }

        void add(int index, long value) {
            for (index++; index < tree.length; index += index & -index) {
                tree[index] += value;
            }
        }

        long query(int index) {
            long result = 0;

            for (index++; index > 0; index -= index & -index) {
                result += tree[index];
            }

            return result;
        }

        int kth(long k) {
            int index = 0;
            int bit = Integer.highestOneBit(tree.length - 1);

            for (; bit > 0; bit >>= 1) {
                int next = index + bit;

                if (next < tree.length && tree[next] < k) {
                    index = next;
                    k -= tree[next];
                }
            }

            return index;
        }
    }
}