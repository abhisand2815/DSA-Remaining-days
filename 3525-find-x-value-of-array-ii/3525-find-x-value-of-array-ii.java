class Solution {
    static final int MAXK = 5;

    int n, k;
    int[] a;
    Node[] tree;

    class Node {
        int prod;
        int[] cnt;

        Node() {
            cnt = new int[MAXK];
        }
    }

    Node merge(Node L, Node R) {
        Node res = new Node();

        res.prod = (int)((long)L.prod * R.prod % k);

        // Prefixes completely inside left part
        for (int r = 0; r < k; r++)
            res.cnt[r] += L.cnt[r];

        // Prefixes which use all of left + a prefix of right
        for (int r = 0; r < k; r++) {
            if (R.cnt[r] != 0) {
                int nr = (int)((long)L.prod * r % k);
                res.cnt[nr] += R.cnt[r];
            }
        }

        return res;
    }

    Node make(int v) {
        Node x = new Node();
        x.prod = v % k;
        x.cnt[x.prod] = 1;
        return x;
    }

    void build(int p, int l, int r) {
        if (l == r) {
            tree[p] = make(a[l]);
            return;
        }

        int m = (l + r) / 2;
        build(p * 2, l, m);
        build(p * 2 + 1, m + 1, r);

        tree[p] = merge(tree[p * 2], tree[p * 2 + 1]);
    }

    void update(int p, int l, int r, int idx, int val) {
        if (l == r) {
            tree[p] = make(val);
            return;
        }

        int m = (l + r) / 2;

        if (idx <= m)
            update(p * 2, l, m, idx, val);
        else
            update(p * 2 + 1, m + 1, r, idx, val);

        tree[p] = merge(tree[p * 2], tree[p * 2 + 1]);
    }

    Node query(int p, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr)
            return tree[p];

        int m = (l + r) / 2;

        if (qr <= m)
            return query(p * 2, l, m, ql, qr);

        if (ql > m)
            return query(p * 2 + 1, m + 1, r, ql, qr);

        Node L = query(p * 2, l, m, ql, qr);
        Node R = query(p * 2 + 1, m + 1, r, ql, qr);

        return merge(L, R);
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;
        this.a = nums;

        tree = new Node[4 * n];
        build(1, 0, n - 1);

        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int idx = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            // Update persists
            update(1, 0, n - 1, idx, value);

            // Remaining array starts from 'start'
            Node res = query(1, 0, n - 1, start, n - 1);

            ans[q] = res.cnt[x];
        }

        return ans;
    }
}