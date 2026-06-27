class Solution {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        Arrays.sort(candidates);

        List<List<Integer>> result = new ArrayList<>();

        dfs(candidates, target, 0, new ArrayList<>(), result);

        return result;
    }

    private void dfs(int[] candidates, int target, int start,
                     List<Integer> current,
                     List<List<Integer>> result) {

        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < candidates.length; i++) {

            // 🔥 Pruning
            if (candidates[i] > target)
                break;

            current.add(candidates[i]);

            // Reuse same element
            dfs(candidates, target - candidates[i], i, current, result);

            current.remove(current.size() - 1);
        }
    }
}