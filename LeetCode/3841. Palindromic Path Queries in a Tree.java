import java.util.*;
//deepseek assisted code
class Solution {
    private List<Integer>[] adj;
    private int[] parent, depth, size, heavy, head, pos, base;
    private int curPos;
    private char[] s;
    private int[] tree;
    private int n;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        this.s = s.toCharArray();   // assign to field
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        parent = new int[n];
        depth = new int[n];
        size = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];
        base = new int[n];
        curPos = 0;

        // ----- Iterative DFS1: parent, depth, size, heavy child -----
        int[] order = new int[n];
        int idx = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        parent[0] = -1;
        depth[0] = 0;
        while (!stack.isEmpty()) {
            int u = stack.pop();
            order[idx++] = u;
            for (int v : adj[u]) {
                if (v != parent[u]) {
                    parent[v] = u;
                    depth[v] = depth[u] + 1;
                    stack.push(v);
                }
            }
        }
        // process in reverse order to compute subtree sizes and heavy child
        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];
            size[u] = 1;
            int maxSize = 0;
            heavy[u] = -1;
            for (int v : adj[u]) {
                if (v != parent[u]) {
                    size[u] += size[v];
                    if (size[v] > maxSize) {
                        maxSize = size[v];
                        heavy[u] = v;
                    }
                }
            }
        }

        // ----- Iterative DFS2: Heavy-Light Decomposition -----
        Deque<Integer> stack2 = new ArrayDeque<>();
        stack2.push(0);
        head[0] = 0;
        while (!stack2.isEmpty()) {
            int u = stack2.pop();
            pos[u] = curPos;
            base[curPos] = u;
            curPos++;

            // push light children first so that heavy child is processed next
            for (int v : adj[u]) {
                if (v != parent[u] && v != heavy[u]) {
                    head[v] = v;
                    stack2.push(v);
                }
            }
            if (heavy[u] != -1) {
                head[heavy[u]] = head[u];
                stack2.push(heavy[u]);
            }
        }

        // ----- Segment tree over the base array (XOR of character masks) -----
        tree = new int[4 * n];
        build(1, 0, n - 1);

        List<Boolean> ans = new ArrayList<>();
        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                this.s[u] = c;   // FIX: use the field
                update(1, 0, n - 1, pos[u], 1 << (c - 'a'));
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                ans.add(queryPath(u, v));
            }
        }
        return ans;
    }

    private void build(int node, int l, int r) {
        if (l == r) {
            int u = base[l];
            tree[node] = 1 << (s[u] - 'a');
            return;
        }
        int mid = (l + r) / 2;
        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);
        tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
    }

    private void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            tree[node] = val;
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid) update(node * 2, l, mid, idx, val);
        else update(node * 2 + 1, mid + 1, r, idx, val);
        tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
    }

    private int query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) return tree[node];
        int mid = (l + r) / 2;
        int res = 0;
        if (ql <= mid) res ^= query(node * 2, l, mid, ql, qr);
        if (qr > mid) res ^= query(node * 2 + 1, mid + 1, r, ql, qr);
        return res;
    }

    private boolean queryPath(int u, int v) {
        int mask = 0;
        while (head[u] != head[v]) {
            if (depth[head[u]] < depth[head[v]]) {
                int tmp = u; u = v; v = tmp;
            }
            mask ^= query(1, 0, n - 1, pos[head[u]], pos[u]);
            u = parent[head[u]];
        }
        if (depth[u] > depth[v]) {
            int tmp = u; u = v; v = tmp;
        }
        mask ^= query(1, 0, n - 1, pos[u], pos[v]);
        return Integer.bitCount(mask) <= 1;
    }
}
