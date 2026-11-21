class Solution {
    int[] newParent;
    int[] last = new int[26];
    List<Integer>[] tree, newTree;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        tree = new ArrayList[n];
        newTree = new ArrayList[n];
        newParent = new int[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
            newTree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++)
            tree[parent[i]].add(i);

        Arrays.fill(last, -1);

        dfs1(0, parent, s);

        for (int i = 1; i < n; i++)
            newTree[newParent[i]].add(i);

        int[] ans = new int[n];
        dfs2(0, ans);

        return ans;
    }

    void dfs1(int node, int[] parent, String s) {
        int c = s.charAt(node) - 'a';
        int prev = last[c];

        newParent[node] = (prev != -1) ? prev : parent[node];

        last[c] = node;

        for (int child : tree[node])
            dfs1(child, parent, s);

        last[c] = prev;
    }

    int dfs2(int node, int[] ans) {
        ans[node] = 1;

        for (int child : newTree[node])
            ans[node] += dfs2(child, ans);

        return ans[node];
    }
}