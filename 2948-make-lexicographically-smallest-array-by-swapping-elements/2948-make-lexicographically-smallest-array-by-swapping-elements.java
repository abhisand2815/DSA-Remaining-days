import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {

        int n = nums.length;

        // [value, original index]
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        // Sort by value
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        int[] result = new int[n];

        int start = 0;

        while (start < n) {

            int end = start;

            // Find one connected group
            while (end + 1 < n &&
                   (long) arr[end + 1][0] - arr[end][0] <= limit) {
                end++;
            }

            // Get original indices of this group
            int size = end - start + 1;

            int[] indices = new int[size];
            int[] values = new int[size];

            for (int i = 0; i < size; i++) {
                values[i] = arr[start + i][0];
                indices[i] = arr[start + i][1];
            }

            // Sort original indices
            Arrays.sort(indices);

            // Values are already sorted
            for (int i = 0; i < size; i++) {
                result[indices[i]] = values[i];
            }

            start = end + 1;
        }

        return result;
    }
}