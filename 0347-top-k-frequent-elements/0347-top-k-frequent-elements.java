import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int x : nums)
            map.put(x, map.getOrDefault(x, 0) + 1);

        List<Integer>[] bucket = new List[nums.length + 1];

        for (int x : map.keySet()) {
            int f = map.get(x);
            if (bucket[f] == null)
                bucket[f] = new ArrayList<>();
            bucket[f].add(x);
        }

        int[] ans = new int[k];
        int idx = 0;

        for (int f = nums.length; f >= 1 && idx < k; f--) {
            if (bucket[f] != null) {
                for (int x : bucket[f]) {
                    ans[idx++] = x;
                    if (idx == k) break;
                }
            }
        }

        return ans;
    }
}