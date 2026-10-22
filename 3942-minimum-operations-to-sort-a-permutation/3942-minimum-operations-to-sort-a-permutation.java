class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;

        // Case 1: nums is a cyclic rotation of [0, 1, ..., n-1]
        boolean ascending = true;
        for (int i = 1; i < n; i++) {
            if (nums[i] != (nums[i - 1] + 1) % n) {
                ascending = false;
                break;
            }
        }

        if (ascending) {
            int k = (n - nums[0]) % n;
            ans = Math.min(ans, Math.min(k, n - k + 2));
        }

        // Case 2: nums follows descending cyclic order
        boolean descending = true;
        for (int i = 1; i < n; i++) {
            if (nums[i] != (nums[i - 1] - 1 + n) % n) {
                descending = false;
                break;
            }
        }

        if (descending) {
            int k = (nums[0] + 1) % n;
            ans = Math.min(ans, 1 + Math.min(k, n - k));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}

