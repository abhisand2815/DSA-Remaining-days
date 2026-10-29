import java.util.*;

class Solution {
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        int LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        int[][] up = new int[LOG][n];
        int[] depth = new int[n];
        int[] tin = new int[n];
        int[] tout = new int[n];
        int[] value = new int[n];

        for (int i = 0; i < n; i++) {
            value[i] = 1 << (s.charAt(i) - 'a');
        }

        int[] stackNode = new int[2 * n + 5];
        int[] stackParent = new int[2 * n + 5];
        int[] stackState = new int[2 * n + 5];

        int top = 0, timer = 0;
        stackNode[top] = 0;
        stackParent[top] = 0;
        stackState[top++] = 0;

        while (top > 0) {
            top--;
            int u = stackNode[top];
            int p = stackParent[top];
            int state = stackState[top];

            if (state == 0) {
                up[0][u] = p;
                tin[u] = timer++;

                stackNode[top] = u;
                stackParent[top] = p;
                stackState[top++] = 1;

                for (int v : adj[u]) {
                    if (v == p) continue;

                    depth[v] = depth[u] + 1;

                    stackNode[top] = v;
                    stackParent[top] = u;
                    stackState[top++] = 0;
                }
            } else {
                tout[u] = timer++;
            }
        }

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        Fenwick bit = new Fenwick(2 * n);

        for (int i = 0; i < n; i++) {
            bit.update(tin[i], value[i]);
            bit.update(tout[i], value[i]);
        }

        List<Boolean> answer = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");
            int u = Integer.parseInt(parts[1]);

            if (parts[0].equals("update")) {
                int newValue = 1 << (parts[2].charAt(0) - 'a');
                int delta = value[u] ^ newValue;

                bit.update(tin[u], delta);
                bit.update(tout[u], delta);

                value[u] = newValue;
            } else {
                int v = Integer.parseInt(parts[2]);
                int lca = getLCA(u, v, up, depth);

                int maskU = bit.prefix(tin[u]);
                int maskV = bit.prefix(tin[v]);

                int pathMask = maskU ^ maskV ^ value[lca];

                answer.add(Integer.bitCount(pathMask) <= 1);
            }
        }

        return answer;
    }

    private int getLCA(int u, int v, int[][] up, int[] depth) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int k = 0; k < up.length; k++) {
            if ((diff & (1 << k)) != 0) {
                u = up[k][u];
            }
        }

        if (u == v) return u;

        for (int k = up.length - 1; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }

        return up[0][u];
    }

    static class Fenwick {
        int[] tree;

        Fenwick(int n) {
            tree = new int[n + 1];
        }

        void update(int index, int value) {
            for (index++; index < tree.length; index += index & -index) {
                tree[index] ^= value;
            }
        }

        int prefix(int index) {
            int result = 0;

            for (index++; index > 0; index -= index & -index) {
                result ^= tree[index];
            }

            return result;
        }
    }
}