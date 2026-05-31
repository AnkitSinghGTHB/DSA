class Solution {
    class SegTree {
        int n;
        int[] tree;
        public SegTree(int size) {
            n = size;
            tree = new int[4 * n];
        }
        void update(int idx, int val, int node, int l, int r) {
            if (l == r) {
                tree[node] = val;
                return;
            }
            int mid = (l + r) / 2;
            if (idx <= mid) update(idx, val, node*2, l, mid);
            else update(idx, val, node*2+1, mid+1, r);
            tree[node] = Math.max(tree[node*2], tree[node*2+1]);
        }
        void update(int idx, int val) {
            update(idx, val, 1, 0, n-1);
        }
        int query(int ql, int qr, int node, int l, int r) {
            if (ql <= l && r <= qr) return tree[node];
            int mid = (l + r) / 2;
            int res = 0;
            if (ql <= mid) res = Math.max(res, query(ql, qr, node*2, l, mid));
            if (qr > mid) res = Math.max(res, query(ql, qr, node*2+1, mid+1, r));
            return res;
        }
        int query(int ql, int qr) {
            if (ql > qr) return 0;
            return query(ql, qr, 1, 0, n-1);
        }
    }

    public List<Boolean> getResults(int[][] queries) {
        int MAX = 50000; //as per constraints
        SegTree seg = new SegTree(MAX + 1); // indices 0..MAX
        TreeSet<Integer> obstacles = new TreeSet<>();
        List<Boolean> ans = new ArrayList<>();
        
        for (int[] q : queries) {
            if (q[0] == 1) {
                int x = q[1];
                Integer pred = obstacles.lower(x);
                Integer succ = obstacles.higher(x);
                //gap from pred to x (or 0 to x)
                int newVal = (pred == null) ? x : (x - pred);
                seg.update(x, newVal);
                if (succ != null) {
                    int newSuccVal = succ - x;
                    seg.update(succ, newSuccVal);
                }
                obstacles.add(x);
            } 
            else { // type 2
                int x = q[1];
                int sz = q[2];
                Integer last = obstacles.floor(x);
                int maxStored = seg.query(0, x);
                int trailing = (last == null) ? x : (x - last);
                int maxFree = Math.max(maxStored, trailing);
                ans.add(maxFree >= sz);
            }
        }
        return ans;
    }
}
