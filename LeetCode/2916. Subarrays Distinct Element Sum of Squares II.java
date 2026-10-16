import java.util.*;
//deepseek helped me
class Solution {
    private static final int MOD = 1_000_000_007;
    
    public int sumCounts(int[] nums) {
        int n = nums.length;
        SegTree st = new SegTree(n);
        Map<Integer, Integer> last = new HashMap<>();
        long ans = 0;
        
        for (int i = 0; i < n; i++) {
            int p = last.getOrDefault(nums[i], -1);
            st.add(p + 1, i, 1);
            last.put(nums[i], i);
            ans = (ans + st.query(0, i)) % MOD;
        }
        return (int) ans;
    }
    
    class SegTree {
        int n;
        long[] sum, sumSq, lazy;
        
        public SegTree(int n) {
            this.n = n;
            sum = new long[4 * n];
            sumSq = new long[4 * n];
            lazy = new long[4 * n];
        }
        
        void add(int l, int r, int v) {
            if (l > r) return;
            add(1, 0, n - 1, l, r, v);
        }
        
        void add(int node, int nl, int nr, int ql, int qr, int v) {
            if (ql <= nl && nr <= qr) {
                apply(node, nl, nr, v);
                return;
            }
            push(node, nl, nr);
            int mid = (nl + nr) / 2;
            if (ql <= mid) add(node * 2, nl, mid, ql, qr, v);
            if (qr > mid) add(node * 2 + 1, mid + 1, nr, ql, qr, v);
            pull(node);
        }
        
        void apply(int node, int l, int r, long v) {
            long len = r - l + 1;
            sumSq[node] = (sumSq[node] + 2 * v * sum[node] + v * v * len) % MOD;
            sum[node] = (sum[node] + v * len) % MOD;
            lazy[node] = (lazy[node] + v) % MOD;
        }
        
        void push(int node, int l, int r) {
            if (lazy[node] != 0) {
                int mid = (l + r) / 2;
                apply(node * 2, l, mid, lazy[node]);
                apply(node * 2 + 1, mid + 1, r, lazy[node]);
                lazy[node] = 0;
            }
        }
        
        void pull(int node) {
            sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
            sumSq[node] = (sumSq[node * 2] + sumSq[node * 2 + 1]) % MOD;
        }
        
        long query(int l, int r) {
            if (l > r) return 0;
            return query(1, 0, n - 1, l, r);
        }
        
        long query(int node, int nl, int nr, int ql, int qr) {
            if (ql <= nl && nr <= qr) return sumSq[node];
            push(node, nl, nr);
            int mid = (nl + nr) / 2;
            long res = 0;
            if (ql <= mid) res = (res + query(node * 2, nl, mid, ql, qr)) % MOD;
            if (qr > mid) res = (res + query(node * 2 + 1, mid + 1, nr, ql, qr)) % MOD;
            return res;
        }
    }
}
