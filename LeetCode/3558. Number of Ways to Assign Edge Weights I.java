class Solution {
    private static final int MOD = 1_000_000_007;
    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        List<Integer>[] adj = new List[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            adj[u].add(v);
            adj[v].add(u);
        }
        //BFS to find depths
        int[] depth = new int[n + 1];
        Arrays.fill(depth, -1);
        Queue<Integer> q = new LinkedList<>();
        depth[1] = 0;
        q.offer(1);
        int maxDepth = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            maxDepth = Math.max(maxDepth, depth[u]);
            for (int v : adj[u]) {
                if (depth[v] == -1) {
                    depth[v] = depth[u] + 1;
                    q.offer(v);
                }
            }
        }
        //number of edges on the path = maxDepth
        //need 2^(maxDepth-1) mod MOD
        return (int) powMod(2, maxDepth - 1);
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
