//another help by deepseek
class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
        }

        // dist[u][run] = minimum cost to reach node u with current consecutive run of labels[u] of length run
        int[][] dist = new int[n][k + 1];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        dist[0][1] = 0;
        pq.offer(new int[]{0, 0, 1});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cost = cur[0], u = cur[1], run = cur[2];
            if (cost != dist[u][run]) continue;

            if (u == n - 1) {
                return cost; // first time we pop target is optimal
            }

            char cu = labels.charAt(u);
            for (int[] e : adj[u]) {
                int v = e[0], w = e[1];
                int newRun;
                if (labels.charAt(v) == cu) {
                    newRun = run + 1;
                } else {
                    newRun = 1;
                }
                if (newRun > k) continue;

                int newCost = cost + w;
                if (newCost < dist[v][newRun]) {
                    dist[v][newRun] = newCost;
                    pq.offer(new int[]{newCost, v, newRun});
                }
            }
        }
        return -1;
    }
}
