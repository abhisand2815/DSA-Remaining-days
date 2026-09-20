import java.util.*;

class Solution {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[1], b[1]));

        int count = 0;
        int end = Integer.MIN_VALUE;

        for (int[] p : pairs) {
            if (p[0] > end) {
                count++;
                end = p[1];
            }
        }

        return count;
    }
}