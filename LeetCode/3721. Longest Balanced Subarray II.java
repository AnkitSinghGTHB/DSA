//this soln is AI generated bcoz i wasnt able to do
class Solution {
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        // positions of each value (value <= 1e5)
        ArrayList<Integer>[] pos = new ArrayList[100001];
        for (int i = 0; i < n; i++) {
            int v = nums[i];
            if (pos[v] == null) pos[v] = new ArrayList<>();
            pos[v].add(i);
        }

        // pointer to current first occurrence for each value
        int[] ptr = new int[100001];
        // sign: +1 for odd, -1 for even
        int[] sign = new int[100001];
        for (int v = 1; v <= 100000; v++) {
            if (pos[v] != null) {
                sign[v] = (v % 2 == 0) ? -1 : 1;
            }
        }

        SegTree seg = new SegTree(n);
        // add contribution of the first occurrence of each value
        boolean[] done = new boolean[100001];
        for (int i = 0; i < n; i++) {
            int v = nums[i];
            if (!done[v] && pos[v] != null) {
                done[v] = true;
                int first = pos[v].get(0);
                seg.rangeAdd(first, n - 1, sign[v]);
            }
        }

        int ans = 0;
        // left = 0
        int r = seg.queryRightmostZero(0, n - 1);
        if (r != -1) ans = Math.max(ans, r - 0 + 1);

        // slide left boundary
        for (int l = 0; l < n - 1; l++) {
            int v = nums[l];
            if (pos[v] != null) {
                int idx = ptr[v];
                if (idx < pos[v].size() && pos[v].get(idx) == l) {
                    // remove contribution of this first occurrence
                    seg.rangeAdd(l, n - 1, -sign[v]);
                    idx++;
                    if (idx < pos[v].size()) {
                        int next = pos[v].get(idx);
                        seg.rangeAdd(next, n - 1, sign[v]);
                    }
                    ptr[v] = idx;
                }
            }
            // now left = l+1
            r = seg.queryRightmostZero(l + 1, n - 1);
            if (r != -1) {
                ans = Math.max(ans, r - l);
            }
        }
        return ans;
    }

    // segment tree with lazy propagation
    // supports range add, and query for rightmost index with value 0 in a range
    class SegTree {
        int n;
        long[] min, max, lazy;

        SegTree(int n) {
            this.n = n;
            min = new long[4 * n];
            max = new long[4 * n];
            lazy = new long[4 * n];
        }

        private void push(int node) {
            if (lazy[node] != 0) {
                long add = lazy[node];
                min[node * 2] += add;
                max[node * 2] += add;
                lazy[node * 2] += add;
                min[node * 2 + 1] += add;
                max[node * 2 + 1] += add;
                lazy[node * 2 + 1] += add;
                lazy[node] = 0;
            }
        }

        void rangeAdd(int ql, int qr, long val) {
            if (ql > qr) return;
            rangeAdd(1, 0, n - 1, ql, qr, val);
        }

        private void rangeAdd(int node, int l, int r, int ql, int qr, long val) {
            if (ql <= l && r <= qr) {
                min[node] += val;
                max[node] += val;
                lazy[node] += val;
                return;
            }
            push(node);
            int mid = (l + r) >>> 1;
            if (ql <= mid) rangeAdd(node * 2, l, mid, ql, qr, val);
            if (qr > mid) rangeAdd(node * 2 + 1, mid + 1, r, ql, qr, val);
            min[node] = Math.min(min[node * 2], min[node * 2 + 1]);
            max[node] = Math.max(max[node * 2], max[node * 2 + 1]);
        }

        int queryRightmostZero(int ql, int qr) {
            if (ql > qr) return -1;
            return queryRightmostZero(1, 0, n - 1, ql, qr);
        }

        private int queryRightmostZero(int node, int l, int r, int ql, int qr) {
            if (r < ql || l > qr) return -1;
            if (min[node] > 0 || max[node] < 0) return -1;
            if (l == r) return l;
            push(node);
            int mid = (l + r) >>> 1;
            int right = queryRightmostZero(node * 2 + 1, mid + 1, r, ql, qr);
            if (right != -1) return right;
            return queryRightmostZero(node * 2, l, mid, ql, qr);
        }
    }
}
