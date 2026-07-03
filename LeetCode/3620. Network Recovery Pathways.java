class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        List<int[]>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        int[] indeg = new int[n];
        long maxCost = 0;
        for (int[] e : edges) {
            int u = e[0], v = e[1], c = e[2];
            adj[u].add(new int[]{v, c});
            indeg[v]++;
            if (c > maxCost) maxCost = c;
        }

        // topological order (original DAG)
        int[] topo = new int[n];
        int idx = 0;
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) if (indeg[i] == 0) q.offer(i);
        while (!q.isEmpty()) {
            int u = q.poll();
            topo[idx++] = u;
            for (int[] e : adj[u]) {
                int v = e[0];
                if (--indeg[v] == 0) q.offer(v);
            }
        }

        // if even with score 0 no valid path exists
        if (!feasible(0, adj, topo, online, k)) return -1;

        // binary search on score
        long lo = 0, hi = maxCost;
        while (lo < hi) {
            long mid = lo + (hi - lo + 1) / 2;
            if (feasible(mid, adj, topo, online, k)) lo = mid;
            else hi = mid - 1;
        }
        return (int) lo;
    }

    private boolean feasible(long threshold, List<int[]>[] adj, int[] topo, boolean[] online, long k) {
        int n = online.length;
        final long INF = Long.MAX_VALUE / 4;
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[0] = 0;

        for (int u : topo) {
            if (!online[u]) continue;      // cannot stay on an offline node
            if (dist[u] == INF) continue;
            for (int[] e : adj[u]) {
                int v = e[0], cost = e[1];
                if (cost < threshold) continue;   // edge too small → score would be lower
                if (!online[v]) continue;         // cannot enter an offline node
                long nd = dist[u] + cost;
                if (nd < dist[v]) dist[v] = nd;
            }
        }
        return dist[n - 1] <= k;
    }
}
