class Solution {
    private int k;
    private int n;
    private Node[] tree;
    private int[] nums;

    class Node {
        int[] finalRem;
        int[][] count;
        Node() {
            finalRem = new int[k];
            count = new int[k][k];
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.nums = nums;
        this.k = k;
        this.n = nums.length;
        tree = new Node[4 * n];
        build(1, 0, n - 1);

        int q = queries.length;
        int[] ans = new int[q];
        int startRem = 1 % k; // empty prefix product is 1, take mod k
        for (int i = 0; i < q; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            nums[idx] = val;
            update(1, 0, n - 1, idx);

            Node res = query(1, 0, n - 1, start, n - 1);
            ans[i] = res.count[startRem][x];
        }
        return ans;
    }

    private void build(int node, int l, int r) {
        tree[node] = new Node();
        if (l == r) {
            int v = nums[l] % k;
            for (int p = 0; p < k; p++) {
                int next = (p * v) % k;
                tree[node].finalRem[p] = next;
                tree[node].count[p][next] = 1;
            }
            return;
        }
        int mid = (l + r) / 2;
        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);
        merge(node);
    }

    private void update(int node, int l, int r, int idx) {
        if (l == r) {
            int v = nums[idx] % k;
            for (int p = 0; p < k; p++) {
                for (int q = 0; q < k; q++) {
                    tree[node].count[p][q] = 0;
                }
            }
            for (int p = 0; p < k; p++) {
                int next = (p * v) % k;
                tree[node].finalRem[p] = next;
                tree[node].count[p][next] = 1;
            }
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid) update(node * 2, l, mid, idx);
        else update(node * 2 + 1, mid + 1, r, idx);
        merge(node);
    }

    private void merge(int node) {
        Node left = tree[node * 2];
        Node right = tree[node * 2 + 1];
        Node cur = tree[node];
        for (int p = 0; p < k; p++) {
            int mid = left.finalRem[p];
            cur.finalRem[p] = right.finalRem[mid];
            for (int q = 0; q < k; q++) {
                cur.count[p][q] = left.count[p][q] + right.count[mid][q];
            }
        }
    }

    private Node query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return tree[node];
        }
        int mid = (l + r) / 2;
        if (qr <= mid) return query(node * 2, l, mid, ql, qr);
        if (ql > mid) return query(node * 2 + 1, mid + 1, r, ql, qr);
        Node left = query(node * 2, l, mid, ql, qr);
        Node right = query(node * 2 + 1, mid + 1, r, ql, qr);
        Node res = new Node();
        for (int p = 0; p < k; p++) {
            int midRem = left.finalRem[p];
            res.finalRem[p] = right.finalRem[midRem];
            for (int q = 0; q < k; q++) {
                res.count[p][q] = left.count[p][q] + right.count[midRem][q];
            }
        }
        return res;
    }
}
