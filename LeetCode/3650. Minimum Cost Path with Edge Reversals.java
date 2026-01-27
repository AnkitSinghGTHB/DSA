//here i needed ai assistance
class Solution {
    public int minCost(int n, int[][] edges) {
        // Build adjacency list with original and reversed edges
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            // Original directed edge
            graph.get(u).add(new int[]{v, w});
            // Reversed edge with doubled weight (can be used if switch at v is available)
            graph.get(v).add(new int[]{u, 2 * w});
        }

        // Dijkstra's algorithm
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        pq.offer(new long[]{0, 0}); //{node, cost}

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            int u = (int) curr[0];
            long cost = curr[1];

            if (cost > dist[u]) continue;
            if (u == n - 1) return (int) cost; //early exit

            for (int[] neighbor : graph.get(u)) {
                int v = neighbor[0];
                int w = neighbor[1];
                long newCost = cost + w;
                if (newCost < dist[v]) {
                    dist[v] = newCost;
                    pq.offer(new long[]{v, newCost});
                }
            }
        }
        return dist[n - 1] == Long.MAX_VALUE ? -1 : (int) dist[n - 1];
    }
}
