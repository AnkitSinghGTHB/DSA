class Solution {
    private static final int MOD = 1_000_000_007;
    private List<Integer>[] adj;
    private int[] depth;
    private int[][] parent;
    private int LOG;
    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;
        adj = new List[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            adj[u].add(v);
            adj[v].add(u);
        }
        //preprocess LCA
        LOG = 1;
        while ((1 << LOG) <= n) LOG++;
        depth = new int[n + 1];
        parent = new int[LOG][n + 1];
        dfs(1, -1, 0);

        //answer queries
        int q = queries.length;
        int[] ans = new int[q];
        for (int i = 0; i < q; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            int lca = getLCA(u, v);
            int dist = depth[u] + depth[v] - 2 * depth[lca];
            if (dist == 0) ans[i] = 0;
            else ans[i] = (int) powMod(2, dist - 1);
        }
        return ans;
    }

    private void dfs(int u, int p, int d) {
        depth[u] = d;
        parent[0][u] = p;
        for (int i = 1; i < LOG; i++) {
            if (parent[i-1][u] != -1) {
                parent[i][u] = parent[i-1][parent[i-1][u]];
            } else {
                parent[i][u] = -1;
            }
        }
        for (int v : adj[u]) {
            if (v == p) continue;
            dfs(v, u, d + 1);
        }
    }

    private int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int tmp = u; u = v; v = tmp;
        }
        //lift u up
        int diff = depth[u] - depth[v];
        for (int i = 0; i < LOG; i++) {
            if ((diff & (1 << i)) != 0) {
                u = parent[i][u];
            }
        }
        if (u == v) return u;
        for (int i = LOG - 1; i >= 0; i--) {
            if (parent[i][u] != parent[i][v]) {
                u = parent[i][u];
                v = parent[i][v];
            }
        }
        return parent[0][u];
    }

    private long powMod(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) res = res * a % MOD;
            a = a * a % MOD;
            b >>= 1;
        }
        return res;
    }
}
