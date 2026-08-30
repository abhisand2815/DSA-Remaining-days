class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int minIndex = 0;
        int maxIndex = 0;
        // Find min and max indices
        for (int i = 0; i < n; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        // Make sure minIndex is before maxIndex
        int left = Math.min(minIndex, maxIndex);
        int right = Math.max(minIndex, maxIndex);
        // Both from front
        int fromFront = right + 1;
        // Both from back
        int fromBack = n - left;
        // One from front, one from back
        int frontBack = (left + 1) + (n - right);
        return Math.min(fromFront, Math.min(fromBack, frontBack));
    }
}
