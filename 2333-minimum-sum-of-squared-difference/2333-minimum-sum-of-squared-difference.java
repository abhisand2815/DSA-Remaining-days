class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        int[] diff = new int[n];
        int max = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            max = Math.max(max, diff[i]);
        }

        long low = 0, high = max;

        while (low < high) {
            long mid = (low + high) / 2;
            long needed = 0;

            for (int d : diff) {
                if (d > mid) needed += d - mid;
            }

            if (needed <= k) high = mid;
            else low = mid + 1;
        }

        long remaining = k;

        for (int i = 0; i < n; i++) {
            int reduce = (int) Math.min(diff[i], low);
            remaining -= diff[i] - reduce;
            diff[i] = reduce;
        }

        for (int i = 0; i < n && remaining > 0; i++) {
            if (diff[i] == low && low > 0) {
                diff[i]--;
                remaining--;
            }
        }

        long ans = 0;
        for (int d : diff) ans += (long) d * d;

        return ans;
    }
}